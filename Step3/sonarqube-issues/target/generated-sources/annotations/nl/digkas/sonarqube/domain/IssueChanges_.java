package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(IssueChanges.class)
public class IssueChanges_ { 

    public static volatile SingularAttribute<IssueChanges, String> userLogin;
    public static volatile SingularAttribute<IssueChanges, BigInteger> createdAt;
    public static volatile SingularAttribute<IssueChanges, BigInteger> issueChangeCreationDate;
    public static volatile SingularAttribute<IssueChanges, String> issueKey;
    public static volatile SingularAttribute<IssueChanges, String> changeType;
    public static volatile SingularAttribute<IssueChanges, String> kee;
    public static volatile SingularAttribute<IssueChanges, Long> id;
    public static volatile SingularAttribute<IssueChanges, String> changeData;
    public static volatile SingularAttribute<IssueChanges, BigInteger> updatedAt;

}