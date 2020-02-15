package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "webhook_deliveries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebhookDeliveries.findAll", query = "SELECT w FROM WebhookDeliveries w")
    , @NamedQuery(name = "WebhookDeliveries.findByUuid", query = "SELECT w FROM WebhookDeliveries w WHERE w.uuid = :uuid")
    , @NamedQuery(name = "WebhookDeliveries.findByComponentUuid", query = "SELECT w FROM WebhookDeliveries w WHERE w.componentUuid = :componentUuid")
    , @NamedQuery(name = "WebhookDeliveries.findByCeTaskUuid", query = "SELECT w FROM WebhookDeliveries w WHERE w.ceTaskUuid = :ceTaskUuid")
    , @NamedQuery(name = "WebhookDeliveries.findByName", query = "SELECT w FROM WebhookDeliveries w WHERE w.name = :name")
    , @NamedQuery(name = "WebhookDeliveries.findByUrl", query = "SELECT w FROM WebhookDeliveries w WHERE w.url = :url")
    , @NamedQuery(name = "WebhookDeliveries.findBySuccess", query = "SELECT w FROM WebhookDeliveries w WHERE w.success = :success")
    , @NamedQuery(name = "WebhookDeliveries.findByHttpStatus", query = "SELECT w FROM WebhookDeliveries w WHERE w.httpStatus = :httpStatus")
    , @NamedQuery(name = "WebhookDeliveries.findByDurationMs", query = "SELECT w FROM WebhookDeliveries w WHERE w.durationMs = :durationMs")
    , @NamedQuery(name = "WebhookDeliveries.findByPayload", query = "SELECT w FROM WebhookDeliveries w WHERE w.payload = :payload")
    , @NamedQuery(name = "WebhookDeliveries.findByErrorStacktrace", query = "SELECT w FROM WebhookDeliveries w WHERE w.errorStacktrace = :errorStacktrace")
    , @NamedQuery(name = "WebhookDeliveries.findByCreatedAt", query = "SELECT w FROM WebhookDeliveries w WHERE w.createdAt = :createdAt")
    , @NamedQuery(name = "WebhookDeliveries.findByAnalysisUuid", query = "SELECT w FROM WebhookDeliveries w WHERE w.analysisUuid = :analysisUuid")})
public class WebhookDeliveries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "component_uuid")
    private String componentUuid;
    @Column(name = "ce_task_uuid")
    private String ceTaskUuid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "success")
    private boolean success;
    @Column(name = "http_status")
    private Integer httpStatus;
    @Column(name = "duration_ms")
    private Integer durationMs;
    @Basic(optional = false)
    @Column(name = "payload")
    private String payload;
    @Column(name = "error_stacktrace")
    private String errorStacktrace;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "analysis_uuid")
    private String analysisUuid;

    public WebhookDeliveries() {
    }

    public WebhookDeliveries(String uuid) {
        this.uuid = uuid;
    }

    public WebhookDeliveries(String uuid, String componentUuid, String name, String url, boolean success, String payload, long createdAt) {
        this.uuid = uuid;
        this.componentUuid = componentUuid;
        this.name = name;
        this.url = url;
        this.success = success;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(String componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getCeTaskUuid() {
        return ceTaskUuid;
    }

    public void setCeTaskUuid(String ceTaskUuid) {
        this.ceTaskUuid = ceTaskUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getErrorStacktrace() {
        return errorStacktrace;
    }

    public void setErrorStacktrace(String errorStacktrace) {
        this.errorStacktrace = errorStacktrace;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
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
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebhookDeliveries)) {
            return false;
        }
        WebhookDeliveries other = (WebhookDeliveries) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.WebhookDeliveries[ uuid=" + uuid + " ]";
    }
    
}
