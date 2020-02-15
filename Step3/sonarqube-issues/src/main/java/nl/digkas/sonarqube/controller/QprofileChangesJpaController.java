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
import nl.digkas.sonarqube.domain.QprofileChanges;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class QprofileChangesJpaController implements Serializable {

    public QprofileChangesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QprofileChanges qprofileChanges) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(qprofileChanges);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findQprofileChanges(qprofileChanges.getKee()) != null) {
                throw new PreexistingEntityException("QprofileChanges " + qprofileChanges + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QprofileChanges qprofileChanges) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            qprofileChanges = em.merge(qprofileChanges);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = qprofileChanges.getKee();
                if (findQprofileChanges(id) == null) {
                    throw new NonexistentEntityException("The qprofileChanges with id " + id + " no longer exists.");
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
            QprofileChanges qprofileChanges;
            try {
                qprofileChanges = em.getReference(QprofileChanges.class, id);
                qprofileChanges.getKee();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The qprofileChanges with id " + id + " no longer exists.", enfe);
            }
            em.remove(qprofileChanges);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QprofileChanges> findQprofileChangesEntities() {
        return findQprofileChangesEntities(true, -1, -1);
    }

    public List<QprofileChanges> findQprofileChangesEntities(int maxResults, int firstResult) {
        return findQprofileChangesEntities(false, maxResults, firstResult);
    }

    private List<QprofileChanges> findQprofileChangesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QprofileChanges.class));
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

    public QprofileChanges findQprofileChanges(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QprofileChanges.class, id);
        } finally {
            em.close();
        }
    }

    public int getQprofileChangesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QprofileChanges> rt = cq.from(QprofileChanges.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
