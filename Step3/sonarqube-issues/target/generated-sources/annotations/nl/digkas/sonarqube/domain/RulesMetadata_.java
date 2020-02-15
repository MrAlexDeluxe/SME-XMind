package nl.digkas.sonarqube.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import nl.digkas.sonarqube.domain.RulesMetadataPK;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(RulesMetadata.class)
public class RulesMetadata_ { 

    public static volatile SingularAttribute<RulesMetadata, Long> createdAt;
    public static volatile SingularAttribute<RulesMetadata, String> noteUserLogin;
    public static volatile SingularAttribute<RulesMetadata, String> remediationBaseEffort;
    public static volatile SingularAttribute<RulesMetadata, BigInteger> noteCreatedAt;
    public static volatile SingularAttribute<RulesMetadata, String> remediationGapMult;
    public static volatile SingularAttribute<RulesMetadata, RulesMetadataPK> rulesMetadataPK;
    public static volatile SingularAttribute<RulesMetadata, String> noteData;
    public static volatile SingularAttribute<RulesMetadata, String> remediationFunction;
    public static volatile SingularAttribute<RulesMetadata, BigInteger> noteUpdatedAt;
    public static volatile SingularAttribute<RulesMetadata, String> tags;
    public static volatile SingularAttribute<RulesMetadata, Long> updatedAt;

}