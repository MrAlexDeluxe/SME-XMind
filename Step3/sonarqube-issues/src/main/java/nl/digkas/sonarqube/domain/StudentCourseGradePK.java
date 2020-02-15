package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Embeddable
public class StudentCourseGradePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "student_id")
    private int studentId;
    @Basic(optional = false)
    @Column(name = "course_id")
    private int courseId;

    public StudentCourseGradePK() {
    }

    public StudentCourseGradePK(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentId;
        hash += (int) courseId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentCourseGradePK)) {
            return false;
        }
        StudentCourseGradePK other = (StudentCourseGradePK) object;
        if (this.studentId != other.studentId) {
            return false;
        }
        if (this.courseId != other.courseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.StudentCourseGradePK[ studentId=" + studentId + ", courseId=" + courseId + " ]";
    }
    
}
