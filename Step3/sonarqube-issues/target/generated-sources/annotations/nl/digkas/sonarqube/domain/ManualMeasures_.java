package nl.digkas.sonarqube.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(ManualMeasures.class)
public class ManualMeasures_ { 

    public static volatile SingularAttribute<ManualMeasures, String> userLogin;
    public static volatile SingularAttribute<ManualMeasures, BigInteger> createdAt;
    public static volatile SingularAttribute<ManualMeasures, Integer> metricId;
    public static volatile SingularAttribute<ManualMeasures, String> textValue;
    public static volatile SingularAttribute<ManualMeasures, String> description;
    public static volatile SingularAttribute<ManualMeasures, Long> id;
    public static volatile SingularAttribute<ManualMeasures, BigDecimal> value;
    public static volatile SingularAttribute<ManualMeasures, String> componentUuid;
    public static volatile SingularAttribute<ManualMeasures, BigInteger> updatedAt;

}