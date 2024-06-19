/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;


import dao.CargoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import modelo.Cargo;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Machado
 */
@WebServlet(name = "CargoServlet", urlPatterns = {"/CargoServlet"})
public class CargoServlet extends HttpServlet {
  private ArrayList<Cargo> listaCargo;
    private Cargo ca=null;
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
        if(filtro == null){
            return ;
        }
        
        switch (filtro) {
            case "si_registro":
                JSONArray array_autores = new JSONArray();
                JSONObject json_autores = new JSONObject();
                String resultado_insert = "";
                Cargo cargo = new Cargo();
                try {
                    CargoDao oa = new CargoDao();
//                    cargo.setIdcargo(req.getIntHeader("idcargo"));
                    cargo.setCargo(req.getParameter("cargo"));
                    
//                    System.out.println("autor filtro entró" + autor.getCodigoAutor());
                  
                    resultado_insert = oa.insertAutor(cargo);
                    if (resultado_insert == "exito") {

                        json_autores.put("resultado", "exito");
//                        json_autores.put("idcargo", cargo.getIdcargo());
                        json_autores.put("cargo", cargo.getCargo());
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
                     
                     try{
                         CargoDao obaut = new CargoDao();
                         this.listaCargo = new ArrayList();
                         
                         this.listaCargo = obaut.selectAllAutores(Integer.valueOf(el_estado), "todos");
                         
                         
                         System.out.println("Esta en resultado en RegAutor");
                         
                         html += "<table id=\"tabla_autores\""
                         + "class=\"table table-bordered dt-resposive nowrap\""
                         +  "cellspacing=\"0\" width=\"100%\">\n"
                                 +"<thead>\n"
                                 +"<tr>\n"
                                 +"<th>idCargo</th>\n"
                                 +"<th>Nombre cargo</th>\n"                               
                                 +"</tr>\n"
                                 +"</thead>\n"
                                 +"</tbody>";
                         
                         int cont = 0;
                         for(Cargo objCargo : this.listaCargo){
                               cont++;
                               html += "<tr>";
                               html += "<td>" + objCargo.getIdCargo()+ "</td>" ;
                               html += "<td>" + objCargo.getCargo()+ "</td>" ;
                    
                               html +="<td>";
                               html += "<div class='dropdown m-b-10'>";
                               html += "<button class = 'btn btn-secondary dropdown-toggle'" + "type='button' id='dropdownMenuButton'" 
                                       + "data-toggle= 'dropdown' aria-haspopup='true' aria-expanded='false'> Seleccione </button>";
                               html += "<ul class=\"dropdown-menu\">";
                               
//                               html +="<li><a class='dropdown-item btn_eliminar' data-id='" + objCargo.getIdCargo()+ "' href='javascrip:void(0)'>Eliminar</a></li>";
    
                               html += "<li><a class='dropdown-item btn_editar' data-id='" + objCargo.getIdCargo()+ "' href = 'javascrip:void(0)'>Editar</a></li>";
                               
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
                               System.out.println("que tiene"+ html );
                               
                         }catch (SQLException e){
                             
                             
                             json_autor.put("resultado", "error sql");
                             json_autor.put("error", e.getMessage());
                             json_autor.put("code error", e.getErrorCode());
                             throw new RuntimeException(e);
                         
                     }catch  (ClassNotFoundException e){
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
                  
                  this.ca= new Cargo();
                  String result_actualizar = "";
                  try {
                      CargoDao naut = new CargoDao();
                      String x = req.getParameter("idcargo");
                      this.ca.setIdCargo(Integer.parseInt(x));
                      this.ca.setCargo(req.getParameter("cargo"));
        
                      result_actualizar= naut.updateCargo(this.ca, req.getParameter("idcargo"));
                      if (result_actualizar == "exito") {
                          json_actualizar.put("resultado","exito");
                          json_actualizar.put("cargo",this.ca.getCargo());
        
                      }else{
                          json_actualizar.put("resultado", "error_sql");
                          json_actualizar.put("resultado_actualizar", result_actualizar);
                          
                      }
 
                  } catch (SQLException e) {
                      json_actualizar.put("resultado", "error_sql");
                      json_actualizar.put("error_mostrado", e.getMessage());
                      System.out.println("Error mostrado: "+ e);
                      System.out.println("Error code error: "+ e.getErrorCode());
                      throw new RuntimeException();
                      
                  }catch (NullPointerException e){
                      json_actualizar.put("resultado","error_class" );
                      json_actualizar.put("error_mostrado",e );
                    throw new RuntimeException(e);
                  }
                  array_actualizar.put(json_actualizar);
                  resp.getWriter().write(array_actualizar.toString());
                  break;
                  
            
                  case "si_cliente_especifico":
                JSONArray array_especifico = new JSONArray();
                CargoDao cargoDao = null;
                
                try {
                    cargoDao = new CargoDao();
                    String x = req.getParameter("id");
                    ResultSet res_indiv = cargoDao.findById(Integer.parseInt(x));
                 
                    while (res_indiv.next()) {
                        JSONObject json_especifico = new JSONObject();
                        json_especifico.put("resultado", "exito");
                        json_especifico.put("idcargo", res_indiv.getInt("idcargo"));                       
                        json_especifico.put("cargo", res_indiv.getString("cargo"));                      
                        array_especifico.put(json_especifico);
                    }
                } catch (SQLException e) {
                    JSONObject json_error = new JSONObject();
                    json_error.put("resultado", "error_sql");
                    json_error.put("error_mostrado", e.getMessage());
                    resp.getWriter().write(json_error.toString());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    JSONObject json_error = new JSONObject();
                    json_error.put("resultado", "error_class");
                    json_error.put("error_mostrado", e.getMessage());
                    resp.getWriter().write(json_error.toString());
                    e.printStackTrace();
                }
                
                resp.getWriter().write(array_especifico.toString());
                break;
                  
                case "si_eliminalo":
                JSONArray array_aElimina = new JSONArray();
                JSONObject json_aElimina = new JSONObject();
                String resultado = "";
                CargoDao obtA = null;
                try {
                    obtA = new CargoDao();
                    resultado = obtA.deleteAutor(Integer.parseInt(req.getParameter("id")));
                    if (resultado == "exito") {
                        json_aElimina.put("resultado", "exito");
                    } else {
                        json_aElimina.put("resultado", "error_eliminar");
                    }
                } catch (SQLException e) {
                    json_aElimina.put("resultado", "error_sql");
                    json_aElimina.put("exception", e.getMessage());
                }catch (NullPointerException e) {
                    json_aElimina.put("resultado", "error_class");
                    json_aElimina.put("exception", e.getMessage());

                    throw new RuntimeException(e);
                }
                array_aElimina.put(json_aElimina);
                resp.getWriter().write(array_aElimina.toString());
                break;
        }
     
    }

    
    

}
