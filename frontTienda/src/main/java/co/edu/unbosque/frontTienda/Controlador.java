package co.edu.unbosque.frontTienda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.frontTienda.JSON.ClienteJSON;
import co.edu.unbosque.frontTienda.JSON.ConsolidadoJSON;
import co.edu.unbosque.frontTienda.JSON.Detalle_VentaJSON;
import co.edu.unbosque.frontTienda.JSON.ProductoJSON;
import co.edu.unbosque.frontTienda.JSON.ProveedorJSON;
import co.edu.unbosque.frontTienda.JSON.UsuarioJSON;
import co.edu.unbosque.frontTienda.JSON.VentaJSON;
import co.edu.unbosque.frontTienda.models.Clientes;
import co.edu.unbosque.frontTienda.models.Consolidado;
import co.edu.unbosque.frontTienda.models.Detalle_Ventas;
import co.edu.unbosque.frontTienda.models.Productos;
import co.edu.unbosque.frontTienda.models.Proveedores;
import co.edu.unbosque.frontTienda.models.Usuarios;
import co.edu.unbosque.frontTienda.models.Ventas;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	//*****************+VARIABLES GENERALES*********************************
	double precio=0, valor_iva=0, iva=0, subtotal=0, totalapagar=0, acusubtotal=0, subtotaliva=0;
	int cantidad=0,item=0;
	long numfac=0;
	String descripcion,cedulaCliente,codProducto, ciudad,fecha;
	
	Usuarios usuario=new Usuarios();
	Detalle_Ventas detalle_venta= new Detalle_Ventas();
	Consolidado consolidado =new Consolidado();
	
	List<Detalle_Ventas> listaVentas=new ArrayList<>();
	//*************************************************************************
	//------------------------------------------------------------------------------
	//--------------------------METODO PARA BUSCAR LA CEDULA DEL CLIENTE--------------------------
	//---------------------------------------------------------------------------------------------
	public void buscarCliente(String id,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			ArrayList<Clientes> listac=ClienteJSON.getJSON();
			for(Clientes clientes:listac) {
				if(clientes.getCedula_cliente().equals(id)) {
					
					request.setAttribute("clienteSeleccionado", clientes);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//------------------------------------------------------------------------------
		//--------------------------METODO PARA BUSCAR LA CCODIGO DEL PRODUCTO--------------------------
		//---------------------------------------------------------------------------------------------
	public void buscarProducto(String id,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			ArrayList<Productos> listap=ProductoJSON.getJSON();
			for(Productos producto:listap) {
				if(producto.getCodigo_producto().equals(id)) {
					request.setAttribute("productoSeleccionado", producto);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//------------------------------------------------------------------------------------------------------------------
	//****************************GRABAR EN LA TABLA DETALLE TIENDAS***********************************************++
	public void grabarDetalle_Venta(Long numFcat,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for(int i=0;i<listaVentas.size();i++) {
			detalle_venta = new Detalle_Ventas();
			
			detalle_venta.setCodigo_venta(numFcat);
			detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
			detalle_venta.setCantidad_producto(listaVentas.get(i).getCantidad_producto());
			detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
			detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
			detalle_venta.setValor_iva(listaVentas.get(i).getValor_iva());
		
			int respuesta=0;
			
			try {
				respuesta= Detalle_VentaJSON.postJSON(detalle_venta);
				PrintWriter write =response.getWriter();
				if(respuesta==200) {
					System.out.println("Registro grabado en detalle venta:"+i);
					request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
				}else {
					write.println("Error detalle venta:"+respuesta);
				}
				write.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	
	}
	//******************************************************************************************************************+
	//*********************+METODO PARA GENERAR EL CONSECUTIVO DE LA FACTURA DE VENTAS**************************************+
	 public Long generarConsecutivo() {
		 
		 long aux=0;
		 try {
			ArrayList<Ventas> listav=VentaJSON.getJSON();
			for(Ventas venta:listav) {
				
				if(venta.getCodigo_venta()>aux) {
					
					aux=venta.getCodigo_venta();
					
				}
				
				return (aux+1);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return (aux+1);
	 }
	
	//***********************************************************+++++++********************************************************
	 //-----------------------------------MOSTRAR CIUDAD Y FECHA-----------------------------------------------------
	 public void mostrarFechaCiudad(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		 fecha=request.getParameter("fecha");
		 ciudad=request.getParameter("ciudad");
		 
		 request.setAttribute("fechaSeleccionada",fecha);
		 request.setAttribute("ciudadSeleccionada",ciudad);
		 
	 }
	 //**************************************************************************************************************
	 //*******************************GRABAR CONSOLIDADO*****************************************
	 public void grabarConsolidado(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		 int respuesta=0;
		 
		 try {
			respuesta=ConsolidadoJSON.postJSON(consolidado);
			PrintWriter write =response.getWriter();
			if(respuesta==200) {
				System.out.println("Registro grabado en consolidacion");
				request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
			}else {
				write.println("Error de Consolidacion" + respuesta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 //*********************************************************************************************************************
	 /*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		//**********************Cedula del usuario activo para el modulo de ventas
		
		String cedula_usuario_activo=request.getParameter("UsuarioActivo");
		usuario.setCedula_usuario(cedula_usuario_activo);
		request.setAttribute("usuarioSeleccionado", usuario);
//_______________________________________________________________________________________________________
		switch (menu) {
		case "Principal":
			request.getRequestDispatcher("/Principal.jsp").forward(request, response);
			break;
			//*************************metodo usuarios*************************+
		case "Usuarios":
			//-------------------------------LISTAR----------------------------------------
			if (accion.equals("Listar")) {
				try {
					ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
					request.setAttribute("listaUsuarios", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(request.getParameter("txtcedula"));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));

				int respuesta = 0;
				try {
					respuesta = UsuarioJSON.postJSON(usuario);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//***********************************************************************************************
				
				//-----------------------------------ACTUALIZAR--------------------------------------------
			} else if (accion.equals("Actualizar")) {
				Usuarios usuario = new Usuarios();
				usuario.set_id(request.getParameter("txtid"));
				usuario.setCedula_usuario(request.getParameter("txtcedula"));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));
				int respuesta = 0;
				try {
					respuesta = UsuarioJSON.putJSON(usuario, usuario.get_id());
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//-------------------------CARGAR------------------------------
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Usuarios> lista1 = UsuarioJSON.getJSON();
					for (Usuarios usuarios : lista1) {
						if (usuarios.getCedula_usuario().equals(id)) {
							request.setAttribute("usuarioSeleccionado", usuarios);
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//--------------------------------ELIMINAR----------------------------------
			} else if (accion.equals("Eliminar")) {
				String id = request.getParameter("id");
				int respuesta = 0;
				try {
					respuesta = UsuarioJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//********************************************************************************
			request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
			break;
			//****************metodo clientes************************************************
		case "Clientes":
			//----------------------------------LISTAR------------------------------------
			if (accion.equals("Listar")) {
				try {
					ArrayList<Clientes> lista = ClienteJSON.getJSON();
					request.setAttribute("listaClientes", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(request.getParameter("txtcedula"));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));

				int respuesta = 0;
				try {
					respuesta = ClienteJSON.postJSON(cliente);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//------------------------------ACTUALIZAR-------------------------------
			} else if (accion.equals("Actualizar")) {
				Clientes cliente = new Clientes();
				
				cliente.set_id(request.getParameter("txtid"));
				cliente.setCedula_cliente(request.getParameter("txtcedula"));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));
				int respuesta = 0;
				try {
					respuesta = ClienteJSON.putJSON(cliente, cliente.get_id());
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//----------------------------CARGAR--------------------------------------
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Clientes> lista1 = ClienteJSON.getJSON();
					for (Clientes clientes : lista1) {
						if (clientes.getCedula_cliente().equals(id)) {
							request.setAttribute("clienteSeleccionado", clientes);
							request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//----------------------------ELIMINAR--------------------------------------
			} else if (accion.equals("Eliminar")) {
				String id = request.getParameter("id");
				int respuesta = 0;
				try {
					respuesta = ClienteJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
			break;
			//************************* METODO PROVEEDORES***********************************
		case "Proveedores":
			//----------------------------------LISTAR------------------------------------
			if (accion.equals("Listar")) {
				try {
					ArrayList<Proveedores> lista = ProveedorJSON.getJSON();
					request.setAttribute("listaProveedores", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNit_proveedor(request.getParameter("txtnit"));
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));

				int respuesta = 0;
				try {
					respuesta = ProveedorJSON.postJSON(proveedor);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//------------------------------ACTUALIZAR-------------------------------
			} else if (accion.equals("Actualizar")) {
				Proveedores proveedor = new Proveedores();
				
				proveedor.set_id(request.getParameter("txtid"));
				proveedor.setNit_proveedor(request.getParameter("txtnit"));
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
				int respuesta = 0;
				try {
					respuesta = ProveedorJSON.putJSON(proveedor, proveedor.get_id());
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//----------------------------CARGAR--------------------------------------
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Proveedores> lista1 = ProveedorJSON.getJSON();
					for (Proveedores proveedores : lista1) {
						if (proveedores.getNit_proveedor().equals(id)) {
							request.setAttribute("proveedorSeleccionado", proveedores);
							request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//----------------------------ELIMINAR--------------------------------------
			} else if (accion.equals("Eliminar")) {
				String id = request.getParameter("id");
				int respuesta = 0;
				try {
					respuesta = ProveedorJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
			break;
			
			//********************************METODO PRODUCTOS*************************************
		case "Productos":
			//-------------------------------------LISTAR------------------------------------------
			if (accion.equals("Listar")) {
				try {
					ArrayList<Productos> lista = ProductoJSON.getJSON();
					request.setAttribute("listaProductos", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//------------------------------AGREGAR---------------------------------
			else if (accion.equals("Agregar")) {
				Productos producto = new Productos();
				
				producto.setCodigo_producto(request.getParameter("txtcodigo"));
				producto.setNombre_producto(request.getParameter("txtnombre"));
				producto.setNit_proveedor(request.getParameter("txtnit"));
				producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtcompra")));
				producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva")));
				producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtventa")));

				int respuesta = 0;
				try {
					respuesta = ProductoJSON.postJSON(producto);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//------------------------------ACTUALIZAR-------------------------------
			} else if (accion.equals("Actualizar")) {
				Productos producto = new Productos();
				
				producto.set_id(request.getParameter("txtid"));
				producto.setCodigo_producto(request.getParameter("txtcodigo"));
				producto.setNombre_producto(request.getParameter("txtnombre"));
				producto.setNit_proveedor(request.getParameter("txtnit"));
				producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtcompra")));
				producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva")));
				producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtventa")));
				int respuesta = 0;
				try {
					respuesta = ProductoJSON.putJSON(producto, producto.get_id());
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						// Aqui muestra un mensaje de que actualizo los datos
						request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//----------------------------CARGAR--------------------------------------
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Productos> lista1 = ProductoJSON.getJSON();
					for (Productos producto : lista1) {
						if (producto.getCodigo_producto().equals(id)) {
							
							request.setAttribute("productoSeleccionado", producto);
							request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
									response);
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//----------------------------ELIMINAR--------------------------------------
			}else if (accion.equals("Eliminar")) {
				String id = request.getParameter("id");
				int respuesta = 0;
				try {
					respuesta = ProductoJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			break;
			//_-----------------------------VENTAS--------------------------
		case "Ventas":
			//**************************Enviamos al formulario la cedula del usuario  y el numero de la factura 
			request.setAttribute("usuarioSeleccionado", usuario);
			request.setAttribute("numerofactura",numfac);
			//**********************************************************************************************************
			if(accion.equals("BuscarCliente")) {
				String id=request.getParameter("cedulacliente");
				this.buscarCliente(id, request, response);
				this.mostrarFechaCiudad(request, response);
				
			}else if(accion.equals("BuscarProductos")) {
				String id=request.getParameter("cedulacliente");
				String cod=request.getParameter("codigoproducto");
				
				this.buscarCliente(id, request, response);				
				this.buscarProducto(cod, request, response);
				this.mostrarFechaCiudad(request, response);
				
			}else if(accion.equals("AgregarProducto")){
				String id=request.getParameter("cedulacliente");
				this.buscarCliente(id, request, response);
				this.mostrarFechaCiudad(request, response);
				
				detalle_venta=new Detalle_Ventas();
				item++;
				totalapagar=0;
				codProducto=request.getParameter("codigoproducto");
				descripcion=request.getParameter("nombreproducto");
				precio=Double.parseDouble(request.getParameter("precioproducto"));
				cantidad=Integer.parseInt(request.getParameter("cantidadproducto"));
				iva=Double.parseDouble(request.getParameter("ivaproducto"));
				
				subtotal=(precio*cantidad);
				valor_iva=(subtotal*iva)/100;
				
				detalle_venta.setCodigo_detalle_venta(item);	
				detalle_venta.setCodigo_producto(codProducto);
				detalle_venta.setNombre_producto(descripcion);
				detalle_venta.setCantidad_producto(cantidad);
				detalle_venta.setPrecio_producto(precio);
				detalle_venta.setCodigo_venta(numfac);
				detalle_venta.setValor_iva(valor_iva);
				detalle_venta.setValor_venta(subtotal);
				listaVentas.add(detalle_venta);
				
				for(int i = 0; i < listaVentas.size(); i++) {
					acusubtotal+=listaVentas.get(i).getValor_venta();
					subtotaliva+=listaVentas.get(i).getValor_iva();
					
				}
				totalapagar=acusubtotal+subtotaliva;
				detalle_venta.setValor_total(totalapagar);
				
				
				request.setAttribute("listaventas",listaVentas);
				request.setAttribute("totalsubtotal",acusubtotal);
				request.setAttribute("totaliva",subtotaliva);
				request.setAttribute("totalapagar",totalapagar);
				
				
			}else if(accion.equals("GenerarVenta")){
					
					cedulaCliente=request.getParameter("cedulaCliente");
					System.out.println(cedulaCliente);
					String numFact = request.getParameter("numerofactura");
					
					Ventas ventas = new Ventas();
					
					ventas.setCodigo_venta(Long.parseLong(numFact));
					ventas.setCedula_cliente(cedulaCliente);

					ventas.setCedula_usuario(usuario.getCedula_usuario());
					ventas.setValor_venta(acusubtotal);
					ventas.setIva_venta(subtotaliva);
					ventas.setTotal_venta(totalapagar);
					ventas.setFecha_venta(fecha);
					ventas.setCiudad_venta(ciudad);
					
					consolidado.setCiudad_venta(ciudad);
					consolidado.setFecha_venta(fecha);
					consolidado.setTotal_ventas(totalapagar);
					
					int respuesta=0;
					
					try {
						respuesta=VentaJSON.postJSON(ventas);
						PrintWriter write =response.getWriter();
						if(respuesta==200) {
							System.out.println("Grabacion exitosa"+respuesta);
							this.grabarConsolidado(request, response);
							this.grabarDetalle_Venta(ventas.getCodigo_venta(), request, response);
						}else {
							write.println("Error ventas" +respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					listaVentas.clear();
					item=0;
					totalapagar=0;
					acusubtotal=0;
					subtotaliva=0;
				}else {
				
				numfac=this.generarConsecutivo();
				
				request.setAttribute("numerofactura",numfac);
			}
		
			
			request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
			break;
		case "Reportes":
			request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
