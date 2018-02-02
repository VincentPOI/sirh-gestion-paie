<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="<c:url value = '/bootstrap-4.0.0-dist/css/bootstrap.css'/>">
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

</head>
<body>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link" href="#">Employés</a>
						</li>
						<li class="nav-item"><a href="#" class="nav-link">Bulletins</a>
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
					<h1 class="display-1 text-center">Ajouter un employé</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<form class="">
						<div class="form-group">
							<label for="exampleInputEmail1">Matricule</label> <input
								type="text" class="form-control" aria-describedby="emailHelp"
								placeholder="" id="matricule">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Entreprise</label> <input
								type="password" class="form-control" id="exampleInputPassword1">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Profil</label> <input
								type="password" class="form-control" id="exampleInputPassword1">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Grade</label> <input type="text"
								class="form-control" id="inlineFormInput">
						</div>
						<button type="submit"
							class="btn btn-primary align-self-end align-items-end">Ajouter</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>