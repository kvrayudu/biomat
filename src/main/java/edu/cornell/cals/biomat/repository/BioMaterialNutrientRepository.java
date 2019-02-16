package edu.cornell.cals.biomat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.cornell.cals.biomat.dao.BioMaterialNutrient;
import edu.cornell.cals.biomat.dao.BioMaterialNutrientId;

@Repository
public interface BioMaterialNutrientRepository extends JpaRepository<BioMaterialNutrient, BioMaterialNutrientId> {
	
	
	List<BioMaterialNutrient> findByIdMaterialId(Long materialId);
	
}
