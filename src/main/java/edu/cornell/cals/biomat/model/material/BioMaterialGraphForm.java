package edu.cornell.cals.biomat.model.material;

import java.io.Serializable;
import java.util.List;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.BioMaterialCompositionModel;

public class BioMaterialGraphForm implements Serializable{
	private static final long serialVersionUID = 1143801111114953319L;
	private BioMaterial bioMaterial;
	private List<BioVariable> variableList;
	private List<BioVariable> dependentVariableList;
	private List<BioFormula> bioFormulaList;
	
	
	private Integer selectedBioMaterialId;
	private Integer selectedBioVariableId;
	private Integer selectedDependentBioVariableId;
	private Integer selectedBioFormulaId;
	private Integer minRange = 50;
	private Integer maxRange = 100;
	
	private List<BioMaterialCompositionModel> bioMaterialCompositionModelList; 
	
	public BioMaterial getBioMaterial() {
		return bioMaterial;
	}
	public void setBioMaterial(BioMaterial bioMaterial) {
		this.bioMaterial = bioMaterial;
	}
	public List<BioVariable> getVariableList() {
		return variableList;
	}
	public void setVariableList(List<BioVariable> variableList) {
		this.variableList = variableList;
	}
	public List<BioVariable> getDependentVariableList() {
		return dependentVariableList;
	}
	public void setDependentVariableList(List<BioVariable> dependentVariableList) {
		this.dependentVariableList = dependentVariableList;
	}
	public Integer getMinRange() {
		return minRange;
	}
	public void setMinRange(Integer minRange) {
		this.minRange = minRange;
	}
	public Integer getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(Integer maxRange) {
		this.maxRange = maxRange;
	}
	public List<BioFormula> getBioFormulaList() {
		return bioFormulaList;
	}
	public void setBioFormulaList(List<BioFormula> bioFormulaList) {
		this.bioFormulaList = bioFormulaList;
	}
	public Integer getSelectedBioMaterialId() {
		return selectedBioMaterialId;
	}
	public void setSelectedBioMaterialId(Integer selectedBioMaterialId) {
		this.selectedBioMaterialId = selectedBioMaterialId;
	}
	public Integer getSelectedBioVariableId() {
		return selectedBioVariableId;
	}
	public void setSelectedBioVariableId(Integer selectedBioVariableId) {
		this.selectedBioVariableId = selectedBioVariableId;
	}
	public Integer getSelectedDependentBioVariableId() {
		return selectedDependentBioVariableId;
	}
	public void setSelectedDependentBioVariableId(Integer selectedDependentBioVariableId) {
		this.selectedDependentBioVariableId = selectedDependentBioVariableId;
	}
	public Integer getSelectedBioFormulaId() {
		return selectedBioFormulaId;
	}
	public void setSelectedBioFormulaId(Integer selectedBioFormulaId) {
		this.selectedBioFormulaId = selectedBioFormulaId;
	}
	public List<BioMaterialCompositionModel> getBioMaterialCompositionModelList() {
		return bioMaterialCompositionModelList;
	}
	public void setBioMaterialCompositionModelList(List<BioMaterialCompositionModel> bioMaterialCompositionModelList) {
		this.bioMaterialCompositionModelList = bioMaterialCompositionModelList;
	}

	


}
