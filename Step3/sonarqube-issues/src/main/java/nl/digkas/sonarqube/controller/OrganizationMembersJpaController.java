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
import nl.digkas.sonarqube.domain.OrganizationMembers;
import nl.digkas.sonarqube.domain.OrganizationMembersPK;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class OrganizationMembersJpaController implements Serializable {

    public OrganizationMembersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrganizationMembers organizationMembers) throws PreexistingEntityException, Exception {
        if (organizationMembers.getOrganizationMembersPK() == null) {
            organizationMembers.setOrganizationMembersPK(new OrganizationMembersPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(organizationMembers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrganizationMembers(organizationMembers.getOrganizationMembersPK()) != null) {
                throw new PreexistingEntityException("OrganizationMembers " + organizationMembers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrganizationMembers organizationMembers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            organizationMembers = em.merge(organizationMembers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OrganizationMembersPK id = organizationMembers.getOrganizationMembersPK();
                if (findOrganizationMembers(id) == null) {
                    throw new NonexistentEntityException("The organizationMembers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OrganizationMembersPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrganizationMembers organizationMembers;
            try {
                organizationMembers = em.getReference(OrganizationMembers.class, id);
                organizationMembers.getOrganizationMembersPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizationMembers with id " + id + " no longer exists.", enfe);
            }
            em.remove(organizationMembers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrganizationMembers> findOrganizationMembersEntities() {
        return findOrganizationMembersEntities(true, -1, -1);
    }

    public List<OrganizationMembers> findOrganizationMembersEntities(int maxResults, int firstResult) {
        return findOrganizationMembersEntities(false, maxResults, firstResult);
    }

    private List<OrganizationMembers> findOrganizationMembersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrganizationMembers.class));
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

    public OrganizationMembers findOrganizationMembers(OrganizationMembersPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrganizationMembers.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizationMembersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrganizationMembers> rt = cq.from(OrganizationMembers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
