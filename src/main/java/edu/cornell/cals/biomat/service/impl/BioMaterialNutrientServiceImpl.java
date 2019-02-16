package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioMaterialNutrient;
import edu.cornell.cals.biomat.model.BioMaterialNutrientModel;
import edu.cornell.cals.biomat.repository.BioMaterialNutrientRepository;
import edu.cornell.cals.biomat.service.BioMaterialNutrientService;
import edu.cornell.cals.biomat.util.ExpressionEvaluator;

@Service
public class BioMaterialNutrientServiceImpl implements BioMaterialNutrientService{
	@Autowired
	BioMaterialNutrientRepository bioMaterialNutrientRepository;
	
	@Override
	public List<BioMaterialNutrient> getNutrients(Long materialId) {
		List<BioMaterialNutrient>  list =  bioMaterialNutrientRepository.findByIdMaterialId(materialId);
		return list;
	}

	@Override
	public List<BioMaterialNutrientModel> getBioMaterialNutrientsForFormula(Long materialId, BioFormula formula) {
		List<BioMaterialNutrient>  bioMaterialNutrientList =  bioMaterialNutrientRepository.findByIdMaterialId(materialId);
		List<String> variablesList =  ExpressionEvaluator.getVariableList(formula.getFormula());
		variablesList.remove(formula.getBioDependentVariable().getSymbol());
		List<BioMaterialNutrientModel>bioMaterialNutrientModelList = new ArrayList<BioMaterialNutrientModel>();

		bioMaterialNutrientList
		.stream()
		.forEach(bmn -> {
			if(variablesList.contains(bmn.getBioVariable().getSymbol())) {
				BioMaterialNutrientModel bmnm = new BioMaterialNutrientModel ();
				bmnm.setBioMaterialId(bmn.getBioMaterial().getId());
				bmnm.setBioNutrientId((long)bmn.getBioVariable().getId());
				bmnm.setNutrientName(bmn.getBioVariable().getName());
				bmnm.setNutrientValue(bmn.getNutrientValue());
				bmnm.setNutrientUnit(bmn.getBioVariable().getSiUnit());
				bmnm.setNutrientSymbol(bmn.getBioVariable().getSymbol());
				bioMaterialNutrientModelList.add(bmnm);
			}
		});
	
		
		
		
		variablesList
		.stream()
		.forEach( variableInFormula ->{
			if(!variableInList(bioMaterialNutrientList,variableInFormula)) {
				BioMaterialNutrientModel bmnm = new BioMaterialNutrientModel ();
				bmnm.setBioMaterialId(materialId);
				bmnm.setBioNutrientId(-1l);
				bmnm.setNutrientName(variableInFormula);
				bmnm.setNutrientValue(0.0);
				bmnm.setNutrientUnit("");
				bmnm.setNutrientSymbol(variableInFormula);
				bioMaterialNutrientModelList.add(bmnm);
			}
		});
		
		return bioMaterialNutrientModelList;
	}
	
	protected boolean variableInList(List<BioMaterialNutrient>  bioMaterialNutrientList, String variable) {
		List<BioMaterialNutrient> list = bioMaterialNutrientList
				.stream()
				.filter(bmn->bmn.getBioVariable().getSymbol()!=null && bmn.getBioVariable().getSymbol().equalsIgnoreCase(variable))
				.collect(Collectors.toList());
		
		return (list.size()>0);
	}
	

}
