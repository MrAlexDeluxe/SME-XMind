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
import nl.digkas.sonarqube.domain.IssueChanges;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class IssueChangesJpaController implements Serializable {

    public IssueChangesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IssueChanges issueChanges) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(issueChanges);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IssueChanges issueChanges) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            issueChanges = em.merge(issueChanges);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = issueChanges.getId();
                if (findIssueChanges(id) == null) {
                    throw new NonexistentEntityException("The issueChanges with id " + id + " no longer exists.");
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
            IssueChanges issueChanges;
            try {
                issueChanges = em.getReference(IssueChanges.class, id);
                issueChanges.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The issueChanges with id " + id + " no longer exists.", enfe);
            }
            em.remove(issueChanges);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IssueChanges> findIssueChangesEntities() {
        return findIssueChangesEntities(true, -1, -1);
    }

    public List<IssueChanges> findIssueChangesEntities(int maxResults, int firstResult) {
        return findIssueChangesEntities(false, maxResults, firstResult);
    }

    private List<IssueChanges> findIssueChangesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IssueChanges.class));
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

    public IssueChanges findIssueChanges(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IssueChanges.class, id);
        } finally {
            em.close();
        }
    }

    public int getIssueChangesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IssueChanges> rt = cq.from(IssueChanges.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
