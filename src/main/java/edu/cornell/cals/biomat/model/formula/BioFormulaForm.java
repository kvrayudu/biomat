package edu.cornell.cals.biomat.model.formula;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BioFormulaForm implements  Serializable{
	private static final long serialVersionUID = -3674920855102623820L;
	
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String formula;
	@NotNull
	@DecimalMin(value = "0.1", inclusive = true)
	@DecimalMax(value = "9999.9", inclusive = true)
	private Double minRange;
	@NotNull
	@DecimalMin(value = "0.1", inclusive = true)
	@DecimalMax(value = "9999.9", inclusive = true)
	private Double maxRange;
	@NotNull
	@NotEmpty
	private String citation;
	@NotNull
	@NotEmpty
	private String doi;
	
	private List<String> variables;
	private boolean validated;
	
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public List<String> getVariables() {
		return variables;
	}
	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public Double getMinRange() {
		return minRange;
	}
	public void setMinRange(Double minRange) {
		this.minRange = minRange;
	}
	public Double getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(Double maxRange) {
		this.maxRange = maxRange;
	}
	public String getCitation() {
		return citation;
	}
	public void setCitation(String citation) {
		this.citation = citation;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	@Override
	public String toString() {
		return "BioFormulaForm [name=" + name + ", formula=" + formula + ", minRange=" + minRange + ", maxRange="
				+ maxRange + ", citation=" + citation + ", doi=" + doi + ", variables=" + variables + ", validated="
				+ validated + "]";
	}

	
	

}
