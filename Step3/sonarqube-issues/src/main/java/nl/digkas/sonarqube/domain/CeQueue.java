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
@Table(name = "ce_queue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeQueue.findAll", query = "SELECT c FROM CeQueue c")
    , @NamedQuery(name = "CeQueue.findById", query = "SELECT c FROM CeQueue c WHERE c.id = :id")
    , @NamedQuery(name = "CeQueue.findByUuid", query = "SELECT c FROM CeQueue c WHERE c.uuid = :uuid")
    , @NamedQuery(name = "CeQueue.findByTaskType", query = "SELECT c FROM CeQueue c WHERE c.taskType = :taskType")
    , @NamedQuery(name = "CeQueue.findByComponentUuid", query = "SELECT c FROM CeQueue c WHERE c.componentUuid = :componentUuid")
    , @NamedQuery(name = "CeQueue.findByStatus", query = "SELECT c FROM CeQueue c WHERE c.status = :status")
    , @NamedQuery(name = "CeQueue.findBySubmitterLogin", query = "SELECT c FROM CeQueue c WHERE c.submitterLogin = :submitterLogin")
    , @NamedQuery(name = "CeQueue.findByStartedAt", query = "SELECT c FROM CeQueue c WHERE c.startedAt = :startedAt")
    , @NamedQuery(name = "CeQueue.findByCreatedAt", query = "SELECT c FROM CeQueue c WHERE c.createdAt = :createdAt")
    , @NamedQuery(name = "CeQueue.findByUpdatedAt", query = "SELECT c FROM CeQueue c WHERE c.updatedAt = :updatedAt")
    , @NamedQuery(name = "CeQueue.findByWorkerUuid", query = "SELECT c FROM CeQueue c WHERE c.workerUuid = :workerUuid")
    , @NamedQuery(name = "CeQueue.findByExecutionCount", query = "SELECT c FROM CeQueue c WHERE c.executionCount = :executionCount")})
public class CeQueue implements Serializable {

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
    @Column(name = "status")
    private String status;
    @Column(name = "submitter_login")
    private String submitterLogin;
    @Column(name = "started_at")
    private BigInteger startedAt;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;
    @Column(name = "worker_uuid")
    private String workerUuid;
    @Basic(optional = false)
    @Column(name = "execution_count")
    private int executionCount;

    public CeQueue() {
    }

    public CeQueue(Integer id) {
        this.id = id;
    }

    public CeQueue(Integer id, String uuid, String taskType, long createdAt, long updatedAt, int executionCount) {
        this.id = id;
        this.uuid = uuid;
        this.taskType = taskType;
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

    public String getSubmitterLogin() {
        return submitterLogin;
    }

    public void setSubmitterLogin(String submitterLogin) {
        this.submitterLogin = submitterLogin;
    }

    public BigInteger getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(BigInteger startedAt) {
        this.startedAt = startedAt;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CeQueue)) {
            return false;
        }
        CeQueue other = (CeQueue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.CeQueue[ id=" + id + " ]";
    }
    
}
