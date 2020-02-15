package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Events.class)
public class Events_ { 

    public static volatile SingularAttribute<Events, Long> createdAt;
    public static volatile SingularAttribute<Events, String> eventData;
    public static volatile SingularAttribute<Events, String> name;
    public static volatile SingularAttribute<Events, String> description;
    public static volatile SingularAttribute<Events, Integer> id;
    public static volatile SingularAttribute<Events, String> category;
    public static volatile SingularAttribute<Events, String> analysisUuid;
    public static volatile SingularAttribute<Events, String> uuid;
    public static volatile SingularAttribute<Events, String> componentUuid;
    public static volatile SingularAttribute<Events, Long> eventDate;

}