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
@Table(name = "qprofile_changes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QprofileChanges.findAll", query = "SELECT q FROM QprofileChanges q")
    , @NamedQuery(name = "QprofileChanges.findByKee", query = "SELECT q FROM QprofileChanges q WHERE q.kee = :kee")
    , @NamedQuery(name = "QprofileChanges.findByRulesProfileUuid", query = "SELECT q FROM QprofileChanges q WHERE q.rulesProfileUuid = :rulesProfileUuid")
    , @NamedQuery(name = "QprofileChanges.findByChangeType", query = "SELECT q FROM QprofileChanges q WHERE q.changeType = :changeType")
    , @NamedQuery(name = "QprofileChanges.findByUserLogin", query = "SELECT q FROM QprofileChanges q WHERE q.userLogin = :userLogin")
    , @NamedQuery(name = "QprofileChanges.findByChangeData", query = "SELECT q FROM QprofileChanges q WHERE q.changeData = :changeData")
    , @NamedQuery(name = "QprofileChanges.findByCreatedAt", query = "SELECT q FROM QprofileChanges q WHERE q.createdAt = :createdAt")})
public class QprofileChanges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kee")
    private String kee;
    @Basic(optional = false)
    @Column(name = "rules_profile_uuid")
    private String rulesProfileUuid;
    @Basic(optional = false)
    @Column(name = "change_type")
    private String changeType;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "change_data")
    private String changeData;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public QprofileChanges() {
    }

    public QprofileChanges(String kee) {
        this.kee = kee;
    }

    public QprofileChanges(String kee, String rulesProfileUuid, String changeType, long createdAt) {
        this.kee = kee;
        this.rulesProfileUuid = rulesProfileUuid;
        this.changeType = changeType;
        this.createdAt = createdAt;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getRulesProfileUuid() {
        return rulesProfileUuid;
    }

    public void setRulesProfileUuid(String rulesProfileUuid) {
        this.rulesProfileUuid = rulesProfileUuid;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getChangeData() {
        return changeData;
    }

    public void setChangeData(String changeData) {
        this.changeData = changeData;
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
        if (!(object instanceof QprofileChanges)) {
            return false;
        }
        QprofileChanges other = (QprofileChanges) object;
        if ((this.kee == null && other.kee != null) || (this.kee != null && !this.kee.equals(other.kee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.QprofileChanges[ kee=" + kee + " ]";
    }
    
}
