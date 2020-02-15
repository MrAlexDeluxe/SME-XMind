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
@Table(name = "duplications_index")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DuplicationsIndex.findAll", query = "SELECT d FROM DuplicationsIndex d")
    , @NamedQuery(name = "DuplicationsIndex.findById", query = "SELECT d FROM DuplicationsIndex d WHERE d.id = :id")
    , @NamedQuery(name = "DuplicationsIndex.findByHash", query = "SELECT d FROM DuplicationsIndex d WHERE d.hash = :hash")
    , @NamedQuery(name = "DuplicationsIndex.findByIndexInFile", query = "SELECT d FROM DuplicationsIndex d WHERE d.indexInFile = :indexInFile")
    , @NamedQuery(name = "DuplicationsIndex.findByStartLine", query = "SELECT d FROM DuplicationsIndex d WHERE d.startLine = :startLine")
    , @NamedQuery(name = "DuplicationsIndex.findByEndLine", query = "SELECT d FROM DuplicationsIndex d WHERE d.endLine = :endLine")
    , @NamedQuery(name = "DuplicationsIndex.findByComponentUuid", query = "SELECT d FROM DuplicationsIndex d WHERE d.componentUuid = :componentUuid")
    , @NamedQuery(name = "DuplicationsIndex.findByAnalysisUuid", query = "SELECT d FROM DuplicationsIndex d WHERE d.analysisUuid = :analysisUuid")})
public class DuplicationsIndex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "hash")
    private String hash;
    @Basic(optional = false)
    @Column(name = "index_in_file")
    private int indexInFile;
    @Basic(optional = false)
    @Column(name = "start_line")
    private int startLine;
    @Basic(optional = false)
    @Column(name = "end_line")
    private int endLine;
    @Basic(optional = false)
    @Column(name = "component_uuid")
    private String componentUuid;
    @Basic(optional = false)
    @Column(name = "analysis_uuid")
    private String analysisUuid;

    public DuplicationsIndex() {
    }

    public DuplicationsIndex(Long id) {
        this.id = id;
    }

    public DuplicationsIndex(Long id, String hash, int indexInFile, int startLine, int endLine, String componentUuid, String analysisUuid) {
        this.id = id;
        this.hash = hash;
        this.indexInFile = indexInFile;
        this.startLine = startLine;
        this.endLine = endLine;
        this.componentUuid = componentUuid;
        this.analysisUuid = analysisUuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getIndexInFile() {
        return indexInFile;
    }

    public void setIndexInFile(int indexInFile) {
        this.indexInFile = indexInFile;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public String getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(String componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getAnalysisUuid() {
        return analysisUuid;
    }

    public void setAnalysisUuid(String analysisUuid) {
        this.analysisUuid = analysisUuid;
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
        if (!(object instanceof DuplicationsIndex)) {
            return false;
        }
        DuplicationsIndex other = (DuplicationsIndex) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nl.digkas.sonarqube.domain.DuplicationsIndex[ id=" + id + " ]";
    }
    
}
