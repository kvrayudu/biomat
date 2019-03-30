package edu.cornell.cals.biomat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioMaterialComposition;
import edu.cornell.cals.biomat.model.material.BioMaterialCompositionForm;
import edu.cornell.cals.biomat.model.material.BioMaterialSearchForm;
import edu.cornell.cals.biomat.model.material.BioMaterialSearchResultsForm;
import edu.cornell.cals.biomat.model.material.BioObservedPointsForm;
import edu.cornell.cals.biomat.model.material.EditBioFormulaForm;
import edu.cornell.cals.biomat.service.BioFormulaMaterialService;
import edu.cornell.cals.biomat.service.BioMatEmailService;
import edu.cornell.cals.biomat.service.BioMaterialCompositionService;
import edu.cornell.cals.biomat.service.BioMaterialService;

@Controller
public class BioMaterialsController {
	private static final int PAGE_SIZE 		= 10;
	private static final int PAGER_ELEMENTS = 15;
	Logger logger = LoggerFactory.getLogger(BioMaterialsController.class);
	
	
	@Autowired
	protected BioMaterialService bioMaterialService;
	@Autowired
	BioMatEmailService bioMatEmailService;
	@Autowired
	protected BioMaterialCompositionService bioMaterialCompositionService;
	//@Autowired
	//protected BioMeasurementRepository bioMeasurementRepository;

	@Autowired
	protected BioFormulaMaterialService bioFormulaMaterialService;
	
	@GetMapping("bioObservedPoints")
	public ModelAndView displayBioObservedPoints() {
		logger.info("displayBioObservedPoints");
		BioObservedPointsForm bioObservedPointsForm = new BioObservedPointsForm();
		
		ModelAndView  mv = new ModelAndView("materials/bioObservedPoints","bioObservedPointsForm",bioObservedPointsForm);
		return mv;
	}	
	/*
	@PostMapping("bioObservedPoints")
	public ModelAndView getBioObservedPoints(@RequestParam(value="selectedBioMaterialId", required=true) Long materialId) {
		logger.info("getBioObservedPoints");
		BioObservedPointsForm bioObservedPointsForm = new BioObservedPointsForm();
		List<BioMeasurement> bioMeasurementList = bioMeasurementRepository.getBioMeasurementsByMaterialId(materialId);
		logger.info("Fetched bioMeasurementList {}", bioMeasurementList.size()  );
		bioObservedPointsForm.setSelectedBioMaterialId(materialId);
		bioObservedPointsForm.setBioMeasurementList(bioMeasurementList);
		ModelAndView  mv = new ModelAndView("materials/bioObservedPoints","bioObservedPointsForm",bioObservedPointsForm);
		return mv;
	}	
*/

	@PostMapping("associateFormulaAndMaterial")
	public ModelAndView getFormulaeForEdit(@RequestParam(value="selectedFormulaId", required=true) Long selectedFormulaId, @RequestParam(value="formulaName", required=true) String formulaName) {
		logger.info("getFormulaeForEdit :: selectedFormulaId:"+selectedFormulaId);
		EditBioFormulaForm editBioFormulaForm= new EditBioFormulaForm();
		editBioFormulaForm.setSelectedFormulaId(selectedFormulaId);
		editBioFormulaForm.setFormulaName(formulaName);
		editBioFormulaForm.setBioMaterials(bioFormulaMaterialService.getBioMaterialByFormulaId(selectedFormulaId));
		ModelAndView  mv = new ModelAndView("materials/associateFormulaAndMaterial","editBioFormulaForm",editBioFormulaForm);
		return mv;
	}	

	@PostMapping("addBioMaterialForm")
	public void addBioMaterialForm(@RequestParam(value="formulaId", required=true) Long selectedFormulaId, @RequestParam(value="selectedBioMaterialId", required=true) Long selectedBioMaterialId, HttpServletResponse response) {
		bioFormulaMaterialService.addBioFormula(selectedFormulaId, selectedBioMaterialId);
		response.setStatus(200); 
	}
	
	@GetMapping("associateFormulaAndMaterial")
	public ModelAndView displayEditFormulaPage() {
		logger.info("displayBioMaterialNutrientsPage");
		EditBioFormulaForm editBioFormulaForm= new EditBioFormulaForm();
		ModelAndView  mv = new ModelAndView("materials/associateFormulaAndMaterial","editBioFormulaForm",editBioFormulaForm);
		return mv;
	}
	
	@PostMapping("bioMaterialComposition")
	public ModelAndView getBioMaterialNutrients(@RequestParam(value="selectedBioMaterialId", required=true) Long materialId) {
		logger.info("getBioMaterialNutrients");
		BioMaterialCompositionForm bioMaterialCompositionForm = new BioMaterialCompositionForm();
		List<BioMaterialComposition> bioMaterialNutrientList = bioMaterialCompositionService.getComposition(materialId);
		logger.info("Fetched bioMaterialNutrientList {} {}", bioMaterialNutrientList.size()  );
		bioMaterialCompositionForm.setSelectedBioMaterialId(materialId);
		bioMaterialCompositionForm.setBioMaterialNutrientList(bioMaterialNutrientList);
		ModelAndView  mv = new ModelAndView("materials/bioMaterialComposition","bioMaterialCompositionForm",bioMaterialCompositionForm);
		return mv;
	}	

	
	@GetMapping("bioMaterialComposition")
	public ModelAndView displayBioMaterialNutrientsPage() {
		logger.info("Display bioMaterialComposition");
		BioMaterialCompositionForm bioMaterialCompositionForm = new BioMaterialCompositionForm();
		ModelAndView  mv = new ModelAndView("materials/bioMaterialComposition","bioMaterialCompositionForm",bioMaterialCompositionForm);
		return mv;
	}	

	
	
	@GetMapping("addBioMaterial")
	public ModelAndView displayAddBioMaterialPage() {
		logger.info("displayAddBioMaterialPage ");
		BioMaterial bioMaterial = new BioMaterial();
		ModelAndView  mv = new ModelAndView("materials/addBioMaterial","bioMaterial",bioMaterial);
		return mv;
	}	
	
	@PostMapping("addBioMaterial")
	public ModelAndView addBioMaterial(HttpServletRequest request, @Valid @ModelAttribute BioMaterial bioMaterial, BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("POST addBioMaterial:user {}  {}", bioMaterial , principal);
		ModelAndView  mv ;
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
			mv = new ModelAndView("materials/addBioMaterial","bioMaterial",bioMaterial);
			
		}
		else if(principal ==null) {
			throw new RuntimeException("User is not authorized to update a material");
		}
		else {
			bioMaterial.setAddedBy(principal.getName());
			BioMaterial bm = bioMaterialService.updateBioMaterial(bioMaterial,principal.getName());
			logger.info("bioMaterialService.updateBioMaterial {}", bm);		
			mv = new ModelAndView("materials/addBioMaterial","bioMaterial",bm);
			mv.addObject("successMessage", "Successfully Added Bio-Material");
			mv.addObject("successMessage", "Thanks for Contributing your Bio-Material.  A message is sent to the administrator for approval. You will get another email when administrator takes an action.");
			bioMatEmailService.emailBioMaterialAdded(principal);
		}
		return mv;	
	}	

	
	@GetMapping("updateBioMaterial")
	public ModelAndView displayUpdateBioMaterialPage(@RequestParam(value="materialId", required=true) Long materialId) {
		logger.info("updateBioMaterial {}", materialId );
		BioMaterial bioMaterial = bioMaterialService.getBioMaterial(materialId);
		logger.debug("Fetched BioMatrial {}", bioMaterial );
		
		ModelAndView  mv = new ModelAndView("materials/updateBioMaterial","bioMaterial",bioMaterial);
		return mv;
	}	

	@PostMapping("updateBioMaterial")
	public ModelAndView updateBioMaterialPage(HttpServletRequest request, @Valid @ModelAttribute BioMaterial bioMaterial, BindingResult bindingResult,@AuthenticationPrincipal Principal principal) {
		logger.info("POST updateBioMaterial:user {}  {}", bioMaterial , principal);
		ModelAndView  mv ;
		if(bindingResult.hasErrors()) {
			logger.info("Error in Form Submission.  NOT Updating Data. ");
			mv = new ModelAndView("materials/updateBioMaterial","bioMaterial",bioMaterial);
			
		}
		else if(principal ==null) {
			throw new RuntimeException("User is not authorized to update a material");
		}
		else {
			BioMaterial bm = bioMaterialService.updateBioMaterial(bioMaterial,principal.getName());
			logger.info("bioMaterialService.updateBioMaterial {}", bm);		
			mv = new ModelAndView("materials/updateBioMaterial","bioMaterial",bm);
			mv.addObject("successMessage", "Successfully Updated Bio-Material");
		}
		return mv;
	}	

	
	@GetMapping("searchBioMaterials")
	public ModelAndView displaySearchBioMaterialsPage(@ModelAttribute BioMaterialSearchForm bioMaterialSearchForm) {
	        
		logger.info("Start searchBioMaterials {}" , bioMaterialSearchForm);
		BioMaterialSearchForm BMSF = new BioMaterialSearchForm();
		BMSF.setUsdaOnly("a");
		logger.debug("Start searchBioMaterials {}" , bioMaterialSearchForm);
		ModelAndView  mv = new ModelAndView("materials/searchBioMaterials","bioMaterialSearchForm",BMSF);
		return mv;
	}
	
	@PostMapping("searchBioMaterials")
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
