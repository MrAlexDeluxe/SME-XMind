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
import nl.digkas.sonarqube.domain.PermissionTemplates;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class PermissionTemplatesJpaController implements Serializable {

    public PermissionTemplatesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PermissionTemplates permissionTemplates) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(permissionTemplates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PermissionTemplates permissionTemplates) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            permissionTemplates = em.merge(permissionTemplates);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permissionTemplates.getId();
                if (findPermissionTemplates(id) == null) {
                    throw new NonexistentEntityException("The permissionTemplates with id " + id + " no longer exists.");
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
            PermissionTemplates permissionTemplates;
            try {
                permissionTemplates = em.getReference(PermissionTemplates.class, id);
                permissionTemplates.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permissionTemplates with id " + id + " no longer exists.", enfe);
            }
            em.remove(permissionTemplates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PermissionTemplates> findPermissionTemplatesEntities() {
        return findPermissionTemplatesEntities(true, -1, -1);
    }

    public List<PermissionTemplates> findPermissionTemplatesEntities(int maxResults, int firstResult) {
        return findPermissionTemplatesEntities(false, maxResults, firstResult);
    }

    private List<PermissionTemplates> findPermissionTemplatesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PermissionTemplates.class));
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

    public PermissionTemplates findPermissionTemplates(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PermissionTemplates.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermissionTemplatesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PermissionTemplates> rt = cq.from(PermissionTemplates.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
