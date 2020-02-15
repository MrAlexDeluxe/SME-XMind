package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "project_measures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectMeasures.findAll", query = "SELECT p FROM ProjectMeasures p")
    , @NamedQuery(name = "ProjectMeasures.findById", query = "SELECT p FROM ProjectMeasures p WHERE p.id = :id")
    , @NamedQuery(name = "ProjectMeasures.findByValue", query = "SELECT p FROM ProjectMeasures p WHERE p.value = :value")
    , @NamedQuery(name = "ProjectMeasures.findByMetricId", query = "SELECT p FROM ProjectMeasures p WHERE p.metricId = :metricId")
    , @NamedQuery(name = "ProjectMeasures.findByTextValue", query = "SELECT p FROM ProjectMeasures p WHERE p.textValue = :textValue")
    , @NamedQuery(name = "ProjectMeasures.findByAlertStatus", query = "SELECT p FROM ProjectMeasures p WHERE p.alertStatus = :alertStatus")
    , @NamedQuery(name = "ProjectMeasures.findByAlertText", query = "SELECT p FROM ProjectMeasures p WHERE p.alertText = :alertText")
    , @NamedQuery(name = "ProjectMeasures.findByDescription", query = "SELECT p FROM ProjectMeasures p WHERE p.description = :description")
    , @NamedQuery(name = "ProjectMeasures.findByPersonId", query = "SELECT p FROM ProjectMeasures p WHERE p.personId = :personId")
    , @NamedQuery(name = "ProjectMeasures.findByVariationValue1", query = "SELECT p FROM ProjectMeasures p WHERE p.variationValue1 = :variationValue1")
    , @NamedQuery(name = "ProjectMeasures.findByVariationValue2", query = "SELECT p FROM ProjectMeasures p WHERE p.variationValue2 = :variationValue2")
    , @NamedQuery(name = "ProjectMeasures.findByVariationValue3", query = "SELECT p FROM ProjectMeasures p WHERE p.variationValue3 = :variationValue3")
    , @NamedQuery(name = "ProjectMeasures.findByVariationValue4", query = "SELECT p FROM ProjectMeasures p WHERE p.variationValue4 = :variationValue4")
    , @NamedQuery(name = "ProjectMeasures.findByVariationValue5", query = "SELECT p FROM ProjectMeasures p WHERE p.variationValue5 = :variationValue5")
    , @NamedQuery(name = "ProjectMeasures.findByComponentUuid", query = "SELECT p FROM ProjectMeasures p WHERE p.componentUuid = :componentUuid")
    , @NamedQuery(name = "ProjectMeasures.findByAnalysisUuid", query = "SELECT p FROM ProjectMeasures p WHERE p.analysisUuid = :analysisUuid")})
public class ProjectMeasures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value")
    private BigDecimal value;
    @Basic(optional = false)
    @Column(name = "metric_id")
    private int metricId;
    @Column(name = "text_value")
    private String textValue;
    @Column(name = "alert_status")
    private String alertStatus;
    @Column(name = "alert_text")
    private String alertText;
    @Column(name = "description")
    private String description;
    @Column(name = "person_id")
    private Integer personId;
    @Column(name = "variation_value_1")
    private BigDecimal variationValue1;
    @Column(name = "variation_value_2")
    private BigDecimal variationValue2;
    @Column(name = "variation_value_3")
    private BigDecimal variationValue3;
    @Column(name = "variation_value_4")
    private BigDecimal variationValue4;
    @Column(name = "variation_value_5")
    private BigDecimal variationValue5;
    @Lob
    @Column(name = "measure_data")
    private byte[] measureData;
    @Basic(optional = false)
    @Column(name = "component_uuid")
    private String componentUuid;
    @Basic(optional = false)
    @Column(name = "analysis_uuid")
    private String analysisUuid;

    public ProjectMeasures() {
    }

    public ProjectMeasures(Long id) {
        this.id = id;
    }

    public ProjectMeasures(Long id, int metricId, String componentUuid, String analysisUuid) {
        this.id = id;
        this.metricId = metricId;
        this.componentUuid = componentUuid;
        this.analysisUuid = analysisUuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }

    public String getAlertText() {
        return alertText;
    }

    public void setAlertText(String alertText) {
        this.alertText = alertText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public BigDecimal getVariationValue1() {
        return variationValue1;
    }

    public void setVariationValue1(BigDecimal variationValue1) {
        this.variationValue1 = variationValue1;
    }

    public BigDecimal getVariationValue2() {
        return variationValue2;
    }

    public void setVariationValue2(BigDecimal variationValue2) {
        this.variationValue2 = variationValue2;
    }

    public BigDecimal getVariationValue3() {
        return variationValue3;
    }

    public void setVariationValue3(BigDecimal variationValue3) {
        this.variationValue3 = variationValue3;
    }

    public BigDecimal getVariationValue4() {
        return variationValue4;
    }

    public void setVariationValue4(BigDecimal variationValue4) {
        this.variationValue4 = variationValue4;
    }

    public BigDecimal getVariationValue5() {
        return variationValue5;
    }

    public void setVariationValue5(BigDecimal variationValue5) {
        this.variationValue5 = variationValue5;
    }

    public byte[] getMeasureData() {
        return measureData;
    }

    public void setMeasureData(byte[] measureData) {
        this.measureData = measureData;
    }

    public String getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(String componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getAnalysisUuid() {
        return analysisUuid;
    }

    public void setAnalysisUuid(String analysisUuid) {
        this.analysisUuid = analysisUuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectMeasures)) {
            return false;
        }
        ProjectMeasures other = (ProjectMeasures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.ProjectMeasures[ id=" + id + " ]";
    }
    
}
