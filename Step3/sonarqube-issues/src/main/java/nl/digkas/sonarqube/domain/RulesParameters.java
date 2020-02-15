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
@Table(name = "rules_parameters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RulesParameters.findAll", query = "SELECT r FROM RulesParameters r")
    , @NamedQuery(name = "RulesParameters.findById", query = "SELECT r FROM RulesParameters r WHERE r.id = :id")
    , @NamedQuery(name = "RulesParameters.findByRuleId", query = "SELECT r FROM RulesParameters r WHERE r.ruleId = :ruleId")
    , @NamedQuery(name = "RulesParameters.findByName", query = "SELECT r FROM RulesParameters r WHERE r.name = :name")
    , @NamedQuery(name = "RulesParameters.findByDescription", query = "SELECT r FROM RulesParameters r WHERE r.description = :description")
    , @NamedQuery(name = "RulesParameters.findByParamType", query = "SELECT r FROM RulesParameters r WHERE r.paramType = :paramType")
    , @NamedQuery(name = "RulesParameters.findByDefaultValue", query = "SELECT r FROM RulesParameters r WHERE r.defaultValue = :defaultValue")})
public class RulesParameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "rule_id")
    private int ruleId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "param_type")
    private String paramType;
    @Column(name = "default_value")
    private String defaultValue;

    public RulesParameters() {
    }

    public RulesParameters(Integer id) {
        this.id = id;
    }

    public RulesParameters(Integer id, int ruleId, String name, String paramType) {
        this.id = id;
        this.ruleId = ruleId;
        this.name = name;
        this.paramType = paramType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
        if (!(object instanceof RulesParameters)) {
            return false;
        }
        RulesParameters other = (RulesParameters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.RulesParameters[ id=" + id + " ]";
    }
    
}
