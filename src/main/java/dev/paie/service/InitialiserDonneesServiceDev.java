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

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	List<Grade> grades;
	@Autowired
	GradeRepository gr;
	@Autowired
	List<Entreprise> entreprises;
	@Autowired
	EntrepriseRepository er;
	@Autowired
	List<ProfilRemuneration> profilsRemuneration;
	@Autowired
	ProfilRemunerationRepository prr;
	@Autowired
	List<Cotisation> cotisations;
	@Autowired
	CotisationRepository cr;
	@Autowired
	PeriodeRepository pr;
	
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
		gr.save(grades);
		cr.save(cotisations);
		er.save(entreprises);
		prr.save(profilsRemuneration);
	}

}