package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.digkas.sonarqube.domain.DefaultQprofilesPK;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(DefaultQprofiles.class)
public class DefaultQprofiles_ { 

    public static volatile SingularAttribute<DefaultQprofiles, Long> createdAt;
    public static volatile SingularAttribute<DefaultQprofiles, String> qprofileUuid;
    public static volatile SingularAttribute<DefaultQprofiles, DefaultQprofilesPK> defaultQprofilesPK;
    public static volatile SingularAttribute<DefaultQprofiles, Long> updatedAt;

}