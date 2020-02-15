package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "file_sources")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileSources.findAll", query = "SELECT f FROM FileSources f")
    , @NamedQuery(name = "FileSources.findById", query = "SELECT f FROM FileSources f WHERE f.id = :id")
    , @NamedQuery(name = "FileSources.findByProjectUuid", query = "SELECT f FROM FileSources f WHERE f.projectUuid = :projectUuid")
    , @NamedQuery(name = "FileSources.findByFileUuid", query = "SELECT f FROM FileSources f WHERE f.fileUuid = :fileUuid")
    , @NamedQuery(name = "FileSources.findByLineHashes", query = "SELECT f FROM FileSources f WHERE f.lineHashes = :lineHashes")
    , @NamedQuery(name = "FileSources.findByDataHash", query = "SELECT f FROM FileSources f WHERE f.dataHash = :dataHash")
    , @NamedQuery(name = "FileSources.findByCreatedAt", query = "SELECT f FROM FileSources f WHERE f.createdAt = :createdAt")
    , @NamedQuery(name = "FileSources.findByUpdatedAt", query = "SELECT f FROM FileSources f WHERE f.updatedAt = :updatedAt")
    , @NamedQuery(name = "FileSources.findBySrcHash", query = "SELECT f FROM FileSources f WHERE f.srcHash = :srcHash")
    , @NamedQuery(name = "FileSources.findByDataType", query = "SELECT f FROM FileSources f WHERE f.dataType = :dataType")
    , @NamedQuery(name = "FileSources.findByRevision", query = "SELECT f FROM FileSources f WHERE f.revision = :revision")})
public class FileSources implements Serializable {

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
    @Column(name = "file_uuid")
    private String fileUuid;
    @Column(name = "line_hashes")
    private String lineHashes;
    @Column(name = "data_hash")
    private String dataHash;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;
    @Column(name = "src_hash")
    private String srcHash;
    @Lob
    @Column(name = "binary_data")
    private byte[] binaryData;
    @Column(name = "data_type")
    private String dataType;
    @Column(name = "revision")
    private String revision;

    public FileSources() {
    }

    public FileSources(Integer id) {
        this.id = id;
    }

    public FileSources(Integer id, String projectUuid, String fileUuid, long createdAt, long updatedAt) {
        this.id = id;
        this.projectUuid = projectUuid;
        this.fileUuid = fileUuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getLineHashes() {
        return lineHashes;
    }

    public void setLineHashes(String lineHashes) {
        this.lineHashes = lineHashes;
    }

    public String getDataHash() {
        return dataHash;
    }

    public void setDataHash(String dataHash) {
        this.dataHash = dataHash;
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

    public String getSrcHash() {
        return srcHash;
    }

    public void setSrcHash(String srcHash) {
        this.srcHash = srcHash;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
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
        if (!(object instanceof FileSources)) {
            return false;
        }
        FileSources other = (FileSources) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.FileSources[ id=" + id + " ]";
    }
    
}
