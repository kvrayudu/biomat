package edu.cornell.cals.biomat.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bio_variable")
@JsonIgnoreProperties({"createdAt", "updatedAt","siUnit","description","symbol","addedBy","updatedBy"})
@EntityListeners(AuditingEntityListener.class)
public class BioVariable implements Serializable{
	private static final long serialVersionUID = 5843943351188943018L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	@Column(name="SIUnit")
	private String siUnit;
	private String description;
	@NotNull
	private Integer isFactor;
	
	private String symbol;

	private String addedBy;
	private String updatedBy;

	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public String getNameAndVariableOrFactor() {
    	String returnValue = name;
    	if(isFactor == 1) {
    		returnValue += " (Factor)";
    	}
    	else {
    		returnValue += " (Variable)";
    	}
    	return returnValue;
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiUnit() {
		return siUnit;
	}

	public void setSiUnit(String siUnit) {
		this.siUnit = siUnit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getIsFactor() {
		return isFactor;
	}
	
	public void setIsFactor(Integer isFactor) {
		this.isFactor = isFactor;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public String toString() {
		return "BioVariable [id=" + id + ", name=" + name + ", siUnit=" + siUnit + ", description=" + description
				+ ", isFactor=" + isFactor + ", symbol=" + symbol + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if(obj instanceof BioVariable) {
			equals = ((BioVariable)obj).getId() == this.getId();
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
	    return (int) id * name.hashCode() * name.hashCode();
	}
	
	
	
}
