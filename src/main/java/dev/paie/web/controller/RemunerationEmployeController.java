package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	RemunEmployeRepository rer;
	@Autowired
	ProfilRemunerationRepository prr;
	@Autowired
	GradeRepository gr;
	@Autowired
	EntrepriseRepository er;
	@Autowired
	PeriodeRepository pr;
	@Autowired
	BulletinSalaireRepository bsr;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {

		List<ProfilRemuneration> profilsRemuneration = prr.findAll();
		List<Grade> grades = gr.findAll();
		List<Entreprise> entreprises = er.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		mv.addObject("entreprises", entreprises);
		mv.addObject("grades", grades);
		mv.addObject("newEmploye", new RemunerationEmploye());
		mv.addObject("profilsRemuneration", profilsRemuneration);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String insertEmploye(@ModelAttribute("newEmploye") RemunerationEmploye remun) {
		remun.setTime();
		rer.save(remun);
		return "redirect:/mvc/employes/lister";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView listerEmploye() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("employes", rer.findAll());
		mv.setViewName("employes/listerEmploye");
		return mv;
	}

}