/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Reserva;
import modelo.Servicio;


public class HistorialReservaDAO {

    private Conexion conexion;
    private ArrayList<Reserva> listaHistorialReservas;

    private static final String SQL_CONSULTA = "SELECT res.iddetalle,re.fechareserva, re.horainicio,re.horafin, cl.nombre||' '||cl.apellido AS Nombre, cl.correo,se.servicio,se.precio\n" +
"FROM detallereserva res\n" +
"INNER JOIN reserva re ON re.idreserva = res.idreserva\n" +
"INNER JOIN cliente cl ON cl.idcliente = re.idcliente\n" +
"INNER JOIN servicio se ON se.idservicio = res.idservicio";

    public HistorialReservaDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new Conexion();
    }

    public ArrayList<Reserva> consultarHistorialReservas() throws SQLException {
        this.listaHistorialReservas = new ArrayList<>();
        
        Reserva obj = null;
        try {
            Connection connection = conexion.getConexion();
            PreparedStatement ps = connection.prepareStatement(SQL_CONSULTA);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {             
               obj = new Reserva();
            Cliente cl = new Cliente();
            Servicio se = new Servicio();
                obj.setIdReserva(rs.getInt("iddetalle"));
                obj.setFechaReserva(rs.getDate("fechareserva"));
                obj.setHoraInicio(rs.getTime("horainicio"));
                obj.setHoraFin(rs.getTime("horafin"));
                
                cl.setNombre(rs.getString("nombre"));
                cl.setCorreo(rs.getString("correo"));
                obj.setCliente(cl);
                
                se.setServicio(rs.getString("servicio"));
                se.setPrecio(rs.getDouble("precio"));
                obj.setServicio(se);
                
                listaHistorialReservas.add(obj);
            }
            conexion.cerrarConexiones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaHistorialReservas;
    }
}