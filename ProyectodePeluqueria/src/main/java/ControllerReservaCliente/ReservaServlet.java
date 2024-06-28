/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerReservaCliente;

import dao.EmpleadoComboDao;
import dao.ReservaDao_1;
import dao.ServicioComboDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Reserva;
import modelo.Servicio;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luis
 */
@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {

    private ArrayList<Reserva> listaReservas;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implementar si es necesario
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");
        JSONObject json_reserva = new JSONObject();

        if (filtro == null) {
            return;
        }

        switch (filtro) {

            case "cargarCombos":
                JSONArray arrayCombos = new JSONArray();
                JSONObject jsonCombos = new JSONObject();
                try {
                    EmpleadoComboDao empleadoDao = new EmpleadoComboDao();
                    ServicioComboDao servicioDao = new ServicioComboDao();
                    ArrayList<Empleado> empleados = empleadoDao.obtenerPeluqueros();
                    ArrayList<Servicio> servicios = servicioDao.obtenerServicios();

                    StringBuilder sbEmpleados = new StringBuilder();
                    for (Empleado empleado : empleados) {
                        sbEmpleados.append("<option value='").append(empleado.getIdEmpleado()).append("'>").append(empleado.getNombre()).append("</option>");
                    }

                    StringBuilder sbServicios = new StringBuilder();
                    for (Servicio servicio : servicios) {
                        sbServicios.append("<option value='").append(servicio.getIdServicio()).append("'>").append(servicio.getServicio()).append("</option>");
                    }

                    jsonCombos.put("empleados", sbEmpleados.toString());
                    jsonCombos.put("servicios", sbServicios.toString());
                    jsonCombos.put("resultado", "exito");
                } catch (SQLException e) {
                    jsonCombos.put("resultado", "error");
                    jsonCombos.put("error", e.getMessage());
                }
                arrayCombos.put(jsonCombos);
                response.getWriter().write(arrayCombos.toString());
                break;

            case "si_consulta":
                JSONArray array_reserva = new JSONArray();
                try {
                    ReservaDao_1 dao = new ReservaDao_1();
                    this.listaReservas = new ArrayList<>();
                    this.listaReservas = dao.obtenerReservas();

                    String html = "<table id='tabla_reservas' class='table table-striped' style='width:100%'>\n"
                            + "<thead>\n"
                            + "<tr>\n"
                            + "<th>Fecha de reservación</th>\n"
                            + "<th>Hora de inicio</th>\n"
                            + "<th>Hora de fin</th>\n"
                            + "</tr>\n"
                            + "</thead>\n"
                            + "<tbody>\n";
                    for (Reserva objRes : this.listaReservas) {
                        html += "<tr>";
                        html += "<td>" + objRes.getFechaReserva() + "</td>";
                        html += "<td>" + objRes.getHoraInicio() + "</td>";
                        html += "<td>" + objRes.getHoraFin() + "</td>";
                        html += "</tr>";
                    }

                    html += "</tbody>\n</table>";

                    json_reserva.put("resultado", "exito");
                    json_reserva.put("tabla", html);
                } catch (SQLException e) {
                    json_reserva.put("resultado", "error sql");
                    json_reserva.put("error", e.getMessage());
                    json_reserva.put("code error", e.getErrorCode());
                }
                array_reserva.put(json_reserva);
                response.getWriter().write(array_reserva.toString());
                break;

            case "insertar":
                JSONObject jsonResponse = new JSONObject();
                System.out.println("llego al case:");
                try {
                    // Obtener el idCliente de la sesión
                    Integer clienteId = (Integer) request.getSession().getAttribute("idCliente");

                    if (clienteId == null) {
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "ID de cliente no encontrado en la sesión.");
                        response.getWriter().write(jsonResponse.toString());
                        break;
                    }

                    String empleadoParam = request.getParameter("empleado");
                    String servicioParam = request.getParameter("servicio");
                    String fechaStr = request.getParameter("fecha_reservacion");
                    String horaInicioStr = request.getParameter("hora_inicio");
                    String horaFinStr = request.getParameter("hora_fin");

                    // Depuración de parámetros recibidos
                    System.out.println("Parámetros recibidos:");
                    System.out.println("Cliente ID (de sesión): " + clienteId);
                    System.out.println("Empleado: " + empleadoParam);
                    System.out.println("Servicio: " + servicioParam);
                    System.out.println("Fecha de Reservación: " + fechaStr);
                    System.out.println("Hora de Inicio: " + horaInicioStr);
                    System.out.println("Hora de Fin: " + horaFinStr);

                    // Conversión de parámetros a tipos correctos
                    int empleadoId = Integer.parseInt(empleadoParam);

                    // Conversión de String a Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaReserva = new Date(dateFormat.parse(fechaStr).getTime());

                    // Obtener la fecha actual
                    Date fechaActual = new Date(System.currentTimeMillis());

                    // Comprobar que la fecha de la reserva no sea anterior a la fecha actual
                    if (fechaReserva.before(fechaActual)) {
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "La fecha de la reserva no puede ser anterior a la fecha actual.");
                        response.getWriter().write(jsonResponse.toString());
                        System.out.println("Error: La fecha de la reserva no puede ser anterior a la fecha actual.");
                        break;
                    }

                    // Verificar si el cliente ya tiene una reserva para el mismo día
                    ReservaDao_1 dao = new ReservaDao_1();
                    if (dao.existeReservaClienteEnFecha(clienteId, fechaReserva)) {
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "Ya existe una reserva para el cliente en la misma fecha.");
                        response.getWriter().write(jsonResponse.toString());
                        System.out.println("Error: Ya existe una reserva para el cliente en la misma fecha.");
                        break;
                    }

                    // Conversión de String a Time
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    Time horaInicio = new Time(timeFormat.parse(horaInicioStr).getTime());
                    Time horaFin = new Time(timeFormat.parse(horaFinStr).getTime());

                    // Comprobar que la hora de fin no sea anterior a la hora de inicio
                    if (horaFin.before(horaInicio)) {
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "La hora de fin no puede ser menor que la hora de inicio.");
                        response.getWriter().write(jsonResponse.toString());
                        System.out.println("Error: La hora de fin no puede ser menor que la hora de inicio.");
                        break;
                    }

                    Time horaInicioLimite = Time.valueOf("08:00:00");
                    Time horaFinLimite = Time.valueOf("17:00:00");

                    if (horaInicio.before(horaInicioLimite) || horaFin.after(horaFinLimite)) {
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "La hora de inicio y fin debe estar dentro del intervalo de 08:00 a 17:00.");
                        response.getWriter().write(jsonResponse.toString());
                        System.out.println("Error: La hora de inicio y fin debe estar dentro del intervalo de 08:00 a 17:00.");
                        break;
                    }

                    long diferenciaTiempo = horaFin.getTime() - horaInicio.getTime();
                    if (diferenciaTiempo < 1800000) { // 1800000 milisegundos = 30 minutos
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "El intervalo de tiempo debe ser de al menos 30 minutos.");
                        response.getWriter().write(jsonResponse.toString());
                        System.out.println("Error: El intervalo de tiempo debe ser de al menos 30 minutos.");
                        break;
                    } else if (diferenciaTiempo > 7200000) { // 7200000 milisegundos = 2 horas
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "El intervalo de tiempo no debe exceder las 2 horas.");
                        response.getWriter().write(jsonResponse.toString());
                        System.out.println("Error: El intervalo de tiempo no debe exceder las 2 horas.");
                        break;
                    }

                    // Crear objetos y establecer valores para la reserva
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(clienteId);
                    Empleado empleado = new Empleado();
                    empleado.setIdEmpleado(empleadoId);
                    Servicio servicio = new Servicio();
                    //servicio.setIdServicio(servicioId);

                    Reserva reserva = new Reserva();
                    reserva.setCliente(cliente);
                    reserva.setEmpleado(empleado);
                    //reserva.setIdServicio(servicio.getIdServicio());
                    reserva.setFechaReserva(fechaReserva);
                    reserva.setHoraInicio(horaInicio);
                    reserva.setHoraFin(horaFin);
                    reserva.setEstado("confirmado");

                    System.out.println("Datos de la reserva a insertar: " + reserva);

                    if (dao.existeReservaDuplicada(empleadoId, fechaReserva, horaInicio, horaFin)) {
                        jsonResponse.put("resultado", "error");
                        jsonResponse.put("mensaje", "Ya existe una reserva para este empleado en el mismo intervalo de tiempo.");
                    } else {

                        // Insertar la reserva en la base de datos
                        boolean resultado = dao.insertarReserva(reserva);

                        //insercion de relaciones
                        JSONArray listaIdServicio = new JSONArray(servicioParam);
                        Reserva utimaReserva = dao.getUltimaReserva();
                        for (int i = 0; i < listaIdServicio.length(); i++) {
                            dao.insertarDetalleReserva(utimaReserva.getIdReserva(), Integer.parseInt(listaIdServicio.getString(i)));
                        }

                        // Verificar el resultado de la inserción
                        if (resultado) {
                            jsonResponse.put("resultado", "exito");
                            System.out.println("Reserva insertada correctamente.");
                        } else {
                            jsonResponse.put("resultado", "error de inserción");
                            System.out.println("Error: No se pudo insertar la reserva.");
                        }

                        // Continuar con la inserción de la reserva
                    }

                } catch (NumberFormatException e) {
                    jsonResponse.put("resultado", "error");
                    jsonResponse.put("mensaje", "Error de formato de número: " + e.getMessage());
                    System.err.println("Error de formato de número: " + e.getMessage());
                } catch (SQLException e) {
                    jsonResponse.put("resultado", "error sql");
                    jsonResponse.put("mensaje", "Error SQL: " + e.getMessage());
                    System.err.println("Error SQL: " + e.getMessage());
                } catch (Exception e) {
                    jsonResponse.put("resultado", "error");
                    jsonResponse.put("mensaje", "Error general: " + e.getMessage());
                    System.err.println("Error general: " + e.getMessage());
                }
                response.getWriter().write(jsonResponse.toString());
                break;

          
        }
    }
}
