/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersConsultas;

import dao.GananciasDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Pago;
//import modelo.SemanaUtils;
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
                consultarPorDia(request, response, gananciasDAO);
                break;
            case "por_semana":
                consultarPorSemana(request, response, gananciasDAO);
                break;
            case "por_mes":
                consultarPorMes(request, response, gananciasDAO);
                break;
            default:
                response.getWriter().write("{\"resultado\":\"error\", \"mensaje\":\"Filtro no válido\"}");
        }
    }

    private void consultarPorDia(HttpServletRequest request, HttpServletResponse response, GananciasDao gananciasDAO) throws IOException {
        String fecha = request.getParameter("fecha");
        JSONArray arrayGanancias = new JSONArray();
        JSONObject jsonGanancias = new JSONObject();
        double totalGanancias = 0;

        try {
            ArrayList<Pago> pagoList = gananciasDAO.getGananciasPorDia(fecha);

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

        } catch (SQLException e) {
            jsonGanancias.put("resultado", "error_sql");
            jsonGanancias.put("error_mostrado", e.getMessage());
            System.out.println("Error Code: " + e.getErrorCode());
            throw new RuntimeException(e);
        }

        response.getWriter().write(jsonGanancias.toString());
    }

    private void consultarPorSemana(HttpServletRequest request, HttpServletResponse response, GananciasDao gananciasDAO) throws IOException {
        String semana = request.getParameter("semana");
        JSONArray arrayGanancias = new JSONArray();
        JSONObject jsonGanancias = new JSONObject();
        double totalGanancias = 0;

        try {
            ArrayList<Pago> pagoList = gananciasDAO.getGananciasPorSemana(semana);

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

        } catch (SQLException e) {
            jsonGanancias.put("resultado", "error_sql");
            jsonGanancias.put("error_mostrado", e.getMessage());
            System.out.println("Error Code: " + e.getErrorCode());
            throw new RuntimeException(e);
        }

        response.getWriter().write(jsonGanancias.toString());
    }

    private void consultarPorMes(HttpServletRequest request, HttpServletResponse response, GananciasDao gananciasDAO) throws IOException {
        String mes = request.getParameter("mes");
        JSONArray arrayGanancias = new JSONArray();
        JSONObject jsonGanancias = new JSONObject();
        double totalGanancias = 0;

        try {
            ArrayList<Pago> pagoList = gananciasDAO.getGananciasPorMes(mes);

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

        } catch (SQLException e) {
            jsonGanancias.put("resultado", "error_sql");
            jsonGanancias.put("error_mostrado", e.getMessage());
            System.out.println("Error Code: " + e.getErrorCode());
            throw new RuntimeException(e);
        }

        response.getWriter().write(jsonGanancias.toString());
    }
    
    
    
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @PostMapping("/guardar-semana")
//    public String guardarSemana(@RequestBody Map<String, String> datos) {
//        String semana = datos.get("semana");
//        LocalDate[] rangoFechas = SemanaUtils.obtenerRangoFechas(semana);
//        LocalDate fechaInicio = rangoFechas[0];
//        LocalDate fechaFin = rangoFechas[1];
//
//        String sql = "SELECT c.nombre AS cliente, p.fechapago AS fecha, s.servicio AS servicio, p.total AS total " +
//                     "FROM pago p " +
//                     "JOIN cliente c ON p.idcliente = c.idcliente " +
//                     "JOIN detallepago dp ON p.idpago = dp.idpago " +
//                     "JOIN servicio s ON dp.idservicio = s.idservicio " +
//                     "WHERE p.fechapago BETWEEN ? AND ?";
//
//        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql, fechaInicio, fechaFin);
//
//        // Procesa los resultados según sea necesario
//
//        return "Consulta realizada";
//    }
}
