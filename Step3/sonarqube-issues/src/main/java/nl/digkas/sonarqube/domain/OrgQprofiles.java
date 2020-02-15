package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "org_qprofiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrgQprofiles.findAll", query = "SELECT o FROM OrgQprofiles o")
    , @NamedQuery(name = "OrgQprofiles.findByUuid", query = "SELECT o FROM OrgQprofiles o WHERE o.uuid = :uuid")
    , @NamedQuery(name = "OrgQprofiles.findByOrganizationUuid", query = "SELECT o FROM OrgQprofiles o WHERE o.organizationUuid = :organizationUuid")
    , @NamedQuery(name = "OrgQprofiles.findByRulesProfileUuid", query = "SELECT o FROM OrgQprofiles o WHERE o.rulesProfileUuid = :rulesProfileUuid")
    , @NamedQuery(name = "OrgQprofiles.findByParentUuid", query = "SELECT o FROM OrgQprofiles o WHERE o.parentUuid = :parentUuid")
    , @NamedQuery(name = "OrgQprofiles.findByLastUsed", query = "SELECT o FROM OrgQprofiles o WHERE o.lastUsed = :lastUsed")
    , @NamedQuery(name = "OrgQprofiles.findByUserUpdatedAt", query = "SELECT o FROM OrgQprofiles o WHERE o.userUpdatedAt = :userUpdatedAt")
    , @NamedQuery(name = "OrgQprofiles.findByCreatedAt", query = "SELECT o FROM OrgQprofiles o WHERE o.createdAt = :createdAt")
    , @NamedQuery(name = "OrgQprofiles.findByUpdatedAt", query = "SELECT o FROM OrgQprofiles o WHERE o.updatedAt = :updatedAt")})
public class OrgQprofiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;
    @Basic(optional = false)
    @Column(name = "rules_profile_uuid")
    private String rulesProfileUuid;
    @Column(name = "parent_uuid")
    private String parentUuid;
    @Column(name = "last_used")
    private BigInteger lastUsed;
    @Column(name = "user_updated_at")
    private BigInteger userUpdatedAt;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public OrgQprofiles() {
    }

    public OrgQprofiles(String uuid) {
        this.uuid = uuid;
    }

    public OrgQprofiles(String uuid, String organizationUuid, String rulesProfileUuid, long createdAt, long updatedAt) {
        this.uuid = uuid;
        this.organizationUuid = organizationUuid;
        this.rulesProfileUuid = rulesProfileUuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrganizationUuid() {
        return organizationUuid;
    }

    public void setOrganizationUuid(String organizationUuid) {
        this.organizationUuid = organizationUuid;
    }

    public String getRulesProfileUuid() {
        return rulesProfileUuid;
    }

    public void setRulesProfileUuid(String rulesProfileUuid) {
        this.rulesProfileUuid = rulesProfileUuid;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    public BigInteger getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(BigInteger lastUsed) {
        this.lastUsed = lastUsed;
    }

    public BigInteger getUserUpdatedAt() {
        return userUpdatedAt;
    }

    public void setUserUpdatedAt(BigInteger userUpdatedAt) {
        this.userUpdatedAt = userUpdatedAt;
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
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrgQprofiles)) {
            return false;
        }
        OrgQprofiles other = (OrgQprofiles) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.OrgQprofiles[ uuid=" + uuid + " ]";
    }
    
}
