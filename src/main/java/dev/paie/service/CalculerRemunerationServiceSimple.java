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
		
	private BigDecimal salaireBase;
	private BigDecimal salaireBrut;
	private BigDecimal totalRetenueSalarial;
	private BigDecimal totalCotisationsPatronal;
	private BigDecimal netImposable;
	private BigDecimal netAPayer;
	
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		
		ResultatCalculRemuneration resultCalculRemun = new ResultatCalculRemuneration();
		
		RemunerationEmploye remunEmploy = bulletin.getRemunerationEmploye();
		Grade grade = remunEmploy.getGrade();
		List<Cotisation> cotisationsImposables = remunEmploy.getProfilRemuneration().getCotisationsImposables();
		List<Cotisation> cotisationsNonImposables = remunEmploy.getProfilRemuneration().getCotisationsNonImposables();
		
		this.salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		resultCalculRemun.setSalaireDeBase(paieUtils.formaterBigDecimal(salaireBase));
		
		this.salaireBrut = new BigDecimal(paieUtils.formaterBigDecimal(salaireBase.add(bulletin.getPrimeExceptionnelle())));
		resultCalculRemun.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBrut));
		
		this.totalRetenueSalarial = calculRetenueSalariale(cotisationsNonImposables, salaireBrut);
		resultCalculRemun.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(totalRetenueSalarial));
		
		this.totalCotisationsPatronal = calculCotisationPatronale(cotisationsNonImposables, salaireBrut);
		resultCalculRemun.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totalCotisationsPatronal));
		
		this.netImposable = salaireBrut.subtract(totalRetenueSalarial);
		resultCalculRemun.setNetImposable(paieUtils.formaterBigDecimal(netImposable));
		
		this.netAPayer = new BigDecimal(paieUtils.formaterBigDecimal(netImposable.subtract((calculRetenueSalariale(cotisationsImposables, salaireBrut)))));
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


	public BigDecimal getSalaireBase() {
		return salaireBase;
	}

	public BigDecimal getSalaireBrut() {
		return salaireBrut;
	}

	public BigDecimal getTotalRetenueSalarial() {
		return totalRetenueSalarial;
	}

	public BigDecimal getTotalCotisationsPatronal() {
		return totalCotisationsPatronal;
	}

	public BigDecimal getNetImposable() {
		return netImposable;
	}

	public BigDecimal getNetAPayer() {
		return netAPayer;
	}
	
	


}
