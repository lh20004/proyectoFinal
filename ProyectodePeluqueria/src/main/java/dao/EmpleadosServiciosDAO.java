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
import modelo.Reserva;
import modelo.Servicio;

public class EmpleadosServiciosDAO {

    private Conexion conexion;
    private ArrayList<DetalleReserva> listaDetallesReservas;

    private static final String SQL_CONSULTA = "SELECT   em.nombre||' '|| em.apellido AS Nombre, COUNT(se.idservicio) AS total_servicios, \n" +
"SUM(se.precio) AS total_precio\n" +
"FROM detallereserva dr\n" +
"INNER JOIN reserva re ON dr.idreserva = re.idreserva\n" +
"INNER JOIN servicio se ON se.idservicio = dr.idservicio\n" +
"INNER JOIN empleado em ON em.idempleado = re.idempleado\n" +
"GROUP BY  em.nombre, em.apellido\n" +
"ORDER BY total_servicios DESC";

    public EmpleadosServiciosDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new Conexion();
    }

    public ArrayList<DetalleReserva> consultarHistorialReservas() throws SQLException {
        this.listaDetallesReservas = new ArrayList<>();

        DetalleReserva obj = null;
        try {
            Connection connection = conexion.getConexion();
            PreparedStatement ps = connection.prepareStatement(SQL_CONSULTA);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new DetalleReserva();
                Reserva re = new Reserva();
                Servicio se = new Servicio();
                Empleado em = new Empleado();

                em.setNombre(rs.getString("Nombre"));
                re.setEmpleado(em);
                obj.setReserva(re);
                se.setServicio(rs.getString("total_servicios"));
                se.setPrecio(rs.getDouble("total_precio"));
                obj.setServicio(se);

                listaDetallesReservas.add(obj);
            }
            conexion.cerrarConexiones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDetallesReservas;
    }
}
