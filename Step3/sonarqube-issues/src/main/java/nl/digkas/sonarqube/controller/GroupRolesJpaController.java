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
import nl.digkas.sonarqube.domain.GroupRoles;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class GroupRolesJpaController implements Serializable {

    public GroupRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GroupRoles groupRoles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(groupRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GroupRoles groupRoles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            groupRoles = em.merge(groupRoles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = groupRoles.getId();
                if (findGroupRoles(id) == null) {
                    throw new NonexistentEntityException("The groupRoles with id " + id + " no longer exists.");
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
            GroupRoles groupRoles;
            try {
                groupRoles = em.getReference(GroupRoles.class, id);
                groupRoles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The groupRoles with id " + id + " no longer exists.", enfe);
            }
            em.remove(groupRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GroupRoles> findGroupRolesEntities() {
        return findGroupRolesEntities(true, -1, -1);
    }

    public List<GroupRoles> findGroupRolesEntities(int maxResults, int firstResult) {
        return findGroupRolesEntities(false, maxResults, firstResult);
    }

    private List<GroupRoles> findGroupRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GroupRoles.class));
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

    public GroupRoles findGroupRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GroupRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getGroupRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GroupRoles> rt = cq.from(GroupRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
