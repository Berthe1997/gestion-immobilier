<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="assetss/images/logo.jpg" rel="icon">
    <title>Code Camp BD - Login</title>
    <!--
    <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/ruang-admin.min.css" rel="stylesheet">
    -->
   
    <!-- Custom fonts for this template-->
	<link href="<c:url value="assets/backend/vendor/fontawesome-free/css/all.min.css" />"  rel="stylesheet" type="text/css">
	<link
		href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
		rel="stylesheet">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- Custom styles for this template-->
	<link href="<c:url value="assets/backend/css/sb-admin-2.min.css" />" rel="stylesheet">

</head>

<body class="bg-gradient-login" style="background-image: url('assetss/images/dimco.jpg');">
   <div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-5 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row justify-content-center">
							<div class="col-lg-11">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4"><img src="<c:url value="/assetss/images/dimco.jpg"/>" style="width:100px;height:100px;"> Connexion DIMCO</h1>
										
									</div>
									<form class="user" action="<c:url value="/Login"/>" method="post">
										<div class="form-group">
											 <input type="text" class="form-control form-control-user" required name="username" id="exampleInputEmail" placeholder="Nom Utilisateur">
										</div>
										<div class="form-group">
											 <input type="password" name="password" required class="form-control form-control-user" id="exampleInputPassword" placeholder="Mot de Passe">
										</div>
										<button type="submit" class="btn btn-success btn-block" value="Connexion" name="login">
											se connecter
										</button>
										
										<input type="hidden" name="page" value="home" />   
										
										<!--  <hr>
                    <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a> -->
									</form>
									<hr>
									<!-- <p align="center" class="login-box-msg">Your IP : <?= $ipaddres; ?></p> -->
<!-- 

            <button type="button" class="btn btn-success btn-block" onclick="function4()">
											ERROR
										</button>
-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>
	
	<script type="text/javascript">	
	
	function function4() {
		swal("success!","your data have been saved.thank you","success");
		
	}
	</script>
    <script src="<c:url value="/assetss/js/jquery.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/assetss/js/bootstrap.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/assetss/js/newVersion.js" />" type="text/javascript"></script>
    	<!-- Bootstrap core JavaScript-->	
	<script src="assets/backend/vendor/jquery/jquery.min.js"></script>
	<script src="assets/backend/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="assets/backend/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="assets/backend/js/sb-admin-2.min.js"></script>
    
</body>

</html>