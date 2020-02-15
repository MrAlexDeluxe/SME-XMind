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
@Table(name = "analysis_properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnalysisProperties.findAll", query = "SELECT a FROM AnalysisProperties a")
    , @NamedQuery(name = "AnalysisProperties.findByUuid", query = "SELECT a FROM AnalysisProperties a WHERE a.uuid = :uuid")
    , @NamedQuery(name = "AnalysisProperties.findBySnapshotUuid", query = "SELECT a FROM AnalysisProperties a WHERE a.snapshotUuid = :snapshotUuid")
    , @NamedQuery(name = "AnalysisProperties.findByKee", query = "SELECT a FROM AnalysisProperties a WHERE a.kee = :kee")
    , @NamedQuery(name = "AnalysisProperties.findByTextValue", query = "SELECT a FROM AnalysisProperties a WHERE a.textValue = :textValue")
    , @NamedQuery(name = "AnalysisProperties.findByClobValue", query = "SELECT a FROM AnalysisProperties a WHERE a.clobValue = :clobValue")
    , @NamedQuery(name = "AnalysisProperties.findByIsEmpty", query = "SELECT a FROM AnalysisProperties a WHERE a.isEmpty = :isEmpty")
    , @NamedQuery(name = "AnalysisProperties.findByCreatedAt", query = "SELECT a FROM AnalysisProperties a WHERE a.createdAt = :createdAt")})
public class AnalysisProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "snapshot_uuid")
    private String snapshotUuid;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Column(name = "text_value")
    private String textValue;
    @Column(name = "clob_value")
    private String clobValue;
    @Basic(optional = false)
    @Column(name = "is_empty")
    private boolean isEmpty;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public AnalysisProperties() {
    }

    public AnalysisProperties(String uuid) {
        this.uuid = uuid;
    }

    public AnalysisProperties(String uuid, String snapshotUuid, String kee, boolean isEmpty, long createdAt) {
        this.uuid = uuid;
        this.snapshotUuid = snapshotUuid;
        this.kee = kee;
        this.isEmpty = isEmpty;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSnapshotUuid() {
        return snapshotUuid;
    }

    public void setSnapshotUuid(String snapshotUuid) {
        this.snapshotUuid = snapshotUuid;
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

    public String getClobValue() {
        return clobValue;
    }

    public void setClobValue(String clobValue) {
        this.clobValue = clobValue;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof AnalysisProperties)) {
            return false;
        }
        AnalysisProperties other = (AnalysisProperties) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.AnalysisProperties[ uuid=" + uuid + " ]";
    }
    
}
