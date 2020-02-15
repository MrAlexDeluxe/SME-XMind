package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(ProjectBranches.class)
public class ProjectBranches_ { 

    public static volatile SingularAttribute<ProjectBranches, Long> createdAt;
    public static volatile SingularAttribute<ProjectBranches, String> mergeBranchUuid;
    public static volatile SingularAttribute<ProjectBranches, String> kee;
    public static volatile SingularAttribute<ProjectBranches, String> branchType;
    public static volatile SingularAttribute<ProjectBranches, String> uuid;
    public static volatile SingularAttribute<ProjectBranches, String> projectUuid;
    public static volatile SingularAttribute<ProjectBranches, Long> updatedAt;

}