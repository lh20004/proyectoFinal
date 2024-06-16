/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;

import dao.ClienteDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jonathan Flores
 */
@WebServlet(name = "RegCliente", urlPatterns = {"/RegCliente"})
public class RegCliente extends HttpServlet {

    private ArrayList<Cliente> autoresList;
    private Cliente cl = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            response.sendRedirect("Login");
            return;
        } else {
            request.getRequestDispatcher("modulos/mtto/cliente/Crud_Cliente.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("aplication/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");
        System.out.println(filtro);
        if (filtro == null) {
            return;
        }
        switch (filtro) {
            case "si_registro":
                JSONArray array_clientes = new JSONArray();
                JSONObject json_clientes = new JSONObject();
                String resultado_insert = "";
                Cliente cliente = new Cliente();
                try {
                    ClienteDao oa = new ClienteDao();
                  //  int id = oa.obtenerTotalClientes()+1;
            
                    int id = Integer.parseInt(oa.generarId());
                    cliente .setIdCliente(id);
                    cliente .setNombre(request.getParameter("nombrecliente"));
                    cliente .setApellido(request.getParameter("apellidocliente"));
                    cliente .setTelefono(request.getParameter("telefono"));
                    cliente .setCorreo(request.getParameter("correo"));
                    cliente .setClave(request.getParameter("contrasenia"));
                    
                    System.out.println("cliente filtro entro" + cliente .getIdCliente());
                    resultado_insert = oa.insertar(cliente );
                    
                    if (resultado_insert == "exito") {
                        json_clientes.put("resultado", "exito");
                        json_clientes.put("codigoclienter", cliente .getApellido());
                        json_clientes.put("nombrecliente", cliente .getNombre());
                    } else {
                        json_clientes.put("resultado", "error");
                        json_clientes.put("resultado_insertar", resultado_insert);
                    }
                }catch (SQLException e) {
                    json_clientes.put("resultado", "error_sql");
                    json_clientes.put("error_mostrado", e.getMessage());
                    System.out.println("Error Code error: " + e.getErrorCode());
                    throw new RuntimeException();
                }
                
                array_clientes.put(json_clientes);
                response.getWriter().write(array_clientes.toString());
                break;
                
            case "si_consulta":
                JSONArray array_cliente = new JSONArray();
                JSONObject json_cliente = new JSONObject();
                
                String html = "";
                String el_estado = request.getParameter("estado");
                try {
                    ClienteDao obaut = new ClienteDao();
                    this.autoresList = obaut.getClientes();
                    
                    html += "<table id=\"tabla_clientes\""
                            + "class=\"table table-bordered dt-responsive nowrap\""
                            + "cellspacing=\"0\" width=\"100%\">\n"
                            + "<thead>\n"
                            + "<tr>\n"
                            + "<th>Codigo</th>\n"
                            + "<th>Nombre</th>\n"
                            + "<th>Apellido</th>\n"
                            + "<th>Telefono</th>\n"
                            + "<th>Correo</th>\n"
                            + "<th>Contraseña</th>\n"
                            + "<th>Acciones</th>\n"
                            + "</tr>\n"
                            + "</thead>\n"
                            + "<tbody>"; // Open tbody tag here
                    int cont = 0;
                    for (Cliente obj : this.autoresList) { // Use for-each loop
                        html += "<tr>";
                        html += "<td>" + obj.getIdCliente() + "</td>";
                        html += "<td>" + obj.getNombre() + "</td>";
                        html += "<td>" + obj.getApellido() + "</td>";
                        html += "<td>" + obj.getTelefono() + "</td>";
                        html += "<td>" + obj.getCorreo() + "</td>";
                        html += "<td>" + obj.getClave() + "</td>";
                        html += "<td>";
                        html += "<div class='dropdown m-b-10'>";
                        html += "<button class='btn btn-secondary dropdown-toggle'" + "type='button' id ='dropdownMenuButton'"
                                + "data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'> Seleccione</button>"; // Correct typo in 'button'
                        html += "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>";
                        html += "<a class='dropdown-item btn_eliminar' data-id='" + obj.getIdCliente() + "' href='javascript:void(0)'>Eliminar</a>";
                        html += "<a class='dropdown-item btn_editar' data-id='" + obj.getIdCliente() + "' href='javascript:void(0)'>Editar</a>";
                        html += "</div>";
                        html += "</div>";
                        html += "</td>";
                        html += "</tr>";
                        cont++;
                    }
                    html += "</tbody>\n"; // Close tbody tag
                    html += "</table>";
                    
                    json_cliente.put("resultado", "exito");
                    json_cliente.put("tabla", html);
                    json_cliente.put("cuantos", cont);
                } catch (SQLException e) {
                    json_cliente.put("resultado", "error_sql");
                    json_cliente.put("error_mostrado", e.getMessage());
                    System.out.println("Error Code: " + e.getErrorCode()); // Correct log message
                    throw new RuntimeException(e);
                }
                
                array_cliente.put(json_cliente);
                response.getWriter().write(array_cliente.toString());
                break;
                
            case "si_actualizalo":
                JSONArray array_actualizar = new JSONArray();
                JSONObject json_actualizar = new JSONObject();
                Cliente cl = new Cliente();
                String result_actualizar = "";
                try{
                    
                    ClienteDao naut = new ClienteDao();
                    
                    
                    cl.setNombre(request.getParameter("nombrecliente"));
                    cl.setApellido(request.getParameter("apellidocliente"));
                    cl.setTelefono(request.getParameter("telefono"));
                    cl.setCorreo(request.getParameter("correo"));
                    cl.setClave(request.getParameter("contrasenia"));
                    cl.setIdCliente(Integer.parseInt(request.getParameter("llave_persona")));
                    
                    result_actualizar = naut.actualizar(cl);
                    if (result_actualizar == "exito") {
                        json_actualizar.put("resultado", "exito");
                        json_actualizar.put("codigocliente", cl.getIdCliente());
                        json_actualizar.put("nombrecliente", cl.getNombre());
                        json_actualizar.put("apellidocliente", cl.getApellido());
                        json_actualizar.put("telefono", cl.getTelefono());
                        json_actualizar.put("correo", cl.getCorreo());
                        json_actualizar.put("contrasenia", cl.getClave());
                    } else {
                        json_actualizar.put("resultado", "error_sql");
                        json_actualizar.put("resultado_actualizar", result_actualizar);
                    }
                } catch (SQLException e) {
                    json_actualizar.put("resultado", "error_sql");
                    json_actualizar.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    throw new RuntimeException();
                }
                array_actualizar.put(json_actualizar);
                response.getWriter().write(array_actualizar.toString());
                break;
                
                
            case "si_cliente_especifico":
                JSONArray array_especifico = new JSONArray();
                ClienteDao clienteDao = null;
                
                try {
                    clienteDao = new ClienteDao();
                    ResultSet res_indiv = clienteDao.findById(Integer.parseInt(request.getParameter("id")));
                    
                    while (res_indiv.next()) {
                        JSONObject json_especifico = new JSONObject();
                        json_especifico.put("resultado", "exito");
                        json_especifico.put("id_persona", res_indiv.getInt("idcliente"));
                        json_especifico.put("codigocliente", res_indiv.getInt("idcliente"));
                        json_especifico.put("nombrecliente", res_indiv.getString("nombre"));
                        json_especifico.put("apellidocliente", res_indiv.getString("apellido"));
                        json_especifico.put("telefono", res_indiv.getString("telefono"));
                        json_especifico.put("correo", res_indiv.getString("correo"));
                        json_especifico.put("contrasenia", res_indiv.getString("clave"));
                        array_especifico.put(json_especifico);
                    }
                } catch (SQLException e) {
                    JSONObject json_error = new JSONObject();
                    json_error.put("resultado", "error_sql");
                    json_error.put("error_mostrado", e.getMessage());
                    response.getWriter().write(json_error.toString());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    JSONObject json_error = new JSONObject();
                    json_error.put("resultado", "error_class");
                    json_error.put("error_mostrado", e.getMessage());
                    response.getWriter().write(json_error.toString());
                    e.printStackTrace();
                }
                
                response.getWriter().write(array_especifico.toString());
                break;
                
                
                
                
            case "si_eliminalo":
                JSONArray array_aElimina = new JSONArray();
                JSONObject json_aElimina = new JSONObject();
                String resultado = "";
                ClienteDao obtA = null;
                obtA = new ClienteDao();
            {
                try {
                    resultado = obtA.deletecliente(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException ex) {
                    Logger.getLogger(RegCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                if (resultado == "exito") {
                    json_aElimina.put("resultado", "exito");
                } else {
                    json_aElimina.put("resultado", "error_eliminar");
                }
                array_aElimina.put(json_aElimina);
                response.getWriter().write(array_aElimina.toString());
                break;


        }
    }


}
