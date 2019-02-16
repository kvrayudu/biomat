package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.BioMaterialNutrientModel;
import edu.cornell.cals.biomat.model.formula.BioFormulaForm;
import edu.cornell.cals.biomat.repository.BioFormulaRepository;
import edu.cornell.cals.biomat.service.BioFormulaService;
import edu.cornell.cals.biomat.service.BioVariableService;
import edu.cornell.cals.biomat.util.ExpressionEvaluator;

@Service
public class BioFormulaServiceImpl implements BioFormulaService{
	public static String DATA_POINTS_X = "dataPointsX";
	public static String DATA_POINTS_Y = "dataPointsY";
	@Autowired
	BioFormulaRepository bioFormulaRepository;

	@Autowired
	BioVariableService bioVariableService;
	
	
	public Map<String,List<Double>> getCalculatedDataPoints(Long formulaId, 
			List<BioMaterialNutrientModel> bioMaterialNutrientModelList,
			Integer dependentVariableId,
			Integer minRange, Integer maxRange){
		List<Double> dataPointsY = new ArrayList<Double>();
		List<Double> dataPointsX = new ArrayList<Double>();
		BioFormula bf =  getBioMaterialFormula(formulaId);
		BioVariable dependentVariable = bioVariableService.getBioVariable(dependentVariableId.intValue());
		String formula = bf.getFormula();
		
		Map<String,Double> valueMap = new HashMap<String,Double>();
		List<Map<String,Double>>  valueMapList = new ArrayList<Map<String,Double>>();
		if(bioMaterialNutrientModelList!=null) {
			for (BioMaterialNutrientModel model : bioMaterialNutrientModelList) {
				if(model.getNutrientSymbol() == dependentVariable.getSymbol()) 	continue;
				valueMap.put(model.getNutrientSymbol(),Double.parseDouble(model.getUserValue()));
			}
		}

		
//		List<Map<String,Object>> variableList= ExpressionEvaluator.getVariables(bf.getFormula());
	//	if(variableList.size()>1) {
		//	throw new RuntimeException("Can't calculate if more than one variable present");
		//}
		//String variable = (String)variableList.get(0).get(ExpressionEvaluator.KEY_VARIABLE);
		
		int increment = (maxRange - minRange)/10;
		for(int i=0;i<=10;i++) {
			Double datapointX = (double) (minRange + (i*increment));
			valueMap.put(dependentVariable.getSymbol(), datapointX);
			double dataPointY = ExpressionEvaluator.eval(bf.getFormula(),valueMap);
			dataPointsX.add(datapointX);
			dataPointsY.add(dataPointY);
		}	
		
		
		Map<String,List<Double>> resultMap = new HashMap<String,List<Double>>();
		resultMap.put(DATA_POINTS_X, dataPointsX);
		resultMap.put(DATA_POINTS_Y , dataPointsY);
		return resultMap;
	}
	
	protected String substituteVariables(String formula, List<BioMaterialNutrientModel> bioMaterialNutrientModelList) {
		for (BioMaterialNutrientModel model : bioMaterialNutrientModelList) {
			formula = formula.replaceAll(model.getNutrientSymbol(),model.getUserValue());
		}
		return null;
	}
	
	
	@Override
	public BioFormula getBioMaterialFormula(Long id) {
		return bioFormulaRepository.getOne(id);	
	}

	@Override
	public BioFormula updateBioMaterialFormula(BioFormulaForm bioFormulaForm, String userId) {
		BioFormula bioFormula = new BioFormula();
		bioFormula.setName(bioFormulaForm.getName());
		bioFormula.setFormula(bioFormulaForm.getFormula());
		bioFormula.setMinRange(bioFormulaForm.getMinRange());
		bioFormula.setMaxRange(bioFormulaForm.getMaxRange());
		bioFormula.setCitation(bioFormulaForm.getCitation());
		bioFormula.setDoi(bioFormulaForm.getDoi());
		bioFormula.setIsApproved("0");
		bioFormula.setUpdatedBy(userId);
		bioFormula.setAddedBy(userId);
		BioFormula bf =bioFormulaRepository.save(bioFormula);
		return bf;
	}

	@Override
	public List<BioFormula> getBioFormulaByVariableId(Integer variableId) {
		return bioFormulaRepository.getBioFormulaByVaribleId(variableId);
	}
	
	
}
