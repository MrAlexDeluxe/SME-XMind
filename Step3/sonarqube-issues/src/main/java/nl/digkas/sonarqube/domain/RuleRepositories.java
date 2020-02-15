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
@Table(name = "rule_repositories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuleRepositories.findAll", query = "SELECT r FROM RuleRepositories r")
    , @NamedQuery(name = "RuleRepositories.findByKee", query = "SELECT r FROM RuleRepositories r WHERE r.kee = :kee")
    , @NamedQuery(name = "RuleRepositories.findByLanguage", query = "SELECT r FROM RuleRepositories r WHERE r.language = :language")
    , @NamedQuery(name = "RuleRepositories.findByName", query = "SELECT r FROM RuleRepositories r WHERE r.name = :name")
    , @NamedQuery(name = "RuleRepositories.findByCreatedAt", query = "SELECT r FROM RuleRepositories r WHERE r.createdAt = :createdAt")})
public class RuleRepositories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Basic(optional = false)
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public RuleRepositories() {
    }

    public RuleRepositories(String kee) {
        this.kee = kee;
    }

    public RuleRepositories(String kee, String language, String name, long createdAt) {
        this.kee = kee;
        this.language = language;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (kee != null ? kee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleRepositories)) {
            return false;
        }
        RuleRepositories other = (RuleRepositories) object;
        if ((this.kee == null && other.kee != null) || (this.kee != null && !this.kee.equals(other.kee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.RuleRepositories[ kee=" + kee + " ]";
    }
    
}
