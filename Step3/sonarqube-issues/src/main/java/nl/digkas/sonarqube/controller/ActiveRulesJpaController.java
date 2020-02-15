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
import nl.digkas.sonarqube.domain.ActiveRules;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ActiveRulesJpaController implements Serializable {

    public ActiveRulesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActiveRules activeRules) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(activeRules);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActiveRules activeRules) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            activeRules = em.merge(activeRules);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = activeRules.getId();
                if (findActiveRules(id) == null) {
                    throw new NonexistentEntityException("The activeRules with id " + id + " no longer exists.");
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
            ActiveRules activeRules;
            try {
                activeRules = em.getReference(ActiveRules.class, id);
                activeRules.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The activeRules with id " + id + " no longer exists.", enfe);
            }
            em.remove(activeRules);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActiveRules> findActiveRulesEntities() {
        return findActiveRulesEntities(true, -1, -1);
    }

    public List<ActiveRules> findActiveRulesEntities(int maxResults, int firstResult) {
        return findActiveRulesEntities(false, maxResults, firstResult);
    }

    private List<ActiveRules> findActiveRulesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActiveRules.class));
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

    public ActiveRules findActiveRules(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActiveRules.class, id);
        } finally {
            em.close();
        }
    }

    public int getActiveRulesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActiveRules> rt = cq.from(ActiveRules.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
