package edu.cornell.cals.biomat.model.measurement;

import java.util.List;

public class MeasurementPair{
	private Double  measurementValue;
	private Double errorValue;
	public Double getMeasurementValue() {
		return measurementValue;
	}
	public void setMeasurementValue(Double measurementValue) {
		this.measurementValue = measurementValue;
	}
	public Double getErrorValue() {
		return errorValue;
	}
	public void setErrorValue(Double errorValue) {
		this.errorValue = errorValue;
	}
	@Override
	public String toString() {
		return "MeasurementPair [measurementValue=" + measurementValue + ", errorValue=" + errorValue + "]";
	}

	
	
}