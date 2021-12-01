<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Creacion de Usuario</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="static/css/user-form.css"
	th:href="@{/css/user-form.css}">
</head>

<body>
	<!-- tabla-->
	<div class="table-responsive">
		<table id="table"
			class="table table-bordered table-hover table-striped">

			<thead class="thead-dark">
				<tr>
					<th scope="col">Cedula</th>
					<th scope="col">Nombre</th>
					<th scope="col">Email</th>
					<th scope="col">Usuario</th>
					<th scope="col">Password</th>
					<th><a type="button" data-toggle="modal"
						data-target="#usuarioModal"><i class="fas fa-list"></i></a></th>
				</tr>
			</thead>
			<tbody>
				<tr>

					<c:forEach var="lista" items="${listaUsuarios}">
						<tr>
							<td>${lista.getCedula_usuario()}</td>
							<td>${lista.getNombre_usuario()}</td>
							<td>${lista.getEmail_usuario()}</td>
							<td>${lista.getUsuario()}</td>
							<td>${lista.getPassword()}</td>
							<td><a
								href="Controlador?menu=Usuarios&accion=Cargar&id=${lista.getCedula_usuario()}">
									<i class="fas fa-edit"></i></a> | 
									<a href="Controlador?menu=Usuarios&accion=Eliminar&id=${lista.get_id()} ">
									<i class="fas fa-user-times"></i></a></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
		<div class="modal fade" id="usuarioModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">

				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title">Usuarios</h4>

						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>


					<div class="modal-body">

						<!-- Seccion 1 -->

						<h6 class="card-subtitle mb-2 text-muted">En este panel
							podras gestionar los datos de los usuarios del sistema</h6>
						<div>
							<form class="form-sign" method="get" action="Controlador">
								<div class="form-group">
									<input type="hidden" name="menu" value="Usuarios"> <input
										type="hidden" name="txtid"
										value="${usuarioSeleccionado.get_id()}"> <label>Cedula:</label>
									<input type="number" name="txtcedula" class="form-control"
										value="${usuarioSeleccionado.getCedula_usuario()}" minlength="4" maxlength="14" required>
								</div>
								<div class="form-group">
									<label>Nombre:</label> <input type="text" name="txtnombre"
										class="form-control"
										value="${usuarioSeleccionado.getNombre_usuario()}"minlength="5" maxlength="40" required >
								</div>
								<div class="form-group">
									<label>Email:</label> <input type="email" name="txtemail"
										class="form-control"
										value="${usuarioSeleccionado.getEmail_usuario()}" required>
								</div>
								<div class="form-group">
									<label>Usuario:</label> <input type="text" name="txtusuario"
										class="form-control"
										value="${usuarioSeleccionado.getUsuario()}" required>
								</div>
								<div class="form-group">
									<label>Password:</label> <input type="password"
										name="txtpassword" class="form-control"
										value="${usuarioSeleccionado.getPassword()}"required>
								</div>
								<input type="submit" class="btn btn-primary" name="accion"
									value="Agregar"> <input type="submit"
									class="btn btn-success" name="accion" value="Actualizar">
							</form>
						</div>


					</div>

				</div>

			</div>

		</div>
	</div>
	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
</body>

</html>
