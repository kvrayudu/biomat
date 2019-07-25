package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.LoggerFactory;
import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;
import edu.cornell.cals.biomat.controller.BioMaterialsController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.dao.BioComposition;
import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioFormulaMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.BioVariableAndCompostionModel;
import edu.cornell.cals.biomat.repository.BioCompositionRepository;
import edu.cornell.cals.biomat.repository.BioFormulaMaterialRepository;
import edu.cornell.cals.biomat.repository.BioVariableRepository;
import edu.cornell.cals.biomat.service.BioFormulaMaterialService;
import edu.cornell.cals.biomat.service.BioFormulaService;
import edu.cornell.cals.biomat.util.ExpressionEvaluator;
import edu.cornell.cals.biomat.dao.BioMaterial;

@Service
public class BioFormulaMaterialServiceImpl implements BioFormulaMaterialService{

	@Autowired
	BioFormulaMaterialRepository bioFormulaMaterialRepository;
	@Autowired
	BioFormulaService bioFormulaService;
	@Autowired
	BioCompositionRepository bioCompositionRepository;
	@Autowired
	BioVariableRepository bioVariableRepository;
	org.slf4j.Logger logger = LoggerFactory.getLogger(BioFormulaMaterialServiceImpl.class);

	@Override
	public BioFormulaMaterial getBioFormulaMaterial(Long id) {
		return bioFormulaMaterialRepository.getOne(id);
	}

	@Override
	public List<BioFormulaMaterial> getBioFormulaMaterialByMaterialId(Long materialId) {
		return bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);
	}
	
	@Override
	public List<BioMaterial> getBioMaterialByFormulaId(Long selectedFormulaId) {
		List<BioFormulaMaterial> bfms = bioFormulaMaterialRepository.getBioMaterialByFormulaId(selectedFormulaId);
		List<BioMaterial> bm = new ArrayList<BioMaterial>();
		for (BioFormulaMaterial bfm : bfms) {
			bm.add(bfm.getBioMaterial());
		}
		return bm;
	}

	@Override
	public void delete(String materialId, String formulaId) {
		bioFormulaMaterialRepository.deleteBioFormulaMaterialByMaterialIdAndFormulaId(Long.parseLong(materialId), Long.parseLong(formulaId));
	}

	@Override
	//@Transactional
	public void addMaterialToBioFormula(Long selectedFormulaId, Long selectedBioMaterialId, String userName) {

		BioFormulaMaterial bfm = new BioFormulaMaterial();
		bfm.setAddedBy(userName);
		bfm.setFormulaId(selectedFormulaId);
		bfm.setMaterialId(selectedBioMaterialId);
		bioFormulaMaterialRepository.save(bfm);;

	}	

	
	public List<BioVariable> getBioVariables(List<BioFormulaMaterial>  bfmList) {
		List<BioVariable> bvList = new ArrayList<BioVariable>();
		bvList = bfmList.stream().map(bfm -> bfm.getBioFormula().getBioVariable() ).distinct().collect(Collectors.toList());
		return bvList;
	}
	

	public List<BioVariable> getBioVariables(Long materialId) {
		List<BioFormulaMaterial>  bfmList = bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);
		List<BioVariable> bvList = new ArrayList<BioVariable>();
		bvList = bfmList.stream().map(bfm -> bfm.getBioFormula().getBioVariable() ).distinct().collect(Collectors.toList());
		return bvList;
	}
	
	public BioVariableAndCompostionModel getVariablesInFormula(Long materialId, int variableId){
		List<BioFormula> bioFormulaList = new ArrayList<BioFormula>();
		List<BioFormulaMaterial>  bfmList = bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);
		List<String> variables = new ArrayList<String>();
		
		/*
		bfmList.stream()
				.filter(bfm->bfm.getBioFormula().getBioVariable().getId().intValue()!=variableId)
				.map(bfm->bioFormulaList.add(bfm.getBioFormula()));
		 */
		for(BioFormulaMaterial bfm : bfmList) {
			if(bfm.getBioFormula().getBioVariable().getId().intValue()==variableId) {
				bioFormulaList.add(bfm.getBioFormula());
			}
		}
		for(BioFormula bioFormula:bioFormulaList) {

			variables.addAll(ExpressionEvaluator.getVariableList(bioFormulaService.flattenFormula(bioFormula.getFormula())));
			//variables.addAll(ExpressionEvaluator.getVariableList(bioFormulaService.flattenFormula(bioFormula.getName())));
		}
			
		//Filter duplicates
		variables = variables.stream().distinct().collect(Collectors.toList());
		
		List<BioComposition> bcList = bioCompositionRepository.getBioCompositionByTagNameList(variables);
		List<BioVariable> bvList = bioVariableRepository.getVariableBySymbolList(variables);
		BioVariableAndCompostionModel bioVariableAndCompostionModel = new BioVariableAndCompostionModel();
		bioVariableAndCompostionModel.setMaterialId(materialId);
		bioVariableAndCompostionModel.setVariableId(variableId);
		bioVariableAndCompostionModel.setBioVariables(bvList);
		bioVariableAndCompostionModel.setBioComposition(bcList);
		return bioVariableAndCompostionModel;
	
	}
	/*
	public List<BioVariable> getBioDependentVariables(Long materialId, Integer variableID) {
		List<BioFormulaMaterial>  bfmList = bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);
		return getBioDependentVariables(bfmList,variableID);
	}
	
	public List<BioVariable> getBioDependentVariables(List<BioFormulaMaterial>  bfmList, Integer variableID) {
		List<BioVariable> bvList = new ArrayList<BioVariable>();
		
	
		bvList = bfmList.stream()
				.filter(bfm->bfm.getBioFormula().getBioVariable().getId().intValue()==variableID.intValue())
				.map(bfm -> bfm.getBioFormula().getBioDependentVariable()).distinct()
				.collect(Collectors.toList());
	
			return bvList;
	}
*/
	@Override
	public List<BioFormula> getBioFormula(Long materialId, Integer variableID, Integer dependentVariableID) {
		List<BioFormulaMaterial>  bfmList = bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);

		return getBioFormula(bfmList,variableID, dependentVariableID);
	}

	public List<BioFormula> getBioFormula(List<BioFormulaMaterial>  bfmList, Integer variableID, Integer dependentVariableID) {
		List<BioFormula> bfList = new ArrayList<BioFormula>();
		String dependentVariableName ="";
		try {
			BioVariable bv = bioVariableRepository.getOne(dependentVariableID);
			dependentVariableName = bv.getSymbol();
		}
		catch(EntityNotFoundException ex) {
			BioComposition bc = bioCompositionRepository.getOne(dependentVariableID);
			dependentVariableName = bc.getTagName();
		}
		
		final String dvn = dependentVariableName;
		bfList =bfmList.stream()
				.filter(bfm-> bfm.getBioFormula().getBioVariable().getId().intValue()==variableID.intValue() &&
				ExpressionEvaluator.getVariableList(bioFormulaService.flattenFormula(bfm.getBioFormula().getFormula())).contains(dvn))
				.map(bfm -> bfm.getBioFormula()).distinct()
				.collect(Collectors.toList());
		return bfList;
	}
	
}
