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
import nl.digkas.sonarqube.domain.Notifications;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class NotificationsJpaController implements Serializable {

    public NotificationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notifications notifications) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notifications);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notifications notifications) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notifications = em.merge(notifications);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notifications.getId();
                if (findNotifications(id) == null) {
                    throw new NonexistentEntityException("The notifications with id " + id + " no longer exists.");
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
            Notifications notifications;
            try {
                notifications = em.getReference(Notifications.class, id);
                notifications.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notifications with id " + id + " no longer exists.", enfe);
            }
            em.remove(notifications);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notifications> findNotificationsEntities() {
        return findNotificationsEntities(true, -1, -1);
    }

    public List<Notifications> findNotificationsEntities(int maxResults, int firstResult) {
        return findNotificationsEntities(false, maxResults, firstResult);
    }

    private List<Notifications> findNotificationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notifications.class));
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

    public Notifications findNotifications(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notifications.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notifications> rt = cq.from(Notifications.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
