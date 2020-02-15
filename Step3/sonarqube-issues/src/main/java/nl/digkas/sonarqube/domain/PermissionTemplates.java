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
@Table(name = "permission_templates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermissionTemplates.findAll", query = "SELECT p FROM PermissionTemplates p")
    , @NamedQuery(name = "PermissionTemplates.findById", query = "SELECT p FROM PermissionTemplates p WHERE p.id = :id")
    , @NamedQuery(name = "PermissionTemplates.findByName", query = "SELECT p FROM PermissionTemplates p WHERE p.name = :name")
    , @NamedQuery(name = "PermissionTemplates.findByKee", query = "SELECT p FROM PermissionTemplates p WHERE p.kee = :kee")
    , @NamedQuery(name = "PermissionTemplates.findByDescription", query = "SELECT p FROM PermissionTemplates p WHERE p.description = :description")
    , @NamedQuery(name = "PermissionTemplates.findByCreatedAt", query = "SELECT p FROM PermissionTemplates p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PermissionTemplates.findByUpdatedAt", query = "SELECT p FROM PermissionTemplates p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "PermissionTemplates.findByKeyPattern", query = "SELECT p FROM PermissionTemplates p WHERE p.keyPattern = :keyPattern")
    , @NamedQuery(name = "PermissionTemplates.findByOrganizationUuid", query = "SELECT p FROM PermissionTemplates p WHERE p.organizationUuid = :organizationUuid")})
public class PermissionTemplates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "key_pattern")
    private String keyPattern;
    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;

    public PermissionTemplates() {
    }

    public PermissionTemplates(Integer id) {
        this.id = id;
    }

    public PermissionTemplates(Integer id, String name, String kee, String organizationUuid) {
        this.id = id;
        this.name = name;
        this.kee = kee;
        this.organizationUuid = organizationUuid;
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

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getKeyPattern() {
        return keyPattern;
    }

    public void setKeyPattern(String keyPattern) {
        this.keyPattern = keyPattern;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionTemplates)) {
            return false;
        }
        PermissionTemplates other = (PermissionTemplates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.PermissionTemplates[ id=" + id + " ]";
    }
    
}
