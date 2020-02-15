package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Boolean> onboarded;
    public static volatile SingularAttribute<Users, String> salt;
    public static volatile SingularAttribute<Users, String> externalIdentity;
    public static volatile SingularAttribute<Users, Boolean> isRoot;
    public static volatile SingularAttribute<Users, Boolean> active;
    public static volatile SingularAttribute<Users, String> login;
    public static volatile SingularAttribute<Users, Boolean> userLocal;
    public static volatile SingularAttribute<Users, BigInteger> createdAt;
    public static volatile SingularAttribute<Users, String> externalIdentityProvider;
    public static volatile SingularAttribute<Users, String> cryptedPassword;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> scmAccounts;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, BigInteger> updatedAt;

}