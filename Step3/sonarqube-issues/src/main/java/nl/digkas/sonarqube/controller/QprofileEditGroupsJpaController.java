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
import nl.digkas.sonarqube.domain.QprofileEditGroups;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class QprofileEditGroupsJpaController implements Serializable {

    public QprofileEditGroupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QprofileEditGroups qprofileEditGroups) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(qprofileEditGroups);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findQprofileEditGroups(qprofileEditGroups.getUuid()) != null) {
                throw new PreexistingEntityException("QprofileEditGroups " + qprofileEditGroups + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QprofileEditGroups qprofileEditGroups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            qprofileEditGroups = em.merge(qprofileEditGroups);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = qprofileEditGroups.getUuid();
                if (findQprofileEditGroups(id) == null) {
                    throw new NonexistentEntityException("The qprofileEditGroups with id " + id + " no longer exists.");
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
            QprofileEditGroups qprofileEditGroups;
            try {
                qprofileEditGroups = em.getReference(QprofileEditGroups.class, id);
                qprofileEditGroups.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The qprofileEditGroups with id " + id + " no longer exists.", enfe);
            }
            em.remove(qprofileEditGroups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QprofileEditGroups> findQprofileEditGroupsEntities() {
        return findQprofileEditGroupsEntities(true, -1, -1);
    }

    public List<QprofileEditGroups> findQprofileEditGroupsEntities(int maxResults, int firstResult) {
        return findQprofileEditGroupsEntities(false, maxResults, firstResult);
    }

    private List<QprofileEditGroups> findQprofileEditGroupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QprofileEditGroups.class));
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

    public QprofileEditGroups findQprofileEditGroups(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QprofileEditGroups.class, id);
        } finally {
            em.close();
        }
    }

    public int getQprofileEditGroupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QprofileEditGroups> rt = cq.from(QprofileEditGroups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
