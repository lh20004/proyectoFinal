/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author MINEDUCYT
 */
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import modelo.Servicio;
public class ServiciosDAO {

    Conexion conexion = null;
    private ArrayList<Servicio> serviciosList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
     private  ArrayList<Servicio> resultados;

    private static final String INSERT_SERVICIO = "INSERT INTO servicio(servicio,"
            + " descripcion, precio, estado) VALUES (?,?,?,?)";

    private static final String UPDATE_SERVICIO = "UPDATE servicio SET  "
            + " descripcion =?, precio =?, estado =? WHERE idservicio =?";

    private static final String DELETE_SERVICIO = "DELETE FROM servicio WHERE idservicio = ?";

    private static final String SELECT_SERVICIO_BY_ID = "SELECT * FROM servicio WHERE idservicio =?";

    private static final String SELECT_ALL_SERVICIOS = "SELECT * FROM servicio";
    
    private static final String SERVICIOS_REALIZADOS = 
         "SELECT s.servicio AS nombre_servicio, " +
             "COUNT(*) AS cantidad_realizaciones " +
             "FROM servicio s " +
             "JOIN detallereserva dr ON s.idservicio = dr.idservicio " +
             "JOIN reserva r ON dr.idreserva = r.idreserva " +
             "JOIN pago p ON r.idcliente = p.idcliente " +
             "LEFT JOIN detallepago dp ON p.idpago = dp.idpago AND dr.idservicio = dp.idservicio " +
             "WHERE r.fechareserva = CURRENT_DATE " + 
             "GROUP BY s.servicio;";
    
   

    public ServiciosDAO() {
        this.conexion = new Conexion();
    }

    public String insertServicio(Servicio servicio) throws SQLException, ClassNotFoundException {

        String resultado;
        int resultado_insertar;
        try {
            this.conexion = new Conexion();
            this.conexion.getConexion();
            this.accesoDB = conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(INSERT_SERVICIO);
           
            this.ps.setString(1, servicio.getServicio());
            this.ps.setString(2, servicio.getDescripcion());
            this.ps.setDouble(3, servicio.getPrecio());
            this.ps.setString(4, servicio.getEstado());
            System.out.println("servicio_insertar" + servicio);
            resultado_insertar = this.ps.executeUpdate();
            this.conexion.cerrarConexiones();
            if (resultado_insertar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_insertar_servicio";
            }
        } catch (SQLException e) {
            resultado = "error_excepcion";
            System.out.println("fallo insertar" + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ArrayList<Servicio> selectAllServicios(Integer estado, String quien) throws SQLException, ClassNotFoundException {

        this.serviciosList = new ArrayList();

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SELECT_ALL_SERVICIOS);
            this.rs = ps.executeQuery();

            Servicio obj = null;
            while (this.rs.next()) {
                obj = new Servicio();
               obj.setIdServicio(rs.getInt("idservicio"));
                obj.setServicio(rs.getString("servicio"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setPrecio(rs.getDouble("precio"));
                obj.setEstado(rs.getString("estado"));
                this.serviciosList.add(obj);
            }
            this.conexion.cerrarConexiones();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.serviciosList;
    }

    public String updateServicio(Servicio servicio) throws SQLException {
        System.out.println(servicio.getIdServicio());
        String resultado;
        int res_actualizar;
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(UPDATE_SERVICIO);
           
            this.ps.setString(1, servicio.getDescripcion());
            this.ps.setDouble(2, servicio.getPrecio());
            this.ps.setString(3, servicio.getEstado());
            this.ps.setInt(4, servicio.getIdServicio());
            res_actualizar = this.ps.executeUpdate();
            System.out.println(res_actualizar);

            if (res_actualizar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_actualizar";
            }
        } catch (SQLException e) {
            resultado = "error_exception";
            e.printStackTrace();
        }

        return resultado;
    }

    public Servicio findById(int id) throws Exception {

        try {
            Servicio servicio = null;
            this.accesoDB = this.conexion.getConexion();
            System.out.println("sql" + id + SELECT_SERVICIO_BY_ID);
            this.ps = this.accesoDB.prepareStatement(SELECT_SERVICIO_BY_ID);
            this.ps.setInt(1, id);
            this.rs = ps.executeQuery();
            if (this.rs.next()) {
                servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("idservicio"));
                servicio.setServicio(rs.getString("servicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getDouble("precio"));
                servicio.setEstado(rs.getString("estado"));
            }

            this.conexion.cerrarConexiones();
            return servicio;

        } catch (SQLException e) {
            throw new Exception("error en servicio, metodo FindById " + e.getMessage());
        }
    }

    public String deleteServicio(String id) throws SQLException {
        String resultado;
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(DELETE_SERVICIO);
            this.ps.setString(1, id);
            System.out.println("servicio_eliminar " + id);
            System.out.println(DELETE_SERVICIO);
            System.out.println(ps.toString());
            this.ps.executeUpdate();

            this.conexion.cerrarConexiones();
            resultado = "exito";
        } catch (SQLException e) {
            resultado = "error";
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return resultado;

    }
    
    public ArrayList<Servicio> obtenerTotalServiciosHoy(Integer estado, String quien) throws SQLException {
     this.resultados=new ArrayList();
     

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SERVICIOS_REALIZADOS);
            this.rs = ps.executeQuery();

            
            while (rs.next()) {
                String servicio = rs.getString("servicio");
                int total = rs.getInt("total_servicios");
                resultados.add(new Servicio(servicio, total));
                }
             
            this.conexion.cerrarConexiones();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.resultados;
        
       
    }
    
    
    
        
    
   
    }
    
    
    


