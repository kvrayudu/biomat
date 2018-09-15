package edu.cornell.cals.biomat.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.cornell.cals.biomat.dao.BioMaterial;
@Repository
public interface BioMaterialRepository extends JpaRepository<BioMaterial, Long> {


		@Query(value="SELECT bm FROM BioMaterial bm where bm.shortDesc like :shortDesc")
	    List<BioMaterial> getBioMaterial(@Param("shortDesc") String shortDesc);

		@Query(value="SELECT bm FROM BioMaterial bm where bm.shortDesc like :shortDesc")
		Page<BioMaterial> getBioMaterialWithPagination(Pageable pageable,@Param("shortDesc") String shortDesc);
		
		@Query(value="SELECT count(bm) FROM BioMaterial bm where bm.shortDesc like :shortDesc")
	    Integer getBioMaterialCount(@Param("shortDesc") String shortDesc);

}
