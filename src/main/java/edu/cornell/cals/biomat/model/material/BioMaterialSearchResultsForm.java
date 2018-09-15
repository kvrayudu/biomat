package edu.cornell.cals.biomat.model.material;
 
import java.util.List;

import edu.cornell.cals.biomat.dao.BioMaterial;

public class BioMaterialSearchResultsForm {
	protected BioMaterialSearchForm bioMaterialSearchForm;
	
	protected List<BioMaterial> bioMaterials;
	
	private int currentPage;
	private int lastPage;
	
	private int pagerStart;
	private int pagerEnd;
	
	public BioMaterialSearchForm getBioMaterialSearchForm() {
		return bioMaterialSearchForm;
	}

	public void setBioMaterialSearchForm(BioMaterialSearchForm bioMaterialSearchForm) {
		this.bioMaterialSearchForm = bioMaterialSearchForm;
	}

	public List<BioMaterial> getBioMaterials() {
		return bioMaterials;
	}

	public void setBioMaterials(List<BioMaterial> bioMaterials) {
		this.bioMaterials = bioMaterials;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPagerStart() {
		return pagerStart;
	}

	public void setPagerStart(int pagerStart) {
		this.pagerStart = pagerStart;
	}

	public int getPagerEnd() {
		return pagerEnd;
	}

	public void setPagerEnd(int pagerEnd) {
		this.pagerEnd = pagerEnd;
	}

	@Override
	public String toString() {
		return "BioMaterialSearchResultsForm [bioMaterialSearchForm=" + bioMaterialSearchForm + ", bioMaterials="
				+ bioMaterials + ", currentPage=" + currentPage + ", lastPage=" + lastPage + ", pagerStart="
				+ pagerStart + ", pagerEnd=" + pagerEnd + "]";
	}


	

	

	

	
	

		
}
