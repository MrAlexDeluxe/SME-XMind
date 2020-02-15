package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(QualityGateConditions.class)
public class QualityGateConditions_ { 

    public static volatile SingularAttribute<QualityGateConditions, Date> createdAt;
    public static volatile SingularAttribute<QualityGateConditions, Integer> period;
    public static volatile SingularAttribute<QualityGateConditions, Integer> metricId;
    public static volatile SingularAttribute<QualityGateConditions, Integer> qgateId;
    public static volatile SingularAttribute<QualityGateConditions, String> valueWarning;
    public static volatile SingularAttribute<QualityGateConditions, Integer> id;
    public static volatile SingularAttribute<QualityGateConditions, String> valueError;
    public static volatile SingularAttribute<QualityGateConditions, String> operator;
    public static volatile SingularAttribute<QualityGateConditions, Date> updatedAt;

}