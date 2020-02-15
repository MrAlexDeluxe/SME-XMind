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
import nl.digkas.sonarqube.domain.LoadedTemplates;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class LoadedTemplatesJpaController implements Serializable {

    public LoadedTemplatesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LoadedTemplates loadedTemplates) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(loadedTemplates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LoadedTemplates loadedTemplates) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            loadedTemplates = em.merge(loadedTemplates);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = loadedTemplates.getId();
                if (findLoadedTemplates(id) == null) {
                    throw new NonexistentEntityException("The loadedTemplates with id " + id + " no longer exists.");
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
            LoadedTemplates loadedTemplates;
            try {
                loadedTemplates = em.getReference(LoadedTemplates.class, id);
                loadedTemplates.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The loadedTemplates with id " + id + " no longer exists.", enfe);
            }
            em.remove(loadedTemplates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LoadedTemplates> findLoadedTemplatesEntities() {
        return findLoadedTemplatesEntities(true, -1, -1);
    }

    public List<LoadedTemplates> findLoadedTemplatesEntities(int maxResults, int firstResult) {
        return findLoadedTemplatesEntities(false, maxResults, firstResult);
    }

    private List<LoadedTemplates> findLoadedTemplatesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LoadedTemplates.class));
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

    public LoadedTemplates findLoadedTemplates(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LoadedTemplates.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoadedTemplatesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoadedTemplates> rt = cq.from(LoadedTemplates.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
