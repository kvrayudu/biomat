package edu.cornell.cals.biomat.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import edu.cornell.cals.biomat.dao.BioMaterial;

public interface BioMaterialService {
	BioMaterial getBioMaterial(Long id);
	List<BioMaterial> getBioMaterial(String desc);
	Map<String,Object> getBioMaterial(Pageable pageable,String desc);
	BioMaterial getBioMaterialByUsdaId(Long usdaId);
	
	BioMaterial updateBioMaterial(BioMaterial bioMaterial,String userId);
}
