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
@Table(name = "ce_task_characteristics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CeTaskCharacteristics.findAll", query = "SELECT c FROM CeTaskCharacteristics c")
    , @NamedQuery(name = "CeTaskCharacteristics.findByUuid", query = "SELECT c FROM CeTaskCharacteristics c WHERE c.uuid = :uuid")
    , @NamedQuery(name = "CeTaskCharacteristics.findByTaskUuid", query = "SELECT c FROM CeTaskCharacteristics c WHERE c.taskUuid = :taskUuid")
    , @NamedQuery(name = "CeTaskCharacteristics.findByKee", query = "SELECT c FROM CeTaskCharacteristics c WHERE c.kee = :kee")
    , @NamedQuery(name = "CeTaskCharacteristics.findByTextValue", query = "SELECT c FROM CeTaskCharacteristics c WHERE c.textValue = :textValue")})
public class CeTaskCharacteristics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "task_uuid")
    private String taskUuid;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Column(name = "text_value")
    private String textValue;

    public CeTaskCharacteristics() {
    }

    public CeTaskCharacteristics(String uuid) {
        this.uuid = uuid;
    }

    public CeTaskCharacteristics(String uuid, String taskUuid, String kee) {
        this.uuid = uuid;
        this.taskUuid = taskUuid;
        this.kee = kee;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTaskUuid() {
        return taskUuid;
    }

    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
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
        if (!(object instanceof CeTaskCharacteristics)) {
            return false;
        }
        CeTaskCharacteristics other = (CeTaskCharacteristics) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.CeTaskCharacteristics[ uuid=" + uuid + " ]";
    }
    
}
