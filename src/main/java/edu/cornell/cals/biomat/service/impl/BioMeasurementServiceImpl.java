package edu.cornell.cals.biomat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.controller.BioMaterialsController;
import edu.cornell.cals.biomat.dao.BioMeasurement;
import edu.cornell.cals.biomat.model.measurement.MeasurementPair;
import edu.cornell.cals.biomat.repository.BioMeasurementRepository;
import edu.cornell.cals.biomat.service.BioMeasurementService;

@Service
public class BioMeasurementServiceImpl implements BioMeasurementService {
	@Autowired
	BioMeasurementRepository bioMeasurementRepository;
	Logger logger = LoggerFactory.getLogger(BioMeasurementServiceImpl.class);
	
	@Override
	public List<BioMeasurement> addBioMaterial(Long materialId,  String citation, String doi, List<MeasurementPair> mpList, String userId) {
		logger.info("Start {},  {},  {}, {}, {}" + materialId,citation, doi,  mpList, userId);
		List<BioMeasurement> bmList = new ArrayList<BioMeasurement>();
		
		mpList.stream().forEach(mp->{
			bmList.add(createBioMeasurement(materialId, mp.getId(), citation, doi, mp.getMeasurementValue(), mp.getErrorValue(), userId));
			});
		
		logger.info("About to Update {} " , bmList);
		return bioMeasurementRepository.saveAll(bmList);
		
		
	}
	
	private BioMeasurement createBioMeasurement(Long materialId, Integer variableId, String citation, String doi,Double measuredValue,Double errorValue, String userId) {
		BioMeasurement BM = new BioMeasurement();
		BM.setMaterialId(materialId);
		BM.setVariableId(variableId);
		BM.setCitation(citation);
		BM.setDoi(doi);
		BM.setIsFactor("1");
		BM.setIsApproved("0");
		BM.setAddedBy(userId);
		BM.setMeasuredValue(measuredValue);
		BM.setErrorValue(errorValue);
		return BM;
	}

}
