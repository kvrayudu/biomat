package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;
import edu.cornell.cals.biomat.controller.BioMaterialsController;
import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioFormulaMaterial;
import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.repository.BioFormulaMaterialRepository;
import edu.cornell.cals.biomat.service.BioFormulaMaterialService;

@Service
public class BioFormulaMaterialServiceImpl implements BioFormulaMaterialService{

	@Autowired
	BioFormulaMaterialRepository bioFormulaMaterialRepository;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(BioFormulaMaterialServiceImpl.class);
	
	@Override
	public BioFormulaMaterial getBioFormulaMaterial(Long id) {
		return bioFormulaMaterialRepository.getOne(id);
	}

	@Override
	public List<BioFormulaMaterial> getBioFormulaMaterialByMaterialId(Long materialId) {
		return bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);
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

	@Override
	public List<BioFormula> getBioFormula(Long materialId, Integer variableID, Integer dependentVariableID) {
		List<BioFormulaMaterial>  bfmList = bioFormulaMaterialRepository.getBioFormulaMaterialByMaterialId(materialId);

		return getBioFormula(bfmList,variableID, dependentVariableID);
	}

	public List<BioFormula> getBioFormula(List<BioFormulaMaterial>  bfmList, Integer variableID, Integer dependentVariableID) {
		List<BioFormula> bfList = new ArrayList<BioFormula>();
		bfList =bfmList.stream()
				.filter(bfm-> bfm.getBioFormula().getBioVariable().getId().intValue()==variableID.intValue() && bfm.getBioFormula().getBioDependentVariable().getId().intValue()==dependentVariableID.intValue())
				.map(bfm -> bfm.getBioFormula()).distinct()
				.collect(Collectors.toList());
				
		return bfList;
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
	@Transactional
	public void addBioFormula(Long selectedFormulaId, Long selectedBioMaterialId) {
		
		BioFormulaMaterial bfm = new BioFormulaMaterial();
		bfm.setFormulaId(selectedFormulaId);
		bfm.setMaterialId(selectedBioMaterialId);
		bioFormulaMaterialRepository.save(bfm);;
		
	}	
}
