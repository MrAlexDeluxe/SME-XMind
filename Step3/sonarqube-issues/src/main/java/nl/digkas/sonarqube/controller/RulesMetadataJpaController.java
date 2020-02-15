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
import nl.digkas.sonarqube.domain.RulesMetadata;
import nl.digkas.sonarqube.domain.RulesMetadataPK;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class RulesMetadataJpaController implements Serializable {

    public RulesMetadataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RulesMetadata rulesMetadata) throws PreexistingEntityException, Exception {
        if (rulesMetadata.getRulesMetadataPK() == null) {
            rulesMetadata.setRulesMetadataPK(new RulesMetadataPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rulesMetadata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRulesMetadata(rulesMetadata.getRulesMetadataPK()) != null) {
                throw new PreexistingEntityException("RulesMetadata " + rulesMetadata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RulesMetadata rulesMetadata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rulesMetadata = em.merge(rulesMetadata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RulesMetadataPK id = rulesMetadata.getRulesMetadataPK();
                if (findRulesMetadata(id) == null) {
                    throw new NonexistentEntityException("The rulesMetadata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RulesMetadataPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RulesMetadata rulesMetadata;
            try {
                rulesMetadata = em.getReference(RulesMetadata.class, id);
                rulesMetadata.getRulesMetadataPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rulesMetadata with id " + id + " no longer exists.", enfe);
            }
            em.remove(rulesMetadata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RulesMetadata> findRulesMetadataEntities() {
        return findRulesMetadataEntities(true, -1, -1);
    }

    public List<RulesMetadata> findRulesMetadataEntities(int maxResults, int firstResult) {
        return findRulesMetadataEntities(false, maxResults, firstResult);
    }

    private List<RulesMetadata> findRulesMetadataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RulesMetadata.class));
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

    public RulesMetadata findRulesMetadata(RulesMetadataPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RulesMetadata.class, id);
        } finally {
            em.close();
        }
    }

    public int getRulesMetadataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RulesMetadata> rt = cq.from(RulesMetadata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
