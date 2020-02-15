package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(PermissionTemplates.class)
public class PermissionTemplates_ { 

    public static volatile SingularAttribute<PermissionTemplates, String> keyPattern;
    public static volatile SingularAttribute<PermissionTemplates, Date> createdAt;
    public static volatile SingularAttribute<PermissionTemplates, String> organizationUuid;
    public static volatile SingularAttribute<PermissionTemplates, String> name;
    public static volatile SingularAttribute<PermissionTemplates, String> description;
    public static volatile SingularAttribute<PermissionTemplates, String> kee;
    public static volatile SingularAttribute<PermissionTemplates, Integer> id;
    public static volatile SingularAttribute<PermissionTemplates, Date> updatedAt;

}