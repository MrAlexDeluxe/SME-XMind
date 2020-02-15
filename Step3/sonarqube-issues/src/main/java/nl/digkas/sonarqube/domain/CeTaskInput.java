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
@Table(name = "ce_task_input")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeTaskInput.findAll", query = "SELECT c FROM CeTaskInput c")
    , @NamedQuery(name = "CeTaskInput.findByTaskUuid", query = "SELECT c FROM CeTaskInput c WHERE c.taskUuid = :taskUuid")
    , @NamedQuery(name = "CeTaskInput.findByCreatedAt", query = "SELECT c FROM CeTaskInput c WHERE c.createdAt = :createdAt")
    , @NamedQuery(name = "CeTaskInput.findByUpdatedAt", query = "SELECT c FROM CeTaskInput c WHERE c.updatedAt = :updatedAt")})
public class CeTaskInput implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "task_uuid")
    private String taskUuid;
    @Lob
    @Column(name = "input_data")
    private byte[] inputData;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public CeTaskInput() {
    }

    public CeTaskInput(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public CeTaskInput(String taskUuid, long createdAt, long updatedAt) {
        this.taskUuid = taskUuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTaskUuid() {
        return taskUuid;
    }

    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public byte[] getInputData() {
        return inputData;
    }

    public void setInputData(byte[] inputData) {
        this.inputData = inputData;
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
        if (!(object instanceof CeTaskInput)) {
            return false;
        }
        CeTaskInput other = (CeTaskInput) object;
        if ((this.taskUuid == null && other.taskUuid != null) || (this.taskUuid != null && !this.taskUuid.equals(other.taskUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.CeTaskInput[ taskUuid=" + taskUuid + " ]";
    }
    
}
