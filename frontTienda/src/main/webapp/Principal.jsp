<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página principal</title>
<link rel="shortcut icon" href="css/mando-play.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<button type="button" data-bs-toggle="offcanvas"
			data-bs-target="#offcanvas" aria-controls="offcanvas"
			style="color: white;" class="navbar-toggler">
			<span class="navbar-toggler-icon"></span>Menu
		</button>
		<!--Menu sucursal-->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="#"> <img
					src="css/pacman-ghosts.jfif" width="40" height="40"
					class="d-inline-block align-text-top"> Sucursal:<span id="mensaje"></span>
				</a>

			</div>
		</nav>


		<div class="btn-group dropleft">
			<a class="btn btn-secondary btn-lg" type="button"
				style="color: white; background-color: #212529; border: none;">
				${usuario.getUsuario()}</a>
			<a style="color: white; background-color: #212529; border: none;"
				class="btn btn-lg btn-secondary dropdown-toggle dropdown-toggle-split"
				id="dropdownMenuButton" data-bs-toggle="dropdown"> </a> <span
				class="sr-only">Togg l</span>

			<div class="dropdown-menu text-center"
				style="background-color: #212529;">
				<a><img src="img/usuario.png" height="80" width="80"></a><br>
				<a style="border: none;" class="btn btn-dark">${usuario.getUsuario()}</a><br>
				<a style="border: none;" class="btn btn-dark">${usuario.getEmail_usuario()}</a>
				<div class="dropdown-divider"></div>
				<form class="dropdown-item" method="POST" action="#">
					<button class="btn btn-danger " type="submit" name="accion"
						value="Salir">Cerrar Sesion</button>
				</form>

			</div>

		</div>


		<!-- Contenido de off canvas  -->
		<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvas"
			aria-labelledby="offcanvasLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Menu
					Principal</h5>
				<button type="button" class="btn-close text-reset"
					data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div style="background-color: #212529;" class="offcanvas-body">

				<ul class="navbar-nav">
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light" href="Controlador?menu=Principal">Home</a>
					</li>
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light"
						href="Controlador?menu=Usuarios&accion=Listar" target="myFrame"
						onclick="return validarMenu('usuarios')">Usuarios</a></li>
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light"
						href="Controlador?menu=Clientes&accion=Listar" target="myFrame"
						onclick="return validarMenu('clientes')">Clientes</a></li>
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light"
						href="Controlador?menu=Proveedores&accion=Listar" target="myFrame"
						onclick="return validarMenu('proveedores')">Proveedores</a></li>
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light"
						href="Controlador?menu=Productos&accion=Listar" target="myFrame"
						onclick="return validarMenu('productos')">Productos</a></li>
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light" href="Controlador?menu=Ventas&accion=default&UsuarioActivo=${usuario.getCedula_usuario()}"
						target="myFrame" onclick="return validarMenu('ventas')">Ventas</a>
					</li>
					<li class="nav-item"><a
						style="margin-left: 10px; border: none"
						class="btn btn-outline-light" href="Controlador?menu=Reportes"
						target="myFrame" onclick="return validarMenu('reportes')">Reportes</a>
					</li>
				</ul>


			</div>
		</div>
	</nav>

	<!-- Contenido de off canvas, end.  -->
	<div class="m-4" style="height: 550px">
		<iframe style="height: 100%; width: 100%; border: none" name="myFrame"></iframe>
	</div>


	<!-- Seccion para mostrar las paginas -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="js/sucursal.js"></script>

</body>
</html>