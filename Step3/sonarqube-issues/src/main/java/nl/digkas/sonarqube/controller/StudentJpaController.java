package nl.digkas.sonarqube.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import nl.digkas.sonarqube.domain.Course;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import nl.digkas.sonarqube.controller.exceptions.IllegalOrphanException;
import nl.digkas.sonarqube.controller.exceptions.NonexistentEntityException;
import nl.digkas.sonarqube.domain.Student;
import nl.digkas.sonarqube.domain.StudentCourseGrade;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
public class StudentJpaController implements Serializable {

    public StudentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Student student) {
        if (student.getCourseCollection() == null) {
            student.setCourseCollection(new ArrayList<Course>());
        }
        if (student.getStudentCourseGradeCollection() == null) {
            student.setStudentCourseGradeCollection(new ArrayList<StudentCourseGrade>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Course> attachedCourseCollection = new ArrayList<Course>();
            for (Course courseCollectionCourseToAttach : student.getCourseCollection()) {
                courseCollectionCourseToAttach = em.getReference(courseCollectionCourseToAttach.getClass(), courseCollectionCourseToAttach.getId());
                attachedCourseCollection.add(courseCollectionCourseToAttach);
            }
            student.setCourseCollection(attachedCourseCollection);
            Collection<StudentCourseGrade> attachedStudentCourseGradeCollection = new ArrayList<StudentCourseGrade>();
            for (StudentCourseGrade studentCourseGradeCollectionStudentCourseGradeToAttach : student.getStudentCourseGradeCollection()) {
                studentCourseGradeCollectionStudentCourseGradeToAttach = em.getReference(studentCourseGradeCollectionStudentCourseGradeToAttach.getClass(), studentCourseGradeCollectionStudentCourseGradeToAttach.getStudentCourseGradePK());
                attachedStudentCourseGradeCollection.add(studentCourseGradeCollectionStudentCourseGradeToAttach);
            }
            student.setStudentCourseGradeCollection(attachedStudentCourseGradeCollection);
            em.persist(student);
            for (Course courseCollectionCourse : student.getCourseCollection()) {
                courseCollectionCourse.getStudentCollection().add(student);
                courseCollectionCourse = em.merge(courseCollectionCourse);
            }
            for (StudentCourseGrade studentCourseGradeCollectionStudentCourseGrade : student.getStudentCourseGradeCollection()) {
                Student oldStudentOfStudentCourseGradeCollectionStudentCourseGrade = studentCourseGradeCollectionStudentCourseGrade.getStudent();
                studentCourseGradeCollectionStudentCourseGrade.setStudent(student);
                studentCourseGradeCollectionStudentCourseGrade = em.merge(studentCourseGradeCollectionStudentCourseGrade);
                if (oldStudentOfStudentCourseGradeCollectionStudentCourseGrade != null) {
                    oldStudentOfStudentCourseGradeCollectionStudentCourseGrade.getStudentCourseGradeCollection().remove(studentCourseGradeCollectionStudentCourseGrade);
                    oldStudentOfStudentCourseGradeCollectionStudentCourseGrade = em.merge(oldStudentOfStudentCourseGradeCollectionStudentCourseGrade);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Student student) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Student persistentStudent = em.find(Student.class, student.getId());
            Collection<Course> courseCollectionOld = persistentStudent.getCourseCollection();
            Collection<Course> courseCollectionNew = student.getCourseCollection();
            Collection<StudentCourseGrade> studentCourseGradeCollectionOld = persistentStudent.getStudentCourseGradeCollection();
            Collection<StudentCourseGrade> studentCourseGradeCollectionNew = student.getStudentCourseGradeCollection();
            List<String> illegalOrphanMessages = null;
            for (StudentCourseGrade studentCourseGradeCollectionOldStudentCourseGrade : studentCourseGradeCollectionOld) {
                if (!studentCourseGradeCollectionNew.contains(studentCourseGradeCollectionOldStudentCourseGrade)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentCourseGrade " + studentCourseGradeCollectionOldStudentCourseGrade + " since its student field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Course> attachedCourseCollectionNew = new ArrayList<Course>();
            for (Course courseCollectionNewCourseToAttach : courseCollectionNew) {
                courseCollectionNewCourseToAttach = em.getReference(courseCollectionNewCourseToAttach.getClass(), courseCollectionNewCourseToAttach.getId());
                attachedCourseCollectionNew.add(courseCollectionNewCourseToAttach);
            }
            courseCollectionNew = attachedCourseCollectionNew;
            student.setCourseCollection(courseCollectionNew);
            Collection<StudentCourseGrade> attachedStudentCourseGradeCollectionNew = new ArrayList<StudentCourseGrade>();
            for (StudentCourseGrade studentCourseGradeCollectionNewStudentCourseGradeToAttach : studentCourseGradeCollectionNew) {
                studentCourseGradeCollectionNewStudentCourseGradeToAttach = em.getReference(studentCourseGradeCollectionNewStudentCourseGradeToAttach.getClass(), studentCourseGradeCollectionNewStudentCourseGradeToAttach.getStudentCourseGradePK());
                attachedStudentCourseGradeCollectionNew.add(studentCourseGradeCollectionNewStudentCourseGradeToAttach);
            }
            studentCourseGradeCollectionNew = attachedStudentCourseGradeCollectionNew;
            student.setStudentCourseGradeCollection(studentCourseGradeCollectionNew);
            student = em.merge(student);
            for (Course courseCollectionOldCourse : courseCollectionOld) {
                if (!courseCollectionNew.contains(courseCollectionOldCourse)) {
                    courseCollectionOldCourse.getStudentCollection().remove(student);
                    courseCollectionOldCourse = em.merge(courseCollectionOldCourse);
                }
            }
            for (Course courseCollectionNewCourse : courseCollectionNew) {
                if (!courseCollectionOld.contains(courseCollectionNewCourse)) {
                    courseCollectionNewCourse.getStudentCollection().add(student);
                    courseCollectionNewCourse = em.merge(courseCollectionNewCourse);
                }
            }
            for (StudentCourseGrade studentCourseGradeCollectionNewStudentCourseGrade : studentCourseGradeCollectionNew) {
                if (!studentCourseGradeCollectionOld.contains(studentCourseGradeCollectionNewStudentCourseGrade)) {
                    Student oldStudentOfStudentCourseGradeCollectionNewStudentCourseGrade = studentCourseGradeCollectionNewStudentCourseGrade.getStudent();
                    studentCourseGradeCollectionNewStudentCourseGrade.setStudent(student);
                    studentCourseGradeCollectionNewStudentCourseGrade = em.merge(studentCourseGradeCollectionNewStudentCourseGrade);
                    if (oldStudentOfStudentCourseGradeCollectionNewStudentCourseGrade != null && !oldStudentOfStudentCourseGradeCollectionNewStudentCourseGrade.equals(student)) {
                        oldStudentOfStudentCourseGradeCollectionNewStudentCourseGrade.getStudentCourseGradeCollection().remove(studentCourseGradeCollectionNewStudentCourseGrade);
                        oldStudentOfStudentCourseGradeCollectionNewStudentCourseGrade = em.merge(oldStudentOfStudentCourseGradeCollectionNewStudentCourseGrade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = student.getId();
                if (findStudent(id) == null) {
                    throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
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
            Student student;
            try {
                student = em.getReference(Student.class, id);
                student.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<StudentCourseGrade> studentCourseGradeCollectionOrphanCheck = student.getStudentCourseGradeCollection();
            for (StudentCourseGrade studentCourseGradeCollectionOrphanCheckStudentCourseGrade : studentCourseGradeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the StudentCourseGrade " + studentCourseGradeCollectionOrphanCheckStudentCourseGrade + " in its studentCourseGradeCollection field has a non-nullable student field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Course> courseCollection = student.getCourseCollection();
            for (Course courseCollectionCourse : courseCollection) {
                courseCollectionCourse.getStudentCollection().remove(student);
                courseCollectionCourse = em.merge(courseCollectionCourse);
            }
            em.remove(student);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Student.class));
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

    public Student findStudent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Student> rt = cq.from(Student.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
