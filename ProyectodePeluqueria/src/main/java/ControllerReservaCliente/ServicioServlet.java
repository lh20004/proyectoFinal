/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllerReservaCliente;

import dao.ServicioImagenDao;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Servicio;

/**
 *
 * @author guill
 */
@WebServlet("/ServicioServlet")
@MultipartConfig(maxFileSize = 16177215)
public class ServicioServlet extends HttpServlet {

    private ServicioImagenDao servDao;

    @Override
    public void init() throws ServletException {
        servDao = new ServicioImagenDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String servicio = request.getParameter("servicio");
        String descripcion = request.getParameter("descripcion");
        Double precio = Double.valueOf(request.getParameter("precio"));
        String estado = request.getParameter("estado");
        Part filePart = request.getPart("file");

        if (filePart != null) {
            System.out.println("Nombre del archivo: " + filePart.getSubmittedFileName());
            System.out.println("Tamaño del archivo: " + filePart.getSize());
            System.out.println("Tipo de archivo: " + filePart.getContentType());
        } else {
            System.out.println("El archivo no fue recibido.");
        }

        InputStream inputStream = filePart.getInputStream();

        Servicio ser = new Servicio(servicio, descripcion, precio, estado, inputStream);
        String message = servDao.insertarImagen(ser);

        out.println(message);
        out.close();
    }

}
