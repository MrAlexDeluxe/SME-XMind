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
import nl.digkas.sonarqube.controller.exceptions.PreexistingEntityException;
import nl.digkas.sonarqube.domain.CeTaskCharacteristics;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class CeTaskCharacteristicsJpaController implements Serializable {

    public CeTaskCharacteristicsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CeTaskCharacteristics ceTaskCharacteristics) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ceTaskCharacteristics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCeTaskCharacteristics(ceTaskCharacteristics.getUuid()) != null) {
                throw new PreexistingEntityException("CeTaskCharacteristics " + ceTaskCharacteristics + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CeTaskCharacteristics ceTaskCharacteristics) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ceTaskCharacteristics = em.merge(ceTaskCharacteristics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ceTaskCharacteristics.getUuid();
                if (findCeTaskCharacteristics(id) == null) {
                    throw new NonexistentEntityException("The ceTaskCharacteristics with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CeTaskCharacteristics ceTaskCharacteristics;
            try {
                ceTaskCharacteristics = em.getReference(CeTaskCharacteristics.class, id);
                ceTaskCharacteristics.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ceTaskCharacteristics with id " + id + " no longer exists.", enfe);
            }
            em.remove(ceTaskCharacteristics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CeTaskCharacteristics> findCeTaskCharacteristicsEntities() {
        return findCeTaskCharacteristicsEntities(true, -1, -1);
    }

    public List<CeTaskCharacteristics> findCeTaskCharacteristicsEntities(int maxResults, int firstResult) {
        return findCeTaskCharacteristicsEntities(false, maxResults, firstResult);
    }

    private List<CeTaskCharacteristics> findCeTaskCharacteristicsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CeTaskCharacteristics.class));
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

    public CeTaskCharacteristics findCeTaskCharacteristics(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CeTaskCharacteristics.class, id);
        } finally {
            em.close();
        }
    }

    public int getCeTaskCharacteristicsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CeTaskCharacteristics> rt = cq.from(CeTaskCharacteristics.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
