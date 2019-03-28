package edu.cornell.cals.biomat.service;

import java.util.List;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioFormulaMaterial;
import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;

public interface BioFormulaMaterialService {
	BioFormulaMaterial getBioFormulaMaterial(Long id);
	List<BioFormulaMaterial> getBioFormulaMaterialByMaterialId(Long materialId);
	
	
	public List<BioVariable> getBioVariables(Long materialId);
	List<BioVariable> getBioVariables(List<BioFormulaMaterial>  bfmList);

	public List<BioVariable> getBioDependentVariables(Long materialId, Integer variableID);
	public List<BioVariable> getBioDependentVariables(List<BioFormulaMaterial>  bfmList,Integer variableID);

	public List<BioFormula> getBioFormula(Long materialId, Integer variableID, Integer dependentVariableID);
	public List<BioFormula> getBioFormula(List<BioFormulaMaterial>  bfmList, Integer variableID, Integer dependentVariableID);
	List<BioMaterial> getBioMaterialByFormulaId(Long selectedFormulaId);
	public void delete(String materialId, String formulaId);
	void addBioFormula(Long selectedFormulaId, Long selectedBioMaterialId);
	
}
