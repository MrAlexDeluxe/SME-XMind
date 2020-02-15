package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "default_qprofiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DefaultQprofiles.findAll", query = "SELECT d FROM DefaultQprofiles d")
    , @NamedQuery(name = "DefaultQprofiles.findByOrganizationUuid", query = "SELECT d FROM DefaultQprofiles d WHERE d.defaultQprofilesPK.organizationUuid = :organizationUuid")
    , @NamedQuery(name = "DefaultQprofiles.findByLanguage", query = "SELECT d FROM DefaultQprofiles d WHERE d.defaultQprofilesPK.language = :language")
    , @NamedQuery(name = "DefaultQprofiles.findByQprofileUuid", query = "SELECT d FROM DefaultQprofiles d WHERE d.qprofileUuid = :qprofileUuid")
    , @NamedQuery(name = "DefaultQprofiles.findByCreatedAt", query = "SELECT d FROM DefaultQprofiles d WHERE d.createdAt = :createdAt")
    , @NamedQuery(name = "DefaultQprofiles.findByUpdatedAt", query = "SELECT d FROM DefaultQprofiles d WHERE d.updatedAt = :updatedAt")})
public class DefaultQprofiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DefaultQprofilesPK defaultQprofilesPK;
    @Basic(optional = false)
    @Column(name = "qprofile_uuid")
    private String qprofileUuid;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public DefaultQprofiles() {
    }

    public DefaultQprofiles(DefaultQprofilesPK defaultQprofilesPK) {
        this.defaultQprofilesPK = defaultQprofilesPK;
    }

    public DefaultQprofiles(DefaultQprofilesPK defaultQprofilesPK, String qprofileUuid, long createdAt, long updatedAt) {
        this.defaultQprofilesPK = defaultQprofilesPK;
        this.qprofileUuid = qprofileUuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DefaultQprofiles(String organizationUuid, String language) {
        this.defaultQprofilesPK = new DefaultQprofilesPK(organizationUuid, language);
    }

    public DefaultQprofilesPK getDefaultQprofilesPK() {
        return defaultQprofilesPK;
    }

    public void setDefaultQprofilesPK(DefaultQprofilesPK defaultQprofilesPK) {
        this.defaultQprofilesPK = defaultQprofilesPK;
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

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (defaultQprofilesPK != null ? defaultQprofilesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DefaultQprofiles)) {
            return false;
        }
        DefaultQprofiles other = (DefaultQprofiles) object;
        if ((this.defaultQprofilesPK == null && other.defaultQprofilesPK != null) || (this.defaultQprofilesPK != null && !this.defaultQprofilesPK.equals(other.defaultQprofilesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.DefaultQprofiles[ defaultQprofilesPK=" + defaultQprofilesPK + " ]";
    }
    
}
