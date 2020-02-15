package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Projects.class)
public class Projects_ { 

    public static volatile SingularAttribute<Projects, String> bUuidPath;
    public static volatile SingularAttribute<Projects, Boolean> bEnabled;
    public static volatile SingularAttribute<Projects, String> bName;
    public static volatile SingularAttribute<Projects, Boolean> bChanged;
    public static volatile SingularAttribute<Projects, String> bLanguage;
    public static volatile SingularAttribute<Projects, String> bQualifier;
    public static volatile SingularAttribute<Projects, String> bModuleUuid;
    public static volatile SingularAttribute<Projects, String> description;
    public static volatile SingularAttribute<Projects, String> developerUuid;
    public static volatile SingularAttribute<Projects, String> kee;
    public static volatile SingularAttribute<Projects, String> language;
    public static volatile SingularAttribute<Projects, String> uuid;
    public static volatile SingularAttribute<Projects, String> bLongName;
    public static volatile SingularAttribute<Projects, Boolean> enabled;
    public static volatile SingularAttribute<Projects, String> projectUuid;
    public static volatile SingularAttribute<Projects, Date> createdAt;
    public static volatile SingularAttribute<Projects, String> path;
    public static volatile SingularAttribute<Projects, String> bCopyComponentUuid;
    public static volatile SingularAttribute<Projects, String> mainBranchProjectUuid;
    public static volatile SingularAttribute<Projects, String> scope;
    public static volatile SingularAttribute<Projects, String> bDescription;
    public static volatile SingularAttribute<Projects, Integer> id;
    public static volatile SingularAttribute<Projects, String> deprecatedKee;
    public static volatile SingularAttribute<Projects, String> moduleUuid;
    public static volatile SingularAttribute<Projects, String> bPath;
    public static volatile SingularAttribute<Projects, Boolean> private1;
    public static volatile SingularAttribute<Projects, String> tags;
    public static volatile SingularAttribute<Projects, String> rootUuid;
    public static volatile SingularAttribute<Projects, String> organizationUuid;
    public static volatile SingularAttribute<Projects, String> bModuleUuidPath;
    public static volatile SingularAttribute<Projects, String> uuidPath;
    public static volatile SingularAttribute<Projects, String> qualifier;
    public static volatile SingularAttribute<Projects, String> name;
    public static volatile SingularAttribute<Projects, String> moduleUuidPath;
    public static volatile SingularAttribute<Projects, BigInteger> authorizationUpdatedAt;
    public static volatile SingularAttribute<Projects, String> copyComponentUuid;
    public static volatile SingularAttribute<Projects, String> longName;

}