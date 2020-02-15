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
import nl.digkas.sonarqube.domain.AnalysisProperties;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class AnalysisPropertiesJpaController implements Serializable {

    public AnalysisPropertiesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnalysisProperties analysisProperties) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(analysisProperties);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnalysisProperties(analysisProperties.getUuid()) != null) {
                throw new PreexistingEntityException("AnalysisProperties " + analysisProperties + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnalysisProperties analysisProperties) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            analysisProperties = em.merge(analysisProperties);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = analysisProperties.getUuid();
                if (findAnalysisProperties(id) == null) {
                    throw new NonexistentEntityException("The analysisProperties with id " + id + " no longer exists.");
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
            AnalysisProperties analysisProperties;
            try {
                analysisProperties = em.getReference(AnalysisProperties.class, id);
                analysisProperties.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analysisProperties with id " + id + " no longer exists.", enfe);
            }
            em.remove(analysisProperties);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnalysisProperties> findAnalysisPropertiesEntities() {
        return findAnalysisPropertiesEntities(true, -1, -1);
    }

    public List<AnalysisProperties> findAnalysisPropertiesEntities(int maxResults, int firstResult) {
        return findAnalysisPropertiesEntities(false, maxResults, firstResult);
    }

    private List<AnalysisProperties> findAnalysisPropertiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnalysisProperties.class));
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

    public AnalysisProperties findAnalysisProperties(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnalysisProperties.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalysisPropertiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnalysisProperties> rt = cq.from(AnalysisProperties.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
