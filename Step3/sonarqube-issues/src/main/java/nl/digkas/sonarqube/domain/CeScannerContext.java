package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ce_scanner_context")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeScannerContext.findAll", query = "SELECT c FROM CeScannerContext c")
    , @NamedQuery(name = "CeScannerContext.findByTaskUuid", query = "SELECT c FROM CeScannerContext c WHERE c.taskUuid = :taskUuid")
    , @NamedQuery(name = "CeScannerContext.findByCreatedAt", query = "SELECT c FROM CeScannerContext c WHERE c.createdAt = :createdAt")
    , @NamedQuery(name = "CeScannerContext.findByUpdatedAt", query = "SELECT c FROM CeScannerContext c WHERE c.updatedAt = :updatedAt")})
public class CeScannerContext implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "task_uuid")
    private String taskUuid;
    @Basic(optional = false)
    @Lob
    @Column(name = "context_data")
    private byte[] contextData;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public CeScannerContext() {
    }

    public CeScannerContext(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public CeScannerContext(String taskUuid, byte[] contextData, long createdAt, long updatedAt) {
        this.taskUuid = taskUuid;
        this.contextData = contextData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTaskUuid() {
        return taskUuid;
    }

    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public byte[] getContextData() {
        return contextData;
    }

    public void setContextData(byte[] contextData) {
        this.contextData = contextData;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskUuid != null ? taskUuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CeScannerContext)) {
            return false;
        }
        CeScannerContext other = (CeScannerContext) object;
        if ((this.taskUuid == null && other.taskUuid != null) || (this.taskUuid != null && !this.taskUuid.equals(other.taskUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.CeScannerContext[ taskUuid=" + taskUuid + " ]";
    }
    
}
