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

		/*
		 * select DISTINCT bm.id,bm.short_desc from bio_material bm
			INNER join bio_formula_material bfm on bfm.material_id=bm.id
				where bm.short_desc like 'c%';
		 */
		
		@Query(value="SELECT DISTINCT bm FROM BioMaterial bm INNER join BioFormulaMaterial bfm on bfm.materialId=bm.id where bm.shortDesc like :shortDesc ")
	    List<BioMaterial> getBioMaterialWithFormula(@Param("shortDesc") String shortDesc);
		
		
}
