/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersCrud;
import dao.ClienteDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;

@WebServlet("/RegistrarUsuarioServlet")
public class RegistrarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        // Crear un objeto Cliente con los datos recibidos
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setClave(clave);

        // Crear una instancia de ClienteDao para interactuar con la base de datos
        ClienteDao clienteDao = new ClienteDao();
     
        try {
            // Insertar el nuevo cliente en la base de datos
            clienteDao.insertar(cliente);
            // Envía una respuesta al cliente indicando que el registro fue exitoso
            response.getWriter().println("Registro exitoso.");
        } catch (Exception e) {
            // Si ocurre algún error, envía una respuesta con el mensaje de error
            response.getWriter().println("Error al registrar el usuario: " + e.getMessage());
        }
    }
}
