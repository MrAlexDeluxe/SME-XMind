package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Embeddable
public class OrganizationMembersPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public OrganizationMembersPK() {
    }

    public OrganizationMembersPK(String organizationUuid, int userId) {
        this.organizationUuid = organizationUuid;
        this.userId = userId;
    }

    public String getOrganizationUuid() {
        return organizationUuid;
    }

    public void setOrganizationUuid(String organizationUuid) {
        this.organizationUuid = organizationUuid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationUuid != null ? organizationUuid.hashCode() : 0);
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationMembersPK)) {
            return false;
        }
        OrganizationMembersPK other = (OrganizationMembersPK) object;
        if ((this.organizationUuid == null && other.organizationUuid != null) || (this.organizationUuid != null && !this.organizationUuid.equals(other.organizationUuid))) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.OrganizationMembersPK[ organizationUuid=" + organizationUuid + ", userId=" + userId + " ]";
    }
    
}
