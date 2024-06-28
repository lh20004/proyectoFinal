/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;

import dao.PagosDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.DetalleReserva;
import modelo.Pago;
import modelo.Reserva;
import modelo.Servicio;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author javier
 */
@WebServlet(name = "ServiciosPago", urlPatterns = {"/ServiciosPago"})
public class ServiciosPago extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiciosPago</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiciosPago at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
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
        //processRequest(request, response);
        ejecutar(request, response);
    }

    public void ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int opcion = Integer.parseInt(request.getParameter("opcion"));

        PagosDao daoPago = new PagosDao();

        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
        ArrayList<DetalleReserva> listaDetalleReserva = new ArrayList<DetalleReserva>();

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        switch (opcion) {
            case 1:
                //este retorna los clientes con reservas para este dia
                try {
                lista = daoPago.selectCliente();
                for (Cliente c : lista) {
                    json = new JSONObject();
                    json.put("id", c.getIdCliente());
                    json.put("nombre", c.getNombre());
                    array.put(json);
                }
                json = new JSONObject();
                json.put("clientes", array);
                json.put("result", true);
            } catch (Exception e) {
                json.put("result", false);
            }
            response.getWriter().write(json.toString());
            break;
            case 2:
                //este retorna los servicios 
                try {
                listaServicios = daoPago.selectServicios();
                for (Servicio s : listaServicios) {
                    json = new JSONObject();
                    json.put("id", s.getIdServicio());
                    json.put("servicio", s.getServicio());
                    json.put("precio", s.getPrecio());
                    array.put(json);
                }
                json = new JSONObject();
                json.put("servicios", array);
                json.put("result", true);
            } catch (Exception e) {
                json.put("result", false);
            }
            response.getWriter().write(json.toString());
            break;
            case 3:
                //este trae las reservas que tiene un cliente el dia de hoy
                try {
                    listaDetalleReserva = daoPago.selectReservasConfirmadas(Integer.parseInt(
                        request.getParameter("id")));
                    for(DetalleReserva d : listaDetalleReserva){
                        json = new JSONObject();
                        json.put("id", d.getServicio().getIdServicio());
                        json.put("servicio", d.getServicio().getServicio());
                        json.put("precio", d.getServicio().getPrecio());
                        array.put(json);
                    }
                    json = new JSONObject();
                    json.put("servicios", array);
                    json.put("fecha", listaDetalleReserva.get(0).getReserva().getFechaReserva().getTime());
                    json.put("idreserva", listaDetalleReserva.get(0).getReserva().getIdReserva());
                    json.put("result", true);
                } catch (Exception e) {
                    json.put("result", false);
                }
                response.getWriter().write(json.toString());
            break;
            case 4:
                JSONArray extras = new JSONArray(request.getParameter("extras"));
                Date fecha = new Date(Long.parseLong(request.getParameter("fecha")));
                JSONArray idreservas = new JSONArray(request.getParameter("idreservas"));
                double total = Double.parseDouble(request.getParameter("total"));
                
                for(int i =0;i<extras.length();i++){
                    JSONObject tmpjson = extras.getJSONObject(i);
                    
                    DetalleReserva tmpDetalle = new DetalleReserva();
                    
                    Reserva tmpReserva = new Reserva();
                    tmpReserva.setIdReserva(tmpjson.getInt("idreserva"));
                    
                    Servicio tmpServicio = new Servicio();
                    tmpServicio.setIdServicio(tmpjson.getInt("idservicio"));
                    
                    tmpDetalle.setReserva(tmpReserva);
                    tmpDetalle.setServicio(tmpServicio);
                    try{
                        daoPago.insertExtras(tmpDetalle);
                    }catch(Exception e){
                        json.put("result", false);
                        response.getWriter().write(json.toString());
                        return;
                    }
                }
                
                for(int i=0;i<idreservas.length();i++){
                    JSONObject tmpjson = idreservas.getJSONObject(i);
                    Reserva tmpReserva = new Reserva();
                    tmpReserva.setIdReserva(tmpjson.getInt("idreserva"));
                    try{
                        daoPago.CAMBIAR_ESTADO_RESERVA(tmpReserva);
                    }catch(Exception e){
                        json.put("result", false);
                        response.getWriter().write(json.toString());
                        return;
                    }
                    Pago tmpPago = new Pago();
                    tmpPago.setTotal(total);
                    tmpPago.setFechaPago(fecha);
                    Cliente tmpCliente = new Cliente();
                    tmpCliente.setIdCliente(tmpjson.getInt("idcliente"));
                    tmpPago.setCliente(tmpCliente);
                    
                    try{
                        daoPago.insertPago(tmpPago);
                        tmpPago = daoPago.ULTIMO_PAGO();
                        JSONArray idservicios = tmpjson.getJSONArray("idservicios");
                        for(int j=0;j<idservicios.length();j++){
                            daoPago.INSERTAR_DETALLE(tmpPago.getIdPago(), idservicios.getInt(j));
                        }
                    }catch(Exception e){
                        json.put("result", false);
                        response.getWriter().write(json.toString());
                        return;
                    }
                }
                json.put("result", true);
                response.getWriter().write(json.toString());
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
