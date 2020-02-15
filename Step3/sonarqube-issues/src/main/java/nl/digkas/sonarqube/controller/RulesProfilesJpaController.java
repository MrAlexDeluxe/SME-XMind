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
import nl.digkas.sonarqube.domain.RulesProfiles;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class RulesProfilesJpaController implements Serializable {

    public RulesProfilesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RulesProfiles rulesProfiles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rulesProfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RulesProfiles rulesProfiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rulesProfiles = em.merge(rulesProfiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rulesProfiles.getId();
                if (findRulesProfiles(id) == null) {
                    throw new NonexistentEntityException("The rulesProfiles with id " + id + " no longer exists.");
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
            RulesProfiles rulesProfiles;
            try {
                rulesProfiles = em.getReference(RulesProfiles.class, id);
                rulesProfiles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rulesProfiles with id " + id + " no longer exists.", enfe);
            }
            em.remove(rulesProfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RulesProfiles> findRulesProfilesEntities() {
        return findRulesProfilesEntities(true, -1, -1);
    }

    public List<RulesProfiles> findRulesProfilesEntities(int maxResults, int firstResult) {
        return findRulesProfilesEntities(false, maxResults, firstResult);
    }

    private List<RulesProfiles> findRulesProfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RulesProfiles.class));
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

    public RulesProfiles findRulesProfiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RulesProfiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRulesProfilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RulesProfiles> rt = cq.from(RulesProfiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
