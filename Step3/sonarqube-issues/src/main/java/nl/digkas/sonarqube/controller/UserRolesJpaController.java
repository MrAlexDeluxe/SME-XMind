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
import nl.digkas.sonarqube.domain.UserRoles;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class UserRolesJpaController implements Serializable {

    public UserRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserRoles userRoles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserRoles userRoles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userRoles = em.merge(userRoles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userRoles.getId();
                if (findUserRoles(id) == null) {
                    throw new NonexistentEntityException("The userRoles with id " + id + " no longer exists.");
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
            UserRoles userRoles;
            try {
                userRoles = em.getReference(UserRoles.class, id);
                userRoles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userRoles with id " + id + " no longer exists.", enfe);
            }
            em.remove(userRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserRoles> findUserRolesEntities() {
        return findUserRolesEntities(true, -1, -1);
    }

    public List<UserRoles> findUserRolesEntities(int maxResults, int firstResult) {
        return findUserRolesEntities(false, maxResults, firstResult);
    }

    private List<UserRoles> findUserRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRoles.class));
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

    public UserRoles findUserRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserRoles> rt = cq.from(UserRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
