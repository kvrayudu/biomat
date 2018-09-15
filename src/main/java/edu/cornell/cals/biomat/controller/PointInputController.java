package edu.cornell.cals.biomat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.model.variable.BioVariableSearchForm;
import edu.cornell.cals.biomat.model.variable.BioVariableSearchResultsForm;
import edu.cornell.cals.biomat.service.BioVariableService;

@Controller
public class PointInputController {
	Logger logger = LoggerFactory.getLogger(PointInputController.class);
	
	
	@GetMapping("/addPointInput")
	public ModelAndView displayDataInputPage() {
		logger.info("displayDataInputPage {} " );
		
		ModelAndView  mv = new ModelAndView("contribute/addPointInput");
		return mv;
	}	

}
