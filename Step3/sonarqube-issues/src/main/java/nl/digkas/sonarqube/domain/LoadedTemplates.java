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
@Table(name = "loaded_templates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoadedTemplates.findAll", query = "SELECT l FROM LoadedTemplates l")
    , @NamedQuery(name = "LoadedTemplates.findById", query = "SELECT l FROM LoadedTemplates l WHERE l.id = :id")
    , @NamedQuery(name = "LoadedTemplates.findByKee", query = "SELECT l FROM LoadedTemplates l WHERE l.kee = :kee")
    , @NamedQuery(name = "LoadedTemplates.findByTemplateType", query = "SELECT l FROM LoadedTemplates l WHERE l.templateType = :templateType")})
public class LoadedTemplates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "kee")
    private String kee;
    @Basic(optional = false)
    @Column(name = "template_type")
    private String templateType;

    public LoadedTemplates() {
    }

    public LoadedTemplates(Integer id) {
        this.id = id;
    }

    public LoadedTemplates(Integer id, String templateType) {
        this.id = id;
        this.templateType = templateType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKee() {
        return kee;
    }

    public void setKee(String kee) {
        this.kee = kee;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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
        if (!(object instanceof LoadedTemplates)) {
            return false;
        }
        LoadedTemplates other = (LoadedTemplates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.LoadedTemplates[ id=" + id + " ]";
    }
    
}
