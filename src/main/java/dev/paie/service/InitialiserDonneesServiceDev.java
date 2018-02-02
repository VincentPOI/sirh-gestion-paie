package dev.paie.service;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	List<Grade> grades;
	@Autowired
	List<Entreprise> entreprises;
	@Autowired
	List<ProfilRemuneration> profilsRemuneration;
	@Autowired
	List<Cotisation> cotisations;

	@Override
	public void initialiser() {

		int nbmois = 12;
		int annee = LocalDate.now().getYear();

		for (int i = 1; i < nbmois; i++) {
			Periode p = new Periode();
			LocalDate dateDebut = LocalDate.of(annee, i, 13).with(firstDayOfMonth());
			LocalDate dateFin = LocalDate.of(annee, i, 13).with(lastDayOfMonth());
			p.setDateDebut(dateDebut);
			p.setDateFin(dateFin);
			em.persist(p);
		}

		grades.stream().forEach(g -> em.persist(g));
		cotisations.stream().forEach(c -> em.persist(c));
		entreprises.stream().forEach(e -> em.persist(e));
		profilsRemuneration.stream().forEach(p -> em.persist(p));
		

	}

}
