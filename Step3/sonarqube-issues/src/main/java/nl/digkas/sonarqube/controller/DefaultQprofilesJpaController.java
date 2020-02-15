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
import nl.digkas.sonarqube.domain.DefaultQprofiles;
import nl.digkas.sonarqube.domain.DefaultQprofilesPK;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class DefaultQprofilesJpaController implements Serializable {

    public DefaultQprofilesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DefaultQprofiles defaultQprofiles) throws PreexistingEntityException, Exception {
        if (defaultQprofiles.getDefaultQprofilesPK() == null) {
            defaultQprofiles.setDefaultQprofilesPK(new DefaultQprofilesPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(defaultQprofiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDefaultQprofiles(defaultQprofiles.getDefaultQprofilesPK()) != null) {
                throw new PreexistingEntityException("DefaultQprofiles " + defaultQprofiles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DefaultQprofiles defaultQprofiles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            defaultQprofiles = em.merge(defaultQprofiles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DefaultQprofilesPK id = defaultQprofiles.getDefaultQprofilesPK();
                if (findDefaultQprofiles(id) == null) {
                    throw new NonexistentEntityException("The defaultQprofiles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DefaultQprofilesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DefaultQprofiles defaultQprofiles;
            try {
                defaultQprofiles = em.getReference(DefaultQprofiles.class, id);
                defaultQprofiles.getDefaultQprofilesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The defaultQprofiles with id " + id + " no longer exists.", enfe);
            }
            em.remove(defaultQprofiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DefaultQprofiles> findDefaultQprofilesEntities() {
        return findDefaultQprofilesEntities(true, -1, -1);
    }

    public List<DefaultQprofiles> findDefaultQprofilesEntities(int maxResults, int firstResult) {
        return findDefaultQprofilesEntities(false, maxResults, firstResult);
    }

    private List<DefaultQprofiles> findDefaultQprofilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DefaultQprofiles.class));
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

    public DefaultQprofiles findDefaultQprofiles(DefaultQprofilesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DefaultQprofiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getDefaultQprofilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DefaultQprofiles> rt = cq.from(DefaultQprofiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
