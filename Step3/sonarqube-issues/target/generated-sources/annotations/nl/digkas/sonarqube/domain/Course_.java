package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.digkas.sonarqube.domain.Student;
import nl.digkas.sonarqube.domain.StudentCourseGrade;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, String> courseSecription;
    public static volatile CollectionAttribute<Course, StudentCourseGrade> studentCourseGradeCollection;
    public static volatile CollectionAttribute<Course, Student> studentCollection;
    public static volatile SingularAttribute<Course, Integer> semester;
    public static volatile SingularAttribute<Course, Integer> id;

}