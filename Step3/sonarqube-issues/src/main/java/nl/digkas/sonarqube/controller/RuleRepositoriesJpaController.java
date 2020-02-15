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
import nl.digkas.sonarqube.domain.RuleRepositories;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class RuleRepositoriesJpaController implements Serializable {

    public RuleRepositoriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RuleRepositories ruleRepositories) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ruleRepositories);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRuleRepositories(ruleRepositories.getKee()) != null) {
                throw new PreexistingEntityException("RuleRepositories " + ruleRepositories + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RuleRepositories ruleRepositories) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ruleRepositories = em.merge(ruleRepositories);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ruleRepositories.getKee();
                if (findRuleRepositories(id) == null) {
                    throw new NonexistentEntityException("The ruleRepositories with id " + id + " no longer exists.");
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
            RuleRepositories ruleRepositories;
            try {
                ruleRepositories = em.getReference(RuleRepositories.class, id);
                ruleRepositories.getKee();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ruleRepositories with id " + id + " no longer exists.", enfe);
            }
            em.remove(ruleRepositories);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RuleRepositories> findRuleRepositoriesEntities() {
        return findRuleRepositoriesEntities(true, -1, -1);
    }

    public List<RuleRepositories> findRuleRepositoriesEntities(int maxResults, int firstResult) {
        return findRuleRepositoriesEntities(false, maxResults, firstResult);
    }

    private List<RuleRepositories> findRuleRepositoriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RuleRepositories.class));
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

    public RuleRepositories findRuleRepositories(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RuleRepositories.class, id);
        } finally {
            em.close();
        }
    }

    public int getRuleRepositoriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RuleRepositories> rt = cq.from(RuleRepositories.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
