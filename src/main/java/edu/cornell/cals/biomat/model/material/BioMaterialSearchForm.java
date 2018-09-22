package edu.cornell.cals.biomat.model.material;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BioMaterialSearchForm implements Serializable{
	private static final long serialVersionUID = -6859715018342757198L;
	@NotNull
	@Size(min=2, max=300)
	private String bioMaterialName;
	private String usdaOnly;
	
	
	
	public String getBioMaterialName() {
		return bioMaterialName;
	}
	public String getUsdaOnly() {
		return usdaOnly;
	}
	public void setUsdaOnly(String usdaOnly) {
		this.usdaOnly = usdaOnly;
	}
	public void setBioMaterialName(String bioMaterialName) {
		this.bioMaterialName = bioMaterialName;
	}
	@Override
	public String toString() {
		return "BioMaterialSearchForm [bioMaterialName=" + bioMaterialName + ", usdaOnly=" + usdaOnly + "]";
	}


	
	
}
