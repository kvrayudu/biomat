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
import edu.cornell.cals.biomat.service.BioMaterialService;

@RestController
public class BioMaterialRestController {
	Logger logger = LoggerFactory.getLogger(BioMaterialRestController.class);
	@Autowired
	protected BioMaterialService bioMaterialService;

	
	@GetMapping("getMaterials")
	public ResponseEntity<String>  searchBiMaterials(@RequestParam(value="q", required=false) String q) throws Exception {
		logger.info("Start searchBiMaterials {} " + q);
		List<BioMaterial>bioMaterials = new ArrayList<BioMaterial>();
		if(q.length() >=3)
			bioMaterials =bioMaterialService.getBioMaterial(q);
		ObjectMapper mapper = new ObjectMapper();
		String jsonMaterialArray = mapper.writeValueAsString(bioMaterials);
		
		String s="{\"msg\":\"\", \"data\":" + jsonMaterialArray +"}";
		//String s = "{\"msg\":\"\", \"data\":[{\"id\":1,\"name\":\"Auckland\",\"hasc\":\"AU\"},{\"id\":2,\"name\":\"Bay of Plenty\",\"hasc\":\"BP\"},{\"id\":3,\"name\":\"Canterbury\",\"hasc\":\"CA\"},{\"id\":4,\"name\":\"Chatham Islands\",\"hasc\":\"CI\"},{\"id\":5,\"name\":\"Gisborne\",\"hasc\":\"GI\"},{\"id\":6,\"name\":\"Hawke's Bay\",\"hasc\":\"HB\"},{\"id\":7,\"name\":\"Manawatu-Wanganui\",\"hasc\":\"MW\"},{\"id\":8,\"name\":\"Marlborough\",\"hasc\":\"MA\"},{\"id\":9,\"name\":\"Nelson\",\"hasc\":\"NE\"},{\"id\":10,\"name\":\"Northland\",\"hasc\":\"NO\"},{\"id\":11,\"name\":\"Otago\",\"hasc\":\"OT\"},{\"id\":12,\"name\":\"Southland\",\"hasc\":\"SO\"},{\"id\":13,\"name\":\"Taranaki\",\"hasc\":\"TK\"},{\"id\":14,\"name\":\"Tasman\",\"hasc\":\"TS\"},{\"id\":15,\"name\":\"Waikato\",\"hasc\":\"WK\"},{\"id\":16,\"name\":\"Wellington\",\"hasc\":\"WG\"},{\"id\":17,\"name\":\"West Coast\",\"hasc\":\"WC\"}]}";
		logger.info("Start searchBiMaterials " + s);
		
		//return s;
		return ResponseEntity.ok(s);
	}
	

}
