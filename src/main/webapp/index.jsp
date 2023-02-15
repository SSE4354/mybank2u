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
	<div id="login">
		<h3 class="text-center pt-5">MyBank2u Banking System</h3>
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
						<form name="loginForm" id="loginForm" class="form"
							action="Login" method="post" onSubmit="return validateLogin()">
							<h3 class="text-center text-info">Login</h3>
							<div class="form-group">
								<label for="username" class="text-info">Username:</label><br>
								<input type="text" name="username" id="username" value="faizal"
									class="form-control">
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Password:</label><br>
								<input type="password" name="password" id="password" value="dHadnAfn9P"
									class="form-control">
							</div>
							<div class="form-group">
								<!-- <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br> -->
								<input type="submit" name="submit" class="btn btn-info btn-md"
									value="Login">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
		<!-- Bootstrap core JavaScript
         ================================================== -->
		<script>
			function validateLogin() {
				var username = document.loginForm.username.value;
				var password = document.loginForm.password.value;

				if ((username == "" || password == "")
						|| (username == "" && password == "")) {
					alert("Username and Password must not be void");
					return false;
				} else {
					return true;
				}
			}
		</script>
	
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>		
</body>
</html>