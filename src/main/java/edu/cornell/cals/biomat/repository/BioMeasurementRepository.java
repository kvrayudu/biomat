package edu.cornell.cals.biomat.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	
	@Query(value="select BM from BioMeasurement BM "
			+ " INNER JOIN BioMaterial bmat ON bmat.id = BM.materialId "
			+ " INNER JOIN BioVariable bv ON bv.id = BM.variableId "
			+ " WHERE BM.addedBy = :userName")
    List<BioMeasurement> getBioMeasurementsByContributor(@Param("userName") String userName);
	

	
	@Query(value="select BM from BioMeasurement BM "
			+ " INNER JOIN BioMaterial bmat ON bmat.id = BM.materialId "
			+ " INNER JOIN BioVariable bv ON bv.id = BM.variableId "
			+ " WHERE BM.addedBy = :userName")
    Page<BioMeasurement> getBioMeasurementsByContributorWithPagination(Pageable pageable,@Param("userName") String userName);

	@Query(value="SELECT count(bm) FROM BioMeasurement bm where bm.addedBy = :userName")
    Integer getBioMeasurementCount(@Param("userName") String userName);

	
}
