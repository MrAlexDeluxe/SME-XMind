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
import nl.digkas.sonarqube.domain.QualityGates;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class QualityGatesJpaController implements Serializable {

    public QualityGatesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QualityGates qualityGates) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(qualityGates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QualityGates qualityGates) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            qualityGates = em.merge(qualityGates);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = qualityGates.getId();
                if (findQualityGates(id) == null) {
                    throw new NonexistentEntityException("The qualityGates with id " + id + " no longer exists.");
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
            QualityGates qualityGates;
            try {
                qualityGates = em.getReference(QualityGates.class, id);
                qualityGates.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The qualityGates with id " + id + " no longer exists.", enfe);
            }
            em.remove(qualityGates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QualityGates> findQualityGatesEntities() {
        return findQualityGatesEntities(true, -1, -1);
    }

    public List<QualityGates> findQualityGatesEntities(int maxResults, int firstResult) {
        return findQualityGatesEntities(false, maxResults, firstResult);
    }

    private List<QualityGates> findQualityGatesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QualityGates.class));
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

    public QualityGates findQualityGates(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QualityGates.class, id);
        } finally {
            em.close();
        }
    }

    public int getQualityGatesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QualityGates> rt = cq.from(QualityGates.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
