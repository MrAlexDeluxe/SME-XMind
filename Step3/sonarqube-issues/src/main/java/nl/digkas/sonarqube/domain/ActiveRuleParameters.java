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
@Table(name = "active_rule_parameters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActiveRuleParameters.findAll", query = "SELECT a FROM ActiveRuleParameters a")
    , @NamedQuery(name = "ActiveRuleParameters.findById", query = "SELECT a FROM ActiveRuleParameters a WHERE a.id = :id")
    , @NamedQuery(name = "ActiveRuleParameters.findByActiveRuleId", query = "SELECT a FROM ActiveRuleParameters a WHERE a.activeRuleId = :activeRuleId")
    , @NamedQuery(name = "ActiveRuleParameters.findByRulesParameterId", query = "SELECT a FROM ActiveRuleParameters a WHERE a.rulesParameterId = :rulesParameterId")
    , @NamedQuery(name = "ActiveRuleParameters.findByValue", query = "SELECT a FROM ActiveRuleParameters a WHERE a.value = :value")
    , @NamedQuery(name = "ActiveRuleParameters.findByRulesParameterKey", query = "SELECT a FROM ActiveRuleParameters a WHERE a.rulesParameterKey = :rulesParameterKey")})
public class ActiveRuleParameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "active_rule_id")
    private int activeRuleId;
    @Basic(optional = false)
    @Column(name = "rules_parameter_id")
    private int rulesParameterId;
    @Column(name = "value")
    private String value;
    @Column(name = "rules_parameter_key")
    private String rulesParameterKey;

    public ActiveRuleParameters() {
    }

    public ActiveRuleParameters(Integer id) {
        this.id = id;
    }

    public ActiveRuleParameters(Integer id, int activeRuleId, int rulesParameterId) {
        this.id = id;
        this.activeRuleId = activeRuleId;
        this.rulesParameterId = rulesParameterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getActiveRuleId() {
        return activeRuleId;
    }

    public void setActiveRuleId(int activeRuleId) {
        this.activeRuleId = activeRuleId;
    }

    public int getRulesParameterId() {
        return rulesParameterId;
    }

    public void setRulesParameterId(int rulesParameterId) {
        this.rulesParameterId = rulesParameterId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRulesParameterKey() {
        return rulesParameterKey;
    }

    public void setRulesParameterKey(String rulesParameterKey) {
        this.rulesParameterKey = rulesParameterKey;
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
        if (!(object instanceof ActiveRuleParameters)) {
            return false;
        }
        ActiveRuleParameters other = (ActiveRuleParameters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.ActiveRuleParameters[ id=" + id + " ]";
    }
    
}
