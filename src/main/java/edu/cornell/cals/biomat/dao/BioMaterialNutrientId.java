package edu.cornell.cals.biomat.dao;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BioMaterialNutrientId implements Serializable{
	private static final long serialVersionUID = 2302910861837400324L;

	@Column(name = "material_id")
	private Long materialId;
	
	@Column(name = "nutrient_id")
	private Integer nutrientId;
	
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Integer getNutrientId() {
		return nutrientId;
	}
	public void setNutrientId(Integer nutrientId) {
		this.nutrientId = nutrientId;
	}
	
	 @Override
	 public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof BioMaterialNutrientId)) return false;
	        
	        BioMaterialNutrientId that = (BioMaterialNutrientId) o;
	        
	        return  Objects.equals(getMaterialId(), that.getMaterialId()) &&
	                Objects.equals(getNutrientId(), that.getNutrientId());
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(getMaterialId(), getNutrientId());
	    }
 

}
