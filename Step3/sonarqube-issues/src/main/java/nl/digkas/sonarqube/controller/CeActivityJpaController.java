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
import nl.digkas.sonarqube.domain.CeActivity;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class CeActivityJpaController implements Serializable {

    public CeActivityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeActivity ceActivity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceActivity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeActivity ceActivity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceActivity = em.merge(ceActivity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ceActivity.getId();
                if (findCeActivity(id) == null) {
                    throw new NonexistentEntityException("The ceActivity with id " + id + " no longer exists.");
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
            CeActivity ceActivity;
            try {
                ceActivity = em.getReference(CeActivity.class, id);
                ceActivity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceActivity with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceActivity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeActivity> findCeActivityEntities() {
        return findCeActivityEntities(true, -1, -1);
    }

    public List<CeActivity> findCeActivityEntities(int maxResults, int firstResult) {
        return findCeActivityEntities(false, maxResults, firstResult);
    }

    private List<CeActivity> findCeActivityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeActivity.class));
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

    public CeActivity findCeActivity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeActivity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeActivityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeActivity> rt = cq.from(CeActivity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
