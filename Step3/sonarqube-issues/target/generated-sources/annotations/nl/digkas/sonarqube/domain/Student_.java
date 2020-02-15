package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.digkas.sonarqube.domain.Course;
import nl.digkas.sonarqube.domain.StudentCourseGrade;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, String> studentSurname;
    public static volatile SingularAttribute<Student, String> studentName;
    public static volatile CollectionAttribute<Student, Course> courseCollection;
    public static volatile CollectionAttribute<Student, StudentCourseGrade> studentCourseGradeCollection;
    public static volatile SingularAttribute<Student, Integer> id;

}