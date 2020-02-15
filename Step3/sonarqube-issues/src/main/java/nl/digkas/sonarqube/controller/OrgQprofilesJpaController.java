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
import nl.digkas.sonarqube.domain.OrgQprofiles;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class OrgQprofilesJpaController implements Serializable {

    public OrgQprofilesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrgQprofiles orgQprofiles) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(orgQprofiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrgQprofiles(orgQprofiles.getUuid()) != null) {
                throw new PreexistingEntityException("OrgQprofiles " + orgQprofiles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrgQprofiles orgQprofiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            orgQprofiles = em.merge(orgQprofiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = orgQprofiles.getUuid();
                if (findOrgQprofiles(id) == null) {
                    throw new NonexistentEntityException("The orgQprofiles with id " + id + " no longer exists.");
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
            OrgQprofiles orgQprofiles;
            try {
                orgQprofiles = em.getReference(OrgQprofiles.class, id);
                orgQprofiles.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orgQprofiles with id " + id + " no longer exists.", enfe);
            }
            em.remove(orgQprofiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrgQprofiles> findOrgQprofilesEntities() {
        return findOrgQprofilesEntities(true, -1, -1);
    }

    public List<OrgQprofiles> findOrgQprofilesEntities(int maxResults, int firstResult) {
        return findOrgQprofilesEntities(false, maxResults, firstResult);
    }

    private List<OrgQprofiles> findOrgQprofilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrgQprofiles.class));
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

    public OrgQprofiles findOrgQprofiles(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrgQprofiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrgQprofilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrgQprofiles> rt = cq.from(OrgQprofiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
