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
@Table(name = "plugins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plugins.findAll", query = "SELECT p FROM Plugins p")
    , @NamedQuery(name = "Plugins.findByUuid", query = "SELECT p FROM Plugins p WHERE p.uuid = :uuid")
    , @NamedQuery(name = "Plugins.findByKee", query = "SELECT p FROM Plugins p WHERE p.kee = :kee")
    , @NamedQuery(name = "Plugins.findByBasePluginKey", query = "SELECT p FROM Plugins p WHERE p.basePluginKey = :basePluginKey")
    , @NamedQuery(name = "Plugins.findByFileHash", query = "SELECT p FROM Plugins p WHERE p.fileHash = :fileHash")
    , @NamedQuery(name = "Plugins.findByCreatedAt", query = "SELECT p FROM Plugins p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Plugins.findByUpdatedAt", query = "SELECT p FROM Plugins p WHERE p.updatedAt = :updatedAt")})
public class Plugins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Column(name = "base_plugin_key")
    private String basePluginKey;
    @Basic(optional = false)
    @Column(name = "file_hash")
    private String fileHash;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private long updatedAt;

    public Plugins() {
    }

    public Plugins(String uuid) {
        this.uuid = uuid;
    }

    public Plugins(String uuid, String kee, String fileHash, long createdAt, long updatedAt) {
        this.uuid = uuid;
        this.kee = kee;
        this.fileHash = fileHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getBasePluginKey() {
        return basePluginKey;
    }

    public void setBasePluginKey(String basePluginKey) {
        this.basePluginKey = basePluginKey;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
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
        if (!(object instanceof Plugins)) {
            return false;
        }
        Plugins other = (Plugins) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.Plugins[ uuid=" + uuid + " ]";
    }
    
}
