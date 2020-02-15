package nl.digkas.sonarqube.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.5.v20170607-rNA", date="2020-02-15T23:06:00")
@StaticMetamodel(Metrics.class)
public class Metrics_ { 

    public static volatile SingularAttribute<Metrics, String> valType;
    public static volatile SingularAttribute<Metrics, Boolean> hidden;
    public static volatile SingularAttribute<Metrics, Integer> decimalScale;
    public static volatile SingularAttribute<Metrics, BigDecimal> worstValue;
    public static volatile SingularAttribute<Metrics, String> description;
    public static volatile SingularAttribute<Metrics, Boolean> optimizedBestValue;
    public static volatile SingularAttribute<Metrics, Boolean> enabled;
    public static volatile SingularAttribute<Metrics, Boolean> qualitative;
    public static volatile SingularAttribute<Metrics, String> domain;
    public static volatile SingularAttribute<Metrics, String> name;
    public static volatile SingularAttribute<Metrics, Integer> id;
    public static volatile SingularAttribute<Metrics, Boolean> deleteHistoricalData;
    public static volatile SingularAttribute<Metrics, String> shortName;
    public static volatile SingularAttribute<Metrics, BigDecimal> bestValue;
    public static volatile SingularAttribute<Metrics, Boolean> userManaged;
    public static volatile SingularAttribute<Metrics, Integer> direction;

}