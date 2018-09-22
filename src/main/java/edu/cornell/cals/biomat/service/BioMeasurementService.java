package edu.cornell.cals.biomat.service;

import java.util.List;

import edu.cornell.cals.biomat.dao.BioMeasurement;
import edu.cornell.cals.biomat.model.measurement.MeasurementPair;

public interface BioMeasurementService {
	
	List<BioMeasurement> addBioMaterial(Long materialId, Integer variableId, String citation, String doi, List<MeasurementPair> mpList, String userId);

}
