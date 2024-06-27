/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerReservaCliente;

import dao.ServicioImagenDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Servicio;

/**
 *
 * @author guill
 */
@WebServlet(name = "ServicioGetImganServlet", urlPatterns = {"/ServicioGetImganServlet"})
public class ServicioGetImganServlet extends HttpServlet {

    private ServicioImagenDao dao;
    
    @Override
    public void init() throws ServletException {
        dao = new ServicioImagenDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        getImage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getImage(request, response);
    }

    private void getImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        Servicio serv = dao.getServiciosById(id);

        if (serv != null && serv.getImagen() != null) {
            InputStream imgData = serv.getImagen();
            response.setContentType("image/jpeg");

            OutputStream os = response.getOutputStream();
            byte [] buffer = new byte[4096];
            int bytesRead = -1;
            
            while ((bytesRead = imgData.read(buffer)) !=-1) {                
                os.write(buffer, 0, bytesRead);
            }
            
            imgData.close();
            os.flush();
            os.close();
        } else {
            response.getWriter().write("No se encontro la imagen por ID" + id);
        }
    }

}
