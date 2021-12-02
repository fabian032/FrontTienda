<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
	<link href="//oss.maxcdn.com/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" rel="stylesheet"></link>
<meta charset="ISO-8859-1">
<title>Creacion de Productos</title>
</head>
<body>
	<div class="row">
		<!-- Seccion 1 -->
		<div class="card col-md-4">
			<div class="card-body">
				<h5 class="card-title">Productos</h5>
				<h6 class="card-subtitle mb-2 text-muted">En este panel podras
					gestionar los datos de los usuarios del sistema</h6>
				<div>
					<form id="validarc" class="form-sign" method="get" action="Controlador">
						<div class="form-group">
							<input type="hidden" name="menu" value="Productos"> <input
								type="hidden" name="txtid"
								value="${productoSeleccionado.get_id()}"> 
								<label>Codigo:</label> <input id="codigo" type="text" name="txtcodigo" class="form-control"
								value="${productoSeleccionado.getCodigo_producto()}" required title="EL CODIGO ES REQUERIDO">
						</div>
						<div class="form-group">
							<label>Nombre:</label> <input id="nombre" type="text" name="txtnombre"
								class="form-control"
								value="${productoSeleccionado.getNombre_producto()}" required title="EL NOMBRE ES REQUERIDO">
						</div>

						<div class="form-group">
							<label>Nit proveedor:</label> <input id="nit" type="text" name="txtnit"
								class="form-control"
								value="${productoSeleccionado.getNit_proveedor()}" required title="EL Nit ES REQUERIDO">
						</div>
						<div class="form-group">
							<label>precio compra:</label> <input id="precioc" type="text" name="txtcompra"
								class="form-control" value="${productoSeleccionado.getPrecio_compra()}" required title="EL PRECIO DE COMPRA ES REQUERIDO">
						</div>
						<div class="form-group">
							<label>Iva compra:</label> <input id="iva" type="text"
								name="txtiva" class="form-control"
								value="${productoSeleccionado.getIva_compra()}" required title="EL Iva ES REQUERIDO">
						</div>
						<div class="form-group">
							<label>Precio venta:</label> <input id="preciov" type="text"
								name="txtventa" class="form-control"
								value="${productoSeleccionado.getPrecio_venta()}" required title="EL PRECIO DE VENTA ES REQUERIDO">
						</div>
						<input type="submit" class="btn btn-primary" name="accion" value="Agregar">
					    <input type="submit" class="btn btn-success" name="accion" value="Actualizar"> 
					</form>
					
					<!-- parte para importar un archivo csv -->
					<div class="form-group">
					<form action="http://localhost:8083/api/productos/import" method="post" enctype="multipart/form-data">
					 importar archivo CSV:
					 <input type="file" name="file" class="form-control" id="archivoInput" onchange="return validarExt()">
					 <input type="submit"  class="btn btn-success" name="accion" value="Cargar">
					</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Seccion 2  tabla de productos-->
		<div class="col-md-8">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Codigo</th>
						<th scope="col">Nombre</th>
						<th scope="col">Nit proveedor</th>
						<th scope="col">Precio Compra </th>
						<th scope="col">iva compra</th>
						<th scope="col">precio venta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lista" items="${listaProductos}">
						<tr>
							<td>${lista.getCodigo_producto()}</td>
							<td>${lista.getNombre_producto()}</td>
							<td>${lista.getNit_proveedor()}</td>
							<td>${lista.getPrecio_compra()}</td>
							<td>${lista.getIva_compra()}</td>
							<td>${lista.getPrecio_venta()}</td>
							<td><a class="btn btn-warning"
								href="Controlador?menu=Productos&accion=Cargar&id=${lista.getCodigo_producto()}">
									Editar</a> 
								<a class="btn btn-danger"
								href="Controlador?menu=Productos&accion=Eliminar&id=${lista.get_id()}">Eliminar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="js/validar.js"></script>
</body>
</html>