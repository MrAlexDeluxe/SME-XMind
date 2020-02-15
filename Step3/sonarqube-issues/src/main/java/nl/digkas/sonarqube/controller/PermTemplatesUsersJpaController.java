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
import nl.digkas.sonarqube.domain.PermTemplatesUsers;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class PermTemplatesUsersJpaController implements Serializable {

    public PermTemplatesUsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PermTemplatesUsers permTemplatesUsers) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(permTemplatesUsers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PermTemplatesUsers permTemplatesUsers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            permTemplatesUsers = em.merge(permTemplatesUsers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permTemplatesUsers.getId();
                if (findPermTemplatesUsers(id) == null) {
                    throw new NonexistentEntityException("The permTemplatesUsers with id " + id + " no longer exists.");
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
            PermTemplatesUsers permTemplatesUsers;
            try {
                permTemplatesUsers = em.getReference(PermTemplatesUsers.class, id);
                permTemplatesUsers.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permTemplatesUsers with id " + id + " no longer exists.", enfe);
            }
            em.remove(permTemplatesUsers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PermTemplatesUsers> findPermTemplatesUsersEntities() {
        return findPermTemplatesUsersEntities(true, -1, -1);
    }

    public List<PermTemplatesUsers> findPermTemplatesUsersEntities(int maxResults, int firstResult) {
        return findPermTemplatesUsersEntities(false, maxResults, firstResult);
    }

    private List<PermTemplatesUsers> findPermTemplatesUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PermTemplatesUsers.class));
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

    public PermTemplatesUsers findPermTemplatesUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PermTemplatesUsers.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermTemplatesUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PermTemplatesUsers> rt = cq.from(PermTemplatesUsers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
