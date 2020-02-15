package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(OrgQprofiles.class)
public class OrgQprofiles_ { 

    public static volatile SingularAttribute<OrgQprofiles, String> rulesProfileUuid;
    public static volatile SingularAttribute<OrgQprofiles, BigInteger> lastUsed;
    public static volatile SingularAttribute<OrgQprofiles, Long> createdAt;
    public static volatile SingularAttribute<OrgQprofiles, BigInteger> userUpdatedAt;
    public static volatile SingularAttribute<OrgQprofiles, String> organizationUuid;
    public static volatile SingularAttribute<OrgQprofiles, String> uuid;
    public static volatile SingularAttribute<OrgQprofiles, String> parentUuid;
    public static volatile SingularAttribute<OrgQprofiles, Long> updatedAt;

}