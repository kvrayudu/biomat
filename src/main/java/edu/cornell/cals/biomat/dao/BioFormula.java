package edu.cornell.cals.biomat.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bio_formula")
@JsonIgnoreProperties({"createdAt", "updatedAt","addedBy","updatedBy","bioVariable","bioDependentVariable"})
@EntityListeners(AuditingEntityListener.class)
public class BioFormula implements Serializable{
	
	private static final long serialVersionUID = 3775220820478810683L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String formula;
	private String legacyFormula;
	private String legacyValidMaterials;
	
	private Integer variableId;
	private Integer dependentVariableId;
	 
	private Double minRange;
	private Double maxRange;
	
	private String formulaDesc;
	private Double errorReading;
	private Double rSq;
	
	private String citation;
	private String doi;
	private String isApproved;

	private String addedBy;
	private String updatedBy;
	private String approvedBy;

	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "variableId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private BioVariable bioVariable;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "dependentVariableId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private BioVariable bioDependentVariable;

	
	
	//Transiant
	public String getFormulaAndName() {
		return name + " - " + formula;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getFormula() {
		return formula;
	}



	public void setFormula(String formula) {
		this.formula = formula;
	}



	public String getLegacyFormula() {
		return legacyFormula;
	}



	public void setLegacyFormula(String legacyFormula) {
		this.legacyFormula = legacyFormula;
	}



	public String getLegacyValidMaterials() {
		return legacyValidMaterials;
	}



	public void setLegacyValidMaterials(String legacyValidMaterials) {
		this.legacyValidMaterials = legacyValidMaterials;
	}



	public Integer getVariableId() {
		return variableId;
	}



	public void setVariableId(Integer variableId) {
		this.variableId = variableId;
	}



	public Integer getDependentVariableId() {
		return dependentVariableId;
	}



	public void setDependentVariableId(Integer dependentVariableId) {
		this.dependentVariableId = dependentVariableId;
	}



	public Double getMinRange() {
		return minRange;
	}



	public void setMinRange(Double minRange) {
		this.minRange = minRange;
	}



	public Double getMaxRange() {
		return maxRange;
	}



	public void setMaxRange(Double maxRange) {
		this.maxRange = maxRange;
	}



	public String getFormulaDesc() {
		return formulaDesc;
	}



	public void setFormulaDesc(String formulaDesc) {
		this.formulaDesc = formulaDesc;
	}



	public Double getErrorReading() {
		return errorReading;
	}



	public void setErrorReading(Double errorReading) {
		this.errorReading = errorReading;
	}



	public Double getrSq() {
		return rSq;
	}



	public void setrSq(Double rSq) {
		this.rSq = rSq;
	}



	public String getCitation() {
		return citation;
	}



	public void setCitation(String citation) {
		this.citation = citation;
	}



	public String getDoi() {
		return doi;
	}



	public void setDoi(String doi) {
		this.doi = doi;
	}



	public String getIsApproved() {
		return isApproved;
	}



	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}



	public String getAddedBy() {
		return addedBy;
	}



	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public String getApprovedBy() {
		return approvedBy;
	}



	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public BioVariable getBioVariable() {
		return bioVariable;
	}



	public void setBioVariable(BioVariable bioVariable) {
		this.bioVariable = bioVariable;
	}



	public BioVariable getBioDependentVariable() {
		return bioDependentVariable;
	}



	public void setBioDependentVariable(BioVariable bioDependentVariable) {
		this.bioDependentVariable = bioDependentVariable;
	}



	@Override
	public String toString() {
		return "BioFormula [id=" + id + ", name=" + name + ", formula=" + formula + ", legacyFormula=" + legacyFormula
				+ ", legacyValidMaterials=" + legacyValidMaterials + ", variableId=" + variableId
				+ ", dependentVariableId=" + dependentVariableId + ", minRange=" + minRange + ", maxRange=" + maxRange
				+ ", formulaDesc=" + formulaDesc + ", errorReading=" + errorReading + ", rSq=" + rSq + ", citation="
				+ citation + ", doi=" + doi + ", isApproved=" + isApproved + ", addedBy=" + addedBy + ", updatedBy="
				+ updatedBy + ", approvedBy=" + approvedBy + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", bioVariable=" + bioVariable + ", bioDependentVariable=" + bioDependentVariable
				+ ", getFormulaAndName()=" + getFormulaAndName() + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getFormula()=" + getFormula() + ", getLegacyFormula()=" + getLegacyFormula()
				+ ", getLegacyValidMaterials()=" + getLegacyValidMaterials() + ", getVariableId()=" + getVariableId()
				+ ", getDependentVariableId()=" + getDependentVariableId() + ", getMinRange()=" + getMinRange()
				+ ", getMaxRange()=" + getMaxRange() + ", getFormulaDesc()=" + getFormulaDesc() + ", getErrorReading()="
				+ getErrorReading() + ", getrSq()=" + getrSq() + ", getCitation()=" + getCitation() + ", getDoi()="
				+ getDoi() + ", getIsApproved()=" + getIsApproved() + ", getAddedBy()=" + getAddedBy()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getApprovedBy()=" + getApprovedBy() + ", getCreatedAt()="
				+ getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + ", getBioVariable()=" + getBioVariable()
				+ ", getBioDependentVariable()=" + getBioDependentVariable() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


}
