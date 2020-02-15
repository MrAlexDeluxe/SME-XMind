package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "quality_gate_conditions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QualityGateConditions.findAll", query = "SELECT q FROM QualityGateConditions q")
    , @NamedQuery(name = "QualityGateConditions.findById", query = "SELECT q FROM QualityGateConditions q WHERE q.id = :id")
    , @NamedQuery(name = "QualityGateConditions.findByQgateId", query = "SELECT q FROM QualityGateConditions q WHERE q.qgateId = :qgateId")
    , @NamedQuery(name = "QualityGateConditions.findByMetricId", query = "SELECT q FROM QualityGateConditions q WHERE q.metricId = :metricId")
    , @NamedQuery(name = "QualityGateConditions.findByPeriod", query = "SELECT q FROM QualityGateConditions q WHERE q.period = :period")
    , @NamedQuery(name = "QualityGateConditions.findByOperator", query = "SELECT q FROM QualityGateConditions q WHERE q.operator = :operator")
    , @NamedQuery(name = "QualityGateConditions.findByValueError", query = "SELECT q FROM QualityGateConditions q WHERE q.valueError = :valueError")
    , @NamedQuery(name = "QualityGateConditions.findByValueWarning", query = "SELECT q FROM QualityGateConditions q WHERE q.valueWarning = :valueWarning")
    , @NamedQuery(name = "QualityGateConditions.findByCreatedAt", query = "SELECT q FROM QualityGateConditions q WHERE q.createdAt = :createdAt")
    , @NamedQuery(name = "QualityGateConditions.findByUpdatedAt", query = "SELECT q FROM QualityGateConditions q WHERE q.updatedAt = :updatedAt")})
public class QualityGateConditions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "qgate_id")
    private Integer qgateId;
    @Column(name = "metric_id")
    private Integer metricId;
    @Column(name = "period")
    private Integer period;
    @Column(name = "operator")
    private String operator;
    @Column(name = "value_error")
    private String valueError;
    @Column(name = "value_warning")
    private String valueWarning;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public QualityGateConditions() {
    }

    public QualityGateConditions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQgateId() {
        return qgateId;
    }

    public void setQgateId(Integer qgateId) {
        this.qgateId = qgateId;
    }

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValueError() {
        return valueError;
    }

    public void setValueError(String valueError) {
        this.valueError = valueError;
    }

    public String getValueWarning() {
        return valueWarning;
    }

    public void setValueWarning(String valueWarning) {
        this.valueWarning = valueWarning;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        if (!(object instanceof QualityGateConditions)) {
            return false;
        }
        QualityGateConditions other = (QualityGateConditions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.QualityGateConditions[ id=" + id + " ]";
    }
    
}
