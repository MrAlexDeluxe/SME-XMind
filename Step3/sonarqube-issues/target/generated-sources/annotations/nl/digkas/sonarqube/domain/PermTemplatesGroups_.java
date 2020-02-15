package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(PermTemplatesGroups.class)
public class PermTemplatesGroups_ { 

    public static volatile SingularAttribute<PermTemplatesGroups, Date> createdAt;
    public static volatile SingularAttribute<PermTemplatesGroups, Integer> groupId;
    public static volatile SingularAttribute<PermTemplatesGroups, Integer> id;
    public static volatile SingularAttribute<PermTemplatesGroups, String> permissionReference;
    public static volatile SingularAttribute<PermTemplatesGroups, Integer> templateId;
    public static volatile SingularAttribute<PermTemplatesGroups, Date> updatedAt;

}