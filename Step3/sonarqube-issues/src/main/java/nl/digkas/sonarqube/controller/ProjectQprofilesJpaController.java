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
import nl.digkas.sonarqube.domain.ProjectQprofiles;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ProjectQprofilesJpaController implements Serializable {

    public ProjectQprofilesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProjectQprofiles projectQprofiles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(projectQprofiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProjectQprofiles projectQprofiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            projectQprofiles = em.merge(projectQprofiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = projectQprofiles.getId();
                if (findProjectQprofiles(id) == null) {
                    throw new NonexistentEntityException("The projectQprofiles with id " + id + " no longer exists.");
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
            ProjectQprofiles projectQprofiles;
            try {
                projectQprofiles = em.getReference(ProjectQprofiles.class, id);
                projectQprofiles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projectQprofiles with id " + id + " no longer exists.", enfe);
            }
            em.remove(projectQprofiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProjectQprofiles> findProjectQprofilesEntities() {
        return findProjectQprofilesEntities(true, -1, -1);
    }

    public List<ProjectQprofiles> findProjectQprofilesEntities(int maxResults, int firstResult) {
        return findProjectQprofilesEntities(false, maxResults, firstResult);
    }

    private List<ProjectQprofiles> findProjectQprofilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectQprofiles.class));
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

    public ProjectQprofiles findProjectQprofiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProjectQprofiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectQprofilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectQprofiles> rt = cq.from(ProjectQprofiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
