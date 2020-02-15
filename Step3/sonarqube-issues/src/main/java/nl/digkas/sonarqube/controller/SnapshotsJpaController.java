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
import nl.digkas.sonarqube.domain.Snapshots;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class SnapshotsJpaController implements Serializable {

    public SnapshotsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Snapshots snapshots) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(snapshots);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Snapshots snapshots) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            snapshots = em.merge(snapshots);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = snapshots.getId();
                if (findSnapshots(id) == null) {
                    throw new NonexistentEntityException("The snapshots with id " + id + " no longer exists.");
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
            Snapshots snapshots;
            try {
                snapshots = em.getReference(Snapshots.class, id);
                snapshots.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The snapshots with id " + id + " no longer exists.", enfe);
            }
            em.remove(snapshots);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Snapshots> findSnapshotsEntities() {
        return findSnapshotsEntities(true, -1, -1);
    }

    public List<Snapshots> findSnapshotsEntities(int maxResults, int firstResult) {
        return findSnapshotsEntities(false, maxResults, firstResult);
    }

    private List<Snapshots> findSnapshotsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Snapshots.class));
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

    public Snapshots findSnapshots(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Snapshots.class, id);
        } finally {
            em.close();
        }
    }

    public int getSnapshotsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Snapshots> rt = cq.from(Snapshots.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
