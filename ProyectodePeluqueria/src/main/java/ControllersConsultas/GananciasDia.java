/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllersConsultas;

import dao.ServiciosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Pago;
import modelo.Servicio;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "GananciasDiaServlet", urlPatterns = {"/GananciasDiaServlet"})
public class GananciasDia extends HttpServlet{
    ArrayList<Pago> listaServicio;
    Servicio au=null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.setContentType("aplication/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String filtro = req.getParameter("consultar_datos");

        System.out.println(filtro);
        if (filtro == null) {
            return;
        }

        switch (filtro) {
            

            case "si_consulta":
                JSONArray array_autor = new JSONArray();
                JSONObject json_autor = new JSONObject();
                String html = "";
                String el_estado = req.getParameter("estado");

                try {
                    ServiciosDAO obaut = new ServiciosDAO();
                    this.listaServicio = new ArrayList();

                    this.listaServicio = obaut.obtenerGananciasDia(Integer.valueOf(el_estado), "todo");

                    System.out.println("Esta en resultado en RegAutor");

                    html += "<table id=\"tabla_ganancias\""
                            + "class=\"table table-bordered dt-resposive nowrap\""
                            + "cellspacing=\"0\" width=\"100%\">\n"
                            + "<thead>\n"
                            + "<tr>\n"
                            
                            + "<th>Fecha </th>\n"
                             + "<th>Servicio</th>\n"
                            + "<th>Ganancias </th>\n"
                           
                            + "</tr>\n"
                            + "</thead>\n"
                            + "</tbody>";

                    int cont = 0;
                    for (Pago objAutor : this.listaServicio) {
                        cont++;
                        html += "<tr>";
                        
                        html += "<td>" + objAutor.getFechaPago() + "</td>";
                         html += "<td>" + objAutor.getServicio().getServicio() + "</td>";
                          html += "<td>" + objAutor.getTotal() + "</td>";
                        
                       

                        html += "<td>";
                        
                        html += "</td>";
                        html += "</tr>";
                    }
                    html += "</tbody\n>"
                            + "\t\t </table>";
                    json_autor.put("resultado", "exito");
                    json_autor.put("tabla", html);
                    json_autor.put("cuanto", cont);
                    System.out.println("que tiene" + html);

                } catch (SQLException e) {

                    json_autor.put("resultado", "error sql");
                    json_autor.put("error", e.getMessage());
                    json_autor.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);

                }
                array_autor.put(json_autor);
                resp.getWriter().write(array_autor.toString());
                break;

            

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    
    
}
