function validarExt() {
	var archivoInput = document.getElementById('archivoInput');
	var archivoRuta = archivoInput.value;
	var extPermitidas = /(.csv|.CSV)$/i;
	if (!extPermitidas.exec(archivoRuta)) {
		alert('Asegurese de haber seleccionado un archivo CSV');
		archivoInput.value = '';
		return false;
	}
}

//************validar productos************

var input =
	document.getElementById('codigo');
	input.oninvalid = function(event) {
	alert('El codigo es requerido');
}
var input =
	document.getElementById('nombre');
	input.oninvalid = function(event) {
	alert('El nombre es requerido');
}
var input =
	document.getElementById('nit');
	input.oninvalid = function(event) {
	alert('El nit es requerido');
}
var input =
	document.getElementById('precioc');
	input.oninvalid = function(event) {
	alert('El precio de compra es requerido');
}
var input =
	document.getElementById('iva');
	input.oninvalid = function(event) {
	alert('El iva es requerido');
}
var input =
	document.getElementById('preciov');
	input.oninvalid = function(event) {
	alert('El precio de venta es requerido');
}		

//************************************************************

