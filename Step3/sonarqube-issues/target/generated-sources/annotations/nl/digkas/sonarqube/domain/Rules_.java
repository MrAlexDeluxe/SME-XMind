package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Rules.class)
public class Rules_ { 

    public static volatile SingularAttribute<Rules, String> pluginRuleKey;
    public static volatile SingularAttribute<Rules, String> defRemediationFunction;
    public static volatile SingularAttribute<Rules, String> description;
    public static volatile SingularAttribute<Rules, String> language;
    public static volatile SingularAttribute<Rules, String> pluginKey;
    public static volatile SingularAttribute<Rules, Integer> priority;
    public static volatile SingularAttribute<Rules, Integer> templateId;
    public static volatile SingularAttribute<Rules, String> pluginConfigKey;
    public static volatile SingularAttribute<Rules, BigInteger> createdAt;
    public static volatile SingularAttribute<Rules, String> defRemediationGapMult;
    public static volatile SingularAttribute<Rules, String> pluginName;
    public static volatile SingularAttribute<Rules, Boolean> isTemplate;
    public static volatile SingularAttribute<Rules, Short> ruleType;
    public static volatile SingularAttribute<Rules, String> name;
    public static volatile SingularAttribute<Rules, String> systemTags;
    public static volatile SingularAttribute<Rules, String> defRemediationBaseEffort;
    public static volatile SingularAttribute<Rules, Integer> id;
    public static volatile SingularAttribute<Rules, String> descriptionFormat;
    public static volatile SingularAttribute<Rules, String> status;
    public static volatile SingularAttribute<Rules, String> gapDescription;
    public static volatile SingularAttribute<Rules, BigInteger> updatedAt;

}