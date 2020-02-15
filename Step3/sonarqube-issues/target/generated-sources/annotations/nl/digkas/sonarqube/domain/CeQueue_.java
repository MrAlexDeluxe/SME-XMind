package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(CeQueue.class)
public class CeQueue_ { 

    public static volatile SingularAttribute<CeQueue, Long> createdAt;
    public static volatile SingularAttribute<CeQueue, String> workerUuid;
    public static volatile SingularAttribute<CeQueue, String> taskType;
    public static volatile SingularAttribute<CeQueue, BigInteger> startedAt;
    public static volatile SingularAttribute<CeQueue, Integer> executionCount;
    public static volatile SingularAttribute<CeQueue, Integer> id;
    public static volatile SingularAttribute<CeQueue, String> uuid;
    public static volatile SingularAttribute<CeQueue, String> componentUuid;
    public static volatile SingularAttribute<CeQueue, String> status;
    public static volatile SingularAttribute<CeQueue, String> submitterLogin;
    public static volatile SingularAttribute<CeQueue, Long> updatedAt;

}