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
import nl.digkas.sonarqube.domain.DuplicationsIndex;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class DuplicationsIndexJpaController implements Serializable {

    public DuplicationsIndexJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DuplicationsIndex duplicationsIndex) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(duplicationsIndex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DuplicationsIndex duplicationsIndex) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            duplicationsIndex = em.merge(duplicationsIndex);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = duplicationsIndex.getId();
                if (findDuplicationsIndex(id) == null) {
                    throw new NonexistentEntityException("The duplicationsIndex with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DuplicationsIndex duplicationsIndex;
            try {
                duplicationsIndex = em.getReference(DuplicationsIndex.class, id);
                duplicationsIndex.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The duplicationsIndex with id " + id + " no longer exists.", enfe);
            }
            em.remove(duplicationsIndex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DuplicationsIndex> findDuplicationsIndexEntities() {
        return findDuplicationsIndexEntities(true, -1, -1);
    }

    public List<DuplicationsIndex> findDuplicationsIndexEntities(int maxResults, int firstResult) {
        return findDuplicationsIndexEntities(false, maxResults, firstResult);
    }

    private List<DuplicationsIndex> findDuplicationsIndexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DuplicationsIndex.class));
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

    public DuplicationsIndex findDuplicationsIndex(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DuplicationsIndex.class, id);
        } finally {
            em.close();
        }
    }

    public int getDuplicationsIndexCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DuplicationsIndex> rt = cq.from(DuplicationsIndex.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
