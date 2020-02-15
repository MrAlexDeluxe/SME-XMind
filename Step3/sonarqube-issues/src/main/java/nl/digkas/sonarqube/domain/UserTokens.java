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
@Table(name = "user_tokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTokens.findAll", query = "SELECT u FROM UserTokens u")
    , @NamedQuery(name = "UserTokens.findById", query = "SELECT u FROM UserTokens u WHERE u.id = :id")
    , @NamedQuery(name = "UserTokens.findByLogin", query = "SELECT u FROM UserTokens u WHERE u.login = :login")
    , @NamedQuery(name = "UserTokens.findByName", query = "SELECT u FROM UserTokens u WHERE u.name = :name")
    , @NamedQuery(name = "UserTokens.findByTokenHash", query = "SELECT u FROM UserTokens u WHERE u.tokenHash = :tokenHash")
    , @NamedQuery(name = "UserTokens.findByCreatedAt", query = "SELECT u FROM UserTokens u WHERE u.createdAt = :createdAt")})
public class UserTokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "token_hash")
    private String tokenHash;
    @Basic(optional = false)
    @Column(name = "created_at")
    private long createdAt;

    public UserTokens() {
    }

    public UserTokens(Integer id) {
        this.id = id;
    }

    public UserTokens(Integer id, String login, String name, String tokenHash, long createdAt) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.tokenHash = tokenHash;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
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
        if (!(object instanceof UserTokens)) {
            return false;
        }
        UserTokens other = (UserTokens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.UserTokens[ id=" + id + " ]";
    }
    
}
