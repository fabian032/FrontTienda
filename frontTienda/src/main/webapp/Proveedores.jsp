<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Creacion de Proveedor</title>
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

</head>

<body>
	<!-- tabla-->
	<div class="table-responsive">
		<table id="table"
			class="table table-bordered table-hover table-striped">

			<thead class="thead-dark">
				<tr>
					<th scope="col">Nit proveedor</th>
					<th scope="col">Ciudad </th>
					<th scope="col">Direccion </th>
					<th scope="col">Nombre</th>
					<th scope="col">Telefono</th>
					<th><a type="button" data-toggle="modal"
						data-target="#proveedorModal"><i class="fas fa-list"></i></a></th>
				</tr>
			</thead>
			<tbody>
				<tr>

					<c:forEach var="lista" items="${listaProveedores}">
						<tr>
							<td>${lista.getNit_proveedor()}</td>
							<td>${lista.getCiudad_proveedor()}</td>
							<td>${lista.getDireccion_proveedor()}</td>
							<td>${lista.getNombre_proveedor()}</td>
							<td>${lista.getTelefono_proveedor()}</td>
							<td><a
							 href="Controlador?menu=Proveedores&accion=Cargar&id=${lista.getNit_proveedor()}">
							<i class="fas fa-edit"></i>
							</a> | <a href="Controlador?menu=Proveedores&accion=Eliminar&id=${lista.get_id()}"><i
									class="fas fa-user-times"></i></a></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
		<div class="modal fade" id="proveedorModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">

				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title">Proveedores</h4>

						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>


					<div class="modal-body">

						<!-- Seccion 1 -->

						<h6 class="card-subtitle mb-2 text-muted">En este panel
							podras gestionar los datos de los proveedores del sistema</h6>
						<div>
							<form class="form-sign" method="get" action="Controlador">
								<div class="form-group">
									<input type="hidden" name="menu" value="Proveedores"> <input
										type="hidden" name="txtid"
										value="${proveedorSeleccionado.get_id()}"> 
										<label>Nit:</label>
									<input type="text" name="txtnit" class="form-control"
										value="${proveedorSeleccionado.getNit_proveedor()}">
								</div>
								<div class="form-group">
									<label>Ciudad:</label>
									 <input type="text" name="txtciudad"class="form-control"
										value="${proveedorSeleccionado.getCiudad_proveedor()}">
								</div>
								<div class="form-group">
									<label>Direccion:</label> <input type="text" name="txtdireccion"
										class="form-control"
										value="${proveedorSeleccionado.getDireccion_proveedor()}">
								</div>
								<div class="form-group">
									<label>Nombre:</label> <input type="text" name="txtnombre"
										class="form-control"
										value="${proveedorSeleccionado.getNombre_proveedor()}">
								</div>
								<div class="form-group">
									<label>Telefono:</label> <input type="text"
										name="txttelefono" class="form-control"
										value="${proveedorSeleccionado.getTelefono_proveedor()}">
								</div>
								<input type="submit" class="btn btn-primary" name="accion"
									value="Agregar">
								<input type="submit" class="btn btn-success" name="accion" 
									value="Actualizar">
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
