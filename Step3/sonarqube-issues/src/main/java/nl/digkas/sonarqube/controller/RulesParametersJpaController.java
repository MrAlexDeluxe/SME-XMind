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
import nl.digkas.sonarqube.domain.RulesParameters;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class RulesParametersJpaController implements Serializable {

    public RulesParametersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RulesParameters rulesParameters) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rulesParameters);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RulesParameters rulesParameters) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rulesParameters = em.merge(rulesParameters);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rulesParameters.getId();
                if (findRulesParameters(id) == null) {
                    throw new NonexistentEntityException("The rulesParameters with id " + id + " no longer exists.");
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
            RulesParameters rulesParameters;
            try {
                rulesParameters = em.getReference(RulesParameters.class, id);
                rulesParameters.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rulesParameters with id " + id + " no longer exists.", enfe);
            }
            em.remove(rulesParameters);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RulesParameters> findRulesParametersEntities() {
        return findRulesParametersEntities(true, -1, -1);
    }

    public List<RulesParameters> findRulesParametersEntities(int maxResults, int firstResult) {
        return findRulesParametersEntities(false, maxResults, firstResult);
    }

    private List<RulesParameters> findRulesParametersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RulesParameters.class));
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

    public RulesParameters findRulesParameters(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RulesParameters.class, id);
        } finally {
            em.close();
        }
    }

    public int getRulesParametersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RulesParameters> rt = cq.from(RulesParameters.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
