/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;

import dao.ServiciosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ServiciosDisponiblesServlet", urlPatterns = {"/ServiciosDisponiblesServlet"})
public class ServiciosDisponiblesServlet extends HttpServlet {

    ServiciosDAO servicios = new ServiciosDAO();
    ArrayList<Servicio> listaSer = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            listaSer = servicios.selecttodosServicios();
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosDisponiblesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiciosDisponiblesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (accion) {
            case "ejemplo":

                break;
            default:
                request.setAttribute("servicios", listaSer);
                request.getRequestDispatcher("ServiciosDisponibles.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
