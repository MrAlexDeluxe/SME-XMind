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
public class RulesMetadataPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "rule_id")
    private int ruleId;
    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;

    public RulesMetadataPK() {
    }

    public RulesMetadataPK(int ruleId, String organizationUuid) {
        this.ruleId = ruleId;
        this.organizationUuid = organizationUuid;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getOrganizationUuid() {
        return organizationUuid;
    }

    public void setOrganizationUuid(String organizationUuid) {
        this.organizationUuid = organizationUuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ruleId;
        hash += (organizationUuid != null ? organizationUuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RulesMetadataPK)) {
            return false;
        }
        RulesMetadataPK other = (RulesMetadataPK) object;
        if (this.ruleId != other.ruleId) {
            return false;
        }
        if ((this.organizationUuid == null && other.organizationUuid != null) || (this.organizationUuid != null && !this.organizationUuid.equals(other.organizationUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.RulesMetadataPK[ ruleId=" + ruleId + ", organizationUuid=" + organizationUuid + " ]";
    }
    
}
