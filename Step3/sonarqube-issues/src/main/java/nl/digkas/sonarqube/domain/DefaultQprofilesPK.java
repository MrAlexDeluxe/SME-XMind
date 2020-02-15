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
public class DefaultQprofilesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;
    @Basic(optional = false)
    @Column(name = "language")
    private String language;

    public DefaultQprofilesPK() {
    }

    public DefaultQprofilesPK(String organizationUuid, String language) {
        this.organizationUuid = organizationUuid;
        this.language = language;
    }

    public String getOrganizationUuid() {
        return organizationUuid;
    }

    public void setOrganizationUuid(String organizationUuid) {
        this.organizationUuid = organizationUuid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationUuid != null ? organizationUuid.hashCode() : 0);
        hash += (language != null ? language.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DefaultQprofilesPK)) {
            return false;
        }
        DefaultQprofilesPK other = (DefaultQprofilesPK) object;
        if ((this.organizationUuid == null && other.organizationUuid != null) || (this.organizationUuid != null && !this.organizationUuid.equals(other.organizationUuid))) {
            return false;
        }
        if ((this.language == null && other.language != null) || (this.language != null && !this.language.equals(other.language))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.DefaultQprofilesPK[ organizationUuid=" + organizationUuid + ", language=" + language + " ]";
    }
    
}
