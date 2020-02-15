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
import nl.digkas.sonarqube.domain.CeQueue;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class CeQueueJpaController implements Serializable {

    public CeQueueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeQueue ceQueue) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceQueue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeQueue ceQueue) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceQueue = em.merge(ceQueue);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceQueue.getId();
                if (findCeQueue(id) == null) {
                    throw new NonexistentEntityException("The ceQueue with id " + id + " no longer exists.");
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
            CeQueue ceQueue;
            try {
                ceQueue = em.getReference(CeQueue.class, id);
                ceQueue.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceQueue with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceQueue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeQueue> findCeQueueEntities() {
        return findCeQueueEntities(true, -1, -1);
    }

    public List<CeQueue> findCeQueueEntities(int maxResults, int firstResult) {
        return findCeQueueEntities(false, maxResults, firstResult);
    }

    private List<CeQueue> findCeQueueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeQueue.class));
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

    public CeQueue findCeQueue(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeQueue.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeQueueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeQueue> rt = cq.from(CeQueue.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
