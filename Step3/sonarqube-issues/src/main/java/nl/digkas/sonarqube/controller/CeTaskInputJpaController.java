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
import nl.digkas.sonarqube.domain.CeTaskInput;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class CeTaskInputJpaController implements Serializable {

    public CeTaskInputJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeTaskInput ceTaskInput) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceTaskInput);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCeTaskInput(ceTaskInput.getTaskUuid()) != null) {
                throw new PreexistingEntityException("CeTaskInput " + ceTaskInput + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeTaskInput ceTaskInput) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceTaskInput = em.merge(ceTaskInput);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ceTaskInput.getTaskUuid();
                if (findCeTaskInput(id) == null) {
                    throw new NonexistentEntityException("The ceTaskInput with id " + id + " no longer exists.");
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
            CeTaskInput ceTaskInput;
            try {
                ceTaskInput = em.getReference(CeTaskInput.class, id);
                ceTaskInput.getTaskUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceTaskInput with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceTaskInput);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeTaskInput> findCeTaskInputEntities() {
        return findCeTaskInputEntities(true, -1, -1);
    }

    public List<CeTaskInput> findCeTaskInputEntities(int maxResults, int firstResult) {
        return findCeTaskInputEntities(false, maxResults, firstResult);
    }

    private List<CeTaskInput> findCeTaskInputEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeTaskInput.class));
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

    public CeTaskInput findCeTaskInput(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeTaskInput.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeTaskInputCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeTaskInput> rt = cq.from(CeTaskInput.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
