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
@Table(name = "project_links")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectLinks.findAll", query = "SELECT p FROM ProjectLinks p")
    , @NamedQuery(name = "ProjectLinks.findById", query = "SELECT p FROM ProjectLinks p WHERE p.id = :id")
    , @NamedQuery(name = "ProjectLinks.findByLinkType", query = "SELECT p FROM ProjectLinks p WHERE p.linkType = :linkType")
    , @NamedQuery(name = "ProjectLinks.findByName", query = "SELECT p FROM ProjectLinks p WHERE p.name = :name")
    , @NamedQuery(name = "ProjectLinks.findByHref", query = "SELECT p FROM ProjectLinks p WHERE p.href = :href")
    , @NamedQuery(name = "ProjectLinks.findByComponentUuid", query = "SELECT p FROM ProjectLinks p WHERE p.componentUuid = :componentUuid")})
public class ProjectLinks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "link_type")
    private String linkType;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "href")
    private String href;
    @Column(name = "component_uuid")
    private String componentUuid;

    public ProjectLinks() {
    }

    public ProjectLinks(Integer id) {
        this.id = id;
    }

    public ProjectLinks(Integer id, String href) {
        this.id = id;
        this.href = href;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(String componentUuid) {
        this.componentUuid = componentUuid;
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
        if (!(object instanceof ProjectLinks)) {
            return false;
        }
        ProjectLinks other = (ProjectLinks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.ProjectLinks[ id=" + id + " ]";
    }
    
}
