package dev.paie.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
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
	CalculerRemunerationServiceSimple calculs;
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
		bsr.save(bull);
//		System.out.println(bsr.findOne(1).getPeriode().getDateDebut());
		return "redirect:/mvc/bulletins/lister";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Transactional
	public ModelAndView listerBulletin() {
		
		ModelAndView mv = new ModelAndView();
		List<BulletinSalaire> bulletins = bsr.findAll();
		List<String> salairesBruts = new ArrayList<>();
		List<String> netImposables = new ArrayList<>();
		List<String> netAPayers = new ArrayList<>();
		for(BulletinSalaire b : bulletins){
			calculs.calculer(b);
			salairesBruts.add(paieUtils.formaterBigDecimal(calculs.getSalaireBrut()));
			netImposables.add(paieUtils.formaterBigDecimal(calculs.getNetImposable()));
			netAPayers.add(paieUtils.formaterBigDecimal(calculs.getNetAPayer()));
		}	
		
		mv.addObject("bulletins",bulletins);	
		mv.addObject("salairesBruts",salairesBruts);
		mv.addObject("netImposables",netImposables);
		mv.addObject("netAPayers",netAPayers);
		mv.setViewName("bulletins/listerBulletin");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser")
	@Transactional
	public ModelAndView visualiserBulletin(@RequestParam("id") int i) {	
		
		
		List<String> salairesBases = new ArrayList<>();
		List<String> salairesBruts = new ArrayList<>();
		List<String> netImposables = new ArrayList<>();
		List<String> netAPayers = new ArrayList<>();
		for(BulletinSalaire b : bsr.findAll()){
			calculs.calculer(b);
			salairesBruts.add(paieUtils.formaterBigDecimal(calculs.getSalaireBrut()));
			netImposables.add(paieUtils.formaterBigDecimal(calculs.getNetImposable()));
			netAPayers.add(paieUtils.formaterBigDecimal(calculs.getNetAPayer()));
		}	
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bulletin",bsr.findOne(i));
		mv.setViewName("bulletins/visualiser");
		return mv;
	}
	
}
