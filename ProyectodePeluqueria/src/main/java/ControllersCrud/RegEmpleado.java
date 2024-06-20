/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;

import dao.CargosRegistradosDao;
import dao.EmpleadoRegistradosDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cargo;
import modelo.Empleado;
import modelo.Encriptar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author javier
 */
@WebServlet(name = "RegEmpleado", urlPatterns = {"/RegEmpleado"})
public class RegEmpleado extends HttpServlet {

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
            out.println("<title>Servlet RegEmpleado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegEmpleado at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));
        
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONArray array = new JSONArray();
        
        List<Empleado> listaEmpleado;
        List<Cargo> listaCargo;
        
        Empleado empleado = null;
        Cargo cargo = null;
        
        switch(opcion){
            case 1:
                //cargar los empleados
                listaEmpleado = EmpleadoRegistradosDao.getTodoEmpleado();
                for(Empleado e : listaEmpleado){
                    json = new JSONObject();
                    json.put("idempleado", e.getIdEmpleado());
                    json.put("nombre", e.getNombre());
                    json.put("apellido", e.getApellido());
                    json.put("telefono", e.getTelefono());
                    json.put("dui", e.getDui());
                    json.put("estado", e.getEstado());
                    json.put("correo", e.getCorreo());
                    json.put("clave", e.getClave());
                    json2 = new JSONObject();
                    json2.put("idcargo", e.getCargo().getIdCargo());
                    json2.put("cargo", e.getCargo().getCargo());
                    json.put("cargo", json2);
                    array.put(json);
                }
                response.getWriter().write(array.toString());
                break;
            case 2:
                //cargar los cargos
                listaCargo = CargosRegistradosDao.getTodoCargo();
                for(Cargo c: listaCargo){
                    json = new JSONObject();
                    json.put("idcargo", c.getIdCargo());
                    json.put("cargo", c.getCargo());
                    array.put(json);
                }
                response.getWriter().write(array.toString());
                break;
            case 3:
                //crear un nuevo empleado
                empleado = new Empleado();
                empleado.setNombre(request.getParameter("nombre"));
                empleado.setApellido(request.getParameter("apellido"));
                empleado.setTelefono(request.getParameter("telefono"));
                empleado.setDui(request.getParameter("dui"));
                empleado.setEstado(request.getParameter("estado"));
                empleado.setCorreo(request.getParameter("correo"));
                empleado.setClave(Encriptar.encriptar(request.getParameter("clave")));
                cargo = new Cargo();
                cargo.setIdCargo(Integer.parseInt(request.getParameter("cargo")));
                empleado.setCargo(cargo);
                if(EmpleadoRegistradosDao.GuardarEmpleado(empleado)){
                    json.put("result", true);
                }else{
                    json.put("result", false);
                }
                response.getWriter().write(json.toString());
                break;
            case 4:
                //actualizar un empleado
                empleado = new Empleado();
                empleado.setIdEmpleado(Integer.parseInt(request.getParameter("idempleado")));
                empleado.setNombre(request.getParameter("nombre"));
                empleado.setApellido(request.getParameter("apellido"));
                empleado.setTelefono(request.getParameter("telefono"));
                empleado.setDui(request.getParameter("dui"));
                empleado.setEstado(request.getParameter("estado"));
                empleado.setCorreo(request.getParameter("correo"));
                empleado.setClave(Encriptar.encriptar(request.getParameter("clave")));
                cargo = new Cargo();
                cargo.setIdCargo(Integer.parseInt(request.getParameter("cargo")));
                empleado.setCargo(cargo);
                if(EmpleadoRegistradosDao.actualizarEmpleado(empleado)){
                    json.put("result", true);
                }else{
                    json.put("result", false);
                }
                response.getWriter().write(json.toString());
                break;
            case 5:
                //desinciptar contraseña
                String password = request.getParameter("password");
                json.put("password", Encriptar.desencriptar(password));
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
