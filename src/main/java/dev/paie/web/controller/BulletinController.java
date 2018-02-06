package dev.paie.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunEmployeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;
import dev.paie.util.PaieUtils;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

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
	@Autowired
	CalculerRemunerationServiceSimple calculer;
	ResultatCalculRemuneration calculs = new ResultatCalculRemuneration();
	@Autowired 
	PaieUtils paieUtils;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin() {	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes",pr.findAll());
		mv.addObject("newBulletin", new BulletinSalaire());
		mv.addObject("remunerationsEmployes",rer.findAll());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String insertBulletin(@ModelAttribute("newBulletin") BulletinSalaire bull) {
		bull.setTime();
		bsr.save(bull);
		return "redirect:/mvc/bulletins/lister";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Transactional
	public ModelAndView listerBulletin() {
				
		calculs = new ResultatCalculRemuneration();
		ModelAndView mv = new ModelAndView();
		Map<BulletinSalaire, ResultatCalculRemuneration> calculsBulletins = new HashMap<>();
		List<BulletinSalaire> bulletins = bsr.findAll();
		for(BulletinSalaire b : bulletins){
			calculs = calculer.calculer(b);
			calculsBulletins.put(b, calculs);
		}	
		
		mv.addObject("bulletins",bulletins);	
		mv.addObject("calculsBulletins",calculsBulletins);
		mv.setViewName("bulletins/listerBulletin");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser")
	@Transactional
	public ModelAndView visualiserBulletin(@RequestParam("id") int i) {	
				
		BulletinSalaire bull = bsr.findOne(i);
		calculs = calculer.calculer(bull);
		ModelAndView mv = new ModelAndView();
		mv.addObject("bulletin",bull);
		mv.addObject("calculs",calculs);
		mv.setViewName("bulletins/visualiser");
		return mv;
	}
}
