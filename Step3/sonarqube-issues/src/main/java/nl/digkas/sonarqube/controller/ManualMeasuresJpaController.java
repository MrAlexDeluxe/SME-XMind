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
import nl.digkas.sonarqube.domain.ManualMeasures;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class ManualMeasuresJpaController implements Serializable {

    public ManualMeasuresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ManualMeasures manualMeasures) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(manualMeasures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ManualMeasures manualMeasures) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            manualMeasures = em.merge(manualMeasures);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = manualMeasures.getId();
                if (findManualMeasures(id) == null) {
                    throw new NonexistentEntityException("The manualMeasures with id " + id + " no longer exists.");
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
            ManualMeasures manualMeasures;
            try {
                manualMeasures = em.getReference(ManualMeasures.class, id);
                manualMeasures.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The manualMeasures with id " + id + " no longer exists.", enfe);
            }
            em.remove(manualMeasures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ManualMeasures> findManualMeasuresEntities() {
        return findManualMeasuresEntities(true, -1, -1);
    }

    public List<ManualMeasures> findManualMeasuresEntities(int maxResults, int firstResult) {
        return findManualMeasuresEntities(false, maxResults, firstResult);
    }

    private List<ManualMeasures> findManualMeasuresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ManualMeasures.class));
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

    public ManualMeasures findManualMeasures(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ManualMeasures.class, id);
        } finally {
            em.close();
        }
    }

    public int getManualMeasuresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ManualMeasures> rt = cq.from(ManualMeasures.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
