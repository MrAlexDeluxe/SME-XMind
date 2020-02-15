package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p")
    , @NamedQuery(name = "Projects.findById", query = "SELECT p FROM Projects p WHERE p.id = :id")
    , @NamedQuery(name = "Projects.findByName", query = "SELECT p FROM Projects p WHERE p.name = :name")
    , @NamedQuery(name = "Projects.findByDescription", query = "SELECT p FROM Projects p WHERE p.description = :description")
    , @NamedQuery(name = "Projects.findByEnabled", query = "SELECT p FROM Projects p WHERE p.enabled = :enabled")
    , @NamedQuery(name = "Projects.findByScope", query = "SELECT p FROM Projects p WHERE p.scope = :scope")
    , @NamedQuery(name = "Projects.findByQualifier", query = "SELECT p FROM Projects p WHERE p.qualifier = :qualifier")
    , @NamedQuery(name = "Projects.findByKee", query = "SELECT p FROM Projects p WHERE p.kee = :kee")
    , @NamedQuery(name = "Projects.findByLanguage", query = "SELECT p FROM Projects p WHERE p.language = :language")
    , @NamedQuery(name = "Projects.findByLongName", query = "SELECT p FROM Projects p WHERE p.longName = :longName")
    , @NamedQuery(name = "Projects.findByCreatedAt", query = "SELECT p FROM Projects p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Projects.findByPath", query = "SELECT p FROM Projects p WHERE p.path = :path")
    , @NamedQuery(name = "Projects.findByDeprecatedKee", query = "SELECT p FROM Projects p WHERE p.deprecatedKee = :deprecatedKee")
    , @NamedQuery(name = "Projects.findByUuid", query = "SELECT p FROM Projects p WHERE p.uuid = :uuid")
    , @NamedQuery(name = "Projects.findByProjectUuid", query = "SELECT p FROM Projects p WHERE p.projectUuid = :projectUuid")
    , @NamedQuery(name = "Projects.findByModuleUuid", query = "SELECT p FROM Projects p WHERE p.moduleUuid = :moduleUuid")
    , @NamedQuery(name = "Projects.findByModuleUuidPath", query = "SELECT p FROM Projects p WHERE p.moduleUuidPath = :moduleUuidPath")
    , @NamedQuery(name = "Projects.findByAuthorizationUpdatedAt", query = "SELECT p FROM Projects p WHERE p.authorizationUpdatedAt = :authorizationUpdatedAt")
    , @NamedQuery(name = "Projects.findByRootUuid", query = "SELECT p FROM Projects p WHERE p.rootUuid = :rootUuid")
    , @NamedQuery(name = "Projects.findByCopyComponentUuid", query = "SELECT p FROM Projects p WHERE p.copyComponentUuid = :copyComponentUuid")
    , @NamedQuery(name = "Projects.findByDeveloperUuid", query = "SELECT p FROM Projects p WHERE p.developerUuid = :developerUuid")
    , @NamedQuery(name = "Projects.findByUuidPath", query = "SELECT p FROM Projects p WHERE p.uuidPath = :uuidPath")
    , @NamedQuery(name = "Projects.findByBChanged", query = "SELECT p FROM Projects p WHERE p.bChanged = :bChanged")
    , @NamedQuery(name = "Projects.findByBCopyComponentUuid", query = "SELECT p FROM Projects p WHERE p.bCopyComponentUuid = :bCopyComponentUuid")
    , @NamedQuery(name = "Projects.findByBDescription", query = "SELECT p FROM Projects p WHERE p.bDescription = :bDescription")
    , @NamedQuery(name = "Projects.findByBEnabled", query = "SELECT p FROM Projects p WHERE p.bEnabled = :bEnabled")
    , @NamedQuery(name = "Projects.findByBLanguage", query = "SELECT p FROM Projects p WHERE p.bLanguage = :bLanguage")
    , @NamedQuery(name = "Projects.findByBLongName", query = "SELECT p FROM Projects p WHERE p.bLongName = :bLongName")
    , @NamedQuery(name = "Projects.findByBModuleUuid", query = "SELECT p FROM Projects p WHERE p.bModuleUuid = :bModuleUuid")
    , @NamedQuery(name = "Projects.findByBModuleUuidPath", query = "SELECT p FROM Projects p WHERE p.bModuleUuidPath = :bModuleUuidPath")
    , @NamedQuery(name = "Projects.findByBName", query = "SELECT p FROM Projects p WHERE p.bName = :bName")
    , @NamedQuery(name = "Projects.findByBPath", query = "SELECT p FROM Projects p WHERE p.bPath = :bPath")
    , @NamedQuery(name = "Projects.findByBQualifier", query = "SELECT p FROM Projects p WHERE p.bQualifier = :bQualifier")
    , @NamedQuery(name = "Projects.findByBUuidPath", query = "SELECT p FROM Projects p WHERE p.bUuidPath = :bUuidPath")
    , @NamedQuery(name = "Projects.findByOrganizationUuid", query = "SELECT p FROM Projects p WHERE p.organizationUuid = :organizationUuid")
    , @NamedQuery(name = "Projects.findByTags", query = "SELECT p FROM Projects p WHERE p.tags = :tags")
    , @NamedQuery(name = "Projects.findByPrivate1", query = "SELECT p FROM Projects p WHERE p.private1 = :private1")
    , @NamedQuery(name = "Projects.findByMainBranchProjectUuid", query = "SELECT p FROM Projects p WHERE p.mainBranchProjectUuid = :mainBranchProjectUuid")
    , @NamedQuery(name = "Projects.findProjectByKee", query = "SELECT p FROM Projects p WHERE p.scope = 'PRJ' AND p.qualifier = 'TRK' AND p.kee = :kee")})
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "scope")
    private String scope;
    @Column(name = "qualifier")
    private String qualifier;
    @Column(name = "kee")
    private String kee;
    @Column(name = "language")
    private String language;
    @Column(name = "long_name")
    private String longName;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "path")
    private String path;
    @Column(name = "deprecated_kee")
    private String deprecatedKee;
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "project_uuid")
    private String projectUuid;
    @Column(name = "module_uuid")
    private String moduleUuid;
    @Column(name = "module_uuid_path")
    private String moduleUuidPath;
    @Column(name = "authorization_updated_at")
    private BigInteger authorizationUpdatedAt;
    @Basic(optional = false)
    @Column(name = "root_uuid")
    private String rootUuid;
    @Column(name = "copy_component_uuid")
    private String copyComponentUuid;
    @Column(name = "developer_uuid")
    private String developerUuid;
    @Basic(optional = false)
    @Column(name = "uuid_path")
    private String uuidPath;
    @Column(name = "b_changed")
    private Boolean bChanged;
    @Column(name = "b_copy_component_uuid")
    private String bCopyComponentUuid;
    @Column(name = "b_description")
    private String bDescription;
    @Column(name = "b_enabled")
    private Boolean bEnabled;
    @Column(name = "b_language")
    private String bLanguage;
    @Column(name = "b_long_name")
    private String bLongName;
    @Column(name = "b_module_uuid")
    private String bModuleUuid;
    @Column(name = "b_module_uuid_path")
    private String bModuleUuidPath;
    @Column(name = "b_name")
    private String bName;
    @Column(name = "b_path")
    private String bPath;
    @Column(name = "b_qualifier")
    private String bQualifier;
    @Column(name = "b_uuid_path")
    private String bUuidPath;
    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;
    @Column(name = "tags")
    private String tags;
    @Basic(optional = false)
    @Column(name = "private")
    private boolean private1;
    @Column(name = "main_branch_project_uuid")
    private String mainBranchProjectUuid;

    public Projects() {
    }

    public Projects(Integer id) {
        this.id = id;
    }

    public Projects(Integer id, boolean enabled, String uuid, String projectUuid, String rootUuid, String uuidPath, String organizationUuid, boolean private1) {
        this.id = id;
     //   this.enabled = enabled;
        this.uuid = uuid;
        this.projectUuid = projectUuid;
        this.rootUuid = rootUuid;
        this.uuidPath = uuidPath;
        this.organizationUuid = organizationUuid;
        this.private1 = private1;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
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

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDeprecatedKee() {
        return deprecatedKee;
    }

    public void setDeprecatedKee(String deprecatedKee) {
        this.deprecatedKee = deprecatedKee;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProjectUuid() {
        return projectUuid;
    }

    public void setProjectUuid(String projectUuid) {
        this.projectUuid = projectUuid;
    }

    public String getModuleUuid() {
        return moduleUuid;
    }

    public void setModuleUuid(String moduleUuid) {
        this.moduleUuid = moduleUuid;
    }

    public String getModuleUuidPath() {
        return moduleUuidPath;
    }

    public void setModuleUuidPath(String moduleUuidPath) {
        this.moduleUuidPath = moduleUuidPath;
    }

    public BigInteger getAuthorizationUpdatedAt() {
        return authorizationUpdatedAt;
    }

    public void setAuthorizationUpdatedAt(BigInteger authorizationUpdatedAt) {
        this.authorizationUpdatedAt = authorizationUpdatedAt;
    }

    public String getRootUuid() {
        return rootUuid;
    }

    public void setRootUuid(String rootUuid) {
        this.rootUuid = rootUuid;
    }

    public String getCopyComponentUuid() {
        return copyComponentUuid;
    }

    public void setCopyComponentUuid(String copyComponentUuid) {
        this.copyComponentUuid = copyComponentUuid;
    }

    public String getDeveloperUuid() {
        return developerUuid;
    }

    public void setDeveloperUuid(String developerUuid) {
        this.developerUuid = developerUuid;
    }

    public String getUuidPath() {
        return uuidPath;
    }

    public void setUuidPath(String uuidPath) {
        this.uuidPath = uuidPath;
    }

    public Boolean getBChanged() {
        return bChanged;
    }

    public void setBChanged(Boolean bChanged) {
        this.bChanged = bChanged;
    }

    public String getBCopyComponentUuid() {
        return bCopyComponentUuid;
    }

    public void setBCopyComponentUuid(String bCopyComponentUuid) {
        this.bCopyComponentUuid = bCopyComponentUuid;
    }

    public String getBDescription() {
        return bDescription;
    }

    public void setBDescription(String bDescription) {
        this.bDescription = bDescription;
    }

    public Boolean getBEnabled() {
        return bEnabled;
    }

    public void setBEnabled(Boolean bEnabled) {
        this.bEnabled = bEnabled;
    }

    public String getBLanguage() {
        return bLanguage;
    }

    public void setBLanguage(String bLanguage) {
        this.bLanguage = bLanguage;
    }

    public String getBLongName() {
        return bLongName;
    }

    public void setBLongName(String bLongName) {
        this.bLongName = bLongName;
    }

    public String getBModuleUuid() {
        return bModuleUuid;
    }

    public void setBModuleUuid(String bModuleUuid) {
        this.bModuleUuid = bModuleUuid;
    }

    public String getBModuleUuidPath() {
        return bModuleUuidPath;
    }

    public void setBModuleUuidPath(String bModuleUuidPath) {
        this.bModuleUuidPath = bModuleUuidPath;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBPath() {
        return bPath;
    }

    public void setBPath(String bPath) {
        this.bPath = bPath;
    }

    public String getBQualifier() {
        return bQualifier;
    }

    public void setBQualifier(String bQualifier) {
        this.bQualifier = bQualifier;
    }

    public String getBUuidPath() {
        return bUuidPath;
    }

    public void setBUuidPath(String bUuidPath) {
        this.bUuidPath = bUuidPath;
    }

    public String getOrganizationUuid() {
        return organizationUuid;
    }

    public void setOrganizationUuid(String organizationUuid) {
        this.organizationUuid = organizationUuid;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean getPrivate1() {
        return private1;
    }

    public void setPrivate1(boolean private1) {
        this.private1 = private1;
    }

    public String getMainBranchProjectUuid() {
        return mainBranchProjectUuid;
    }

    public void setMainBranchProjectUuid(String mainBranchProjectUuid) {
        this.mainBranchProjectUuid = mainBranchProjectUuid;
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
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.Projects[ id=" + id + " ]";
    }

}
