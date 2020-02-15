package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "active_rules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiveRules.findAll", query = "SELECT a FROM ActiveRules a")
    , @NamedQuery(name = "ActiveRules.findById", query = "SELECT a FROM ActiveRules a WHERE a.id = :id")
    , @NamedQuery(name = "ActiveRules.findByProfileId", query = "SELECT a FROM ActiveRules a WHERE a.profileId = :profileId")
    , @NamedQuery(name = "ActiveRules.findByRuleId", query = "SELECT a FROM ActiveRules a WHERE a.ruleId = :ruleId")
    , @NamedQuery(name = "ActiveRules.findByFailureLevel", query = "SELECT a FROM ActiveRules a WHERE a.failureLevel = :failureLevel")
    , @NamedQuery(name = "ActiveRules.findByInheritance", query = "SELECT a FROM ActiveRules a WHERE a.inheritance = :inheritance")
    , @NamedQuery(name = "ActiveRules.findByCreatedAt", query = "SELECT a FROM ActiveRules a WHERE a.createdAt = :createdAt")
    , @NamedQuery(name = "ActiveRules.findByUpdatedAt", query = "SELECT a FROM ActiveRules a WHERE a.updatedAt = :updatedAt")})
public class ActiveRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "profile_id")
    private int profileId;
    @Basic(optional = false)
    @Column(name = "rule_id")
    private int ruleId;
    @Basic(optional = false)
    @Column(name = "failure_level")
    private int failureLevel;
    @Column(name = "inheritance")
    private String inheritance;
    @Column(name = "created_at")
    private BigInteger createdAt;
    @Column(name = "updated_at")
    private BigInteger updatedAt;

    public ActiveRules() {
    }

    public ActiveRules(Integer id) {
        this.id = id;
    }

    public ActiveRules(Integer id, int profileId, int ruleId, int failureLevel) {
        this.id = id;
        this.profileId = profileId;
        this.ruleId = ruleId;
        this.failureLevel = failureLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public int getFailureLevel() {
        return failureLevel;
    }

    public void setFailureLevel(int failureLevel) {
        this.failureLevel = failureLevel;
    }

    public String getInheritance() {
        return inheritance;
    }

    public void setInheritance(String inheritance) {
        this.inheritance = inheritance;
    }

    public BigInteger getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public BigInteger getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(BigInteger updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiveRules)) {
            return false;
        }
        ActiveRules other = (ActiveRules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.ActiveRules[ id=" + id + " ]";
    }
    
}
