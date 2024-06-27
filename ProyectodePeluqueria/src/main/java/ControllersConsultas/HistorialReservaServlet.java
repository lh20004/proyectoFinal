/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersConsultas;

import dao.HistorialReservaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reserva;

import org.json.JSONArray;
import org.json.JSONObject;



@WebServlet(name = "HistorialReservaServlet", urlPatterns = {"/HistorialReservaServlet"})
public class HistorialReservaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");

        if (filtro == null) {
            return;
        }
        System.out.println(filtro);

        switch (filtro) {
            case "si_consulta":
                System.out.println("ENTRO A MOSTRAR EN EL SERVLET");
                JSONArray array_reservaciones = new JSONArray();
                String html = "";

                try {
                    HistorialReservaDAO obaut = new HistorialReservaDAO();
                    ArrayList<Reserva> listaReservaciones = obaut.consultarHistorialReservas();

                    html += "<table id=\"tabla_historial_reservaciones\""
                            + "+class=\"table table-bordered dt-responsive nowrap\""
                            + "cellspacing=\"0\" width=\"100%\">\n"
                            + "<thead>\n"
                            + "<th>ID Reserva</th>\n"
                            + "<th>Fecha</th>\n"
                            + "<th>Hora Inicio</th>\n"
                            + "<th>Hora fin</th>\n"
                            + "<th>Nombre</th>\n"
                            + "<th>Correo</th>\n"
                            + "<th>Tipo de Corte</th>\n"
                            + "<th>Precio</th>\n"
                            + "</tr>\n"
                            + "</thead>\n"
                            + "<tbody>";

                    int cont = 0;

                    for (Reserva objReservacion : listaReservaciones) {
                        System.out.println(objReservacion.getEmpleado());
                        cont++;
                        html += "<tr>";
                        html += "<td>" + objReservacion.getIdReserva()+ "</td>";
                        html += "<td>" + objReservacion.getFechaReserva()+ "</td>";
                        html += "<td>" + objReservacion.getHoraInicio()+ "</td>";
                        html += "<td>" + objReservacion.getHoraFin()+ "</td>";
                        html += "<td>" + objReservacion.getCliente().getNombre()+ "</td>";
                        html += "<td>" + objReservacion.getCliente().getCorreo()+ "</td>";
                        html += "<td>" + objReservacion.getServicio().getServicio()+ "</td>";
                        html += "<td>" + "$" + objReservacion.getServicio().getPrecio() + "</td>";
                        html += "</tr>";
                    }

                    html += "</tbody>\n"
                            + "\t\t</table>";

                    JSONObject json_reservacion = new JSONObject();
                    json_reservacion.put("resultado", "exito");
                    json_reservacion.put("tabla", html);
                    json_reservacion.put("cuantos", cont);

                    array_reservaciones.put(json_reservacion);

                    out.print(array_reservaciones.toString());

                } catch (SQLException e) {
                    JSONObject json_reservacion = new JSONObject();
                    json_reservacion.put("resultado", "error sql");
                    json_reservacion.put("error", e.getMessage());
                    json_reservacion.put("code error", e.getErrorCode());
                    array_reservaciones.put(json_reservacion);
                    out.print(array_reservaciones.toString());
                } catch (ClassNotFoundException ex) {
                    JSONObject json_reservacion = new JSONObject();
                    json_reservacion.put("resultado", "class not found");
                    json_reservacion.put("error", ex.getMessage());
                    array_reservaciones.put(json_reservacion);
                    out.print(array_reservaciones.toString());
                }
                break;

        }
    }
}