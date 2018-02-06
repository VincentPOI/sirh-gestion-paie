<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value ='/bootstrap-4.0.0-dist/css/bootstrap.css'/>">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js">
	
</script>

</head>
<body class="ui container-fluid">
	<%
		
	%>
	<h1 class="ui header" align="center">Bulletin de salaire</h1>
	<br />
	<p align="right">
		<strong>Periode</strong> <br /> Du ${bulletin.periode.dateDebut} au
		${bulletin.periode.dateFin}
	</p>
	<div class="d-flex flex-row flex-wrap">
		<div class="p-2">
			<p>
				<strong>Entreprise</strong> <br />
				${bulletin.remunerationEmploye.entreprise.denomination} <br />
				SIRET : ${bulletin.remunerationEmploye.entreprise.siret}
		</div>
		<div class="p-2">
			<p>
				<strong>Matricule</strong> :
				${bulletin.remunerationEmploye.matricule}
		</div>
		<br />
	</div>
	<strong>Salaire</strong>
	<table class="table table-striped table-bordered l-10 r-10">
		<thead>
			<tr>
				<th scope="col">Rubriques</th>
				<th scope="col">Base</th>
				<th scope="col">Taux Salarial</th>
				<th scope="col">Montant Salarial</th>
				<th scope="col">Taux Patronal</th>
				<th scope="col">Cot. patronales</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Salaire de base</td>
				<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}</td>
				<td>${calculs.totalRetenueSalarial}</td>
				<td>${calculs.salaireDeBase}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Prime Except.</td>
				<td></td>
				<td></td>
				<td>${bulletin.primeExceptionnelle}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Salaire Brut</td>
				<td></td>
				<td></td>
				<td>${calculs.salaireBrut}</td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<br />
	<strong>Cotisations</strong>
	<table class="table table-striped table-bordered l-10 r-10">
		<thead>
			<tr>
				<th scope="col">Rubriques</th>
				<th scope="col">Base</th>
				<th scope="col">Taux Salarial</th>
				<th scope="col">Montant Salarial</th>
				<th scope="col">Taux Patronal</th>
				<th scope="col">Cot. patronales</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="cotisation"
				items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsNonImposables}">
				<tr>
					<td>${cotisation.code}/ ${cotisation.libelle}</td>
					<td>${calculs.salaireBrut}</td>
					<td>${cotisation.tauxSalarial}</td>
					<td>${calculs.salaireBrut * cotisation.tauxSalarial}</td>
					<td>${cotisation.tauxPatronal}</td>
					<td>${calculs.salaireBrut*cotisation.tauxPatronal}</td>
				</tr>
			</c:forEach>


			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Total Retenue</td>
				<td></td>
				<td></td>
				<td>${calculs.totalRetenueSalarial}</td>
				<td></td>
				<td>${calculs.totalCotisationsPatronales}</td>
			</tr>
		</tbody>
	</table>
	<br />
	<strong>NET Imposable : </strong> ${calculs.netImposable}
	<table class="table table-striped table-bordered l-10 r-10">
		<thead>
			<tr>
				<th scope="col">Rubriques</th>
				<th scope="col">Base</th>
				<th scope="col">Taux Salarial</th>
				<th scope="col">Montant Salarial</th>
				<th scope="col">Taux Patronal</th>
				<th scope="col">Cot. patronales</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="cotisation"
				items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsImposables}">
				<tr>
					<td>${cotisation.code}/ ${cotisation.libelle}</td>
					<td>${calculs.salaireBrut}</td>
					<td>${cotisation.tauxSalarial}</td>
					<td>${calculs.salaireBrut * cotisation.tauxSalarial}</td>
					<td>${cotisation.tauxPatronal}</td>
					<td>${calculs.salaireBrut*cotisation.tauxPatronal}</td>
				</tr>
			</c:forEach>


		</tbody>
	</table>
	<br />
	<p align="right">
		<strong>NET A PAYER :</strong> ${calculs.netAPayer}
	</p>
</body>
</html>