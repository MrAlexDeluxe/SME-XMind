package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(DuplicationsIndex.class)
public class DuplicationsIndex_ { 

    public static volatile SingularAttribute<DuplicationsIndex, Integer> endLine;
    public static volatile SingularAttribute<DuplicationsIndex, Integer> startLine;
    public static volatile SingularAttribute<DuplicationsIndex, Integer> indexInFile;
    public static volatile SingularAttribute<DuplicationsIndex, Long> id;
    public static volatile SingularAttribute<DuplicationsIndex, String> analysisUuid;
    public static volatile SingularAttribute<DuplicationsIndex, String> hash;
    public static volatile SingularAttribute<DuplicationsIndex, String> componentUuid;

}