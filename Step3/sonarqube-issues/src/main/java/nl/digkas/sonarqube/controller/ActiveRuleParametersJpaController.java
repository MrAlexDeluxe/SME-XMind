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
import nl.digkas.sonarqube.domain.ActiveRuleParameters;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ActiveRuleParametersJpaController implements Serializable {

    public ActiveRuleParametersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActiveRuleParameters activeRuleParameters) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(activeRuleParameters);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActiveRuleParameters activeRuleParameters) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            activeRuleParameters = em.merge(activeRuleParameters);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = activeRuleParameters.getId();
                if (findActiveRuleParameters(id) == null) {
                    throw new NonexistentEntityException("The activeRuleParameters with id " + id + " no longer exists.");
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
            ActiveRuleParameters activeRuleParameters;
            try {
                activeRuleParameters = em.getReference(ActiveRuleParameters.class, id);
                activeRuleParameters.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The activeRuleParameters with id " + id + " no longer exists.", enfe);
            }
            em.remove(activeRuleParameters);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActiveRuleParameters> findActiveRuleParametersEntities() {
        return findActiveRuleParametersEntities(true, -1, -1);
    }

    public List<ActiveRuleParameters> findActiveRuleParametersEntities(int maxResults, int firstResult) {
        return findActiveRuleParametersEntities(false, maxResults, firstResult);
    }

    private List<ActiveRuleParameters> findActiveRuleParametersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActiveRuleParameters.class));
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

    public ActiveRuleParameters findActiveRuleParameters(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActiveRuleParameters.class, id);
        } finally {
            em.close();
        }
    }

    public int getActiveRuleParametersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActiveRuleParameters> rt = cq.from(ActiveRuleParameters.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
