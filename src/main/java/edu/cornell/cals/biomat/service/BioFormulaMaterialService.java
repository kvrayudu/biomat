package edu.cornell.cals.biomat.service;

import java.util.List;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioFormulaMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.BioVariableAndCompostionModel;
import edu.cornell.cals.biomat.dao.BioMaterial;

public interface BioFormulaMaterialService {
	BioFormulaMaterial getBioFormulaMaterial(Long id);
	List<BioFormulaMaterial> getBioFormulaMaterialByMaterialId(Long materialId);

	List<BioVariable> getBioVariables(Long materialId);
	List<BioVariable> getBioVariables(List<BioFormulaMaterial>  bfmList);


	List<BioFormula> getBioFormula(Long materialId, Integer variableID, Integer dependentVariableID);
	List<BioFormula> getBioFormula(List<BioFormulaMaterial>  bfmList, Integer variableID, Integer dependentVariableID);

	List<BioMaterial> getBioMaterialByFormulaId(Long selectedFormulaId);
	public void delete(String materialId, String formulaId);
	void addMaterialToBioFormula(Long selectedFormulaId, Long selectedBioMaterialId, String userName);

	BioVariableAndCompostionModel getVariablesInFormula(Long materialId, int variableId);
	
}
