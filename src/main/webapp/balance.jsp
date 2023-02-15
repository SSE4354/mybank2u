<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>MyBank2u Banking System</title>

<!--Bootsrap 4 CDN-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<!--Custom styles-->

</head>
<body>
	<div id="">
		<h3 class="text-center pt-5">MyBank2u Banking System</h3>
		<div class="container">
			<div class="row justify-content-center align-items-center">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<h3 class="card-title text-info">Account Balance</h3>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Account
									Number</label>
								<div class="col-sm-10">: ${account.id}</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Name</label>
								<div class="col-sm-10">: ${account.name}</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Email</label>
								<div class="col-sm-10">: ${account.email}</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Balance</label>
								<div class="col-sm-10">: RM ${totalBalance}</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 m-3 text-right">
						<a href="Login" class="btn btn-info btn-md">Logout</a>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>