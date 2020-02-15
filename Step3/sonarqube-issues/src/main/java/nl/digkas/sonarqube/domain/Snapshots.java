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
@Table(name = "snapshots")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Snapshots.findAll", query = "SELECT s FROM Snapshots s")
    , @NamedQuery(name = "Snapshots.findById", query = "SELECT s FROM Snapshots s WHERE s.id = :id")
    , @NamedQuery(name = "Snapshots.findByStatus", query = "SELECT s FROM Snapshots s WHERE s.status = :status")
    , @NamedQuery(name = "Snapshots.findByIslast", query = "SELECT s FROM Snapshots s WHERE s.islast = :islast")
    , @NamedQuery(name = "Snapshots.findByVersion", query = "SELECT s FROM Snapshots s WHERE s.version = :version")
    , @NamedQuery(name = "Snapshots.findByPurgeStatus", query = "SELECT s FROM Snapshots s WHERE s.purgeStatus = :purgeStatus")
    , @NamedQuery(name = "Snapshots.findByPeriod1Mode", query = "SELECT s FROM Snapshots s WHERE s.period1Mode = :period1Mode")
    , @NamedQuery(name = "Snapshots.findByPeriod1Param", query = "SELECT s FROM Snapshots s WHERE s.period1Param = :period1Param")
    , @NamedQuery(name = "Snapshots.findByPeriod2Mode", query = "SELECT s FROM Snapshots s WHERE s.period2Mode = :period2Mode")
    , @NamedQuery(name = "Snapshots.findByPeriod2Param", query = "SELECT s FROM Snapshots s WHERE s.period2Param = :period2Param")
    , @NamedQuery(name = "Snapshots.findByPeriod3Mode", query = "SELECT s FROM Snapshots s WHERE s.period3Mode = :period3Mode")
    , @NamedQuery(name = "Snapshots.findByPeriod3Param", query = "SELECT s FROM Snapshots s WHERE s.period3Param = :period3Param")
    , @NamedQuery(name = "Snapshots.findByPeriod4Mode", query = "SELECT s FROM Snapshots s WHERE s.period4Mode = :period4Mode")
    , @NamedQuery(name = "Snapshots.findByPeriod4Param", query = "SELECT s FROM Snapshots s WHERE s.period4Param = :period4Param")
    , @NamedQuery(name = "Snapshots.findByPeriod5Mode", query = "SELECT s FROM Snapshots s WHERE s.period5Mode = :period5Mode")
    , @NamedQuery(name = "Snapshots.findByPeriod5Param", query = "SELECT s FROM Snapshots s WHERE s.period5Param = :period5Param")
    , @NamedQuery(name = "Snapshots.findByCreatedAt", query = "SELECT s FROM Snapshots s WHERE s.createdAt = :createdAt")
    , @NamedQuery(name = "Snapshots.findByBuildDate", query = "SELECT s FROM Snapshots s WHERE s.buildDate = :buildDate")
    , @NamedQuery(name = "Snapshots.findByPeriod1Date", query = "SELECT s FROM Snapshots s WHERE s.period1Date = :period1Date")
    , @NamedQuery(name = "Snapshots.findByPeriod2Date", query = "SELECT s FROM Snapshots s WHERE s.period2Date = :period2Date")
    , @NamedQuery(name = "Snapshots.findByPeriod3Date", query = "SELECT s FROM Snapshots s WHERE s.period3Date = :period3Date")
    , @NamedQuery(name = "Snapshots.findByPeriod4Date", query = "SELECT s FROM Snapshots s WHERE s.period4Date = :period4Date")
    , @NamedQuery(name = "Snapshots.findByPeriod5Date", query = "SELECT s FROM Snapshots s WHERE s.period5Date = :period5Date")
    , @NamedQuery(name = "Snapshots.findByComponentUuid", query = "SELECT s FROM Snapshots s WHERE s.componentUuid = :componentUuid")
    , @NamedQuery(name = "Snapshots.findByUuid", query = "SELECT s FROM Snapshots s WHERE s.uuid = :uuid")})
public class Snapshots implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "islast")
    private boolean islast;
    @Column(name = "version")
    private String version;
    @Column(name = "purge_status")
    private Integer purgeStatus;
    @Column(name = "period1_mode")
    private String period1Mode;
    @Column(name = "period1_param")
    private String period1Param;
    @Column(name = "period2_mode")
    private String period2Mode;
    @Column(name = "period2_param")
    private String period2Param;
    @Column(name = "period3_mode")
    private String period3Mode;
    @Column(name = "period3_param")
    private String period3Param;
    @Column(name = "period4_mode")
    private String period4Mode;
    @Column(name = "period4_param")
    private String period4Param;
    @Column(name = "period5_mode")
    private String period5Mode;
    @Column(name = "period5_param")
    private String period5Param;
    @Column(name = "created_at")
    private BigInteger createdAt;
    @Column(name = "build_date")
    private BigInteger buildDate;
    @Column(name = "period1_date")
    private BigInteger period1Date;
    @Column(name = "period2_date")
    private BigInteger period2Date;
    @Column(name = "period3_date")
    private BigInteger period3Date;
    @Column(name = "period4_date")
    private BigInteger period4Date;
    @Column(name = "period5_date")
    private BigInteger period5Date;
    @Basic(optional = false)
    @Column(name = "component_uuid")
    private String componentUuid;
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    public Snapshots() {
    }

    public Snapshots(Integer id) {
        this.id = id;
    }

    public Snapshots(Integer id, String status, boolean islast, String componentUuid, String uuid) {
        this.id = id;
        this.status = status;
        this.islast = islast;
        this.componentUuid = componentUuid;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getIslast() {
        return islast;
    }

    public void setIslast(boolean islast) {
        this.islast = islast;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPurgeStatus() {
        return purgeStatus;
    }

    public void setPurgeStatus(Integer purgeStatus) {
        this.purgeStatus = purgeStatus;
    }

    public String getPeriod1Mode() {
        return period1Mode;
    }

    public void setPeriod1Mode(String period1Mode) {
        this.period1Mode = period1Mode;
    }

    public String getPeriod1Param() {
        return period1Param;
    }

    public void setPeriod1Param(String period1Param) {
        this.period1Param = period1Param;
    }

    public String getPeriod2Mode() {
        return period2Mode;
    }

    public void setPeriod2Mode(String period2Mode) {
        this.period2Mode = period2Mode;
    }

    public String getPeriod2Param() {
        return period2Param;
    }

    public void setPeriod2Param(String period2Param) {
        this.period2Param = period2Param;
    }

    public String getPeriod3Mode() {
        return period3Mode;
    }

    public void setPeriod3Mode(String period3Mode) {
        this.period3Mode = period3Mode;
    }

    public String getPeriod3Param() {
        return period3Param;
    }

    public void setPeriod3Param(String period3Param) {
        this.period3Param = period3Param;
    }

    public String getPeriod4Mode() {
        return period4Mode;
    }

    public void setPeriod4Mode(String period4Mode) {
        this.period4Mode = period4Mode;
    }

    public String getPeriod4Param() {
        return period4Param;
    }

    public void setPeriod4Param(String period4Param) {
        this.period4Param = period4Param;
    }

    public String getPeriod5Mode() {
        return period5Mode;
    }

    public void setPeriod5Mode(String period5Mode) {
        this.period5Mode = period5Mode;
    }

    public String getPeriod5Param() {
        return period5Param;
    }

    public void setPeriod5Param(String period5Param) {
        this.period5Param = period5Param;
    }

    public BigInteger getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public BigInteger getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(BigInteger buildDate) {
        this.buildDate = buildDate;
    }

    public BigInteger getPeriod1Date() {
        return period1Date;
    }

    public void setPeriod1Date(BigInteger period1Date) {
        this.period1Date = period1Date;
    }

    public BigInteger getPeriod2Date() {
        return period2Date;
    }

    public void setPeriod2Date(BigInteger period2Date) {
        this.period2Date = period2Date;
    }

    public BigInteger getPeriod3Date() {
        return period3Date;
    }

    public void setPeriod3Date(BigInteger period3Date) {
        this.period3Date = period3Date;
    }

    public BigInteger getPeriod4Date() {
        return period4Date;
    }

    public void setPeriod4Date(BigInteger period4Date) {
        this.period4Date = period4Date;
    }

    public BigInteger getPeriod5Date() {
        return period5Date;
    }

    public void setPeriod5Date(BigInteger period5Date) {
        this.period5Date = period5Date;
    }

    public String getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(String componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        if (!(object instanceof Snapshots)) {
            return false;
        }
        Snapshots other = (Snapshots) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.Snapshots[ id=" + id + " ]";
    }
    
}
