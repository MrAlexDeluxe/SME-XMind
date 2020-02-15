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
import nl.digkas.sonarqube.domain.QprofileEditUsers;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class QprofileEditUsersJpaController implements Serializable {

    public QprofileEditUsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QprofileEditUsers qprofileEditUsers) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(qprofileEditUsers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findQprofileEditUsers(qprofileEditUsers.getUuid()) != null) {
                throw new PreexistingEntityException("QprofileEditUsers " + qprofileEditUsers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QprofileEditUsers qprofileEditUsers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            qprofileEditUsers = em.merge(qprofileEditUsers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = qprofileEditUsers.getUuid();
                if (findQprofileEditUsers(id) == null) {
                    throw new NonexistentEntityException("The qprofileEditUsers with id " + id + " no longer exists.");
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
            QprofileEditUsers qprofileEditUsers;
            try {
                qprofileEditUsers = em.getReference(QprofileEditUsers.class, id);
                qprofileEditUsers.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The qprofileEditUsers with id " + id + " no longer exists.", enfe);
            }
            em.remove(qprofileEditUsers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QprofileEditUsers> findQprofileEditUsersEntities() {
        return findQprofileEditUsersEntities(true, -1, -1);
    }

    public List<QprofileEditUsers> findQprofileEditUsersEntities(int maxResults, int firstResult) {
        return findQprofileEditUsersEntities(false, maxResults, firstResult);
    }

    private List<QprofileEditUsers> findQprofileEditUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QprofileEditUsers.class));
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

    public QprofileEditUsers findQprofileEditUsers(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QprofileEditUsers.class, id);
        } finally {
            em.close();
        }
    }

    public int getQprofileEditUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QprofileEditUsers> rt = cq.from(QprofileEditUsers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
