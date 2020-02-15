package nl.digkas.sonarqube.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nl.digkas.sonarqube.domain.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import nl.digkas.sonarqube.controller.exceptions.IllegalOrphanException;
import nl.digkas.sonarqube.controller.exceptions.NonexistentEntityException;
import nl.digkas.sonarqube.domain.Course;
import nl.digkas.sonarqube.domain.StudentCourseGrade;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class CourseJpaController implements Serializable {

    public CourseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Course course) {
        if (course.getStudentCollection() == null) {
            course.setStudentCollection(new ArrayList<Student>());
        }
        if (course.getStudentCourseGradeCollection() == null) {
            course.setStudentCourseGradeCollection(new ArrayList<StudentCourseGrade>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : course.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            course.setStudentCollection(attachedStudentCollection);
            Collection<StudentCourseGrade> attachedStudentCourseGradeCollection = new ArrayList<StudentCourseGrade>();
            for (StudentCourseGrade studentCourseGradeCollectionStudentCourseGradeToAttach : course.getStudentCourseGradeCollection()) {
                studentCourseGradeCollectionStudentCourseGradeToAttach = em.getReference(studentCourseGradeCollectionStudentCourseGradeToAttach.getClass(), studentCourseGradeCollectionStudentCourseGradeToAttach.getStudentCourseGradePK());
                attachedStudentCourseGradeCollection.add(studentCourseGradeCollectionStudentCourseGradeToAttach);
            }
            course.setStudentCourseGradeCollection(attachedStudentCourseGradeCollection);
            em.persist(course);
            for (Student studentCollectionStudent : course.getStudentCollection()) {
                studentCollectionStudent.getCourseCollection().add(course);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            for (StudentCourseGrade studentCourseGradeCollectionStudentCourseGrade : course.getStudentCourseGradeCollection()) {
                Course oldCourseOfStudentCourseGradeCollectionStudentCourseGrade = studentCourseGradeCollectionStudentCourseGrade.getCourse();
                studentCourseGradeCollectionStudentCourseGrade.setCourse(course);
                studentCourseGradeCollectionStudentCourseGrade = em.merge(studentCourseGradeCollectionStudentCourseGrade);
                if (oldCourseOfStudentCourseGradeCollectionStudentCourseGrade != null) {
                    oldCourseOfStudentCourseGradeCollectionStudentCourseGrade.getStudentCourseGradeCollection().remove(studentCourseGradeCollectionStudentCourseGrade);
                    oldCourseOfStudentCourseGradeCollectionStudentCourseGrade = em.merge(oldCourseOfStudentCourseGradeCollectionStudentCourseGrade);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Course course) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course persistentCourse = em.find(Course.class, course.getId());
            Collection<Student> studentCollectionOld = persistentCourse.getStudentCollection();
            Collection<Student> studentCollectionNew = course.getStudentCollection();
            Collection<StudentCourseGrade> studentCourseGradeCollectionOld = persistentCourse.getStudentCourseGradeCollection();
            Collection<StudentCourseGrade> studentCourseGradeCollectionNew = course.getStudentCourseGradeCollection();
            List<String> illegalOrphanMessages = null;
            for (StudentCourseGrade studentCourseGradeCollectionOldStudentCourseGrade : studentCourseGradeCollectionOld) {
                if (!studentCourseGradeCollectionNew.contains(studentCourseGradeCollectionOldStudentCourseGrade)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentCourseGrade " + studentCourseGradeCollectionOldStudentCourseGrade + " since its course field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            course.setStudentCollection(studentCollectionNew);
            Collection<StudentCourseGrade> attachedStudentCourseGradeCollectionNew = new ArrayList<StudentCourseGrade>();
            for (StudentCourseGrade studentCourseGradeCollectionNewStudentCourseGradeToAttach : studentCourseGradeCollectionNew) {
                studentCourseGradeCollectionNewStudentCourseGradeToAttach = em.getReference(studentCourseGradeCollectionNewStudentCourseGradeToAttach.getClass(), studentCourseGradeCollectionNewStudentCourseGradeToAttach.getStudentCourseGradePK());
                attachedStudentCourseGradeCollectionNew.add(studentCourseGradeCollectionNewStudentCourseGradeToAttach);
            }
            studentCourseGradeCollectionNew = attachedStudentCourseGradeCollectionNew;
            course.setStudentCourseGradeCollection(studentCourseGradeCollectionNew);
            course = em.merge(course);
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.getCourseCollection().remove(course);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    studentCollectionNewStudent.getCourseCollection().add(course);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                }
            }
            for (StudentCourseGrade studentCourseGradeCollectionNewStudentCourseGrade : studentCourseGradeCollectionNew) {
                if (!studentCourseGradeCollectionOld.contains(studentCourseGradeCollectionNewStudentCourseGrade)) {
                    Course oldCourseOfStudentCourseGradeCollectionNewStudentCourseGrade = studentCourseGradeCollectionNewStudentCourseGrade.getCourse();
                    studentCourseGradeCollectionNewStudentCourseGrade.setCourse(course);
                    studentCourseGradeCollectionNewStudentCourseGrade = em.merge(studentCourseGradeCollectionNewStudentCourseGrade);
                    if (oldCourseOfStudentCourseGradeCollectionNewStudentCourseGrade != null && !oldCourseOfStudentCourseGradeCollectionNewStudentCourseGrade.equals(course)) {
                        oldCourseOfStudentCourseGradeCollectionNewStudentCourseGrade.getStudentCourseGradeCollection().remove(studentCourseGradeCollectionNewStudentCourseGrade);
                        oldCourseOfStudentCourseGradeCollectionNewStudentCourseGrade = em.merge(oldCourseOfStudentCourseGradeCollectionNewStudentCourseGrade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = course.getId();
                if (findCourse(id) == null) {
                    throw new NonexistentEntityException("The course with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course course;
            try {
                course = em.getReference(Course.class, id);
                course.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The course with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<StudentCourseGrade> studentCourseGradeCollectionOrphanCheck = course.getStudentCourseGradeCollection();
            for (StudentCourseGrade studentCourseGradeCollectionOrphanCheckStudentCourseGrade : studentCourseGradeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Course (" + course + ") cannot be destroyed since the StudentCourseGrade " + studentCourseGradeCollectionOrphanCheckStudentCourseGrade + " in its studentCourseGradeCollection field has a non-nullable course field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Student> studentCollection = course.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.getCourseCollection().remove(course);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            em.remove(course);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Course> findCourseEntities() {
        return findCourseEntities(true, -1, -1);
    }

    public List<Course> findCourseEntities(int maxResults, int firstResult) {
        return findCourseEntities(false, maxResults, firstResult);
    }

    private List<Course> findCourseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Course.class));
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

    public Course findCourse(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            em.close();
        }
    }

    public int getCourseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Course> rt = cq.from(Course.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
