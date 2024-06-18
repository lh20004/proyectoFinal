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
import modelo.DetalleReserva;
import modelo.Empleado;

import modelo.Servicio;

public class ServicioMasSolicitadoDAO {

    private Conexion conexion;
    private ArrayList<DetalleReserva> ServicioMasSolicitados;

    private static final String SQL_CONSULTA = "SELECT se.servicio AS nombre_servicio,se.descripcion AS descripcion_servicio,se.precio, COUNT(dr.idservicio) AS cantidad_solicitudes\n" +
"FROM detallereserva dr\n" +
"INNER JOIN servicio se ON dr.idservicio = se.idservicio\n" +
"GROUP BY se.servicio,se.descripcion,se.precio\n" +
"ORDER BY cantidad_solicitudes DESC";

    public ServicioMasSolicitadoDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new Conexion();
    }

    public ArrayList<DetalleReserva> consultarHistorialReservas() throws SQLException {
        this.ServicioMasSolicitados = new ArrayList<>();

        DetalleReserva obj = null;
        try {
            Connection connection = conexion.getConexion();
            PreparedStatement ps = connection.prepareStatement(SQL_CONSULTA);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            obj = new DetalleReserva();

            Servicio se = new Servicio();
            
            se.setServicio(rs.getString("nombre_servicio"));
            se.setDescripcion(rs.getString("descripcion_servicio"));
            se.setPrecio(rs.getDouble("precio"));
            obj.setServicio(se);
            obj.setIdDetalle(rs.getInt("cantidad_solicitudes"));

                ServicioMasSolicitados.add(obj);
            }
            conexion.cerrarConexiones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServicioMasSolicitados;
    }
}
