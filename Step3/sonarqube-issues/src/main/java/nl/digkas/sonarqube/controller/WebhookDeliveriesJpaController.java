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
import nl.digkas.sonarqube.domain.WebhookDeliveries;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class WebhookDeliveriesJpaController implements Serializable {

    public WebhookDeliveriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WebhookDeliveries webhookDeliveries) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(webhookDeliveries);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findWebhookDeliveries(webhookDeliveries.getUuid()) != null) {
                throw new PreexistingEntityException("WebhookDeliveries " + webhookDeliveries + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WebhookDeliveries webhookDeliveries) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            webhookDeliveries = em.merge(webhookDeliveries);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = webhookDeliveries.getUuid();
                if (findWebhookDeliveries(id) == null) {
                    throw new NonexistentEntityException("The webhookDeliveries with id " + id + " no longer exists.");
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
            WebhookDeliveries webhookDeliveries;
            try {
                webhookDeliveries = em.getReference(WebhookDeliveries.class, id);
                webhookDeliveries.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The webhookDeliveries with id " + id + " no longer exists.", enfe);
            }
            em.remove(webhookDeliveries);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WebhookDeliveries> findWebhookDeliveriesEntities() {
        return findWebhookDeliveriesEntities(true, -1, -1);
    }

    public List<WebhookDeliveries> findWebhookDeliveriesEntities(int maxResults, int firstResult) {
        return findWebhookDeliveriesEntities(false, maxResults, firstResult);
    }

    private List<WebhookDeliveries> findWebhookDeliveriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WebhookDeliveries.class));
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

    public WebhookDeliveries findWebhookDeliveries(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WebhookDeliveries.class, id);
        } finally {
            em.close();
        }
    }

    public int getWebhookDeliveriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WebhookDeliveries> rt = cq.from(WebhookDeliveries.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
