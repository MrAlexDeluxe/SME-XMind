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
import nl.digkas.sonarqube.domain.CeScannerContext;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class CeScannerContextJpaController implements Serializable {

    public CeScannerContextJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeScannerContext ceScannerContext) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceScannerContext);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCeScannerContext(ceScannerContext.getTaskUuid()) != null) {
                throw new PreexistingEntityException("CeScannerContext " + ceScannerContext + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeScannerContext ceScannerContext) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceScannerContext = em.merge(ceScannerContext);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ceScannerContext.getTaskUuid();
                if (findCeScannerContext(id) == null) {
                    throw new NonexistentEntityException("The ceScannerContext with id " + id + " no longer exists.");
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
            CeScannerContext ceScannerContext;
            try {
                ceScannerContext = em.getReference(CeScannerContext.class, id);
                ceScannerContext.getTaskUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceScannerContext with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceScannerContext);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeScannerContext> findCeScannerContextEntities() {
        return findCeScannerContextEntities(true, -1, -1);
    }

    public List<CeScannerContext> findCeScannerContextEntities(int maxResults, int firstResult) {
        return findCeScannerContextEntities(false, maxResults, firstResult);
    }

    private List<CeScannerContext> findCeScannerContextEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeScannerContext.class));
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

    public CeScannerContext findCeScannerContext(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeScannerContext.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeScannerContextCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeScannerContext> rt = cq.from(CeScannerContext.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
