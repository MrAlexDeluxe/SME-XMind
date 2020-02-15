package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(ActiveRules.class)
public class ActiveRules_ { 

    public static volatile SingularAttribute<ActiveRules, BigInteger> createdAt;
    public static volatile SingularAttribute<ActiveRules, Integer> profileId;
    public static volatile SingularAttribute<ActiveRules, String> inheritance;
    public static volatile SingularAttribute<ActiveRules, Integer> id;
    public static volatile SingularAttribute<ActiveRules, Integer> ruleId;
    public static volatile SingularAttribute<ActiveRules, Integer> failureLevel;
    public static volatile SingularAttribute<ActiveRules, BigInteger> updatedAt;

}