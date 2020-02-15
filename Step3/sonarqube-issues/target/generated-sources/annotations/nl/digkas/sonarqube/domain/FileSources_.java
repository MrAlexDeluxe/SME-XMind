package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(FileSources.class)
public class FileSources_ { 

    public static volatile SingularAttribute<FileSources, Long> createdAt;
    public static volatile SingularAttribute<FileSources, String> dataHash;
    public static volatile SingularAttribute<FileSources, byte[]> binaryData;
    public static volatile SingularAttribute<FileSources, String> dataType;
    public static volatile SingularAttribute<FileSources, String> srcHash;
    public static volatile SingularAttribute<FileSources, Integer> id;
    public static volatile SingularAttribute<FileSources, String> lineHashes;
    public static volatile SingularAttribute<FileSources, String> fileUuid;
    public static volatile SingularAttribute<FileSources, String> projectUuid;
    public static volatile SingularAttribute<FileSources, Long> updatedAt;
    public static volatile SingularAttribute<FileSources, String> revision;

}