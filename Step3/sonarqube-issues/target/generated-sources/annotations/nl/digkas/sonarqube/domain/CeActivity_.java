package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(CeActivity.class)
public class CeActivity_ { 

    public static volatile SingularAttribute<CeActivity, String> workerUuid;
    public static volatile SingularAttribute<CeActivity, String> errorStacktrace;
    public static volatile SingularAttribute<CeActivity, BigInteger> executedAt;
    public static volatile SingularAttribute<CeActivity, Boolean> isLast;
    public static volatile SingularAttribute<CeActivity, String> errorType;
    public static volatile SingularAttribute<CeActivity, BigInteger> executionTimeMs;
    public static volatile SingularAttribute<CeActivity, String> errorMessage;
    public static volatile SingularAttribute<CeActivity, BigInteger> startedAt;
    public static volatile SingularAttribute<CeActivity, String> uuid;
    public static volatile SingularAttribute<CeActivity, String> componentUuid;
    public static volatile SingularAttribute<CeActivity, String> submitterLogin;
    public static volatile SingularAttribute<CeActivity, Long> createdAt;
    public static volatile SingularAttribute<CeActivity, String> taskType;
    public static volatile SingularAttribute<CeActivity, Integer> executionCount;
    public static volatile SingularAttribute<CeActivity, Integer> id;
    public static volatile SingularAttribute<CeActivity, Long> submittedAt;
    public static volatile SingularAttribute<CeActivity, String> analysisUuid;
    public static volatile SingularAttribute<CeActivity, String> isLastKey;
    public static volatile SingularAttribute<CeActivity, String> status;
    public static volatile SingularAttribute<CeActivity, Long> updatedAt;

}