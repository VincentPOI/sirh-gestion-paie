package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;
	
	

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Grade g1 = new Grade();
		g1.setCode("ABC");
		g1.setNbHeuresBase(new BigDecimal(24));
		g1.setTauxBase(new BigDecimal(6.67));
		gradeService.sauvegarder(g1);
		
		Grade g2 = new Grade();
		g2.setCode("AAA");
		g2.setNbHeuresBase(new BigDecimal(24));
		g2.setTauxBase(new BigDecimal(6.67));
		gradeService.sauvegarder(g2);
		

		Grade g3 = new Grade();
		
		g3.setCode("BBB");
		g3.setNbHeuresBase(new BigDecimal(32));
		g3.setTauxBase(new BigDecimal(99));
		gradeService.sauvegarder(g3);
		
		List<Grade> listeGrade = gradeService.lister();

		Stream.of("ABC","AAA","BBB").forEach(
				code -> assertThat(listeGrade.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent())
						.isTrue());

		g3.setCode("XXX");
		g3.setId(3);
		gradeService.mettreAJour(g3);
		listeGrade.clear();
		listeGrade.addAll(gradeService.lister());
		
		Stream.of("ABC","AAA","XXX").forEach(
				code -> assertThat(listeGrade.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent())
						.isTrue());		
	}

}
