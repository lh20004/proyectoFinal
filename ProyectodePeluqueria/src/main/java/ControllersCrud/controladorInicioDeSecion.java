/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;


import dao.daoClienteSecion;
import dao.daoEmpleadoSecion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Encriptar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author javier
 */
@WebServlet(name = "controladorInicioDeSecion", urlPatterns = {"/controladorInicioDeSecion"})
public class controladorInicioDeSecion extends HttpServlet {

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
            out.println("<title>Servlet controladorInicioDeSecion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorInicioDeSecion at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
//        processRequest(request, response);
        
        String adminName = "admin";
        String urlAdmin = "../MenuAdmin.html";
        String urlUsuario = "../Menu_Empleado.html";
        String urlCliente = "../Menu_Cliente.html";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        JSONObject json = new JSONObject();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Empleado em = daoEmpleadoSecion.getEmpleadoPorCorreo(email);
        Cliente cl = daoClienteSecion.getClientePorCorreo(email);
        
        
        if(em!=null||cl!=null){
            if(em!=null){
                em.setClave(desencriptar(em.getClave()));
                if(em.getClave().equals(password)){
                    json.put("result", "succes");
                    if(em.getCargo().getCargo().equals(adminName)){
                        json.put("url", urlAdmin);
                    }else{
                        json.put("url", urlUsuario);
                    }
                }else{
                    json.put("result", "claveError");
                }
            }else{
                cl.setClave(desencriptar(cl.getClave()));
                if(cl.getClave().equals(password)){
                    json.put("result", "succes");
                    json.put("url", urlCliente);
                }else{
                    json.put("result", "claveError");
                }
            }
        }else{
            json.put("result", "correoError");
        }
        response.getWriter().write(json.toString());
    }
    
    private String encriptar(String cadena){
        cadena = Encriptar.encriptar(cadena);
        return cadena;
    }
    
    private String desencriptar(String cadena){
        cadena = Encriptar.desencriptar(cadena);
        return cadena;
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
