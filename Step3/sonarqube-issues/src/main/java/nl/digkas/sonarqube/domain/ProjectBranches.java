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
@Table(name = "project_branches")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectBranches.findAll", query = "SELECT p FROM ProjectBranches p")
    , @NamedQuery(name = "ProjectBranches.findByUuid", query = "SELECT p FROM ProjectBranches p WHERE p.uuid = :uuid")
    , @NamedQuery(name = "ProjectBranches.findByProjectUuid", query = "SELECT p FROM ProjectBranches p WHERE p.projectUuid = :projectUuid")
    , @NamedQuery(name = "ProjectBranches.findByKee", query = "SELECT p FROM ProjectBranches p WHERE p.kee = :kee")
    , @NamedQuery(name = "ProjectBranches.findByBranchType", query = "SELECT p FROM ProjectBranches p WHERE p.branchType = :branchType")
    , @NamedQuery(name = "ProjectBranches.findByMergeBranchUuid", query = "SELECT p FROM ProjectBranches p WHERE p.mergeBranchUuid = :mergeBranchUuid")
    , @NamedQuery(name = "ProjectBranches.findByCreatedAt", query = "SELECT p FROM ProjectBranches p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "ProjectBranches.findByUpdatedAt", query = "SELECT p FROM ProjectBranches p WHERE p.updatedAt = :updatedAt")})
public class ProjectBranches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "project_uuid")
    private String projectUuid;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Column(name = "branch_type")
    private String branchType;
    @Column(name = "merge_branch_uuid")
    private String mergeBranchUuid;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public ProjectBranches() {
    }

    public ProjectBranches(String uuid) {
        this.uuid = uuid;
    }

    public ProjectBranches(String uuid, String projectUuid, String kee, long createdAt, long updatedAt) {
        this.uuid = uuid;
        this.projectUuid = projectUuid;
        this.kee = kee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getMergeBranchUuid() {
        return mergeBranchUuid;
    }

    public void setMergeBranchUuid(String mergeBranchUuid) {
        this.mergeBranchUuid = mergeBranchUuid;
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
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectBranches)) {
            return false;
        }
        ProjectBranches other = (ProjectBranches) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.ProjectBranches[ uuid=" + uuid + " ]";
    }
    
}
