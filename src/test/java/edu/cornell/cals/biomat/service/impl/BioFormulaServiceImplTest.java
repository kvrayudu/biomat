package edu.cornell.cals.biomat.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.cornell.cals.biomat.dao.BioFormula;
import edu.cornell.cals.biomat.service.BioFormulaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BioFormulaServiceImplTest {
		Logger logger = LoggerFactory.getLogger(BioFormulaServiceImplTest.class);
		@Autowired
		BioFormulaService bioFormulaService;

		
		@Test
		public void getBioFormula() {
			Long id = 1l;
			BioFormula bf =bioFormulaService.getBioMaterialFormula(id);
			logger.info("Fetched {} ", bf);
			assertThat( bf.getId()).isEqualTo(id);
		}
		
		
		@Test
		public void getBioFormulaByVariableId() {
			Integer id = 6;
			List<BioFormula> bf =bioFormulaService.getBioFormulaByVariableId(id);
			StringBuffer sb = new StringBuffer();
			bf.forEach(f->sb.append(f.toString()));
			logger.info("Fetched {} {} ", bf.size(), sb.toString());
			assertThat(bf.size()>0);
		}

}
