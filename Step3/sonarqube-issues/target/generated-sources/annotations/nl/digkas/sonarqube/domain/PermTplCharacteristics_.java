package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(PermTplCharacteristics.class)
public class PermTplCharacteristics_ { 

    public static volatile SingularAttribute<PermTplCharacteristics, Boolean> withProjectCreator;
    public static volatile SingularAttribute<PermTplCharacteristics, Long> createdAt;
    public static volatile SingularAttribute<PermTplCharacteristics, String> permissionKey;
    public static volatile SingularAttribute<PermTplCharacteristics, Integer> id;
    public static volatile SingularAttribute<PermTplCharacteristics, Integer> templateId;
    public static volatile SingularAttribute<PermTplCharacteristics, Long> updatedAt;

}