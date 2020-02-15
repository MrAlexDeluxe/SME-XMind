package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(UserTokens.class)
public class UserTokens_ { 

    public static volatile SingularAttribute<UserTokens, Long> createdAt;
    public static volatile SingularAttribute<UserTokens, String> name;
    public static volatile SingularAttribute<UserTokens, Integer> id;
    public static volatile SingularAttribute<UserTokens, String> login;
    public static volatile SingularAttribute<UserTokens, String> tokenHash;

}