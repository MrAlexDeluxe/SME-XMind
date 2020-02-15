package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(QualityGates.class)
public class QualityGates_ { 

    public static volatile SingularAttribute<QualityGates, Date> createdAt;
    public static volatile SingularAttribute<QualityGates, String> name;
    public static volatile SingularAttribute<QualityGates, Integer> id;
    public static volatile SingularAttribute<QualityGates, Date> updatedAt;

}