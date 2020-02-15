package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ce_activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeActivity.findAll", query = "SELECT c FROM CeActivity c")
    , @NamedQuery(name = "CeActivity.findById", query = "SELECT c FROM CeActivity c WHERE c.id = :id")
    , @NamedQuery(name = "CeActivity.findByUuid", query = "SELECT c FROM CeActivity c WHERE c.uuid = :uuid")
    , @NamedQuery(name = "CeActivity.findByTaskType", query = "SELECT c FROM CeActivity c WHERE c.taskType = :taskType")
    , @NamedQuery(name = "CeActivity.findByComponentUuid", query = "SELECT c FROM CeActivity c WHERE c.componentUuid = :componentUuid")
    , @NamedQuery(name = "CeActivity.findByStatus", query = "SELECT c FROM CeActivity c WHERE c.status = :status")
    , @NamedQuery(name = "CeActivity.findByIsLast", query = "SELECT c FROM CeActivity c WHERE c.isLast = :isLast")
    , @NamedQuery(name = "CeActivity.findByIsLastKey", query = "SELECT c FROM CeActivity c WHERE c.isLastKey = :isLastKey")
    , @NamedQuery(name = "CeActivity.findBySubmitterLogin", query = "SELECT c FROM CeActivity c WHERE c.submitterLogin = :submitterLogin")
    , @NamedQuery(name = "CeActivity.findBySubmittedAt", query = "SELECT c FROM CeActivity c WHERE c.submittedAt = :submittedAt")
    , @NamedQuery(name = "CeActivity.findByStartedAt", query = "SELECT c FROM CeActivity c WHERE c.startedAt = :startedAt")
    , @NamedQuery(name = "CeActivity.findByExecutedAt", query = "SELECT c FROM CeActivity c WHERE c.executedAt = :executedAt")
    , @NamedQuery(name = "CeActivity.findByCreatedAt", query = "SELECT c FROM CeActivity c WHERE c.createdAt = :createdAt")
    , @NamedQuery(name = "CeActivity.findByUpdatedAt", query = "SELECT c FROM CeActivity c WHERE c.updatedAt = :updatedAt")
    , @NamedQuery(name = "CeActivity.findByExecutionTimeMs", query = "SELECT c FROM CeActivity c WHERE c.executionTimeMs = :executionTimeMs")
    , @NamedQuery(name = "CeActivity.findByAnalysisUuid", query = "SELECT c FROM CeActivity c WHERE c.analysisUuid = :analysisUuid")
    , @NamedQuery(name = "CeActivity.findByErrorMessage", query = "SELECT c FROM CeActivity c WHERE c.errorMessage = :errorMessage")
    , @NamedQuery(name = "CeActivity.findByErrorStacktrace", query = "SELECT c FROM CeActivity c WHERE c.errorStacktrace = :errorStacktrace")
    , @NamedQuery(name = "CeActivity.findByWorkerUuid", query = "SELECT c FROM CeActivity c WHERE c.workerUuid = :workerUuid")
    , @NamedQuery(name = "CeActivity.findByExecutionCount", query = "SELECT c FROM CeActivity c WHERE c.executionCount = :executionCount")
    , @NamedQuery(name = "CeActivity.findByErrorType", query = "SELECT c FROM CeActivity c WHERE c.errorType = :errorType")})
public class CeActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "task_type")
    private String taskType;
    @Column(name = "component_uuid")
    private String componentUuid;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "is_last")
    private boolean isLast;
    @Basic(optional = false)
    @Column(name = "is_last_key")
    private String isLastKey;
    @Column(name = "submitter_login")
    private String submitterLogin;
    @Basic(optional = false)
    @Column(name = "submitted_at")
    private long submittedAt;
    @Column(name = "started_at")
    private BigInteger startedAt;
    @Column(name = "executed_at")
    private BigInteger executedAt;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;
    @Column(name = "execution_time_ms")
    private BigInteger executionTimeMs;
    @Column(name = "analysis_uuid")
    private String analysisUuid;
    @Column(name = "error_message")
    private String errorMessage;
    @Column(name = "error_stacktrace")
    private String errorStacktrace;
    @Column(name = "worker_uuid")
    private String workerUuid;
    @Basic(optional = false)
    @Column(name = "execution_count")
    private int executionCount;
    @Column(name = "error_type")
    private String errorType;

    public CeActivity() {
    }

    public CeActivity(Integer id) {
        this.id = id;
    }

    public CeActivity(Integer id, String uuid, String taskType, String status, boolean isLast, String isLastKey, long submittedAt, long createdAt, long updatedAt, int executionCount) {
        this.id = id;
        this.uuid = uuid;
        this.taskType = taskType;
        this.status = status;
        this.isLast = isLast;
        this.isLastKey = isLastKey;
        this.submittedAt = submittedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.executionCount = executionCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(String componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public String getIsLastKey() {
        return isLastKey;
    }

    public void setIsLastKey(String isLastKey) {
        this.isLastKey = isLastKey;
    }

    public String getSubmitterLogin() {
        return submitterLogin;
    }

    public void setSubmitterLogin(String submitterLogin) {
        this.submitterLogin = submitterLogin;
    }

    public long getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(long submittedAt) {
        this.submittedAt = submittedAt;
    }

    public BigInteger getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(BigInteger startedAt) {
        this.startedAt = startedAt;
    }

    public BigInteger getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(BigInteger executedAt) {
        this.executedAt = executedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigInteger getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(BigInteger executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getAnalysisUuid() {
        return analysisUuid;
    }

    public void setAnalysisUuid(String analysisUuid) {
        this.analysisUuid = analysisUuid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStacktrace() {
        return errorStacktrace;
    }

    public void setErrorStacktrace(String errorStacktrace) {
        this.errorStacktrace = errorStacktrace;
    }

    public String getWorkerUuid() {
        return workerUuid;
    }

    public void setWorkerUuid(String workerUuid) {
        this.workerUuid = workerUuid;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
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
        if (!(object instanceof CeActivity)) {
            return false;
        }
        CeActivity other = (CeActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.CeActivity[ id=" + id + " ]";
    }
    
}
