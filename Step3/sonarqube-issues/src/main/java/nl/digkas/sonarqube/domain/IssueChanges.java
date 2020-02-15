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
@Table(name = "issue_changes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IssueChanges.findAll", query = "SELECT i FROM IssueChanges i")
    , @NamedQuery(name = "IssueChanges.findById", query = "SELECT i FROM IssueChanges i WHERE i.id = :id")
    , @NamedQuery(name = "IssueChanges.findByKee", query = "SELECT i FROM IssueChanges i WHERE i.kee = :kee")
    , @NamedQuery(name = "IssueChanges.findByIssueKey", query = "SELECT i FROM IssueChanges i WHERE i.issueKey = :issueKey")
    , @NamedQuery(name = "IssueChanges.findByUserLogin", query = "SELECT i FROM IssueChanges i WHERE i.userLogin = :userLogin")
    , @NamedQuery(name = "IssueChanges.findByChangeType", query = "SELECT i FROM IssueChanges i WHERE i.changeType = :changeType")
    , @NamedQuery(name = "IssueChanges.findByChangeData", query = "SELECT i FROM IssueChanges i WHERE i.changeData = :changeData")
    , @NamedQuery(name = "IssueChanges.findByCreatedAt", query = "SELECT i FROM IssueChanges i WHERE i.createdAt = :createdAt")
    , @NamedQuery(name = "IssueChanges.findByUpdatedAt", query = "SELECT i FROM IssueChanges i WHERE i.updatedAt = :updatedAt")
    , @NamedQuery(name = "IssueChanges.findByIssueChangeCreationDate", query = "SELECT i FROM IssueChanges i WHERE i.issueChangeCreationDate = :issueChangeCreationDate")})
public class IssueChanges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "kee")
    private String kee;
    @Basic(optional = false)
    @Column(name = "issue_key")
    private String issueKey;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "change_type")
    private String changeType;
    @Column(name = "change_data")
    private String changeData;
    @Column(name = "created_at")
    private BigInteger createdAt;
    @Column(name = "updated_at")
    private BigInteger updatedAt;
    @Column(name = "issue_change_creation_date")
    private BigInteger issueChangeCreationDate;

    public IssueChanges() {
    }

    public IssueChanges(Long id) {
        this.id = id;
    }

    public IssueChanges(Long id, String issueKey) {
        this.id = id;
        this.issueKey = issueKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeData() {
        return changeData;
    }

    public void setChangeData(String changeData) {
        this.changeData = changeData;
    }

    public BigInteger getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public BigInteger getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(BigInteger updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigInteger getIssueChangeCreationDate() {
        return issueChangeCreationDate;
    }

    public void setIssueChangeCreationDate(BigInteger issueChangeCreationDate) {
        this.issueChangeCreationDate = issueChangeCreationDate;
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
        if (!(object instanceof IssueChanges)) {
            return false;
        }
        IssueChanges other = (IssueChanges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.IssueChanges[ id=" + id + " ]";
    }
    
}
