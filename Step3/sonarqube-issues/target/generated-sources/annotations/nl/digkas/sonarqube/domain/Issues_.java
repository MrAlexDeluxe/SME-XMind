package nl.digkas.sonarqube.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Issues.class)
public class Issues_ { 

    public static volatile SingularAttribute<Issues, Integer> line;
    public static volatile SingularAttribute<Issues, String> kee;
    public static volatile SingularAttribute<Issues, Integer> effort;
    public static volatile SingularAttribute<Issues, String> resolution;
    public static volatile SingularAttribute<Issues, String> projectUuid;
    public static volatile SingularAttribute<Issues, BigInteger> createdAt;
    public static volatile SingularAttribute<Issues, BigDecimal> gap;
    public static volatile SingularAttribute<Issues, String> checksum;
    public static volatile SingularAttribute<Issues, Long> id;
    public static volatile SingularAttribute<Issues, Integer> ruleId;
    public static volatile SingularAttribute<Issues, BigInteger> issueUpdateDate;
    public static volatile SingularAttribute<Issues, BigInteger> updatedAt;
    public static volatile SingularAttribute<Issues, String> severity;
    public static volatile SingularAttribute<Issues, BigInteger> issueCloseDate;
    public static volatile SingularAttribute<Issues, String> reporter;
    public static volatile SingularAttribute<Issues, String> authorLogin;
    public static volatile SingularAttribute<Issues, String> message;
    public static volatile SingularAttribute<Issues, String> actionPlanKey;
    public static volatile SingularAttribute<Issues, String> componentUuid;
    public static volatile SingularAttribute<Issues, String> tags;
    public static volatile SingularAttribute<Issues, Short> issueType;
    public static volatile SingularAttribute<Issues, String> issueAttributes;
    public static volatile SingularAttribute<Issues, BigInteger> issueCreationDate;
    public static volatile SingularAttribute<Issues, Boolean> manualSeverity;
    public static volatile SingularAttribute<Issues, byte[]> locations;
    public static volatile SingularAttribute<Issues, String> assignee;
    public static volatile SingularAttribute<Issues, String> status;

}