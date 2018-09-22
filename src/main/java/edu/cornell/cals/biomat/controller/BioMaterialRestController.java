package edu.cornell.cals.biomat.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.service.BioMaterialService;
import edu.cornell.cals.biomat.service.BioVariableService;

@RestController
public class BioMaterialRestController {
	Logger logger = LoggerFactory.getLogger(BioMaterialRestController.class);
	
	@Autowired
	protected BioMaterialService bioMaterialService;

	@Autowired
	protected BioVariableService bioVariableService;
	
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
