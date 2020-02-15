package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile SingularAttribute<Groups, Date> createdAt;
    public static volatile SingularAttribute<Groups, String> organizationUuid;
    public static volatile SingularAttribute<Groups, String> name;
    public static volatile SingularAttribute<Groups, String> description;
    public static volatile SingularAttribute<Groups, Integer> id;
    public static volatile SingularAttribute<Groups, Date> updatedAt;

}