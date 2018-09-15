package edu.cornell.cals.biomat.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.cornell.cals.biomat.dao.BioMaterial;
import edu.cornell.cals.biomat.service.BioMaterialService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BioMaterialServiceImplTest {

		@Autowired
		BioMaterialService bioMaterialService;
		
		@Test
		public void getBioMaterial() {
			Long id = 15959l;
			BioMaterial bm =bioMaterialService.getBioMaterial(id);
			assertThat( bm.getId()).isEqualTo(id);
			assertThat( bm.getShortDesc()).startsWith("BUTTER");
			
		}
}
