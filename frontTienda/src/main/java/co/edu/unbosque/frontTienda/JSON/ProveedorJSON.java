package co.edu.unbosque.frontTienda.JSON;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.frontTienda.models.Proveedores;

public class ProveedorJSON {
	private static URL url;
	private static String sitio = "http://localhost:8083/";
//**************************Metodos que permiten mostar la informacion de clientes
	
	public static ArrayList<Proveedores> parsingProveedores(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Proveedores> lista = new ArrayList<Proveedores>();
		JSONArray proveedores = (JSONArray) jsonParser.parse(json);
		Iterator i = proveedores.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Proveedores proveedor = new Proveedores();
			proveedor.set_id(innerObj.get("_id").toString());
			proveedor.setNit_proveedor(innerObj.get("nit_proveedor").toString());
			proveedor.setCiudad_proveedor(innerObj.get("ciudad_proveedor").toString());
			proveedor.setDireccion_proveedor(innerObj.get("direccion_proveedor").toString());
			proveedor.setNombre_proveedor(innerObj.get("nombre_proveedor").toString());
			proveedor.setTelefono_proveedor(innerObj.get("telefono_proveedor").toString());
			lista.add(proveedor);
		}
		return lista;
	}
	
	public static ArrayList<Proveedores> getJSON() throws IOException, ParseException{
		url = new URL(sitio +"api/proveedores/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
		json += (char)inp[i];
		}
		ArrayList<Proveedores> lista = new ArrayList<Proveedores>();
		lista = parsingProveedores(json);
		http.disconnect();
		return lista;

	}
	//************METODO GUARDAR CLIENTS***********************	
	public static int postJSON(Proveedores proveedor) throws IOException {
		url = new URL(sitio+"api/proveedores/guardar");
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
				+"\"nit_proveedor\":\""+ String.valueOf(proveedor.getNit_proveedor())
				+"\",\"ciudad_proveedor\": \""+proveedor.getCiudad_proveedor()
				+"\",\"direccion_proveedor\": \""+proveedor.getDireccion_proveedor()
				+"\",\"nombre_proveedor\":\""+proveedor.getNombre_proveedor()
				+"\",\"telefono_proveedor\":\""+proveedor.getTelefono_proveedor()
				+"\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
		}
	public static int putJSON(Proveedores proveedor, String id) throws IOException {
		url = new URL(sitio+"api/proveedores/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
		http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
		e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"_id\":\""+ id
				+"\",\"nit_proveedor\":\""+ proveedor.getNit_proveedor()
				+"\",\"ciudad_proveedor\": \""+proveedor.getCiudad_proveedor()
				+"\",\"direccion_proveedor\": \""+proveedor.getDireccion_proveedor()
				+"\",\"nombre_proveedor\":\""+proveedor.getNombre_proveedor()
				+"\",\"telefono_proveedor\":\""+proveedor.getTelefono_proveedor()
				+"\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
		}
	public static int deleteJSON(String id) throws IOException {
		url = new URL(sitio+"api/proveedores/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
		try {
			http.setRequestMethod("DELETE");
			} catch (ProtocolException e) {
			e.printStackTrace();
			}
		
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
		}
}