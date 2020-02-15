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
import nl.digkas.sonarqube.domain.EsQueue;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class EsQueueJpaController implements Serializable {

    public EsQueueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EsQueue esQueue) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esQueue);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEsQueue(esQueue.getUuid()) != null) {
                throw new PreexistingEntityException("EsQueue " + esQueue + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsQueue esQueue) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esQueue = em.merge(esQueue);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = esQueue.getUuid();
                if (findEsQueue(id) == null) {
                    throw new NonexistentEntityException("The esQueue with id " + id + " no longer exists.");
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
            EsQueue esQueue;
            try {
                esQueue = em.getReference(EsQueue.class, id);
                esQueue.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esQueue with id " + id + " no longer exists.", enfe);
            }
            em.remove(esQueue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsQueue> findEsQueueEntities() {
        return findEsQueueEntities(true, -1, -1);
    }

    public List<EsQueue> findEsQueueEntities(int maxResults, int firstResult) {
        return findEsQueueEntities(false, maxResults, firstResult);
    }

    private List<EsQueue> findEsQueueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsQueue.class));
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

    public EsQueue findEsQueue(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsQueue.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsQueueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsQueue> rt = cq.from(EsQueue.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
