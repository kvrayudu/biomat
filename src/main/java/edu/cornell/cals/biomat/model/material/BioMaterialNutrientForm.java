package edu.cornell.cals.biomat.model.material;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.cornell.cals.biomat.dao.BioMaterialNutrient;

public class BioMaterialNutrientForm implements Serializable{
	private static final long serialVersionUID = -5114043930431101518L;
	@NotNull
	@Size(min=2, max=300)
	private String materialName;
	private Long selectedBioMaterialId;
	
	private List<BioMaterialNutrient> bioMaterialNutrientList;
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Long getSelectedBioMaterialId() {
		return selectedBioMaterialId;
	}
	public void setSelectedBioMaterialId(Long selectedBioMaterialId) {
		this.selectedBioMaterialId = selectedBioMaterialId;
	}
	public List<BioMaterialNutrient> getBioMaterialNutrientList() {
		return bioMaterialNutrientList;
	}
	public void setBioMaterialNutrientList(List<BioMaterialNutrient> bioMaterialNutrientList) {
		this.bioMaterialNutrientList = bioMaterialNutrientList;
	}
	@Override
	public String toString() {
		return "BioMaterialNutrientForm [materialName=" + materialName + ", selectedBioMaterialId="
				+ selectedBioMaterialId + ", bioMaterialNutrientList=" + bioMaterialNutrientList + "]";
	}
	
	
	
	
	
	
	

}
