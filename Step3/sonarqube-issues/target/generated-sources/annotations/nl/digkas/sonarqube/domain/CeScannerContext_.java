package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(CeScannerContext.class)
public class CeScannerContext_ { 

    public static volatile SingularAttribute<CeScannerContext, Long> createdAt;
    public static volatile SingularAttribute<CeScannerContext, String> taskUuid;
    public static volatile SingularAttribute<CeScannerContext, byte[]> contextData;
    public static volatile SingularAttribute<CeScannerContext, Long> updatedAt;

}