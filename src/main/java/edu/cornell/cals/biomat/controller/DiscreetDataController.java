package edu.cornell.cals.biomat.controller;

import java.security.Principal;

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
import org.springframework.web.servlet.ModelAndView;

import edu.cornell.cals.biomat.dao.BioDiscreetData;

import edu.cornell.cals.biomat.repository.BioDiscreetDataRepository;


@Controller
public class DiscreetDataController {

	@Autowired
	BioDiscreetDataRepository bioDiscreetDataRepository;

	
	Logger logger = LoggerFactory.getLogger(DiscreetDataController.class);
	@GetMapping("addDiscreetData")
	public ModelAndView displayAddDiscreetDataPage() {
		logger.info("Start" );
		BioDiscreetData BDD = new BioDiscreetData ();
		ModelAndView  mv = new ModelAndView("contribute/addDiscreetData","bioDiscreetData",BDD);
		return mv;
	}

	@PostMapping("addDiscreetData")
	public ModelAndView saveAddDiscreetDataPage(  @Valid @ModelAttribute BioDiscreetData BDD,BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("Start " );
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
			ModelAndView  mv = new ModelAndView("contribute/addDiscreetData","bioDiscreetData",BDD);
			return mv;
		}
		else if(principal ==null) {
			throw new RuntimeException("User is not authorized to update a material");
		}
		else {
			BDD.setAddedBy(principal.getName());
			bioDiscreetDataRepository.save(BDD);
			ModelAndView  mv = new ModelAndView("contribute/addDiscreetData","bioDiscreetData",BDD);
			mv.addObject("successMessage","Successfully Saved Discreet DataSet");
			return mv;
		}
	}

}
