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
@Table(name = "perm_templates_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermTemplatesUsers.findAll", query = "SELECT p FROM PermTemplatesUsers p")
    , @NamedQuery(name = "PermTemplatesUsers.findById", query = "SELECT p FROM PermTemplatesUsers p WHERE p.id = :id")
    , @NamedQuery(name = "PermTemplatesUsers.findByUserId", query = "SELECT p FROM PermTemplatesUsers p WHERE p.userId = :userId")
    , @NamedQuery(name = "PermTemplatesUsers.findByTemplateId", query = "SELECT p FROM PermTemplatesUsers p WHERE p.templateId = :templateId")
    , @NamedQuery(name = "PermTemplatesUsers.findByPermissionReference", query = "SELECT p FROM PermTemplatesUsers p WHERE p.permissionReference = :permissionReference")
    , @NamedQuery(name = "PermTemplatesUsers.findByCreatedAt", query = "SELECT p FROM PermTemplatesUsers p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PermTemplatesUsers.findByUpdatedAt", query = "SELECT p FROM PermTemplatesUsers p WHERE p.updatedAt = :updatedAt")})
public class PermTemplatesUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "template_id")
    private int templateId;
    @Basic(optional = false)
    @Column(name = "permission_reference")
    private String permissionReference;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public PermTemplatesUsers() {
    }

    public PermTemplatesUsers(Integer id) {
        this.id = id;
    }

    public PermTemplatesUsers(Integer id, int userId, int templateId, String permissionReference) {
        this.id = id;
        this.userId = userId;
        this.templateId = templateId;
        this.permissionReference = permissionReference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getPermissionReference() {
        return permissionReference;
    }

    public void setPermissionReference(String permissionReference) {
        this.permissionReference = permissionReference;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermTemplatesUsers)) {
            return false;
        }
        PermTemplatesUsers other = (PermTemplatesUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.PermTemplatesUsers[ id=" + id + " ]";
    }
    
}
