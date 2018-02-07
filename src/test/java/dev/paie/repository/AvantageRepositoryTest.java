package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.TestConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { TestConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Avantage a = new Avantage();
		a.setCode("ABC");
		a.setNom("avantage1");
		a.setMontant(new BigDecimal(12));

		avantageRepository.save(a);

		List<Avantage> avantages = avantageRepository.findAll();

		Stream.of("ABC").forEach(
				code -> assertThat(avantages.stream().filter(co -> co.getCode().equals(code)).findAny().isPresent())
						.isTrue());

		a.setId(1);
		a.setCode("XXX");
		avantageRepository.saveAndFlush(a);
		avantages.clear();
		avantages.addAll(avantageRepository.findAll());

		Stream.of("XXX").forEach(
				code -> assertThat(avantages.stream().filter(co -> co.getCode().equals(code)).findAny().isPresent())
						.isTrue());
		Stream.of("aaa").forEach(
				code -> assertThat(avantages.stream().filter(co -> co.getCode().equals(code)).findAny().isPresent())
						.isFalse());

		// TODO sauvegarder un nouvel avantage
		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via
		// la méthode findOne
		// TODO modifier un avantage
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode findOne
	}

}
