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
import nl.digkas.sonarqube.domain.ProjectLinks;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ProjectLinksJpaController implements Serializable {

    public ProjectLinksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProjectLinks projectLinks) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(projectLinks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProjectLinks projectLinks) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            projectLinks = em.merge(projectLinks);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = projectLinks.getId();
                if (findProjectLinks(id) == null) {
                    throw new NonexistentEntityException("The projectLinks with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProjectLinks projectLinks;
            try {
                projectLinks = em.getReference(ProjectLinks.class, id);
                projectLinks.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projectLinks with id " + id + " no longer exists.", enfe);
            }
            em.remove(projectLinks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProjectLinks> findProjectLinksEntities() {
        return findProjectLinksEntities(true, -1, -1);
    }

    public List<ProjectLinks> findProjectLinksEntities(int maxResults, int firstResult) {
        return findProjectLinksEntities(false, maxResults, firstResult);
    }

    private List<ProjectLinks> findProjectLinksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectLinks.class));
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

    public ProjectLinks findProjectLinks(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProjectLinks.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectLinksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectLinks> rt = cq.from(ProjectLinks.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
