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
import nl.digkas.sonarqube.domain.Metrics;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class MetricsJpaController implements Serializable {

    public MetricsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Metrics metrics) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(metrics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metrics metrics) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            metrics = em.merge(metrics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = metrics.getId();
                if (findMetrics(id) == null) {
                    throw new NonexistentEntityException("The metrics with id " + id + " no longer exists.");
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
            Metrics metrics;
            try {
                metrics = em.getReference(Metrics.class, id);
                metrics.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metrics with id " + id + " no longer exists.", enfe);
            }
            em.remove(metrics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Metrics> findMetricsEntities() {
        return findMetricsEntities(true, -1, -1);
    }

    public List<Metrics> findMetricsEntities(int maxResults, int firstResult) {
        return findMetricsEntities(false, maxResults, firstResult);
    }

    private List<Metrics> findMetricsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Metrics.class));
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

    public Metrics findMetrics(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Metrics.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetricsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Metrics> rt = cq.from(Metrics.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
