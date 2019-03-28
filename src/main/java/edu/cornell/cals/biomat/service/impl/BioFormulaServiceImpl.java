package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.controller.BioMaterialRestController;
import edu.cornell.cals.biomat.dao.BioComposition;
import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.BioMaterialCompositionModel;
import edu.cornell.cals.biomat.model.formula.BioFormulaForm;
import edu.cornell.cals.biomat.repository.BioCompositionRepository;
import edu.cornell.cals.biomat.repository.BioFormulaRepository;
import edu.cornell.cals.biomat.repository.FormulaRepository;
import edu.cornell.cals.biomat.service.BioCompositionService;
import edu.cornell.cals.biomat.service.BioCompositionService;
import edu.cornell.cals.biomat.service.BioFormulaService;
import edu.cornell.cals.biomat.service.BioVariableService;
import edu.cornell.cals.biomat.util.BalancedParenthesisCheck;
import edu.cornell.cals.biomat.util.ExpressionEvaluator;

@Service
public class BioFormulaServiceImpl implements BioFormulaService{
	
	Logger logger = LoggerFactory.getLogger(BioMaterialRestController.class);
	public static String DATA_POINTS_X = "dataPointsX";
	public static String DATA_POINTS_Y = "dataPointsY";
	@Autowired
	BioFormulaRepository bioFormulaRepository;
	@Autowired
	BioFormulaService bioFormulaService;

	@Autowired
	BioVariableService bioVariableService;
	
	@Autowired
	FormulaRepository formulaRepository;
	@Autowired
	BioCompositionRepository bioCompositionRepository;
	
	
	
	public Map<String,List<Double>> getCalculatedDataPoints(Long formulaId, 
			List<BioMaterialNutrientModel> bioMaterialNutrientModelList,
			Integer dependentVariableId,
			Integer minRange, Integer maxRange){
		logger.info("getCalculatedDataPoints");
		
		List<Double> dataPointsY = new ArrayList<Double>();
		List<Double> dataPointsX = new ArrayList<Double>();
		
		BioFormula bf =  getBioMaterialFormula(formulaId);
		String dependentVariable = "";
		try {
			BioVariable dv = bioVariableService.getBioVariable(dependentVariableId.intValue());
			dependentVariable = dv.getSymbol();
		}
		catch(EntityNotFoundException ex) {
			BioComposition bc  = bioCompositionRepository.getOne(dependentVariableId);
			dependentVariable = bc.getTagName();
		}
		
		String formula = bioFormulaService.flattenFormula(bf.getFormula());
		logger.info("flattened Formula: {}" ,formula );
		
		Map<String,Double> valueMap = new HashMap<String,Double>();
		List<Map<String,Double>>  valueMapList = new ArrayList<Map<String,Double>>();
		
		if(bioMaterialCompositionModelList!=null) {
			for (BioMaterialCompositionModel model : bioMaterialCompositionModelList) {
				if(model.getNutrientSymbol().equals(dependentVariable)) 	continue;
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
			valueMap.put(dependentVariable, datapointX);
			double dataPointY = ExpressionEvaluator.eval(formula,valueMap);
			dataPointsX.add(datapointX);
			dataPointsY.add(dataPointY);
		}	
		
		
		Map<String,List<Double>> resultMap = new HashMap<String,List<Double>>();
		resultMap.put(DATA_POINTS_X, dataPointsX);
		resultMap.put(DATA_POINTS_Y , dataPointsY);
		return resultMap;
	}
	
	protected String substituteVariables(String formula, List<BioMaterialCompositionModel> bioMaterialCompositionModelList) {
		for (BioMaterialCompositionModel model : bioMaterialCompositionModelList) {
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
		bioFormula.setName(bioFormulaForm.getName().toUpperCase());
		bioFormula.setFormula(bioFormulaForm.getFormula());
		bioFormula.setVariableId(bioFormulaForm.getVariableId());
		bioFormula.setMinRange(bioFormulaForm.getMinRange());
		bioFormula.setMaxRange(bioFormulaForm.getMaxRange());
		bioFormula.setCitation(bioFormulaForm.getCitation());
		bioFormula.setDoi(bioFormulaForm.getDoi());
		bioFormula.setFormulaDesc(bioFormulaForm.getFormulaDesc());
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
