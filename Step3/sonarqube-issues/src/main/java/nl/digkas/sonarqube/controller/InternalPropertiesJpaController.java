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
import nl.digkas.sonarqube.domain.InternalProperties;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class InternalPropertiesJpaController implements Serializable {

    public InternalPropertiesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InternalProperties internalProperties) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(internalProperties);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInternalProperties(internalProperties.getKee()) != null) {
                throw new PreexistingEntityException("InternalProperties " + internalProperties + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InternalProperties internalProperties) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            internalProperties = em.merge(internalProperties);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = internalProperties.getKee();
                if (findInternalProperties(id) == null) {
                    throw new NonexistentEntityException("The internalProperties with id " + id + " no longer exists.");
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
            InternalProperties internalProperties;
            try {
                internalProperties = em.getReference(InternalProperties.class, id);
                internalProperties.getKee();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The internalProperties with id " + id + " no longer exists.", enfe);
            }
            em.remove(internalProperties);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InternalProperties> findInternalPropertiesEntities() {
        return findInternalPropertiesEntities(true, -1, -1);
    }

    public List<InternalProperties> findInternalPropertiesEntities(int maxResults, int firstResult) {
        return findInternalPropertiesEntities(false, maxResults, firstResult);
    }

    private List<InternalProperties> findInternalPropertiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InternalProperties.class));
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

    public InternalProperties findInternalProperties(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InternalProperties.class, id);
        } finally {
            em.close();
        }
    }

    public int getInternalPropertiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InternalProperties> rt = cq.from(InternalProperties.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
