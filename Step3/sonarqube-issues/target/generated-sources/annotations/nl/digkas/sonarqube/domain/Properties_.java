package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Properties.class)
public class Properties_ { 

    public static volatile SingularAttribute<Properties, Long> createdAt;
    public static volatile SingularAttribute<Properties, BigInteger> resourceId;
    public static volatile SingularAttribute<Properties, String> textValue;
    public static volatile SingularAttribute<Properties, Boolean> isEmpty;
    public static volatile SingularAttribute<Properties, Integer> id;
    public static volatile SingularAttribute<Properties, String> clobValue;
    public static volatile SingularAttribute<Properties, BigInteger> userId;
    public static volatile SingularAttribute<Properties, String> propKey;

}