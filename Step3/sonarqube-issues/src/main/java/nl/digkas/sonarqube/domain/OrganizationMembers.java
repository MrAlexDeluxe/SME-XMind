package nl.digkas.sonarqube.domain;

import java.io.Serializable;
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
@Table(name = "organization_members")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizationMembers.findAll", query = "SELECT o FROM OrganizationMembers o")
    , @NamedQuery(name = "OrganizationMembers.findByOrganizationUuid", query = "SELECT o FROM OrganizationMembers o WHERE o.organizationMembersPK.organizationUuid = :organizationUuid")
    , @NamedQuery(name = "OrganizationMembers.findByUserId", query = "SELECT o FROM OrganizationMembers o WHERE o.organizationMembersPK.userId = :userId")})
public class OrganizationMembers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrganizationMembersPK organizationMembersPK;

    public OrganizationMembers() {
    }

    public OrganizationMembers(OrganizationMembersPK organizationMembersPK) {
        this.organizationMembersPK = organizationMembersPK;
    }

    public OrganizationMembers(String organizationUuid, int userId) {
        this.organizationMembersPK = new OrganizationMembersPK(organizationUuid, userId);
    }

    public OrganizationMembersPK getOrganizationMembersPK() {
        return organizationMembersPK;
    }

    public void setOrganizationMembersPK(OrganizationMembersPK organizationMembersPK) {
        this.organizationMembersPK = organizationMembersPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationMembersPK != null ? organizationMembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationMembers)) {
            return false;
        }
        OrganizationMembers other = (OrganizationMembers) object;
        if ((this.organizationMembersPK == null && other.organizationMembersPK != null) || (this.organizationMembersPK != null && !this.organizationMembersPK.equals(other.organizationMembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.OrganizationMembers[ organizationMembersPK=" + organizationMembersPK + " ]";
    }
    
}
