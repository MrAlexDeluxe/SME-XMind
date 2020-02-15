package nl.digkas.sonarqube.issues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import nl.digkas.sonarqube.domain.Events;
import nl.digkas.sonarqube.domain.Issues;
import nl.digkas.sonarqube.domain.ProjectMeasures;
import nl.digkas.sonarqube.domain.Projects;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class TestMain {

    private static final int JDBC_PARAMETERS_LIMIT = 32767;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SonarQubePU");
        EntityManager em = emf.createEntityManager();

        Projects project = em
                .createNamedQuery("Projects.findProjectByKee", Projects.class)
                .setParameter("kee", "incubator-mxnet")
                .getSingleResult();

        List<Events> revisions = findAllVersionsByProjectKee(em, project);
        Set<Long> revisionsSet = revisions.stream().map(Events::getEventDate).collect(Collectors.toSet());

        System.out.println(revisionsSet);

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

        List<Issues> issues = new ArrayList<>();
        for (int i = 0; i < projectUuids.size(); i += JDBC_PARAMETERS_LIMIT) {
            issues.addAll(em.createNamedQuery("Issues.findByComponentUuids", Issues.class).setParameter("componentUuids", projectUuids.subList(i, Math.min(projectUuids.size(), i + JDBC_PARAMETERS_LIMIT))).getResultList());
        }

        Set<Long> issueCloseDates = issues.stream().filter(issue -> Objects.nonNull(issue.getIssueCloseDate())).map(issue -> issue.getIssueCloseDate().longValue()).collect(Collectors.toSet());
        Set<Long> issueCreationDates = issues.stream().filter(issue -> Objects.nonNull(issue.getIssueCloseDate())).map(issue -> issue.getIssueCreationDate().longValue()).collect(Collectors.toSet());
        System.out.println(issueCreationDates);

        issueCreationDates.removeAll(revisionsSet);

        System.out.println(issueCreationDates);
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
