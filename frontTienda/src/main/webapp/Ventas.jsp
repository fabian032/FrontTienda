<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	ntegrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<title>Ventas</title>

</head>

<body>
	<div class="row">
	<!-- Seccion1 -->
		<div class="col-md-5 seccion1">
			<form method="get" action="Controlador">
				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label>Seleccione fecha</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="date" name="fecha" class="form-control" value="${fechaSeleccionada}" required>
							</div>
							<div class="col-sm-3">
								<label>Seleccione la ciudad </label> 
							</div>
							<div class="col-sm-3">
									<select name="ciudad"class="form-select" required>
									<option>${ciudadSeleccionada}</option> 
									<option value="Bogota">Bogota</option>
									<option value="Cali">Cali</option>
									<option value="Medellin">Medellin</option>
								</select>
							</div>
						</div>
					</div>
					
				</div>

				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label>Datos Clientes </label>
						</div>
						<input type="hidden" name="menu" value="Ventas"> 
						<input type="hidden" name="UsuarioActivo" value="${usuarioSeleccionado.getCedula_usuario()}">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulacliente" class="form-control"
									placeholder="Cedula cliente" value="${clienteSeleccionado.getCedula_cliente()}" required> 
								<input
									type="submit" name="accion" value="BuscarCliente"
									class="btn btn-outline-info">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombrecliente" class="form-control"
									placeholder="Nombre cliente" value="${clienteSeleccionado.getNombre_cliente()}">
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label>Datos Productos </label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="codigoproducto" class="form-control"placeholder="Codigo producto" value="${productoSeleccionado.getCodigo_producto()}" > 
								<input type="submit" name="accion" value="BuscarProductos" class="btn btn-outline-info">
							</div>
							<div class="col-sm-6 d-flex">
								<input type="text" name="nombreproducto" class="form-control"placeholder="Nombre producto" value="${productoSeleccionado.getNombre_producto()}">
							</div>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="text" name="precioproducto" class="form-control"placeholder="$ 0000.00" value="${productoSeleccionado.getPrecio_compra()}">
							</div>
							<div class="col-sm-3">
								<input type="number" name="cantidadproducto" class="form-control" placeholder="Cantidad" value="">
							</div>
							<div class="col-sm-3">
								<input type="text" name="ivaproducto" class="form-control" placeholder="Valor iva" value="${productoSeleccionado.getIva_compra()}">
							</div>
						</div>
						<div class="form-group d-flex">
						<input type="submit" name="accion" value="AgregarProducto" class="btn btn-outline-info	">
						</div>
					</div>
				</div>
			</form>
		</div>
	<!-- seccion2 -->
	
	<div class="col-md-7">
	
		<div class="card">
			<div class="card-header">
				<div class="form-group row">
					<label class="col-sm-3 col-form-label">Numero de factura</label> <input
						class="form-control col-md-4" type="text" name="numerofactura"
						value="${numerofactura}">
				</div>
			</div>
			<div class="card-body">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Codigo</th>
							<th scope="col">Producto</th>
							<th scope="col">Precio</th>
							<th scope="col">Cantidad</th>
							<th scope="col">Iva</th>
							<th scope="col">Total</th>
					</thead>
					<tbody>
					<c:forEach var="lista" items="${listaventas}">
							<tr>
								<th>${lista.getCodigo_detalle_venta()}</th>
								<td>${lista.getCodigo_producto()}</td>
								<td>${lista.getNombre_producto()}</td>
								<td>${lista.getPrecio_producto()}</td>
								<td>x ${lista.getCantidad_producto()}</td>
								<td>${lista.getValor_iva()}</td>
								<td>${lista.getValor_venta()}</td>

							</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
			<div class="card-footer d-flex">
				<div class="col-md-4">
					<label>Subtotal</label><br><br>
					<label>Iva</label><br><br>
					<label>Total a pagar </label>
				</div>
				<div class=col-md-4>
					<input type="text" name="txtsubtotal" class="form-control"placeholder="$00.000.0" disabled="disabled" value="${totalsubtotal}">
					<input type="text" name="txttotaliva" class="form-control" placeholder="$00.000.0" disabled="disabled" value="${totaliva}">
					<input type="text" name="txttotalapagar" class="form-control"placeholder="$00.000.0" disabled="disabled" value="${totalapagar}">
				</div>
			</div>
		</div>
		<div class="card-footer d-flex">
			<div class="col md-8">
				<a class="btn btn-success" onclick="print()" 
				href="Controlador?menu=Ventas&accion=GenerarVenta&cedulaCliente=${clienteSeleccionado.getCedula_cliente()}&UsuarioActivo=${usuarioSeleccionado.getCedula_usuario()}&numerofactura=${numerofactura}">Generar Venta</a>
				<a class="btn btn-danger" href="Controlador?menu=Ventas&accion=default">Cancelar Venta</a>
			</div>
		</div>
	</div>
	</div>
</body>
</html>