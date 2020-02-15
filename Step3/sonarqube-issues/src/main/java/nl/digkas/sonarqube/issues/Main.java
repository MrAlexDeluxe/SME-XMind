package nl.digkas.sonarqube.issues;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import nl.digkas.sonarqube.domain.Events;
import nl.digkas.sonarqube.domain.Issues;
import nl.digkas.sonarqube.domain.ProjectMeasures;
import nl.digkas.sonarqube.domain.Projects;
import nl.digkas.sonarqube.domain.Rules;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class Main {

    private static final int JDBC_PARAMETERS_LIMIT = 3000;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SonarQubePU");
        EntityManager em = emf.createEntityManager();
        PrintStream out = null;
        try {
            out = new PrintStream(
                    new FileOutputStream("sonarqube-issues-export.txt", true), true);
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Projects project = em.createNamedQuery("Projects.findProjectByKee", Projects.class)
                .setParameter("kee", "org.xmind.releng:org.xmind.cathy.releng")
                .getSingleResult();

        List<Events> revisions = findAllVersionsByProjectKee(em, project);
        Map<String, Events> eventSHA = revisions.stream().collect(Collectors.toMap(Events::getName, Function.identity()));
        Map<Long, Events> eventsDateMAP = revisions.stream().collect(Collectors.toMap(Events::getEventDate, Function.identity()));

        List<String> projectUuids = em
                .createNamedQuery("Projects.findByProjectUuid", Projects.class)
                .setParameter("projectUuid", project.getUuid())
                .getResultList()
                .stream()
                .filter(p -> Objects.equals(p.getScope(), "FIL"))
                .filter(p -> Objects.equals(p.getQualifier(), "FIL"))
                .filter(Projects::getEnabled)
                .map(Projects::getUuid)
                .collect(Collectors.toList());

        Map<String, Projects> projectUuidsMap = em
                .createNamedQuery("Projects.findByProjectUuid", Projects.class)
                .setParameter("projectUuid", project.getUuid())
                .getResultList()
                .stream()
                .collect(Collectors.toMap(Projects::getUuid, Function.identity()));

        List<Issues> issues = new ArrayList<>();
        for (int i = 0; i < projectUuids.size(); i += JDBC_PARAMETERS_LIMIT) {
            issues.addAll(em.createNamedQuery("Issues.findByComponentUuids", Issues.class).setParameter("componentUuids", projectUuids.subList(i, Math.min(projectUuids.size(), i + JDBC_PARAMETERS_LIMIT))).getResultList());
        }

        Map<Integer, Rules> rulesMap = em.createNamedQuery("Rules.findAll", Rules.class).getResultList().stream().collect(Collectors.toMap(Rules::getId, Function.identity()));

        //Keeps only the fixed Isseus
        //issues = issues.parallelStream().filter(issue -> Objects.nonNull(issue.getIssueCloseDate())).collect(Collectors.toList());
        for (Issues issue : issues) {
            // System.out.println(issue.getRuleId() + "\t" + rulesMap.get(issue.getRuleId()).getName() + "\t" + issue.getSeverity() + "\t" + issue.getStatus() + "\t" + issue.getEffort() + "\t" + issue.getIssueCreationDate() + "\t" + getDate(issue.getIssueCloseDate()) + "\t" + issue.getComponentUuid());
            System.out.print(issue.getRuleId()
                    + "\t" + rulesMap.get(issue.getRuleId()).getName()
                    + "\t" + issue.getSeverity()
                    + "\t" + issue.getStatus()
                    + "\t" + issue.getEffort()
                    + "\t" + getDate(issue.getIssueCreationDate())
                    + "\t" + getDate(issue.getIssueCloseDate())
                    + "\t" + issue.getComponentUuid()
                    + "\t" + projectUuidsMap.get(issue.getComponentUuid()).getPath());
            System.out.print("\t" + (Objects.isNull(issue.getIssueCreationDate()) ? "" : (Objects.isNull(eventsDateMAP.get(issue.getIssueCreationDate().longValue())) ? "" : eventsDateMAP.get(issue.getIssueCreationDate().longValue()).getName())));
            System.out.println("\t" + (Objects.isNull(issue.getIssueCloseDate()) ? "" : (Objects.isNull(eventsDateMAP.get(issue.getIssueCloseDate().longValue())) ? "" : eventsDateMAP.get(issue.getIssueCloseDate().longValue()).getName())));
        }

        System.out.println(issues.parallelStream().map(Issues::getStatus).collect(Collectors.toSet()));
        System.out.println(issues.size());

        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }

    private static String getDate(BigInteger date) {
        if (Objects.isNull(date)) {
            return "";
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format(Date.from(Instant.ofEpochSecond(fixEpochSeconds(date.longValue()))));
            //    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(Date.from(Instant.ofEpochSecond(fixEpochSeconds(date.longValue()))));
        }
    }

    private static Long fixEpochSeconds(long seconds) {
        if (Objects.nonNull(seconds)) {
            return seconds / 1000;
        }
        return null;
    }

    private static List<Events> findAllVersionsByProjectKee(EntityManager em, Projects project) {
        Set<Events> eventsSet = new HashSet<>();
        List<Events> analyzedVersions = new ArrayList<>();

        if (Objects.nonNull(project)) {
            Collection<ProjectMeasures> projectMeasures = em.createNamedQuery("ProjectMeasures.findByComponentUuid", ProjectMeasures.class).setParameter("componentUuid", project.getUuid()).getResultList();
            List<String> projectMeasuresAnalysisUuids = projectMeasures.stream().map(ProjectMeasures::getAnalysisUuid).collect(Collectors.toList());

            for (int i = 0; i < projectMeasuresAnalysisUuids.size(); i += JDBC_PARAMETERS_LIMIT) {
                eventsSet.addAll(findVersionsByAnalysisUuid(em, projectMeasuresAnalysisUuids.subList(i, Math.min(projectMeasuresAnalysisUuids.size(), i + JDBC_PARAMETERS_LIMIT))));
            }

            analyzedVersions = eventsSet.stream().sorted(Comparator.comparing(Events::getEventDate)).collect(Collectors.toList());
        }
        return analyzedVersions;
    }

    private static Collection<Events> findVersionsByAnalysisUuid(EntityManager em, Collection<String> analysisUuids) {
        return em.createNamedQuery("Events.findVersionsByAnalysisUuid", Events.class).setParameter("analysisUuids", analysisUuids).getResultList();
    }

}
