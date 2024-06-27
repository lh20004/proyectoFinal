package ControllersCrud;

import com.sun.net.httpserver.HttpServer;
import dao.ServiciosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Servicio;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "ServiciosServlet", urlPatterns = {"/ServiciosServlet"})
public class ServiciosServlet extends HttpServlet {

    private ArrayList<Servicio> listaServicio;
    private Servicio au = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

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
            case "si_registro":
                JSONArray array_autores = new JSONArray();
                JSONObject json_autores = new JSONObject();
                String resultado_insert = "";
                Servicio servicio = new Servicio();
                try {
                    ServiciosDAO oa = new ServiciosDAO();
                    servicio.setServicio(req.getParameter("servicio"));
                    servicio.setDescripcion(req.getParameter("descripcion"));
                    servicio.setPrecio(Double.parseDouble(req.getParameter("precio")));
                    String estado= req.getParameter("estado");
                    servicio.setEstado(estado);
                    System.out.println("autor filtro entró" + servicio.getIdServicio());

                    resultado_insert = oa.insertServicio(servicio);
                    if (resultado_insert == "exito") {

                        json_autores.put("resultado", "exito");
                        json_autores.put("idservicio", servicio.getIdServicio());
                        json_autores.put("servicio", servicio.getServicio());
                        json_autores.put("descripcion", servicio.getDescripcion());
                        json_autores.put("precio", servicio.getPrecio());
                        json_autores.put("estado", servicio.getEstado());

                    } else {
                        json_autores.put("resultado", "error");
                        json_autores.put("resultado_insertar", resultado_insert);
                    }

                } catch (SQLException e) {
                    json_autores.put("resultado", "error_sql");
                    json_autores.put("error_mostrado", e.getMessage());
                    System.out.println("Error Code error:" + e.getErrorCode());
                    throw new RuntimeException();
                } catch (ClassNotFoundException e) {
                    json_autores.put("resultado", "error_class");
                    json_autores.put("error_mostrado", e.getMessage());
                    throw new RuntimeException();
                }
                array_autores.put(json_autores);
                resp.getWriter().write(array_autores.toString());
                break;

            case "si_consulta":
                JSONArray array_autor = new JSONArray();
                JSONObject json_autor = new JSONObject();
                String html = "";
                String el_estado = req.getParameter("estado");

                try {
                    ServiciosDAO obaut = new ServiciosDAO();
                    this.listaServicio = new ArrayList();

                    this.listaServicio = obaut.selectAllServicios(Integer.valueOf(el_estado), "todos");

                    System.out.println("Esta en resultado en RegAutor");

                    html += "<table id=\"tabla_servicios\""
                            + "class=\"table table-bordered dt-resposive nowrap\""
                            + "cellspacing=\"0\" width=\"100%\">\n"
                            + "<thead>\n"
                            + "<tr>\n"
                             + "<th>ID Servicio</th>\n"
                            + "<th>Servicio</th>\n"
                            + "<th>Descripcion</th>\n"
                            + "<th>Precio</th>\n"
                            + "<th>Estado</th>\n"
                            + "<th>Acciones</th>\n"
                            + "</tr>\n"
                            + "</thead>\n"
                            + "</tbody>";

                    int cont = 0;
                    for (Servicio objAutor : this.listaServicio) {
                        cont++;
                        html += "<tr>";
                         html += "<td>" + objAutor.getIdServicio() + "</td>";
                        html += "<td>" + objAutor.getServicio() + "</td>";
                        html += "<td>" + objAutor.getDescripcion() + "</td>";
                        html += "<td>" + objAutor.getPrecio() + "</td>";
                        html += "<td>" + objAutor.getEstado() + "</td>";

                        html += "<td>";
                        html += "<div class='dropdown m-b-10'>";
                        html += "<button class = 'btn btn-secondary dropdown-toggle'" + "type='button' id='dropdownMenuButton'"
                                + "data-toggle= 'dropdown' aria-haspopup='true' aria-expanded='false'> Seleccione </button>";
                        html += "<ul class=\"dropdown-menu\">";

                        html += "<li><a class='dropdown-item btn_eliminar' data-id='" + objAutor.getIdServicio() + "' href='javascrip:void(0)'>Eliminar</a></li>";

                        html += "<li><a class='dropdown-item btn_editar' data-id='" + objAutor.getIdServicio() + "' href = 'javascrip:void(0)'>Editar</a></li>";

                        html += "</ul>";
                        html += "</div>";
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

                } catch (ClassNotFoundException e) {
                    json_autor.put("resultado", "class not found");
                    json_autor.put("error", e.getMessage());
                    throw new RuntimeException(e);

                }
                array_autor.put(json_autor);
                resp.getWriter().write(array_autor.toString());
                break;
            case "si_actualizalo":
                JSONArray array_actualizar = new JSONArray();
                JSONObject json_actualizar = new JSONObject();

                this.au = new Servicio();
                String result_actualizar = "";
                try {
                    ServiciosDAO naut = new ServiciosDAO();
                    this.au.setIdServicio(Integer.parseInt(req.getParameter("idservicio")));
                    this.au.setDescripcion(req.getParameter("descripcion"));
                    this.au.setPrecio(Double.parseDouble(req.getParameter("precio")));
                    this.au.setEstado(req.getParameter("estado"));

                    result_actualizar = naut.updateServicio(this.au);
                    if (result_actualizar == "exito") {
                        json_actualizar.put("resultado", "exito");
                        json_actualizar.put("codigoservicio", this.au.getIdServicio());
                        //json_actualizar.put("servicio", this.au.getServicio());
                        json_actualizar.put("descripcion", this.au.getDescripcion());
                        json_actualizar.put("precio", this.au.getPrecio());
                        json_actualizar.put("estado", this.au.getEstado());

                    } else {
                        json_actualizar.put("resultado", "error_sql");
                        json_actualizar.put("resultado_actualizar", result_actualizar);

                    }

                } catch (SQLException e) {
                    json_actualizar.put("resultado", "error_sql");
                    json_actualizar.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error code error: " + e.getErrorCode());
                    throw new RuntimeException();

                } catch (NullPointerException e) {
                    json_actualizar.put("resultado", "error_class");
                    json_actualizar.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                array_actualizar.put(json_actualizar);
                resp.getWriter().write(array_actualizar.toString());
                break;

            case "si_servicio_especifico":
                JSONArray array_especifico = new JSONArray();
                JSONObject json_especifico = new JSONObject();
                ServiciosDAO outAutor = null;
                try {
                    outAutor = new ServiciosDAO();

                    Servicio res_indiv = outAutor.findById(Integer.parseInt(req.getParameter("id")));

                    json_especifico.put("resultado", "exito");
                    json_especifico.put("idservicio", res_indiv.getIdServicio());
                    json_especifico.put("servicio", res_indiv.getServicio());
                    json_especifico.put("descripcion", res_indiv.getDescripcion());
                    json_especifico.put("precio", res_indiv.getPrecio());
                    json_especifico.put("estado", res_indiv.getEstado());

                } catch (Exception e) {
                    json_especifico.put("resultado", "error_sql");
                    json_especifico.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado" + e);
                    System.out.println("Error excepcion: " + e);
                    throw new RuntimeException(e);

                }
                array_especifico.put(json_especifico);
                resp.getWriter().write(array_especifico.toString());
                break;

        }

    }

}
