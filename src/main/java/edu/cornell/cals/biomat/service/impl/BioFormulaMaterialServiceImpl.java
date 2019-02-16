package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioFormulaMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.repository.BioFormulaMaterialRepository;
import edu.cornell.cals.biomat.service.BioFormulaMaterialService;

@Service
public class BioFormulaMaterialServiceImpl implements BioFormulaMaterialService{

	@Autowired
	BioFormulaMaterialRepository bioFormulaMaterialRepository;

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
	
}
