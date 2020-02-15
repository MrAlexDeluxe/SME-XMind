package nl.digkas.sonarqube.domain;

import java.io.Serializable;
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
@Table(name = "perm_tpl_characteristics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermTplCharacteristics.findAll", query = "SELECT p FROM PermTplCharacteristics p")
    , @NamedQuery(name = "PermTplCharacteristics.findById", query = "SELECT p FROM PermTplCharacteristics p WHERE p.id = :id")
    , @NamedQuery(name = "PermTplCharacteristics.findByTemplateId", query = "SELECT p FROM PermTplCharacteristics p WHERE p.templateId = :templateId")
    , @NamedQuery(name = "PermTplCharacteristics.findByPermissionKey", query = "SELECT p FROM PermTplCharacteristics p WHERE p.permissionKey = :permissionKey")
    , @NamedQuery(name = "PermTplCharacteristics.findByWithProjectCreator", query = "SELECT p FROM PermTplCharacteristics p WHERE p.withProjectCreator = :withProjectCreator")
    , @NamedQuery(name = "PermTplCharacteristics.findByCreatedAt", query = "SELECT p FROM PermTplCharacteristics p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PermTplCharacteristics.findByUpdatedAt", query = "SELECT p FROM PermTplCharacteristics p WHERE p.updatedAt = :updatedAt")})
public class PermTplCharacteristics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "template_id")
    private int templateId;
    @Basic(optional = false)
    @Column(name = "permission_key")
    private String permissionKey;
    @Basic(optional = false)
    @Column(name = "with_project_creator")
    private boolean withProjectCreator;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public PermTplCharacteristics() {
    }

    public PermTplCharacteristics(Integer id) {
        this.id = id;
    }

    public PermTplCharacteristics(Integer id, int templateId, String permissionKey, boolean withProjectCreator, long createdAt, long updatedAt) {
        this.id = id;
        this.templateId = templateId;
        this.permissionKey = permissionKey;
        this.withProjectCreator = withProjectCreator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public boolean getWithProjectCreator() {
        return withProjectCreator;
    }

    public void setWithProjectCreator(boolean withProjectCreator) {
        this.withProjectCreator = withProjectCreator;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermTplCharacteristics)) {
            return false;
        }
        PermTplCharacteristics other = (PermTplCharacteristics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.PermTplCharacteristics[ id=" + id + " ]";
    }
    
}
