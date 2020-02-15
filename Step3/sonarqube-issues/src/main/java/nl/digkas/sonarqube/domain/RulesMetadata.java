package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "rules_metadata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RulesMetadata.findAll", query = "SELECT r FROM RulesMetadata r")
    , @NamedQuery(name = "RulesMetadata.findByRuleId", query = "SELECT r FROM RulesMetadata r WHERE r.rulesMetadataPK.ruleId = :ruleId")
    , @NamedQuery(name = "RulesMetadata.findByOrganizationUuid", query = "SELECT r FROM RulesMetadata r WHERE r.rulesMetadataPK.organizationUuid = :organizationUuid")
    , @NamedQuery(name = "RulesMetadata.findByNoteData", query = "SELECT r FROM RulesMetadata r WHERE r.noteData = :noteData")
    , @NamedQuery(name = "RulesMetadata.findByNoteUserLogin", query = "SELECT r FROM RulesMetadata r WHERE r.noteUserLogin = :noteUserLogin")
    , @NamedQuery(name = "RulesMetadata.findByNoteCreatedAt", query = "SELECT r FROM RulesMetadata r WHERE r.noteCreatedAt = :noteCreatedAt")
    , @NamedQuery(name = "RulesMetadata.findByNoteUpdatedAt", query = "SELECT r FROM RulesMetadata r WHERE r.noteUpdatedAt = :noteUpdatedAt")
    , @NamedQuery(name = "RulesMetadata.findByRemediationFunction", query = "SELECT r FROM RulesMetadata r WHERE r.remediationFunction = :remediationFunction")
    , @NamedQuery(name = "RulesMetadata.findByRemediationGapMult", query = "SELECT r FROM RulesMetadata r WHERE r.remediationGapMult = :remediationGapMult")
    , @NamedQuery(name = "RulesMetadata.findByRemediationBaseEffort", query = "SELECT r FROM RulesMetadata r WHERE r.remediationBaseEffort = :remediationBaseEffort")
    , @NamedQuery(name = "RulesMetadata.findByTags", query = "SELECT r FROM RulesMetadata r WHERE r.tags = :tags")
    , @NamedQuery(name = "RulesMetadata.findByCreatedAt", query = "SELECT r FROM RulesMetadata r WHERE r.createdAt = :createdAt")
    , @NamedQuery(name = "RulesMetadata.findByUpdatedAt", query = "SELECT r FROM RulesMetadata r WHERE r.updatedAt = :updatedAt")})
public class RulesMetadata implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RulesMetadataPK rulesMetadataPK;
    @Column(name = "note_data")
    private String noteData;
    @Column(name = "note_user_login")
    private String noteUserLogin;
    @Column(name = "note_created_at")
    private BigInteger noteCreatedAt;
    @Column(name = "note_updated_at")
    private BigInteger noteUpdatedAt;
    @Column(name = "remediation_function")
    private String remediationFunction;
    @Column(name = "remediation_gap_mult")
    private String remediationGapMult;
    @Column(name = "remediation_base_effort")
    private String remediationBaseEffort;
    @Column(name = "tags")
    private String tags;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public RulesMetadata() {
    }

    public RulesMetadata(RulesMetadataPK rulesMetadataPK) {
        this.rulesMetadataPK = rulesMetadataPK;
    }

    public RulesMetadata(RulesMetadataPK rulesMetadataPK, long createdAt, long updatedAt) {
        this.rulesMetadataPK = rulesMetadataPK;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RulesMetadata(int ruleId, String organizationUuid) {
        this.rulesMetadataPK = new RulesMetadataPK(ruleId, organizationUuid);
    }

    public RulesMetadataPK getRulesMetadataPK() {
        return rulesMetadataPK;
    }

    public void setRulesMetadataPK(RulesMetadataPK rulesMetadataPK) {
        this.rulesMetadataPK = rulesMetadataPK;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public String getNoteUserLogin() {
        return noteUserLogin;
    }

    public void setNoteUserLogin(String noteUserLogin) {
        this.noteUserLogin = noteUserLogin;
    }

    public BigInteger getNoteCreatedAt() {
        return noteCreatedAt;
    }

    public void setNoteCreatedAt(BigInteger noteCreatedAt) {
        this.noteCreatedAt = noteCreatedAt;
    }

    public BigInteger getNoteUpdatedAt() {
        return noteUpdatedAt;
    }

    public void setNoteUpdatedAt(BigInteger noteUpdatedAt) {
        this.noteUpdatedAt = noteUpdatedAt;
    }

    public String getRemediationFunction() {
        return remediationFunction;
    }

    public void setRemediationFunction(String remediationFunction) {
        this.remediationFunction = remediationFunction;
    }

    public String getRemediationGapMult() {
        return remediationGapMult;
    }

    public void setRemediationGapMult(String remediationGapMult) {
        this.remediationGapMult = remediationGapMult;
    }

    public String getRemediationBaseEffort() {
        return remediationBaseEffort;
    }

    public void setRemediationBaseEffort(String remediationBaseEffort) {
        this.remediationBaseEffort = remediationBaseEffort;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
        hash += (rulesMetadataPK != null ? rulesMetadataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RulesMetadata)) {
            return false;
        }
        RulesMetadata other = (RulesMetadata) object;
        if ((this.rulesMetadataPK == null && other.rulesMetadataPK != null) || (this.rulesMetadataPK != null && !this.rulesMetadataPK.equals(other.rulesMetadataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.RulesMetadata[ rulesMetadataPK=" + rulesMetadataPK + " ]";
    }
    
}
