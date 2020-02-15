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
@Table(name = "internal_properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InternalProperties.findAll", query = "SELECT i FROM InternalProperties i")
    , @NamedQuery(name = "InternalProperties.findByKee", query = "SELECT i FROM InternalProperties i WHERE i.kee = :kee")
    , @NamedQuery(name = "InternalProperties.findByIsEmpty", query = "SELECT i FROM InternalProperties i WHERE i.isEmpty = :isEmpty")
    , @NamedQuery(name = "InternalProperties.findByTextValue", query = "SELECT i FROM InternalProperties i WHERE i.textValue = :textValue")
    , @NamedQuery(name = "InternalProperties.findByClobValue", query = "SELECT i FROM InternalProperties i WHERE i.clobValue = :clobValue")
    , @NamedQuery(name = "InternalProperties.findByCreatedAt", query = "SELECT i FROM InternalProperties i WHERE i.createdAt = :createdAt")})
public class InternalProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Basic(optional = false)
    @Column(name = "is_empty")
    private boolean isEmpty;
    @Column(name = "text_value")
    private String textValue;
    @Column(name = "clob_value")
    private String clobValue;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public InternalProperties() {
    }

    public InternalProperties(String kee) {
        this.kee = kee;
    }

    public InternalProperties(String kee, boolean isEmpty, long createdAt) {
        this.kee = kee;
        this.isEmpty = isEmpty;
        this.createdAt = createdAt;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getClobValue() {
        return clobValue;
    }

    public void setClobValue(String clobValue) {
        this.clobValue = clobValue;
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
        hash += (kee != null ? kee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InternalProperties)) {
            return false;
        }
        InternalProperties other = (InternalProperties) object;
        if ((this.kee == null && other.kee != null) || (this.kee != null && !this.kee.equals(other.kee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.InternalProperties[ kee=" + kee + " ]";
    }
    
}
