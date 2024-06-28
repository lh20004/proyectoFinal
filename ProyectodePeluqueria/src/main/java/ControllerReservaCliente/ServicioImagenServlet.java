/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerReservaCliente;

import dao.ServicioImagenDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Servicio;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author guill
 */
@WebServlet(name = "ServicioImagenServlet", urlPatterns = {"/ServicioImagenServlet"})
public class ServicioImagenServlet extends HttpServlet {

    private ServicioImagenDao serviDao;

    @Override
    public void init() {
        serviDao = new ServicioImagenDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<Servicio> listServicio = serviDao.getAllServicios();
        
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        
        for (Servicio serv : listServicio) {
            JSONObject jsonimage = new JSONObject();
            
            jsonimage.put("id", serv.getIdServicio());
            jsonimage.put("servicio", serv.getServicio());
            jsonimage.put("descripcion", serv.getDescripcion());
            jsonimage.put("precio",  "$" + serv.getPrecio());
            jsonimage.put("estado", serv.getEstado());
         
            array.add(jsonimage);
        }
        
        json.put("imagenes", array);
        json.put("result", true);
        
        response.getWriter().write(json.toString());
    }

    @Override
    public String getServletInfo() {
        return "Servlet that retrieves image information from the database";
    }// </editor-fold>

}
