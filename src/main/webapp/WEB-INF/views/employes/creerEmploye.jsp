<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
<body>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link" href="<c:url value ='/mvc/employes/lister'/>">Employ�s</a>
						</li>
						<li class="nav-item"><a href="<c:url value ='/mvc/bulletins/lister'/>" class="nav-link">Bulletins</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="display-1 text-center">Ajouter un employ�</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form:form method="POST" modelAttribute="newEmploye" >
						<div class="form-group">
							<form:label path="matricule">Matricule</form:label>
							<form:input path="matricule" />
						</div>
						<div class="form-group">
							<form:label path="entreprise.id">Entreprise</form:label>
							<form:select path="entreprise.id">
								<form:options items="${entreprises}" itemLabel="denomination" itemValue="id"/>
							</form:select>
						</div>
						<div class="form-group">
							<form:label path="profilRemuneration.id">Profil</form:label>
							<form:select path="profilRemuneration.id">
								<form:options items="${profilsRemuneration}" itemLabel="code" itemValue="id"/>
							</form:select>
							<div class="form-group">
								<form:label path="grade.id">Grade</form:label>
								<form:select path="grade.id">
									<form:options items="${grades}" itemLabel="code" itemValue="id"/>
								</form:select>
							</div>
							<input type="submit" value="Ajouter">
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>