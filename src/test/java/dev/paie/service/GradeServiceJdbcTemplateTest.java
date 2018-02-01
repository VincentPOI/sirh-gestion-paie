package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;
import static org.assertj.core.api.Assertions.assertThat;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private DataSource ds;
	

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {


		Grade g2 = new Grade();
		g2.setId(3);
		g2.setCode("BBB");
		g2.setNbHeuresBase(new BigDecimal(32));
		g2.setTauxBase(new BigDecimal(99));
		gradeService.sauvegarder(g2);
		
		List<Grade> listeGrade = gradeService.lister();

		Stream.of("ABC","AAA","BBB").forEach(
				code -> assertThat(listeGrade.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent())
						.isTrue());

		g2.setCode("XXX");
		gradeService.mettreAJour(g2);
		listeGrade.clear();
		listeGrade.addAll(gradeService.lister());
		
		Stream.of("ABC","AAA","XXX").forEach(
				code -> assertThat(listeGrade.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent())
						.isTrue());		
	}

}
