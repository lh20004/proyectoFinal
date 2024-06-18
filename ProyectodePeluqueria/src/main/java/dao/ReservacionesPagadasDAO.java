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

public class ReservacionesPagadasDAO {

    private Conexion conexion;
    private ArrayList<Reserva> ReservacionesPagadas;

    private static final String SQL_CONSULTA = "SELECT cl.nombre AS nombre_cliente,re.estado AS estado_reserva\n" +
"FROM  reserva re\n" +
"INNER JOIN cliente cl ON re.idcliente = cl.idcliente\n" +
"ORDER BY re.estado = 'confirmada' DESC";

    public ReservacionesPagadasDAO() throws SQLException, ClassNotFoundException {
        this.conexion = new Conexion();
    }

    public ArrayList<Reserva> consultarHistorialReservas() throws SQLException {
        this.ReservacionesPagadas = new ArrayList<>();

        Reserva obj = null;
        try {
            Connection connection = conexion.getConexion();
            PreparedStatement ps = connection.prepareStatement(SQL_CONSULTA);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new Reserva();
            Cliente cl = new Cliente();
               cl.setNombre(rs.getString("nombre_cliente"));
               obj.setCliente(cl);
               obj.setEstado(rs.getString("estado_reserva"));

                ReservacionesPagadas.add(obj);
            }
            conexion.cerrarConexiones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReservacionesPagadas;
    }
}
