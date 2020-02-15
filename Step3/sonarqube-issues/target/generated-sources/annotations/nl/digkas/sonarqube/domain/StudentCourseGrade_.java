package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.digkas.sonarqube.domain.Course;
import nl.digkas.sonarqube.domain.Student;
import nl.digkas.sonarqube.domain.StudentCourseGradePK;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(StudentCourseGrade.class)
public class StudentCourseGrade_ { 

    public static volatile SingularAttribute<StudentCourseGrade, Student> student;
    public static volatile SingularAttribute<StudentCourseGrade, StudentCourseGradePK> studentCourseGradePK;
    public static volatile SingularAttribute<StudentCourseGrade, Integer> grade;
    public static volatile SingularAttribute<StudentCourseGrade, Course> course;

}