package edu.cornell.cals.biomat.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.cornell.cals.biomat.dao.BioMaterialNutrient;
import edu.cornell.cals.biomat.model.BioMaterialNutrientModel;
import edu.cornell.cals.biomat.service.BioMaterialNutrientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BioMaterialNutrientServiceImplTest {

		@Autowired
		BioMaterialNutrientService bioMaterialNutrientService;
		
		@Test
		public void getBioMaterial() {
			Long id = 5000l;
			List<BioMaterialNutrient> bmnList =bioMaterialNutrientService.getNutrients(id);
			assertThat( bmnList.get(0).getBioMaterial().getId()).isEqualTo(id);
			
		}
		
		
		@Test
		public void getBioMaterialNutrientsForFormula() {
			String complexExp = "(1 / ((1 / 273.15) - ((8.314  / (333.64 * 18)) * log((((H2O/100) - xb) / 18) / ((((H2O/100) - xb) / 18) + ((((Protein + Carbs + FIBTG) / 100) / 50000) + (((GLUS / 100) + (FRUS / 100) + (GALS / 100)) / 180.16) + (((SUCS / 100) + (LACS / 100) + (MALS / 100)) / 342.29) + (((K_pot + Sodium + MG + P + CA + Cl) / pow(10,5)) / 32.13) + ((((Nitrate / 1000) / 100) + ((CitAcid / 1000) / 100) + ((MalAcid / 1000) / 100) + ((LacAcid / 1000) / 100) + ((OxAcid / 1000) / 100) + ((VITC / 1000) / 100)) / 90.03))))))) - 273";
			//List<BioMaterialNutrientModel>  bmnmList = bioMaterialNutrientService.getBioMaterialNutrientsForFormula(5000l, complexExp);
			//assertThat(bmnmList.size()>0);
			
			
		}
}
