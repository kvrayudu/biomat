package edu.cornell.cals.biomat.controller;

import java.security.Principal;

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

import edu.cornell.cals.biomat.model.formula.BioFormulaForm;
import edu.cornell.cals.biomat.service.BioFormulaService;
import edu.cornell.cals.biomat.util.BalancedParenthesisCheck;

@Controller
public class BioFormulaController {
	Logger logger = LoggerFactory.getLogger(BioFormulaController.class);
	
	@Autowired
	BioFormulaService bioFormulaService;
	
	@GetMapping("addBioMaterialFormula")
	public ModelAndView displayAddBioMaterialFormulaPage() {
		logger.info("Start displayAddBioMaterialFormulaPage" );
		
		BioFormulaForm bioFormulaForm = new BioFormulaForm();
		
		ModelAndView  mv = new ModelAndView("contribute/addBioMaterialFormula","bioFormulaForm",bioFormulaForm);
		return mv;
	}	
	
	@PostMapping("addBioMaterialFormula")
	public ModelAndView saveBioMaterialFormula(HttpServletRequest request, @Valid @ModelAttribute BioFormulaForm bioFormulaForm, BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("Start saveBioMaterialFormula {}", bioFormulaForm  );
		ModelAndView  mv;
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
			mv = new ModelAndView("contribute/addBioMaterialFormula","bioFormulaForm",bioFormulaForm);
		}
		else if(!BalancedParenthesisCheck.valid(bioFormulaForm.getFormula())) {
			logger.info("Error in Form Submission. Formula's braces are NOT Balanced.   NOT Updating Data.  {}" ,bioFormulaForm.getFormula());
			bindingResult.rejectValue("formula","formula","Formula's braces are NOT balanced");
			mv = new ModelAndView("contribute/addBioMaterialFormula","bioFormulaForm",bioFormulaForm);
		}
		/*else if(!bioFormulaForm.isValidated()) {
			List<Map<String,Object>> variableList = ExpressionEvaluator.getVariables(bioFormulaForm.getFormula());
			List<String> list = new ArrayList<String>();
			
			variableList.stream().forEach((map)->{
				list.add((String)map.get(ExpressionEvaluator.KEY_VARIABLE));
			});
			bioFormulaForm.setVariables(list);
			mv = new ModelAndView("contribute/addBioMaterialFormula","bioFormulaForm",bioFormulaForm);
		}*/
		else {
			bioFormulaService.updateBioMaterialFormula(bioFormulaForm, principal.getName());
			mv = new ModelAndView("contribute/addBioMaterialFormula","bioFormulaForm",bioFormulaForm);
			mv.addObject("successMessage", "Thanks for Contributing your Bio-Material Formula.  A message is sent to the administrator for approval. You will get another email when administrator takes an action.");
		}

		return mv;
	}	
	
}
