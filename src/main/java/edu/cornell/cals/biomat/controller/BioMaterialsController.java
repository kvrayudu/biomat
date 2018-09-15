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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.model.material.BioMaterialSearchForm;
import edu.cornell.cals.biomat.model.material.BioMaterialSearchResultsForm;
import edu.cornell.cals.biomat.service.BioMaterialService;

@Controller
public class BioMaterialsController {
	private static final int PAGE_SIZE 		= 10;
	private static final int PAGER_ELEMENTS = 15;
	Logger logger = LoggerFactory.getLogger(BioMaterialsController.class);
	
	
	@Autowired
	protected BioMaterialService bioMaterialService;
	
	
	
	@GetMapping("/updateBioMaterial")
	public ModelAndView displayUpdateBioMaterialPage(@RequestParam(value="materialId", required=true) Long materialId) {
		logger.info("updateBioMaterial {}", materialId );
		BioMaterial bioMaterial = bioMaterialService.getBioMaterial(materialId);
		logger.debug("Fetched BioMatrial {}", bioMaterial );
		
		ModelAndView  mv = new ModelAndView("materials/updateBioMaterial","bioMaterial",bioMaterial);
		return mv;
	}	

	@PostMapping("/updateBioMaterial")
	public ModelAndView updateBioMaterialPage(HttpServletRequest request, @Valid @ModelAttribute BioMaterial bioMaterial, BindingResult bindingResult,@AuthenticationPrincipal User user) {
		logger.info("POST updateBioMaterial:user {}  {}", bioMaterial , user);
		ModelAndView  mv ;
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
			mv = new ModelAndView("materials/updateBioMaterial","bioMaterial",bioMaterial);
			
		}
		else if(bioMaterial.getUsdaId()!=null) {
			throw new RuntimeException("USDA Material can't be updated");
		}
		else {
			BioMaterial bm = bioMaterialService.updateBioMaterial(bioMaterial,user.getUsername());
			logger.info("bioMaterialService.updateBioMaterial {}", bm);		
			mv = new ModelAndView("materials/updateBioMaterial","bioMaterial",bm);
			mv.addObject("message", "Successfully Updated Bio-Material");
		}
		return mv;
	}	

	
	@GetMapping("/searchBioMaterials")
	public ModelAndView displaySearchBioMaterialsPage(@ModelAttribute BioMaterialSearchForm bioMaterialSearchForm) {
	        
		logger.info("Start searchBioMaterials {}" , bioMaterialSearchForm);
		BioMaterialSearchForm BMSF = new BioMaterialSearchForm();
		BMSF.setUsdaOnly("a");
		logger.debug("Start searchBioMaterials {}" , bioMaterialSearchForm);
		ModelAndView  mv = new ModelAndView("materials/searchBioMaterials","bioMaterialSearchForm",BMSF);
		return mv;
	}
	
	@PostMapping("/searchBioMaterials")
	public ModelAndView paginatedBioMaterialsResultsPage(HttpServletRequest request, @ModelAttribute @Valid BioMaterialSearchForm bioMaterialSearchForm,BindingResult bindingResult,  @RequestParam(value="pageNumber", required=false, defaultValue = "0") Integer pageNumber) {
		logger.info("Start searchBioMaterials with params {}",bioMaterialSearchForm);;
		ModelAndView  mv =null;
		

		List<BioMaterial> bmList = new ArrayList<BioMaterial>();
		Map<String,Object> map = bioMaterialService.getBioMaterial(gotoPage(pageNumber), bioMaterialSearchForm.getBioMaterialName());
		Page<BioMaterial> boiMaterialPage = (Page<BioMaterial>)map.get("bioMaterialPage"); 		
		for(BioMaterial bm : boiMaterialPage){
			bmList.add(bm);
		}
		int totalBioMaterialPages= 	((int)map.get("count")) / PAGE_SIZE;
		if(((int)map.get("count")) % PAGE_SIZE > 0) totalBioMaterialPages++;
			
		BioMaterialSearchResultsForm BMSRF = new BioMaterialSearchResultsForm();
		
		if(totalBioMaterialPages <=  PAGER_ELEMENTS ) {
			BMSRF.setPagerStart(0);
			BMSRF.setPagerEnd(totalBioMaterialPages);
			
		}
		else {
			
			if(pageNumber <= 5) {
				BMSRF.setPagerStart(0);
				BMSRF.setPagerEnd(9);
			}
			else {
				
				BMSRF.setPagerStart(pageNumber-4);
				if(totalBioMaterialPages<(pageNumber+5))
					BMSRF.setPagerEnd(totalBioMaterialPages);
				else
					BMSRF.setPagerEnd(pageNumber+5);
			}
		}
		
		BMSRF.setBioMaterials(bmList);
		BMSRF.setLastPage(totalBioMaterialPages);
		BMSRF.setCurrentPage(pageNumber);
		BMSRF.setBioMaterialSearchForm(bioMaterialSearchForm);
		logger.info("Total Pages for this Search {}" , totalBioMaterialPages);
		logger.info("SearchData {} ",BMSRF);
		
		mv = new ModelAndView("materials/searchBioMaterialsResults","bioMaterialSearchResultsForm",BMSRF);
		return mv;
	}
	
	private PageRequest gotoPage(int pageNumber){
		PageRequest requestedPage = new PageRequest(pageNumber,PAGE_SIZE,Sort.Direction.ASC,"shortDesc");
		return requestedPage;
	}
	
	
}
