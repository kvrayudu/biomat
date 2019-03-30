package edu.cornell.cals.biomat.model.material;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.cornell.cals.biomat.dao.BioMaterial;

public class EditBioFormulaForm implements Serializable{
	private static final long serialVersionUID = 2947162447360595216L;
	@NotNull
	@Size(min=2, max=300)
	private String formulaName;
	private Long selectedFormulaId;
	List<BioMaterial> bioMaterials;

	public List<BioMaterial> getBioMaterials() {
		return bioMaterials;
	}
	public void setBioMaterials(List<BioMaterial> bioMaterials) {
		this.bioMaterials = bioMaterials;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	public Long getSelectedFormulaId() {
		return selectedFormulaId;
	}
	public void setSelectedFormulaId(Long selectedFormulaId) {
		this.selectedFormulaId = selectedFormulaId;
	}
	@Override
	public String toString() {
		return "EditBioFormulaForm [formulaName=" + formulaName + ", selectedFormulaId="
				+ selectedFormulaId +"bioMaterials "+bioMaterials+"]";
	}

}
  
