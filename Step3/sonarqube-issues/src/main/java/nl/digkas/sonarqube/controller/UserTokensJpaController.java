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
import nl.digkas.sonarqube.domain.UserTokens;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class UserTokensJpaController implements Serializable {

    public UserTokensJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserTokens userTokens) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userTokens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserTokens userTokens) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userTokens = em.merge(userTokens);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userTokens.getId();
                if (findUserTokens(id) == null) {
                    throw new NonexistentEntityException("The userTokens with id " + id + " no longer exists.");
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
            UserTokens userTokens;
            try {
                userTokens = em.getReference(UserTokens.class, id);
                userTokens.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userTokens with id " + id + " no longer exists.", enfe);
            }
            em.remove(userTokens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserTokens> findUserTokensEntities() {
        return findUserTokensEntities(true, -1, -1);
    }

    public List<UserTokens> findUserTokensEntities(int maxResults, int firstResult) {
        return findUserTokensEntities(false, maxResults, firstResult);
    }

    private List<UserTokens> findUserTokensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserTokens.class));
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

    public UserTokens findUserTokens(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserTokens.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserTokensCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserTokens> rt = cq.from(UserTokens.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
