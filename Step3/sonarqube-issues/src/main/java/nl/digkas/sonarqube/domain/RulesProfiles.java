package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "rules_profiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RulesProfiles.findAll", query = "SELECT r FROM RulesProfiles r")
    , @NamedQuery(name = "RulesProfiles.findById", query = "SELECT r FROM RulesProfiles r WHERE r.id = :id")
    , @NamedQuery(name = "RulesProfiles.findByName", query = "SELECT r FROM RulesProfiles r WHERE r.name = :name")
    , @NamedQuery(name = "RulesProfiles.findByLanguage", query = "SELECT r FROM RulesProfiles r WHERE r.language = :language")
    , @NamedQuery(name = "RulesProfiles.findByKee", query = "SELECT r FROM RulesProfiles r WHERE r.kee = :kee")
    , @NamedQuery(name = "RulesProfiles.findByRulesUpdatedAt", query = "SELECT r FROM RulesProfiles r WHERE r.rulesUpdatedAt = :rulesUpdatedAt")
    , @NamedQuery(name = "RulesProfiles.findByCreatedAt", query = "SELECT r FROM RulesProfiles r WHERE r.createdAt = :createdAt")
    , @NamedQuery(name = "RulesProfiles.findByUpdatedAt", query = "SELECT r FROM RulesProfiles r WHERE r.updatedAt = :updatedAt")
    , @NamedQuery(name = "RulesProfiles.findByIsBuiltIn", query = "SELECT r FROM RulesProfiles r WHERE r.isBuiltIn = :isBuiltIn")})
public class RulesProfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Column(name = "rules_updated_at")
    private String rulesUpdatedAt;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "is_built_in")
    private boolean isBuiltIn;

    public RulesProfiles() {
    }

    public RulesProfiles(Integer id) {
        this.id = id;
    }

    public RulesProfiles(Integer id, String name, String kee, boolean isBuiltIn) {
        this.id = id;
        this.name = name;
        this.kee = kee;
        this.isBuiltIn = isBuiltIn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getRulesUpdatedAt() {
        return rulesUpdatedAt;
    }

    public void setRulesUpdatedAt(String rulesUpdatedAt) {
        this.rulesUpdatedAt = rulesUpdatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsBuiltIn() {
        return isBuiltIn;
    }

    public void setIsBuiltIn(boolean isBuiltIn) {
        this.isBuiltIn = isBuiltIn;
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
        if (!(object instanceof RulesProfiles)) {
            return false;
        }
        RulesProfiles other = (RulesProfiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.RulesProfiles[ id=" + id + " ]";
    }
    
}
