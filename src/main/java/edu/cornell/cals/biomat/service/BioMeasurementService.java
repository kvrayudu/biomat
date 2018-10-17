package edu.cornell.cals.biomat.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import edu.cornell.cals.biomat.dao.BioMeasurement;
import edu.cornell.cals.biomat.model.measurement.MeasurementPair;

public interface BioMeasurementService {
	
	List<BioMeasurement> addBioMaterial(Long materialId,  String citation, String doi, List<MeasurementPair> mpList, String userId);
	Map<String,Object>  getBioMeasurementsByContributor(Pageable pageable, String userName);

}
