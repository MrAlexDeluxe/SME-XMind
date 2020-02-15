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
@Table(name = "es_queue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsQueue.findAll", query = "SELECT e FROM EsQueue e")
    , @NamedQuery(name = "EsQueue.findByUuid", query = "SELECT e FROM EsQueue e WHERE e.uuid = :uuid")
    , @NamedQuery(name = "EsQueue.findByDocType", query = "SELECT e FROM EsQueue e WHERE e.docType = :docType")
    , @NamedQuery(name = "EsQueue.findByDocId", query = "SELECT e FROM EsQueue e WHERE e.docId = :docId")
    , @NamedQuery(name = "EsQueue.findByDocIdType", query = "SELECT e FROM EsQueue e WHERE e.docIdType = :docIdType")
    , @NamedQuery(name = "EsQueue.findByDocRouting", query = "SELECT e FROM EsQueue e WHERE e.docRouting = :docRouting")
    , @NamedQuery(name = "EsQueue.findByCreatedAt", query = "SELECT e FROM EsQueue e WHERE e.createdAt = :createdAt")})
public class EsQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "doc_type")
    private String docType;
    @Basic(optional = false)
    @Column(name = "doc_id")
    private String docId;
    @Column(name = "doc_id_type")
    private String docIdType;
    @Column(name = "doc_routing")
    private String docRouting;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public EsQueue() {
    }

    public EsQueue(String uuid) {
        this.uuid = uuid;
    }

    public EsQueue(String uuid, String docType, String docId, long createdAt) {
        this.uuid = uuid;
        this.docType = docType;
        this.docId = docId;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocIdType() {
        return docIdType;
    }

    public void setDocIdType(String docIdType) {
        this.docIdType = docIdType;
    }

    public String getDocRouting() {
        return docRouting;
    }

    public void setDocRouting(String docRouting) {
        this.docRouting = docRouting;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof EsQueue)) {
            return false;
        }
        EsQueue other = (EsQueue) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.EsQueue[ uuid=" + uuid + " ]";
    }
    
}
