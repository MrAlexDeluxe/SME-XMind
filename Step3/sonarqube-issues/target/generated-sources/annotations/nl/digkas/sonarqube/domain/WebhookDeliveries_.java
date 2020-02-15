package nl.digkas.sonarqube.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(WebhookDeliveries.class)
public class WebhookDeliveries_ { 

    public static volatile SingularAttribute<WebhookDeliveries, Long> createdAt;
    public static volatile SingularAttribute<WebhookDeliveries, String> errorStacktrace;
    public static volatile SingularAttribute<WebhookDeliveries, String> payload;
    public static volatile SingularAttribute<WebhookDeliveries, Boolean> success;
    public static volatile SingularAttribute<WebhookDeliveries, Integer> httpStatus;
    public static volatile SingularAttribute<WebhookDeliveries, String> name;
    public static volatile SingularAttribute<WebhookDeliveries, String> analysisUuid;
    public static volatile SingularAttribute<WebhookDeliveries, String> uuid;
    public static volatile SingularAttribute<WebhookDeliveries, String> ceTaskUuid;
    public static volatile SingularAttribute<WebhookDeliveries, Integer> durationMs;
    public static volatile SingularAttribute<WebhookDeliveries, String> componentUuid;
    public static volatile SingularAttribute<WebhookDeliveries, String> url;

}