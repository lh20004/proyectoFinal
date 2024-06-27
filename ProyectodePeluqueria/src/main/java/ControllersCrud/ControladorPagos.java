package ControllersCrud;

/*
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.DetalleReserva;
import modelo.Reserva;
import modelo.Servicio;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "ControladorPagos", urlPatterns = {"/ControladorPagos"})
public class ControladorPagos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "cargarCombos":
                    cargarCombos(response);
                    break;
                case "listReservasConfirmadas":
                    listReservasConfirmadas(request, response);
                    break;
                default:
                    response.getWriter().write("{\"resultado\":\"acción no válida\"}");
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "agregarServicio":
                    agregarServicio(request, response);
                    break;
                default:
                    response.getWriter().write("{\"resultado\":\"acción no válida\"}");
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    private void cargarCombos(HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        JSONArray arrayCombos = new JSONArray();
        JSONObject jsonCombos = new JSONObject();
        PagosDao pagosDao = new PagosDao();
        ArrayList<Cliente> listcliente = pagosDao.selectCliente();
        ArrayList<Servicio> listservicio = pagosDao.selectServicios();

        StringBuilder sbClientes = new StringBuilder();
        for (Cliente cliente : listcliente) {
            sbClientes.append("<option value='").append(cliente.getIdCliente()).append("'>").append(cliente.getNombre()).append("</option>");
        }

        StringBuilder sbServicios = new StringBuilder();
        for (Servicio servicio : listservicio) {
            sbServicios.append("<option value='").append(servicio.getIdServicio()).append("'>").append(servicio.getServicio()).append("</option>");
        }

        jsonCombos.put("clientes", sbClientes.toString());
        jsonCombos.put("servicios", sbServicios.toString());
        jsonCombos.put("resultado", "exito");
        arrayCombos.put(jsonCombos);
        response.getWriter().write(arrayCombos.toString());
    }

    private void listReservasConfirmadas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        PagosDao pagosDao = new PagosDao();
        ArrayList<DetalleReserva> listDetalle = pagosDao.selectReservasConfirmadas(idCliente);

        JSONArray arrayReservas = new JSONArray();
        for (DetalleReserva detalle : listDetalle) {
            JSONObject jsonDetalle = new JSONObject();
            jsonDetalle.put("idReserva", detalle.getReserva().getIdReserva());
            jsonDetalle.put("Cliente", detalle.getReserva().getCliente().getNombre());
            jsonDetalle.put("fechaReserva", detalle.getReserva().getFechaReserva().toString());
            jsonDetalle.put("servicio", detalle.getServicio().getServicio());
            jsonDetalle.put("precio", detalle.getServicio().getPrecio());
            arrayReservas.put(jsonDetalle);
        }
        response.getWriter().write(arrayReservas.toString());
    }

    private void agregarServicio(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        int idReserva = Integer.parseInt(request.getParameter("idReserva"));
        int idServicio = Integer.parseInt(request.getParameter("idServicio"));

        DetalleReserva detalle = new DetalleReserva();
        Reserva reserva = new Reserva();
        reserva.setIdReserva(idReserva);
        Servicio servicio = new Servicio();
        servicio.setIdServicio(idServicio);
        detalle.setReserva(reserva);
        detalle.setServicio(servicio);

        PagosDao pagosDao = new PagosDao();
        String resultado = pagosDao.insertExtras(detalle);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("resultado", resultado);
        response.getWriter().write(jsonResponse.toString());
    }
}*/
