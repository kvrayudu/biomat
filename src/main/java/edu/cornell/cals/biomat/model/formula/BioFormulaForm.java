package edu.cornell.cals.biomat.model.formula;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import edu.cornell.cals.biomat.dao.BioVariable;

public class BioFormulaForm implements  Serializable{
	private static final long serialVersionUID = -3674920855102623820L;
	
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String formula;
	@NotNull
	@DecimalMin(value = "-9999.9", inclusive = true)
	@DecimalMax(value = "9999.9", inclusive = true)
	private Double minRange;
	@NotNull
	@DecimalMin(value = "-9999.9", inclusive = true)
	@DecimalMax(value = "9999.9", inclusive = true)
	private Double maxRange;
	@NotNull
	@NotEmpty
	private String citation;
	@NotNull
	@NotEmpty
	private String doi;

	@NotNull
	@NotEmpty
	private String formulaDesc;
	
	private Integer variableId;
	
	List<BioVariable> bioVariables;
	
	
	private boolean validated;
	
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
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
	public Integer getVariableId() {
		return variableId;
	}
	public void setVariableId(Integer variableId) {
		this.variableId = variableId;
	}
	public List<BioVariable> getBioVariables() {
		return bioVariables;
	}
	public void setBioVariables(List<BioVariable> bioVariables) {
		this.bioVariables = bioVariables;
	}
	public String getFormulaDesc() {
		return formulaDesc;
	}
	public void setFormulaDesc(String formulaDesc) {
		this.formulaDesc = formulaDesc;
	}
	
	

	
	

}
