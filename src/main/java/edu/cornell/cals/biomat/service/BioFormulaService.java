package edu.cornell.cals.biomat.service;

import java.util.List;
import java.util.Map;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.model.BioMaterialNutrientModel;
import edu.cornell.cals.biomat.model.formula.BioFormulaForm;

public interface BioFormulaService {
	BioFormula getBioMaterialFormula(Long id);
	List<BioFormula> getBioFormulaByVariableId(Integer variableId);
	BioFormula updateBioMaterialFormula(BioFormulaForm bioFormulaForm,String userId);
	
	public Map<String,List<Double>> getCalculatedDataPoints(Long formulaId, List<BioMaterialNutrientModel> bioMaterialNutrientModelList,Integer dependentVariableId, Integer minRange, Integer maxRange);
}
