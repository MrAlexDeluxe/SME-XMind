package nl.digkas.sonarqube.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(RulesProfiles.class)
public class RulesProfiles_ { 

    public static volatile SingularAttribute<RulesProfiles, Date> createdAt;
    public static volatile SingularAttribute<RulesProfiles, String> rulesUpdatedAt;
    public static volatile SingularAttribute<RulesProfiles, String> name;
    public static volatile SingularAttribute<RulesProfiles, String> language;
    public static volatile SingularAttribute<RulesProfiles, String> kee;
    public static volatile SingularAttribute<RulesProfiles, Integer> id;
    public static volatile SingularAttribute<RulesProfiles, Boolean> isBuiltIn;
    public static volatile SingularAttribute<RulesProfiles, Date> updatedAt;

}