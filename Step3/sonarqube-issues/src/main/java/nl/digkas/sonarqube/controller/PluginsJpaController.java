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
import nl.digkas.sonarqube.domain.Plugins;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class PluginsJpaController implements Serializable {

    public PluginsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plugins plugins) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(plugins);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlugins(plugins.getUuid()) != null) {
                throw new PreexistingEntityException("Plugins " + plugins + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Plugins plugins) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            plugins = em.merge(plugins);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = plugins.getUuid();
                if (findPlugins(id) == null) {
                    throw new NonexistentEntityException("The plugins with id " + id + " no longer exists.");
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
            Plugins plugins;
            try {
                plugins = em.getReference(Plugins.class, id);
                plugins.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plugins with id " + id + " no longer exists.", enfe);
            }
            em.remove(plugins);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Plugins> findPluginsEntities() {
        return findPluginsEntities(true, -1, -1);
    }

    public List<Plugins> findPluginsEntities(int maxResults, int firstResult) {
        return findPluginsEntities(false, maxResults, firstResult);
    }

    private List<Plugins> findPluginsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Plugins.class));
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

    public Plugins findPlugins(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plugins.class, id);
        } finally {
            em.close();
        }
    }

    public int getPluginsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Plugins> rt = cq.from(Plugins.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
