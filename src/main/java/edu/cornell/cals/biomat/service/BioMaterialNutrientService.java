package edu.cornell.cals.biomat.service;

import java.util.List;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioMaterialNutrient;
import edu.cornell.cals.biomat.model.BioMaterialNutrientModel;

public interface BioMaterialNutrientService {
	List<BioMaterialNutrient> getNutrients(Long materialId);
	List<BioMaterialNutrientModel> getBioMaterialNutrientsForFormula(Long meatrialId, BioFormula formula);
}
