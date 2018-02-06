package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	
	@Autowired 
	PaieUtils paieUtils;
		
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		
		ResultatCalculRemuneration resultCalculRemun = new ResultatCalculRemuneration();
		
		RemunerationEmploye remunEmploy = bulletin.getRemunerationEmploye();
		Grade grade = remunEmploy.getGrade();
		List<Cotisation> cotisationsImposables = remunEmploy.getProfilRemuneration().getCotisationsImposables();
		List<Cotisation> cotisationsNonImposables = remunEmploy.getProfilRemuneration().getCotisationsNonImposables();
		
		BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		resultCalculRemun.setSalaireDeBase(paieUtils.formaterBigDecimal(salaireBase));
		
		BigDecimal salaireBrut = new BigDecimal(paieUtils.formaterBigDecimal(salaireBase.add(bulletin.getPrimeExceptionnelle())));
		resultCalculRemun.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBrut));
		
		BigDecimal totalRetenueSalarial = calculRetenueSalariale(cotisationsNonImposables, salaireBrut);
		resultCalculRemun.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(totalRetenueSalarial));
		
		BigDecimal totalCotisationsPatronal = calculCotisationPatronale(cotisationsNonImposables, salaireBrut);
		resultCalculRemun.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totalCotisationsPatronal));
		
		BigDecimal netImposable = salaireBrut.subtract(totalRetenueSalarial);
		resultCalculRemun.setNetImposable(paieUtils.formaterBigDecimal(netImposable));
		
		BigDecimal netAPayer = new BigDecimal(paieUtils.formaterBigDecimal(netImposable.subtract((calculRetenueSalariale(cotisationsImposables, salaireBrut)))));
		resultCalculRemun.setNetAPayer(netAPayer.toString());
		return resultCalculRemun;
	}
	
	private BigDecimal calculRetenueSalariale(List<Cotisation> cotisations, BigDecimal salaireBrut){
		BigDecimal somme= cotisations.stream()
                .filter(c -> c.getTauxSalarial()!=null)
                .map(c -> c.getTauxSalarial().multiply(salaireBrut))
                .reduce((a,b) -> a.add(b)).get();
		return new BigDecimal(paieUtils.formaterBigDecimal(somme));
	}
	
	private BigDecimal calculCotisationPatronale(List<Cotisation> cotisations, BigDecimal salaireBrut){

		BigDecimal somme= cotisations.stream()
                .filter(c -> c.getTauxPatronal()!=null)
                .map(c -> c.getTauxPatronal().multiply(salaireBrut))
                .reduce((a,b) -> a.add(b)).get();
		return new BigDecimal(paieUtils.formaterBigDecimal(somme));
	}


}
