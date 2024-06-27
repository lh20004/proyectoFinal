/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;

import dao.ClienteComboDao;

import dao.ServicioComboDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.DetalleReserva;
import modelo.Servicio;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luis
 */
@WebServlet(name = "PagoServlet", urlPatterns = {"/PagoServlet"})
public class PagoServlet extends HttpServlet {

    // Otras declaraciones de variables y métodos
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");
        JSONObject jsonResponse = new JSONObject();

        if (filtro == null) {
            return;
        }

        switch (filtro) {

            case "cargarCombos":
                JSONArray arrayCombos = new JSONArray();
                JSONObject jsonCombos = new JSONObject();
                try {
                    ClienteComboDao clienteDao = new ClienteComboDao();
                    ServicioComboDao servicioDao = new ServicioComboDao();
                    ArrayList<Cliente> clientes = clienteDao.obtenerClientes();
                    ArrayList<Servicio> servicios = servicioDao.obtenerServicios();

                    StringBuilder sbClientes = new StringBuilder();
                    for (Cliente cliente : clientes) {
                        sbClientes.append("<option value='").append(cliente.getIdCliente()).append("'>")
                                .append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("</option>");
                    }

                    StringBuilder sbServicios = new StringBuilder();
                    for (Servicio servicio : servicios) {
                        sbServicios.append("<option value='").append(servicio.getIdServicio()).append("'>")
                                .append(servicio.getServicio()).append("</option>");
                    }

                    jsonCombos.put("clientes", sbClientes.toString());
                    jsonCombos.put("servicios", sbServicios.toString());
                    jsonCombos.put("resultado", "exito");
                } catch (SQLException e) {
                    jsonCombos.put("resultado", "error");
                    jsonCombos.put("error", e.getMessage());
                }
                arrayCombos.put(jsonCombos);
                response.getWriter().write(arrayCombos.toString());
                break;

      
            // Otros casos del switch

        }
    }

    
}
