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
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bio_discreet_data")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class BioDiscreetData implements Serializable{

	private static final long serialVersionUID = 4L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long materialId;
	@NotNull
	private Long relationId;

	@NotNull
	private Double y_value;
	
	@NotNull
	private Double x1_value;
	
	private Double x2_value;
	private Double x3_value;
	private Double x4_value;
	private Double x5_value;
	private Double x6_value;
	private Double x7_value;
	private Double x8_value;
	private Double x9_value;
	private Double x10_value;
	
	
	private String author_name;
	private String year;
	
	private String addedBy;
	private String updatedBy;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "materialId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private BioMaterial bioMaterial;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "relationId", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private BioRelation bioRelation;

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
	
	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	public String getAuthorName() {
		return author_name;
	}

	public void setAuthorName(String author_name) {
		this.author_name = author_name;
	}
	
	public Double getY_value() {
		return y_value;
	}

	public void setY_value(Double y_value) {
		this.y_value = y_value;
	}

	public Double getX1_value() {
		return x1_value;
	}

	public void setX1_value(Double x1_value) {
		this.x1_value = x1_value;
	}

	public Double getX2_value() {
		return x2_value;
	}

	public void setX2_value(Double x2_value) {
		this.x2_value = x2_value;
	}

	public Double getX3_value() {
		return x3_value;
	}

	public void setX3_value(Double x3_value) {
		this.x3_value = x3_value;
	}

	public Double getX4_value() {
		return x4_value;
	}

	public void setX4_value(Double x4_value) {
		this.x4_value = x4_value;
	}

	public Double getX5_value() {
		return x5_value;
	}

	public void setX5_value(Double x5_value) {
		this.x5_value = x5_value;
	}

	public Double getX6_value() {
		return x6_value;
	}

	public void setX6_value(Double x6_value) {
		this.x6_value = x6_value;
	}

	public Double getX7_value() {
		return x7_value;
	}

	public void setX7_value(Double x7_value) {
		this.x7_value = x7_value;
	}

	public Double getX8_value() {
		return x8_value;
	}

	public void setX8_value(Double x8_value) {
		this.x8_value = x8_value;
	}

	public Double getX9_value() {
		return x9_value;
	}

	public void setX9_value(Double x9_value) {
		this.x9_value = x9_value;
	}

	public Double getX10_value() {
		return x10_value;
	}

	public void setX10_value(Double x10_value) {
		this.x10_value = x10_value;
	}

	

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public BioMaterial getBioMaterial() {
		return bioMaterial;
	}

	public void setBioMaterial(BioMaterial bioMaterial) {
		this.bioMaterial = bioMaterial;
	}

	public BioRelation getBioRelation() {
		return bioRelation;
	}

	public void setBioRelation(BioRelation bioRelation) {
		this.bioRelation = bioRelation;
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

	@Override
	public String toString() {
		return "BioDiscreetData [id=" + id + ", materialId=" + materialId + ", relationId=" + relationId + ", y_value="
				+ y_value + ", x1_value=" + x1_value + ", x2_value=" + x2_value + ", x3_value=" + x3_value
				+ ", x4_value=" + x4_value + ", x5_value=" + x5_value + ", x6_value=" + x6_value + ", x7_value="
				+ x7_value + ", x8_value=" + x8_value + ", x9_value=" + x9_value + ", x10_value=" + x10_value
				+ ", author_name=" + author_name + ", year=" + year + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy
				+ ", bioMaterial=" + bioMaterial + ", bioRelation=" + bioRelation + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}



    
    
    
    
    
}
