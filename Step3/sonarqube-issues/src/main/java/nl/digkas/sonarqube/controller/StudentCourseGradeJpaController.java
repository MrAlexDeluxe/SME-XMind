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
import nl.digkas.sonarqube.domain.Course;
import nl.digkas.sonarqube.domain.Student;
import nl.digkas.sonarqube.domain.StudentCourseGrade;
import nl.digkas.sonarqube.domain.StudentCourseGradePK;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class StudentCourseGradeJpaController implements Serializable {

    public StudentCourseGradeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentCourseGrade studentCourseGrade) throws PreexistingEntityException, Exception {
        if (studentCourseGrade.getStudentCourseGradePK() == null) {
            studentCourseGrade.setStudentCourseGradePK(new StudentCourseGradePK());
        }
        studentCourseGrade.getStudentCourseGradePK().setStudentId(studentCourseGrade.getStudent().getId());
        studentCourseGrade.getStudentCourseGradePK().setCourseId(studentCourseGrade.getCourse().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course course = studentCourseGrade.getCourse();
            if (course != null) {
                course = em.getReference(course.getClass(), course.getId());
                studentCourseGrade.setCourse(course);
            }
            Student student = studentCourseGrade.getStudent();
            if (student != null) {
                student = em.getReference(student.getClass(), student.getId());
                studentCourseGrade.setStudent(student);
            }
            em.persist(studentCourseGrade);
            if (course != null) {
                course.getStudentCourseGradeCollection().add(studentCourseGrade);
                course = em.merge(course);
            }
            if (student != null) {
                student.getStudentCourseGradeCollection().add(studentCourseGrade);
                student = em.merge(student);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStudentCourseGrade(studentCourseGrade.getStudentCourseGradePK()) != null) {
                throw new PreexistingEntityException("StudentCourseGrade " + studentCourseGrade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentCourseGrade studentCourseGrade) throws NonexistentEntityException, Exception {
        studentCourseGrade.getStudentCourseGradePK().setStudentId(studentCourseGrade.getStudent().getId());
        studentCourseGrade.getStudentCourseGradePK().setCourseId(studentCourseGrade.getCourse().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentCourseGrade persistentStudentCourseGrade = em.find(StudentCourseGrade.class, studentCourseGrade.getStudentCourseGradePK());
            Course courseOld = persistentStudentCourseGrade.getCourse();
            Course courseNew = studentCourseGrade.getCourse();
            Student studentOld = persistentStudentCourseGrade.getStudent();
            Student studentNew = studentCourseGrade.getStudent();
            if (courseNew != null) {
                courseNew = em.getReference(courseNew.getClass(), courseNew.getId());
                studentCourseGrade.setCourse(courseNew);
            }
            if (studentNew != null) {
                studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
                studentCourseGrade.setStudent(studentNew);
            }
            studentCourseGrade = em.merge(studentCourseGrade);
            if (courseOld != null && !courseOld.equals(courseNew)) {
                courseOld.getStudentCourseGradeCollection().remove(studentCourseGrade);
                courseOld = em.merge(courseOld);
            }
            if (courseNew != null && !courseNew.equals(courseOld)) {
                courseNew.getStudentCourseGradeCollection().add(studentCourseGrade);
                courseNew = em.merge(courseNew);
            }
            if (studentOld != null && !studentOld.equals(studentNew)) {
                studentOld.getStudentCourseGradeCollection().remove(studentCourseGrade);
                studentOld = em.merge(studentOld);
            }
            if (studentNew != null && !studentNew.equals(studentOld)) {
                studentNew.getStudentCourseGradeCollection().add(studentCourseGrade);
                studentNew = em.merge(studentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                StudentCourseGradePK id = studentCourseGrade.getStudentCourseGradePK();
                if (findStudentCourseGrade(id) == null) {
                    throw new NonexistentEntityException("The studentCourseGrade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(StudentCourseGradePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentCourseGrade studentCourseGrade;
            try {
                studentCourseGrade = em.getReference(StudentCourseGrade.class, id);
                studentCourseGrade.getStudentCourseGradePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentCourseGrade with id " + id + " no longer exists.", enfe);
            }
            Course course = studentCourseGrade.getCourse();
            if (course != null) {
                course.getStudentCourseGradeCollection().remove(studentCourseGrade);
                course = em.merge(course);
            }
            Student student = studentCourseGrade.getStudent();
            if (student != null) {
                student.getStudentCourseGradeCollection().remove(studentCourseGrade);
                student = em.merge(student);
            }
            em.remove(studentCourseGrade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StudentCourseGrade> findStudentCourseGradeEntities() {
        return findStudentCourseGradeEntities(true, -1, -1);
    }

    public List<StudentCourseGrade> findStudentCourseGradeEntities(int maxResults, int firstResult) {
        return findStudentCourseGradeEntities(false, maxResults, firstResult);
    }

    private List<StudentCourseGrade> findStudentCourseGradeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentCourseGrade.class));
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

    public StudentCourseGrade findStudentCourseGrade(StudentCourseGradePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentCourseGrade.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentCourseGradeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentCourseGrade> rt = cq.from(StudentCourseGrade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
