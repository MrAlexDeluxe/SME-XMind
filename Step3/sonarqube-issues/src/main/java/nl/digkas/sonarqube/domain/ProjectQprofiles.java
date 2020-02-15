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
@Table(name = "project_qprofiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectQprofiles.findAll", query = "SELECT p FROM ProjectQprofiles p")
    , @NamedQuery(name = "ProjectQprofiles.findById", query = "SELECT p FROM ProjectQprofiles p WHERE p.id = :id")
    , @NamedQuery(name = "ProjectQprofiles.findByProjectUuid", query = "SELECT p FROM ProjectQprofiles p WHERE p.projectUuid = :projectUuid")
    , @NamedQuery(name = "ProjectQprofiles.findByProfileKey", query = "SELECT p FROM ProjectQprofiles p WHERE p.profileKey = :profileKey")})
public class ProjectQprofiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "project_uuid")
    private String projectUuid;
    @Basic(optional = false)
    @Column(name = "profile_key")
    private String profileKey;

    public ProjectQprofiles() {
    }

    public ProjectQprofiles(Integer id) {
        this.id = id;
    }

    public ProjectQprofiles(Integer id, String projectUuid, String profileKey) {
        this.id = id;
        this.projectUuid = projectUuid;
        this.profileKey = profileKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectUuid() {
        return projectUuid;
    }

    public void setProjectUuid(String projectUuid) {
        this.projectUuid = projectUuid;
    }

    public String getProfileKey() {
        return profileKey;
    }

    public void setProfileKey(String profileKey) {
        this.profileKey = profileKey;
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
        if (!(object instanceof ProjectQprofiles)) {
            return false;
        }
        ProjectQprofiles other = (ProjectQprofiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.ProjectQprofiles[ id=" + id + " ]";
    }
    
}
