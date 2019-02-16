package edu.cornell.cals.biomat.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.dao.BioMeasurement;
import edu.cornell.cals.biomat.dao.BioVariable;
import edu.cornell.cals.biomat.dao.Measurements;
import edu.cornell.cals.biomat.repository.BioMaterialRepository;
import edu.cornell.cals.biomat.repository.BioMeasurementRepository;
import edu.cornell.cals.biomat.repository.BioVariableRepository;
import edu.cornell.cals.biomat.repository.MeasurementsRepository;


@Service
public class MeasurementsDataInitializer {
	@Autowired
	MeasurementsRepository measurementsRepository;
	
	@Autowired
	BioMeasurementRepository bioMeasurementRepository;
	
	@Autowired
	BioMaterialRepository bioMaterialRepository;
	
	@Autowired
	BioVariableRepository bioVariableRepository;
	
	Logger logger = LoggerFactory.getLogger(MeasurementsDataInitializer.class);

	public  void populate() {
		logger.info("Start");
		List<Measurements> measurmentsList = measurementsRepository.findAll();
		//Measurements measurmentsList = measurementsRepository.findById(633045);
		logger.info("Fetched Data");
		
		measurmentsList.forEach(measurement ->{
			BioMeasurement bioMeasurement = new BioMeasurement();
			
			
			bioMeasurement.setMaterialId((long)measurement.getMaterialId());
			bioMeasurement.setVariableId(measurement.getVariableId());
			bioMeasurement.setGroupId(-1);
			bioMeasurement.setMeasuredValue(measurement.getValue());
			bioMeasurement.setErrorValue(measurement.getError());
			
			bioMeasurement.setCitation(measurement.getCitations());
			bioMeasurement.setDoi(measurement.getdOI());
			//bioMeasurement.setIsApproved(""+measurement.getIsApproved());
			try {
				BioMaterial bm  = bioMaterialRepository.getOne((long)measurement.getMaterialId());
				long id= bm.getId();
				String desc= bm.getShortDesc();
				BioVariable bv  = bioVariableRepository.getOne(measurement.getVariableId());
				if(bm.getId() != null && bm.getId() == (long)measurement.getMaterialId() && bv!=null && bv.getId()!=null) {
					bioMeasurementRepository.save(bioMeasurement);
					logger.info("Saving " + bioMeasurement.getMaterialId());
				}
				else {
					logger.info("NOT Saving " + bioMeasurement.getMaterialId());
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
	}
	
}
