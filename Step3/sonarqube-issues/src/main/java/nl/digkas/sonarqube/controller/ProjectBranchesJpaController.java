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
import nl.digkas.sonarqube.controller.exceptions.PreexistingEntityException;
import nl.digkas.sonarqube.domain.ProjectBranches;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ProjectBranchesJpaController implements Serializable {

    public ProjectBranchesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProjectBranches projectBranches) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(projectBranches);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProjectBranches(projectBranches.getUuid()) != null) {
                throw new PreexistingEntityException("ProjectBranches " + projectBranches + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProjectBranches projectBranches) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            projectBranches = em.merge(projectBranches);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = projectBranches.getUuid();
                if (findProjectBranches(id) == null) {
                    throw new NonexistentEntityException("The projectBranches with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProjectBranches projectBranches;
            try {
                projectBranches = em.getReference(ProjectBranches.class, id);
                projectBranches.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projectBranches with id " + id + " no longer exists.", enfe);
            }
            em.remove(projectBranches);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProjectBranches> findProjectBranchesEntities() {
        return findProjectBranchesEntities(true, -1, -1);
    }

    public List<ProjectBranches> findProjectBranchesEntities(int maxResults, int firstResult) {
        return findProjectBranchesEntities(false, maxResults, firstResult);
    }

    private List<ProjectBranches> findProjectBranchesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectBranches.class));
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

    public ProjectBranches findProjectBranches(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProjectBranches.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectBranchesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectBranches> rt = cq.from(ProjectBranches.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
