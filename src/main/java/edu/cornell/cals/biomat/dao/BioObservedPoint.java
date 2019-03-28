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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name="bio_observed_point")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class BioObservedPoint implements Serializable{

	private static final long serialVersionUID = 5987829938980111480L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long materialId;
	private int variableId;
	private int groupId;
	private Double measuredValue;
	private Double errorValue;
	
	private String citation;
	private String doi;
	private String isApproved;
	
	
	
	
	private String addedBy;
	private String updatedBy;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "materialId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private BioMaterial bioMaterial;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "variableId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private BioVariable bioVariable;

	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public int getVariableId() {
		return variableId;
	}

	public void setVariableId(int variableId) {
		this.variableId = variableId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Double getMeasuredValue() {
		return measuredValue;
	}

	public void setMeasuredValue(Double measuredValue) {
		this.measuredValue = measuredValue;
	}

	public Double getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(Double errorValue) {
		this.errorValue = errorValue;
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

	public BioMaterial getBioMaterial() {
		return bioMaterial;
	}

	public void setBioMaterial(BioMaterial bioMaterial) {
		this.bioMaterial = bioMaterial;
	}

	public BioVariable getBioVariable() {
		return bioVariable;
	}

	public void setBioVariable(BioVariable bioVariable) {
		this.bioVariable = bioVariable;
	}

	@Override
	public String toString() {
		return "BioMeasurement [id=" + id + ", materialId=" + materialId + ", variableId=" + variableId + ", groupId="
				+ groupId + ", measuredValue=" + measuredValue + ", errorValue=" + errorValue + ", citation=" + citation
				+ ", doi=" + doi + ", isApproved=" + isApproved + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy
				+ ", bioMaterial=" + bioMaterial + ", bioVariable=" + bioVariable + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}


    
    
    
    
    
}
