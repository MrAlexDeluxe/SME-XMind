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
@Table(name = "qprofile_edit_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QprofileEditUsers.findAll", query = "SELECT q FROM QprofileEditUsers q")
    , @NamedQuery(name = "QprofileEditUsers.findByUuid", query = "SELECT q FROM QprofileEditUsers q WHERE q.uuid = :uuid")
    , @NamedQuery(name = "QprofileEditUsers.findByUserId", query = "SELECT q FROM QprofileEditUsers q WHERE q.userId = :userId")
    , @NamedQuery(name = "QprofileEditUsers.findByQprofileUuid", query = "SELECT q FROM QprofileEditUsers q WHERE q.qprofileUuid = :qprofileUuid")
    , @NamedQuery(name = "QprofileEditUsers.findByCreatedAt", query = "SELECT q FROM QprofileEditUsers q WHERE q.createdAt = :createdAt")})
public class QprofileEditUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "qprofile_uuid")
    private String qprofileUuid;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public QprofileEditUsers() {
    }

    public QprofileEditUsers(String uuid) {
        this.uuid = uuid;
    }

    public QprofileEditUsers(String uuid, int userId, String qprofileUuid, long createdAt) {
        this.uuid = uuid;
        this.userId = userId;
        this.qprofileUuid = qprofileUuid;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        if (!(object instanceof QprofileEditUsers)) {
            return false;
        }
        QprofileEditUsers other = (QprofileEditUsers) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.QprofileEditUsers[ uuid=" + uuid + " ]";
    }
    
}
