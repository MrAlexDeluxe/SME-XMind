package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(PermTemplatesUsers.class)
public class PermTemplatesUsers_ { 

    public static volatile SingularAttribute<PermTemplatesUsers, Date> createdAt;
    public static volatile SingularAttribute<PermTemplatesUsers, Integer> id;
    public static volatile SingularAttribute<PermTemplatesUsers, String> permissionReference;
    public static volatile SingularAttribute<PermTemplatesUsers, Integer> templateId;
    public static volatile SingularAttribute<PermTemplatesUsers, Integer> userId;
    public static volatile SingularAttribute<PermTemplatesUsers, Date> updatedAt;

}