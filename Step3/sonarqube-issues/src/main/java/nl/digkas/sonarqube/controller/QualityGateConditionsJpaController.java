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
import nl.digkas.sonarqube.domain.QualityGateConditions;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class QualityGateConditionsJpaController implements Serializable {

    public QualityGateConditionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QualityGateConditions qualityGateConditions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(qualityGateConditions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QualityGateConditions qualityGateConditions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            qualityGateConditions = em.merge(qualityGateConditions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = qualityGateConditions.getId();
                if (findQualityGateConditions(id) == null) {
                    throw new NonexistentEntityException("The qualityGateConditions with id " + id + " no longer exists.");
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
            QualityGateConditions qualityGateConditions;
            try {
                qualityGateConditions = em.getReference(QualityGateConditions.class, id);
                qualityGateConditions.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The qualityGateConditions with id " + id + " no longer exists.", enfe);
            }
            em.remove(qualityGateConditions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QualityGateConditions> findQualityGateConditionsEntities() {
        return findQualityGateConditionsEntities(true, -1, -1);
    }

    public List<QualityGateConditions> findQualityGateConditionsEntities(int maxResults, int firstResult) {
        return findQualityGateConditionsEntities(false, maxResults, firstResult);
    }

    private List<QualityGateConditions> findQualityGateConditionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QualityGateConditions.class));
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

    public QualityGateConditions findQualityGateConditions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QualityGateConditions.class, id);
        } finally {
            em.close();
        }
    }

    public int getQualityGateConditionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QualityGateConditions> rt = cq.from(QualityGateConditions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
