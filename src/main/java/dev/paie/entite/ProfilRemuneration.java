package dev.paie.entite;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="profilRemuneration")
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="code")
	private String code;

	@ManyToMany
	@JoinTable(name="cotNonImpo",
	joinColumns= @JoinColumn(name="ID_profilRemuneration", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="ID_cotisationNomImpo", referencedColumnName="id"))
	private List<Cotisation> cotisationsNonImposables;
	
	@ManyToMany
	@JoinTable(name="cotImpo",
	joinColumns= @JoinColumn(name="ID_profilRemuneration", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="ID_cotisationImpo", referencedColumnName="id"))
	private List<Cotisation> cotisationsImposables;
	
	@ManyToMany
	@JoinTable(name="avantages",
	joinColumns= @JoinColumn(name="ID_profilRemuneration", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="ID_avantage", referencedColumnName="id"))
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
