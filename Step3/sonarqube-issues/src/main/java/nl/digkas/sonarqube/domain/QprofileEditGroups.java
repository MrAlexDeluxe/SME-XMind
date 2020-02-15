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
@Table(name = "qprofile_edit_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QprofileEditGroups.findAll", query = "SELECT q FROM QprofileEditGroups q")
    , @NamedQuery(name = "QprofileEditGroups.findByUuid", query = "SELECT q FROM QprofileEditGroups q WHERE q.uuid = :uuid")
    , @NamedQuery(name = "QprofileEditGroups.findByGroupId", query = "SELECT q FROM QprofileEditGroups q WHERE q.groupId = :groupId")
    , @NamedQuery(name = "QprofileEditGroups.findByQprofileUuid", query = "SELECT q FROM QprofileEditGroups q WHERE q.qprofileUuid = :qprofileUuid")
    , @NamedQuery(name = "QprofileEditGroups.findByCreatedAt", query = "SELECT q FROM QprofileEditGroups q WHERE q.createdAt = :createdAt")})
public class QprofileEditGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "group_id")
    private int groupId;
    @Basic(optional = false)
    @Column(name = "qprofile_uuid")
    private String qprofileUuid;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public QprofileEditGroups() {
    }

    public QprofileEditGroups(String uuid) {
        this.uuid = uuid;
    }

    public QprofileEditGroups(String uuid, int groupId, String qprofileUuid, long createdAt) {
        this.uuid = uuid;
        this.groupId = groupId;
        this.qprofileUuid = qprofileUuid;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getQprofileUuid() {
        return qprofileUuid;
    }

    public void setQprofileUuid(String qprofileUuid) {
        this.qprofileUuid = qprofileUuid;
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
        if (!(object instanceof QprofileEditGroups)) {
            return false;
        }
        QprofileEditGroups other = (QprofileEditGroups) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.QprofileEditGroups[ uuid=" + uuid + " ]";
    }
    
}
