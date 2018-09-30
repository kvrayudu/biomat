package edu.cornell.cals.biomat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.cornell.cals.biomat.dao.BioMeasurement;
import edu.cornell.cals.biomat.model.measurement.BioMeasurementForm;
import edu.cornell.cals.biomat.model.measurement.MeasurementPair;
import edu.cornell.cals.biomat.service.BioMeasurementService;

@Controller
public class PointInputController {
	Logger logger = LoggerFactory.getLogger(PointInputController.class);
	
	@Autowired
	BioMeasurementService bioMeasurementService;
	
	@GetMapping("/addPointInput")
	public ModelAndView displayAddPointInputPage() {
		logger.info("Start" );
		BioMeasurementForm BMF = new BioMeasurementForm();
		MeasurementPair mp = new MeasurementPair();
		List<MeasurementPair> measurementPairs = new ArrayList<MeasurementPair>();
		measurementPairs.add(mp);
		BMF.setMeasurementPairs(measurementPairs);
		ModelAndView  mv = new ModelAndView("contribute/addPointInput","bioMeasurementForm",BMF);
		return mv;
	}
	
	@PostMapping("/addPointInput")
	public ModelAndView addBioMeasurement(HttpServletRequest request, @RequestParam("inputpicker-1") String materialName, @Valid @ModelAttribute BioMeasurementForm bioMeasurementForm,BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("Start {} {}" ,materialName, bioMeasurementForm);
		ModelAndView  mv =  new ModelAndView("contribute/addPointInput","bioMeasurementForm",bioMeasurementForm);
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
		}
		else if(principal ==null) {
			throw new RuntimeException("User is not authorized to update a material");
		}
		else {
			// Filter invalid pair. 
			// 1. Error and Measured Values both 0.  2. both null 3. id == null
			bioMeasurementForm.setMaterialName(materialName);
			List<MeasurementPair> filteredMeasurementPairs = bioMeasurementForm.getMeasurementPairs()
			.stream()
			.filter(pair-> !(pair.getMeasurementValue() == null && pair.getErrorValue()==null) || (pair.getMeasurementValue()==0.0 && pair.getErrorValue()==0.0) || (pair.getId()==null ))
			.collect(Collectors.toList());
			logger.info("filteredMeasurementPairs {}" , filteredMeasurementPairs);
			List<BioMeasurement> bmList = bioMeasurementService.addBioMaterial(bioMeasurementForm.getMaterialId(),bioMeasurementForm.getCitation(), bioMeasurementForm.getDoi(), filteredMeasurementPairs, principal.getName());
			logger.info("Updated BioMeasurements {}", bmList);
			mv.addObject("successMessage", "Thanks for the Input Point Data.  A message is sent to the administrator for approval. You will get another email when administrator takes an action. ");
		}
		
		return mv;
	}
	

}
