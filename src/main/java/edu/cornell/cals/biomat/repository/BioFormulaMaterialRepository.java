package edu.cornell.cals.biomat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.dao.BioFormulaMaterial;

@Repository
public interface BioFormulaMaterialRepository extends JpaRepository<BioFormulaMaterial, Long> {
	
	@Query(value="SELECT bfm FROM BioFormulaMaterial bfm where bfm.materialId = :materialId")
    List<BioFormulaMaterial> getBioFormulaMaterialByMaterialId(@Param("materialId") Long materialId);
	
}
