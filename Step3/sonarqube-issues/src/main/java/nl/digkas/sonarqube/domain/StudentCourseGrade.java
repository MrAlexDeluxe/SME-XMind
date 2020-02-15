package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "student_course_grade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentCourseGrade.findAll", query = "SELECT s FROM StudentCourseGrade s")
    , @NamedQuery(name = "StudentCourseGrade.findByStudentId", query = "SELECT s FROM StudentCourseGrade s WHERE s.studentCourseGradePK.studentId = :studentId")
    , @NamedQuery(name = "StudentCourseGrade.findByCourseId", query = "SELECT s FROM StudentCourseGrade s WHERE s.studentCourseGradePK.courseId = :courseId")
    , @NamedQuery(name = "StudentCourseGrade.findByGrade", query = "SELECT s FROM StudentCourseGrade s WHERE s.grade = :grade")})
public class StudentCourseGrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentCourseGradePK studentCourseGradePK;
    @Column(name = "grade")
    private Integer grade;
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public StudentCourseGrade() {
    }

    public StudentCourseGrade(StudentCourseGradePK studentCourseGradePK) {
        this.studentCourseGradePK = studentCourseGradePK;
    }

    public StudentCourseGrade(int studentId, int courseId) {
        this.studentCourseGradePK = new StudentCourseGradePK(studentId, courseId);
    }

    public StudentCourseGradePK getStudentCourseGradePK() {
        return studentCourseGradePK;
    }

    public void setStudentCourseGradePK(StudentCourseGradePK studentCourseGradePK) {
        this.studentCourseGradePK = studentCourseGradePK;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentCourseGradePK != null ? studentCourseGradePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentCourseGrade)) {
            return false;
        }
        StudentCourseGrade other = (StudentCourseGrade) object;
        if ((this.studentCourseGradePK == null && other.studentCourseGradePK != null) || (this.studentCourseGradePK != null && !this.studentCourseGradePK.equals(other.studentCourseGradePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.StudentCourseGrade[ studentCourseGradePK=" + studentCourseGradePK + " ]";
    }
    
}
