<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- <head> -->
<!-- <meta charset="ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- <link rel="stylesheet" href="css/styles.css"> -->
<!--  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"></link> -->
<!-- <link href="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" rel="stylesheet"></link> -->
<!--  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css"> -->
<!-- </head> -->
<!-- <body> -->
<!--  <div class="modal-dialog text-center"> -->
<!--         <div class="col-sm-8 main-section"> -->
<!--             <div class="modal-content"> -->
<!--                 <div class="col-12 user-img"> -->
<!--                     <img src="img/user.png"/> -->
<!--                 </div> -->
<!--                 <form id="loginForm" class="col-12" action="./Servlet" method="POST"> -->
<!--                     <div class="form-group" id="user-group"> -->
<!--                         <input type="text" class="form-control" placeholder="Nombre de usuario" name="txtusuario"/> -->
<!--                     </div> -->
<!--                     <div class="form-group" id="contrasena-group"> -->
<!--                         <input type="password" class="form-control" placeholder="Contrasena" name="txtpassword"/> -->
<!--                     </div> -->
<!--                     <button type="submit" class="btn btn-primary" name="accion" value="Ingresar"><i class="fas fa-sign-in-alt" ></i>  Ingresar </button> -->
<!--                 </form> -->
<!--                 <div class="col-12 forgot"> -->
<!--                     <a href="#">Recordar contrasena?</a> -->
<!--                 </div> -->
                
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
	<head>
		<meta charset="ISO-8859-1">
		<title>Bienvenido Gamer</title>
		<link rel="shortcut icon" href="css/mando-play.ico">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Gemunu+Libre:wght@300;700&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css"/>	
	</head>
	<body>
		<header>Tienda Gaming</header>
		<form action="./Servlet" method="POST">
			<table>
			    <tr>
			    	<td><label for="usuario">Usuario:</label></td>
			    	<td><input name="txtusuario" id="usuario"></td>			    	
			    </tr>
			    <tr>
			    	<td><label for="password">Contrase?a:</label></td>
			    	<td><input type="password" name="txtpassword" id="password"></td>
			    </tr>
			    <tr>
			    	<td><button type="submit" name="accion" value="Ingresar">Ingresar</button></td>
			    	<td><button type="reset">Cancelar</button></td>
			    </tr>
			</table>
		</form>
		<!-- Agregar correos de todos para referencia -->
		<a href="mailto:heavy_juan@hotmail.com">Correos de contacto aqu?</a>
		<p>Copyright? 2021, Juan Solano, Fabian Alfonso, Osval Zuluaga</p>
<!-- <script src="//oss.maxcdn.com/jquery/1.11.1/jquery.min.js"></script> -->
<!-- <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>   -->
<!-- <script src="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script> -->
<!-- <script type="text/javascript" src="js/login.js"></script> -->
</body>
</html>
