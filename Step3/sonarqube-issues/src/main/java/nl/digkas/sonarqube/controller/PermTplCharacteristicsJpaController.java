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
import nl.digkas.sonarqube.domain.PermTplCharacteristics;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class PermTplCharacteristicsJpaController implements Serializable {

    public PermTplCharacteristicsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PermTplCharacteristics permTplCharacteristics) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(permTplCharacteristics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PermTplCharacteristics permTplCharacteristics) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            permTplCharacteristics = em.merge(permTplCharacteristics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permTplCharacteristics.getId();
                if (findPermTplCharacteristics(id) == null) {
                    throw new NonexistentEntityException("The permTplCharacteristics with id " + id + " no longer exists.");
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
            PermTplCharacteristics permTplCharacteristics;
            try {
                permTplCharacteristics = em.getReference(PermTplCharacteristics.class, id);
                permTplCharacteristics.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permTplCharacteristics with id " + id + " no longer exists.", enfe);
            }
            em.remove(permTplCharacteristics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PermTplCharacteristics> findPermTplCharacteristicsEntities() {
        return findPermTplCharacteristicsEntities(true, -1, -1);
    }

    public List<PermTplCharacteristics> findPermTplCharacteristicsEntities(int maxResults, int firstResult) {
        return findPermTplCharacteristicsEntities(false, maxResults, firstResult);
    }

    private List<PermTplCharacteristics> findPermTplCharacteristicsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PermTplCharacteristics.class));
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

    public PermTplCharacteristics findPermTplCharacteristics(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PermTplCharacteristics.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermTplCharacteristicsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PermTplCharacteristics> rt = cq.from(PermTplCharacteristics.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
