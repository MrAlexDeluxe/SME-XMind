package nl.digkas.sonarqube.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nl.digkas.sonarqube.controller.exceptions.NonexistentEntityException;
import nl.digkas.sonarqube.domain.ProjectMeasures;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ProjectMeasuresJpaController implements Serializable {

    public ProjectMeasuresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProjectMeasures projectMeasures) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(projectMeasures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProjectMeasures projectMeasures) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            projectMeasures = em.merge(projectMeasures);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = projectMeasures.getId();
                if (findProjectMeasures(id) == null) {
                    throw new NonexistentEntityException("The projectMeasures with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProjectMeasures projectMeasures;
            try {
                projectMeasures = em.getReference(ProjectMeasures.class, id);
                projectMeasures.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projectMeasures with id " + id + " no longer exists.", enfe);
            }
            em.remove(projectMeasures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProjectMeasures> findProjectMeasuresEntities() {
        return findProjectMeasuresEntities(true, -1, -1);
    }

    public List<ProjectMeasures> findProjectMeasuresEntities(int maxResults, int firstResult) {
        return findProjectMeasuresEntities(false, maxResults, firstResult);
    }

    private List<ProjectMeasures> findProjectMeasuresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectMeasures.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProjectMeasures findProjectMeasures(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProjectMeasures.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectMeasuresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectMeasures> rt = cq.from(ProjectMeasures.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
