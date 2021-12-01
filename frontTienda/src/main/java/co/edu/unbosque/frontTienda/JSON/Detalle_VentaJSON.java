package co.edu.unbosque.frontTienda.JSON;
import java.io.IOException;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import co.edu.unbosque.frontTienda.models.Detalle_Ventas;


public class Detalle_VentaJSON {
	private static URL url;
	private static String sitio = "http://localhost:8084/";

	

	public static int postJSON(Detalle_Ventas detalle_venta) throws IOException {
		url = new URL(sitio+"api/detalle_venta/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
		http.setRequestMethod("POST");
		} catch (ProtocolException e) {
		e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"codigo_venta\":\""+ String.valueOf(detalle_venta.getCodigo_venta())
				+"\",\"codigo_producto\": \""+String.valueOf(detalle_venta.getCodigo_producto())
				+"\",\"cantidad_producto\": \""+String.valueOf(detalle_venta.getCodigo_producto())
				+"\",\"valor_total\":\""+String.valueOf(detalle_venta.getValor_total())
				+"\",\"valor_venta\":\""+String.valueOf(detalle_venta.getValor_venta())
				+"\",\"valor_iva\":\""+String.valueOf(detalle_venta.getValor_iva())
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
		}
}
	