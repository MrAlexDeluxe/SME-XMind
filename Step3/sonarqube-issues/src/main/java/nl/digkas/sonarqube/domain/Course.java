package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id")
    , @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName")
    , @NamedQuery(name = "Course.findByCourseSecription", query = "SELECT c FROM Course c WHERE c.courseSecription = :courseSecription")
    , @NamedQuery(name = "Course.findBySemester", query = "SELECT c FROM Course c WHERE c.semester = :semester")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "course_name")
    private String courseName;
    @Basic(optional = false)
    @Column(name = "course_secription")
    private String courseSecription;
    @Basic(optional = false)
    @Column(name = "semester")
    private int semester;
    @JoinTable(name = "student_course_enrollment", joinColumns = {
        @JoinColumn(name = "course_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "student_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Student> studentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<StudentCourseGrade> studentCourseGradeCollection;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String courseName, String courseSecription, int semester) {
        this.id = id;
        this.courseName = courseName;
        this.courseSecription = courseSecription;
        this.semester = semester;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSecription() {
        return courseSecription;
    }

    public void setCourseSecription(String courseSecription) {
        this.courseSecription = courseSecription;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @XmlTransient
    public Collection<StudentCourseGrade> getStudentCourseGradeCollection() {
        return studentCourseGradeCollection;
    }

    public void setStudentCourseGradeCollection(Collection<StudentCourseGrade> studentCourseGradeCollection) {
        this.studentCourseGradeCollection = studentCourseGradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.Course[ id=" + id + " ]";
    }
    
}
