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
@Table(name = "organizations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizations.findAll", query = "SELECT o FROM Organizations o")
    , @NamedQuery(name = "Organizations.findByUuid", query = "SELECT o FROM Organizations o WHERE o.uuid = :uuid")
    , @NamedQuery(name = "Organizations.findByKee", query = "SELECT o FROM Organizations o WHERE o.kee = :kee")
    , @NamedQuery(name = "Organizations.findByName", query = "SELECT o FROM Organizations o WHERE o.name = :name")
    , @NamedQuery(name = "Organizations.findByDescription", query = "SELECT o FROM Organizations o WHERE o.description = :description")
    , @NamedQuery(name = "Organizations.findByUrl", query = "SELECT o FROM Organizations o WHERE o.url = :url")
    , @NamedQuery(name = "Organizations.findByAvatarUrl", query = "SELECT o FROM Organizations o WHERE o.avatarUrl = :avatarUrl")
    , @NamedQuery(name = "Organizations.findByCreatedAt", query = "SELECT o FROM Organizations o WHERE o.createdAt = :createdAt")
    , @NamedQuery(name = "Organizations.findByUpdatedAt", query = "SELECT o FROM Organizations o WHERE o.updatedAt = :updatedAt")
    , @NamedQuery(name = "Organizations.findByDefaultPermTemplateProject", query = "SELECT o FROM Organizations o WHERE o.defaultPermTemplateProject = :defaultPermTemplateProject")
    , @NamedQuery(name = "Organizations.findByDefaultPermTemplateView", query = "SELECT o FROM Organizations o WHERE o.defaultPermTemplateView = :defaultPermTemplateView")
    , @NamedQuery(name = "Organizations.findByGuarded", query = "SELECT o FROM Organizations o WHERE o.guarded = :guarded")
    , @NamedQuery(name = "Organizations.findByUserId", query = "SELECT o FROM Organizations o WHERE o.userId = :userId")
    , @NamedQuery(name = "Organizations.findByDefaultGroupId", query = "SELECT o FROM Organizations o WHERE o.defaultGroupId = :defaultGroupId")
    , @NamedQuery(name = "Organizations.findByNewProjectPrivate", query = "SELECT o FROM Organizations o WHERE o.newProjectPrivate = :newProjectPrivate")})
public class Organizations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;
    @Column(name = "default_perm_template_project")
    private String defaultPermTemplateProject;
    @Column(name = "default_perm_template_view")
    private String defaultPermTemplateView;
    @Basic(optional = false)
    @Column(name = "guarded")
    private boolean guarded;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "default_group_id")
    private Integer defaultGroupId;
    @Basic(optional = false)
    @Column(name = "new_project_private")
    private boolean newProjectPrivate;

    public Organizations() {
    }

    public Organizations(String uuid) {
        this.uuid = uuid;
    }

    public Organizations(String uuid, String kee, String name, long createdAt, long updatedAt, boolean guarded, boolean newProjectPrivate) {
        this.uuid = uuid;
        this.kee = kee;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.guarded = guarded;
        this.newProjectPrivate = newProjectPrivate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getDefaultPermTemplateProject() {
        return defaultPermTemplateProject;
    }

    public void setDefaultPermTemplateProject(String defaultPermTemplateProject) {
        this.defaultPermTemplateProject = defaultPermTemplateProject;
    }

    public String getDefaultPermTemplateView() {
        return defaultPermTemplateView;
    }

    public void setDefaultPermTemplateView(String defaultPermTemplateView) {
        this.defaultPermTemplateView = defaultPermTemplateView;
    }

    public boolean getGuarded() {
        return guarded;
    }

    public void setGuarded(boolean guarded) {
        this.guarded = guarded;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDefaultGroupId() {
        return defaultGroupId;
    }

    public void setDefaultGroupId(Integer defaultGroupId) {
        this.defaultGroupId = defaultGroupId;
    }

    public boolean getNewProjectPrivate() {
        return newProjectPrivate;
    }

    public void setNewProjectPrivate(boolean newProjectPrivate) {
        this.newProjectPrivate = newProjectPrivate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizations)) {
            return false;
        }
        Organizations other = (Organizations) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.Organizations[ uuid=" + uuid + " ]";
    }
    
}
