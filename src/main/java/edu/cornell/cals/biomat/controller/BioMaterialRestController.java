package edu.cornell.cals.biomat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.BioMaterialNutrientModel;
import edu.cornell.cals.biomat.model.material.BioMaterialGraphForm;
import edu.cornell.cals.biomat.service.BioFormulaMaterialService;
import edu.cornell.cals.biomat.service.BioFormulaService;
import edu.cornell.cals.biomat.service.BioMaterialNutrientService;
import edu.cornell.cals.biomat.service.BioMaterialService;
import edu.cornell.cals.biomat.service.BioVariableService;

@RestController
public class BioMaterialRestController {
	Logger logger = LoggerFactory.getLogger(BioMaterialRestController.class);
	
	@Autowired
	protected BioMaterialService bioMaterialService;

	@Autowired
	protected BioMaterialNutrientService bioMaterialNutrientService;

	@Autowired
	protected BioVariableService bioVariableService;
	
	@Autowired
	protected BioFormulaMaterialService bioFormulaMaterialService;
	
	@Autowired
	protected BioFormulaService bioFormulaService;


	
	@PostMapping("getCalculatedDataPoints")
	public ResponseEntity<String>  getCalculatedDataPoints(@ModelAttribute BioMaterialGraphForm bioMaterialGraphForm) throws Exception {
		logger.info("getCalculatedDataPoints() {} {} {} ", bioMaterialGraphForm);
		
		
		Map<String,List<Double>> resultMap = bioFormulaService.getCalculatedDataPoints(
				bioMaterialGraphForm.getSelectedBioFormulaId().longValue(),
				bioMaterialGraphForm.getBioMaterialNutrientModelList(),
				bioMaterialGraphForm.getSelectedDependentBioVariableId(),
				bioMaterialGraphForm.getMinRange(),bioMaterialGraphForm.getMaxRange());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonVariablesArray = mapper.writeValueAsString(resultMap );
		String s="{\"msg\":\"\", \"data\":" + jsonVariablesArray +"}";
		logger.info("Returning dataPointList{} {}  ", resultMap,s);
		return ResponseEntity.ok(s);
		

		
	}
	
	@GetMapping("deleteBioMaterialFormula")
	public ResponseEntity<String>  deleteBioMaterialFormula(@RequestParam(value="materialId", required=true) String materialId, @RequestParam(value="formulaId", required=true) String formulaId) throws Exception {
		logger.info("deleteBioMaterialFormula() --> "+ materialId+" "+formulaId);
		bioFormulaMaterialService.delete(materialId, formulaId);
		return ResponseEntity.ok("Success");
	}

	

	@GetMapping("getBioMatrialNutrients")
	public ResponseEntity<String>  getBioMatrialNutrients(
			@RequestParam(value="materialId", required=false) String materialId,
			@RequestParam(value="formulaId", required=false) String formulaId) throws Exception {
		logger.info("getBioMatrialNutrients) {} {}", materialId,formulaId);
		
		List<BioMaterialNutrientModel> bmnmList = bioMaterialNutrientService.getBioMaterialNutrientsForFormula(
				Long.parseLong(materialId), 
				bioFormulaService.getBioMaterialFormula(Long.parseLong(formulaId)));
		bmnmList.forEach(System.out::println);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonVariablesArray = mapper.writeValueAsString(bmnmList);
		String s="{\"msg\":\"\", \"data\":" + jsonVariablesArray +"}";
		logger.info("Returning getBioMatrialNutrients{} {} {} {}  ",s);
		return ResponseEntity.ok(s);
		
	}

	

	@GetMapping("getBioFormula")
	public ResponseEntity<String>  getBioFormula(@RequestParam(value="materialId", required=false) String materialId,@RequestParam(value="variableId", required=false) String variableId,@RequestParam(value="dependentVariableId", required=false) String dependentVariableId) throws Exception {
		logger.info("getBioFormula() {} {} {} ", materialId,  variableId,dependentVariableId);
		List<BioFormula> bfList = bioFormulaMaterialService.getBioFormula(Long.parseLong(materialId), Integer.parseInt(variableId),Integer.parseInt(dependentVariableId));
		bfList.forEach(System.out::println);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String jsonVariablesArray = mapper.writeValueAsString(bfList);
		String s="{\"msg\":\"\", \"data\":" + jsonVariablesArray +"}";
		logger.info("Returning Bio Formula{} {} {} {}  ", materialId,variableId,dependentVariableId,s);
		return ResponseEntity.ok(s);
		
	}

	
	@GetMapping("getDependentVariablesForBiMaterial")
	public ResponseEntity<String>  getDependentVariablesForBiMaterial(@RequestParam(value="materialId", required=false) String materialId,@RequestParam(value="variableId", required=false) String variableId) throws Exception {
		logger.info("getDependentVariablesForBiMaterial() {} {}  ", materialId,  variableId);
		List<BioVariable> bvList = bioFormulaMaterialService.getBioDependentVariables(Long.parseLong(materialId), Integer.parseInt(variableId));
		ObjectMapper mapper = new ObjectMapper();
		String jsonVariablesArray = mapper.writeValueAsString(bvList);
		String s="{\"msg\":\"\", \"data\":" + jsonVariablesArray +"}";
		logger.info("Returning dependent Variable for MaterialId and variableId {} {} {}  ", materialId,variableId,s);
		return ResponseEntity.ok(s);
		
	}

	
	
	@GetMapping("getVariablesForBiMaterial")
	public ResponseEntity<String>  getVariablesForBiMaterial(@RequestParam(value="materialId", required=false) String materialId) throws Exception {
		logger.info("getVariablesForBiMaterial() {}  ", materialId);
		List<BioVariable> bvList = bioFormulaMaterialService.getBioVariables(Long.parseLong(materialId));
		ObjectMapper mapper = new ObjectMapper();
		String jsonVariablesArray = mapper.writeValueAsString(bvList);
		String s="{\"msg\":\"\", \"data\":" + jsonVariablesArray +"}";
		logger.info("Returning variables for Material {} {}  ", materialId,s);
		return ResponseEntity.ok(s);
		
	}

	
	@GetMapping("getMaterials")
	public ResponseEntity<String>  searchBioMaterials(@RequestParam(value="q", required=false) String q) throws Exception {
		logger.info("Start searchBioMaterials {} " + q);
		List<BioMaterial>bioMaterials = new ArrayList<BioMaterial>();
		if(q.length() >=3)
			bioMaterials =bioMaterialService.getBioMaterial(q);
		ObjectMapper mapper = new ObjectMapper();
		String jsonMaterialArray = mapper.writeValueAsString(bioMaterials);
		
		String s="{\"msg\":\"\", \"data\":" + jsonMaterialArray +"}";
		logger.info("end searchBioMaterials " + s);
		return ResponseEntity.ok(s);
	}
	
	@GetMapping("getFormula")
	public ResponseEntity<String>  getFormulae(@RequestParam(value="q", required=false) String q) throws Exception {
		logger.info("Start getFormulae {} " + q);
		List<BioFormula>bioFormulas = new ArrayList<BioFormula>();
		if(q.length() >=3)
			bioFormulas = bioFormulaService.getBioFormulaByName(q);
		ObjectMapper mapper = new ObjectMapper();
		String jsonMaterialArray = mapper.writeValueAsString(bioFormulas);
		
		String s="{\"msg\":\"\", \"data\":" + jsonMaterialArray +"}";
		logger.info("end searchBioMaterials " + s);
		return ResponseEntity.ok(s);
	}
	
	
	@GetMapping("getMaterialsWithFormula")
	public ResponseEntity<String>  searchBioMaterialsWithFormula(@RequestParam(value="q", required=false) String q) throws Exception {
		logger.info("Start searchBioMaterialsWithFormula {} " + q);
		List<BioMaterial>bioMaterials = new ArrayList<BioMaterial>();
		bioMaterials =bioMaterialService.getBioMaterialWithFormula(q);
		ObjectMapper mapper = new ObjectMapper();
		String jsonMaterialArray = mapper.writeValueAsString(bioMaterials);
		
		String s="{\"msg\":\"\", \"data\":" + jsonMaterialArray +"}";
		logger.info("end searchBioMaterials " + s);
		return ResponseEntity.ok(s);
	}
	
	@GetMapping("getVariables")
	public ResponseEntity<String>  searchBioVariables(@RequestParam(value="q", required=false) String q) throws Exception {
		logger.info("Start searchBioVariables {} " + q);
		List<BioVariable>bioVariables = new ArrayList<BioVariable>();
		if(q.length() >=1)
			bioVariables=bioVariableService.getBioVariable(q);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonVariablesArray = mapper.writeValueAsString(bioVariables);
		
		String s="{\"msg\":\"\", \"data\":" + jsonVariablesArray +"}";
		logger.info("end searchBiVariables " + s);
		return ResponseEntity.ok(s);
	}
}
