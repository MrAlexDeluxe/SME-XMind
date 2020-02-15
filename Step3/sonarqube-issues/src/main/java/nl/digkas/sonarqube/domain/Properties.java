package nl.digkas.sonarqube.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Properties.findAll", query = "SELECT p FROM Properties p")
    , @NamedQuery(name = "Properties.findById", query = "SELECT p FROM Properties p WHERE p.id = :id")
    , @NamedQuery(name = "Properties.findByPropKey", query = "SELECT p FROM Properties p WHERE p.propKey = :propKey")
    , @NamedQuery(name = "Properties.findByResourceId", query = "SELECT p FROM Properties p WHERE p.resourceId = :resourceId")
    , @NamedQuery(name = "Properties.findByUserId", query = "SELECT p FROM Properties p WHERE p.userId = :userId")
    , @NamedQuery(name = "Properties.findByIsEmpty", query = "SELECT p FROM Properties p WHERE p.isEmpty = :isEmpty")
    , @NamedQuery(name = "Properties.findByTextValue", query = "SELECT p FROM Properties p WHERE p.textValue = :textValue")
    , @NamedQuery(name = "Properties.findByClobValue", query = "SELECT p FROM Properties p WHERE p.clobValue = :clobValue")
    , @NamedQuery(name = "Properties.findByCreatedAt", query = "SELECT p FROM Properties p WHERE p.createdAt = :createdAt")})
public class Properties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "prop_key")
    private String propKey;
    @Column(name = "resource_id")
    private BigInteger resourceId;
    @Column(name = "user_id")
    private BigInteger userId;
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

    public Properties() {
    }

    public Properties(Integer id) {
        this.id = id;
    }

    public Properties(Integer id, String propKey, boolean isEmpty, long createdAt) {
        this.id = id;
        this.propKey = propKey;
        this.isEmpty = isEmpty;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public BigInteger getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigInteger resourceId) {
        this.resourceId = resourceId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Properties)) {
            return false;
        }
        Properties other = (Properties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.Properties[ id=" + id + " ]";
    }
    
}
