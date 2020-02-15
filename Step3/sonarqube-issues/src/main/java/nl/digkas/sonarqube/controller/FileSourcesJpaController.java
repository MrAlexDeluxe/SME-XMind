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
import nl.digkas.sonarqube.domain.FileSources;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class FileSourcesJpaController implements Serializable {

    public FileSourcesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FileSources fileSources) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fileSources);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FileSources fileSources) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fileSources = em.merge(fileSources);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fileSources.getId();
                if (findFileSources(id) == null) {
                    throw new NonexistentEntityException("The fileSources with id " + id + " no longer exists.");
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
            FileSources fileSources;
            try {
                fileSources = em.getReference(FileSources.class, id);
                fileSources.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fileSources with id " + id + " no longer exists.", enfe);
            }
            em.remove(fileSources);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FileSources> findFileSourcesEntities() {
        return findFileSourcesEntities(true, -1, -1);
    }

    public List<FileSources> findFileSourcesEntities(int maxResults, int firstResult) {
        return findFileSourcesEntities(false, maxResults, firstResult);
    }

    private List<FileSources> findFileSourcesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FileSources.class));
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

    public FileSources findFileSources(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FileSources.class, id);
        } finally {
            em.close();
        }
    }

    public int getFileSourcesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FileSources> rt = cq.from(FileSources.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
