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
import dev.paie.entite.Cotisation;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {

	@Autowired private CotisationService cotisationService;
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// TODO sauvegarder une nouvelle cotisation
		Cotisation c = new Cotisation();
		c.setCode("ABC");
		c.setLibelle("cotisation1");
		c.setTauxPatronal(new BigDecimal(12));
		c.setTauxSalarial(new BigDecimal(88));
		
		cotisationService.sauvegarder(c);
		
		List<Cotisation> cotisations = cotisationService.lister();

		Stream.of("ABC").forEach(
				code -> assertThat(cotisations.stream().filter(co -> co.getCode().equals(code)).findAny().isPresent())
						.isTrue());

		c.setId(1);
		c.setCode("XXX");
		cotisationService.mettreAJour(c);
		cotisations.clear();
		cotisations.addAll(cotisationService.lister());
		
		Stream.of("XXX").forEach(
				code -> assertThat(cotisations.stream().filter(co -> co.getCode().equals(code)).findAny().isPresent())
						.isTrue());
	}

}
