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
import nl.digkas.sonarqube.domain.PermTemplatesGroups;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class PermTemplatesGroupsJpaController implements Serializable {

    public PermTemplatesGroupsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PermTemplatesGroups permTemplatesGroups) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(permTemplatesGroups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PermTemplatesGroups permTemplatesGroups) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            permTemplatesGroups = em.merge(permTemplatesGroups);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permTemplatesGroups.getId();
                if (findPermTemplatesGroups(id) == null) {
                    throw new NonexistentEntityException("The permTemplatesGroups with id " + id + " no longer exists.");
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
            PermTemplatesGroups permTemplatesGroups;
            try {
                permTemplatesGroups = em.getReference(PermTemplatesGroups.class, id);
                permTemplatesGroups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permTemplatesGroups with id " + id + " no longer exists.", enfe);
            }
            em.remove(permTemplatesGroups);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PermTemplatesGroups> findPermTemplatesGroupsEntities() {
        return findPermTemplatesGroupsEntities(true, -1, -1);
    }

    public List<PermTemplatesGroups> findPermTemplatesGroupsEntities(int maxResults, int firstResult) {
        return findPermTemplatesGroupsEntities(false, maxResults, firstResult);
    }

    private List<PermTemplatesGroups> findPermTemplatesGroupsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PermTemplatesGroups.class));
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

    public PermTemplatesGroups findPermTemplatesGroups(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PermTemplatesGroups.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermTemplatesGroupsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PermTemplatesGroups> rt = cq.from(PermTemplatesGroups.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
