/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pago;
import modelo.Servicio;

/**
 *
 * @author MINEDUCYT
 */
public class RealizadosGanaciasDiaDAO {
    
    Conexion conexion = null;
    private ArrayList<Pago> serviciosList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
     private  ArrayList<Pago> resultados;
    
    
   
   private static final String GANANCIAS_DEL_DIA = "SELECT "
                + "p.fechapago AS fecha, "
                + "s.servicio AS servicio, "
                + "SUM(s.precio) AS ganancia_generada "
                + "FROM "
                + "servicio s "
                + "JOIN detallepago dp ON s.idservicio = dp.idservicio "
                + "JOIN pago p ON dp.idpago = p.idpago "
                + "WHERE "
                + "p.fechapago = '2024-06-01' "
                + "GROUP BY "
                + "p.fechapago, s.servicio;";
   
   private static final String TOTAL_DIA="SELECT "
                    + "SUM(s.precio) AS total_ganancias "
                    + "FROM "
                    + "servicio s "
                    + "JOIN detallepago dp ON s.idservicio = dp.idservicio "
                    + "JOIN pago p ON dp.idpago = p.idpago "
                    + "WHERE "
                    + "p.fechapago = '2024-06-01';";
   
   
    public ArrayList<Pago> obtenerGananciasDia(Integer estado, String quien) throws SQLException {
     this.resultados=new ArrayList();
     

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(GANANCIAS_DEL_DIA);
            this.rs = ps.executeQuery();

            Servicio serv=null;
            while (rs.next()) {
                serv= new Servicio();
                Date fecha=rs.getDate("fecha");
                serv.setServicio(rs.getString("servicio"));
               
                       
                
                double total = rs.getDouble("ganancia_generada");
                resultados.add(new Pago( fecha,total,serv));
                }
             
            this.conexion.cerrarConexiones();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.resultados;
        
       
    }
    
    public ArrayList<Pago> obtenerTotalDia(Integer estado, String quien) throws SQLException {
     this.resultados=new ArrayList();
     

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(TOTAL_DIA);
            this.rs = ps.executeQuery();

            
            while (rs.next()) {
               
                
               
                       
                
                double total = rs.getDouble("total_ganancias");
                resultados.add(new Pago(total));
                }
             
            this.conexion.cerrarConexiones();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.resultados;
        
       
    }

   
    
    
}
