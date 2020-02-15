package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(EsQueue.class)
public class EsQueue_ { 

    public static volatile SingularAttribute<EsQueue, Long> createdAt;
    public static volatile SingularAttribute<EsQueue, String> docType;
    public static volatile SingularAttribute<EsQueue, String> docId;
    public static volatile SingularAttribute<EsQueue, String> docIdType;
    public static volatile SingularAttribute<EsQueue, String> uuid;
    public static volatile SingularAttribute<EsQueue, String> docRouting;

}