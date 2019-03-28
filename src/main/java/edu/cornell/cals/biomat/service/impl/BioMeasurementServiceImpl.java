package edu.cornell.cals.biomat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cornell.cals.biomat.repository.BioMeasurementRepository;
import edu.cornell.cals.biomat.service.BioMeasurementService;

@Service
public abstract class BioMeasurementServiceImpl implements BioMeasurementService {
	@Autowired
	BioMeasurementRepository bioMeasurementRepository;
	Logger logger = LoggerFactory.getLogger(BioMeasurementServiceImpl.class);
	/*
	@Override
	public Map<String,Object>  getBioMeasurementsByContributor(Pageable pageable, String userName){
		logger.info("Start {}" + userName);
		Map<String,Object> map = new HashMap<String,Object>();
		int count = bioMeasurementRepository.getBioMeasurementCount(userName);
		logger.info("count {}" + count);
		Page<BioMeasurement> bioMeasurementPage = bioMeasurementRepository.getBioMeasurementsByContributorWithPagination(pageable,userName);
		map.put("count",count);
		map.put("bioMeasurementPage",bioMeasurementPage);

		return map;
	}
	
	
	
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
		BM.setIsApproved("0");
		BM.setAddedBy(userId);
		BM.setMeasuredValue(measuredValue);
		BM.setErrorValue(errorValue);
		return BM;
	}
*/
}
