package edu.cornell.cals.biomat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.cornell.cals.biomat.dao.BioMeasurement;

@Repository
public interface BioMeasurementRepository extends JpaRepository<BioMeasurement, Long> {

	
	@Query(value="SELECT bm FROM BioMeasurement bm where bm.materialId = :materialId")
    List<BioMeasurement> getBioMeasurementsByMaterialId(@Param("materialId") Long materialId);

	@Query(value="SELECT bm FROM BioMeasurement bm where bm.groupId = :groupId")
    List<BioMeasurement> getBioMeasurementsByGroupId(@Param("groupId") Integer groupId);

	@Query(value="SELECT bm FROM BioMeasurement bm where bm.variableId = :variableId")
    List<BioMeasurement> getBioMeasurementsByVariableId(@Param("variableId") Integer variableId);

}
