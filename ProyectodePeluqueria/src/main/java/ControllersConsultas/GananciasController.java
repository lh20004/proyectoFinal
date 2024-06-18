/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersConsultas;

import dao.GananciasDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Pago;
import modelo.SemanaConverter;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "GananciasController", urlPatterns = {"/GananciasController"})
public class GananciasController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GananciasController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GananciasController at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("modulos/ganancias/ConsultaGanancias.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String filtro = request.getParameter("consultar_datos");

        if (filtro == null) {
            response.getWriter().write("{\"resultado\":\"error\", \"mensaje\":\"Filtro no proporcionado\"}");
            return;
        }

        GananciasDao gananciasDAO = new GananciasDao();

        switch (filtro) {
            case "por_dia":
                try {
                    Date fecha = Date.valueOf(request.getParameter("fecha"));
                    ArrayList<Pago> pagoList = gananciasDAO.getGananciasPorDia(fecha);
                    enviarRespuesta(response, pagoList);
                } catch (IllegalArgumentException e) {
                    enviarError(response, "Fecha inválida. Formato esperado: yyyy-MM-dd");
                } catch (SQLException e) {
                    enviarErrorSQL(response, e);
                }
                break;

            case "por_semana":
                try {
                    String semana = request.getParameter("semana");
                    Date[] fechasSemana = SemanaConverter.convertirSemanaAFechas(semana);
                    Date fechaInicio = fechasSemana[0];
                    Date fechaFin = fechasSemana[1];
                    ArrayList<Pago> pagoList = gananciasDAO.getGananciasPorSemana(fechaInicio, fechaFin);
                    enviarRespuesta(response, pagoList);
                } catch (IllegalArgumentException e) {
                    enviarError(response, "Semana inválida. Formato esperado: yyyy-'W'ww");
                } catch (SQLException e) {
                    enviarErrorSQL(response, e);
                }
                break;

            case "por_mes":
                try {
                    String mes = request.getParameter("mes");
                    ArrayList<Pago> pagoList = gananciasDAO.getGananciasPorMes(mes);
                    enviarRespuesta(response, pagoList);
                } catch (SQLException e) {
                    enviarErrorSQL(response, e);
                }
                break;

            default:
                response.getWriter().write("{\"resultado\":\"error\", \"mensaje\":\"Filtro no válido\"}");
        }
    }

    private void enviarRespuesta(HttpServletResponse response, ArrayList<Pago> pagoList) throws IOException {
        JSONArray arrayGanancias = new JSONArray();
        JSONObject jsonGanancias = new JSONObject();
        double totalGanancias = 0;

        for (Pago pago : pagoList) {
            JSONObject jsonGanancia = new JSONObject();
            jsonGanancia.put("cliente", pago.getCliente().getNombre());
            jsonGanancia.put("fecha", pago.getFechaPago());
            jsonGanancia.put("servicio", pago.getServicio().getServicio());
            jsonGanancia.put("total", pago.getTotal());
            totalGanancias += pago.getTotal();
            arrayGanancias.put(jsonGanancia);
        }
        jsonGanancias.put("resultado", "exito");
        jsonGanancias.put("data", arrayGanancias);
        jsonGanancias.put("totalGanancias", totalGanancias);

        response.getWriter().write(jsonGanancias.toString());
    }

    private void enviarError(HttpServletResponse response, String mensaje) throws IOException {
        JSONObject jsonError = new JSONObject();
        jsonError.put("resultado", "error");
        jsonError.put("mensaje", mensaje);
        response.getWriter().write(jsonError.toString());
    }

    private void enviarErrorSQL(HttpServletResponse response, SQLException e) throws IOException {
        JSONObject jsonError = new JSONObject();
        jsonError.put("resultado", "error_sql");
        jsonError.put("error_mostrado", e.getMessage());
        jsonError.put("error_code", e.getErrorCode());
        response.getWriter().write(jsonError.toString());
    }
}
