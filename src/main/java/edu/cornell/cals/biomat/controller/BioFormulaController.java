package edu.cornell.cals.biomat.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.model.formula.BioFormulaForm;
import edu.cornell.cals.biomat.model.formula.BioFormulaSearchForm;
import edu.cornell.cals.biomat.repository.BioVariableRepository;
import edu.cornell.cals.biomat.service.BioFormulaService;

@Controller
public class BioFormulaController {
	Logger logger = LoggerFactory.getLogger(BioFormulaController.class);
	
	@Autowired
	BioFormulaService bioFormulaService;
	
	@Autowired
	BioVariableRepository bioVariableRepository;
	
	
	@GetMapping("searchBioFormula")
	public ModelAndView displaySearchBioFormulaPage() {
		logger.info("Start displaySearchBioFormula" );
		BioFormulaSearchForm bioFormulaSearchForm = new BioFormulaSearchForm();
		ModelAndView  mv = new ModelAndView("contribute/searchBioFormula","bioFormulaSearchForm",bioFormulaSearchForm);
		return mv;
	}	
	
	@PostMapping("searchBioFormula")
	public ModelAndView searchBioFormulaPage(HttpServletRequest request, @Valid @ModelAttribute BioFormulaSearchForm bioFormulaSearchForm, BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("Start displaySearchBioFormula" );
		ModelAndView  mv;
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Searching for  Data. ");
		}
		else {
			List<BioFormula> bioFormula = bioFormulaService.getBioFormulaByNameOrDesc(bioFormulaSearchForm.getSearchString());
			bioFormulaSearchForm.setBioFormulaList(bioFormula);
		}
		mv = new ModelAndView("contribute/searchBioFormula","bioFormulaSearchForm",bioFormulaSearchForm);
		return mv;
	}	
	
	
	@GetMapping("addBioFormula")
	public ModelAndView displayAddBioMaterialFormulaPage() {
		logger.info("Start displayAddBioMaterialFormulaPage" );
		
		BioFormulaForm bioFormulaForm = new BioFormulaForm();
		bioFormulaForm.setBioVariables(bioVariableRepository.findAll());
		ModelAndView  mv = new ModelAndView("contribute/addBioFormula","bioFormulaForm",bioFormulaForm);
		return mv;
	}	
	
	@PostMapping("addBioFormula")
	public ModelAndView saveBioMaterialFormula(HttpServletRequest request, @Valid @ModelAttribute BioFormulaForm bioFormulaForm, BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("Start saveBioMaterialFormula {}", bioFormulaForm  );
		bioFormulaForm.setBioVariables(bioVariableRepository.findAll());
		if(bioFormulaForm.getName()!=null)
			bioFormulaForm.setName(bioFormulaForm.getName().toUpperCase());
		
		ModelAndView  mv;
		Map<String,String> errors = new HashMap();
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
			mv = new ModelAndView("contribute/addBioFormula","bioFormulaForm",bioFormulaForm);
		}
		else if(!bioFormulaService.isValidFormula(bioFormulaForm.getName(),bioFormulaForm.getFormula(), errors)) {
			errors.forEach((k, v) -> bindingResult.rejectValue(k,k,v));
			mv = new ModelAndView("contribute/addBioFormula","bioFormulaForm",bioFormulaForm);
		}
		else {
			bioFormulaService.updateBioMaterialFormula(bioFormulaForm, principal.getName());
			mv = new ModelAndView("contribute/addBioFormula","bioFormulaForm",bioFormulaForm);
			mv.addObject("successMessage", "Thanks for Contributing your Bio-Material Formula.  A message is sent to the administrator for approval. You will get another email when administrator takes an action.");
		}

		return mv;
	}	
	
}
