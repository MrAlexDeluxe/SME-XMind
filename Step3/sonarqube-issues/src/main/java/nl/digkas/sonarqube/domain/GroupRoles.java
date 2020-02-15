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
@Table(name = "group_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupRoles.findAll", query = "SELECT g FROM GroupRoles g")
    , @NamedQuery(name = "GroupRoles.findById", query = "SELECT g FROM GroupRoles g WHERE g.id = :id")
    , @NamedQuery(name = "GroupRoles.findByGroupId", query = "SELECT g FROM GroupRoles g WHERE g.groupId = :groupId")
    , @NamedQuery(name = "GroupRoles.findByResourceId", query = "SELECT g FROM GroupRoles g WHERE g.resourceId = :resourceId")
    , @NamedQuery(name = "GroupRoles.findByRole", query = "SELECT g FROM GroupRoles g WHERE g.role = :role")
    , @NamedQuery(name = "GroupRoles.findByOrganizationUuid", query = "SELECT g FROM GroupRoles g WHERE g.organizationUuid = :organizationUuid")})
public class GroupRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "resource_id")
    private Integer resourceId;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "organization_uuid")
    private String organizationUuid;

    public GroupRoles() {
    }

    public GroupRoles(Integer id) {
        this.id = id;
    }

    public GroupRoles(Integer id, String role, String organizationUuid) {
        this.id = id;
        this.role = role;
        this.organizationUuid = organizationUuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        if (!(object instanceof GroupRoles)) {
            return false;
        }
        GroupRoles other = (GroupRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.GroupRoles[ id=" + id + " ]";
    }
    
}
