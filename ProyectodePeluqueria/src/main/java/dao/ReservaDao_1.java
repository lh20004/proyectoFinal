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

/**
 *
 * @author Luis
 */
public class ReservaDao_1 {

    private Conexion conexion;
    private ArrayList<Reserva> reservaList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String SELECT_FECHA_HORAS = "SELECT fechareserva, horainicio, horafin FROM reserva ORDER BY fechareserva ASC;";
    
    public ReservaDao_1() {
        this.conexion = new Conexion();
    }

    public ArrayList<Reserva> obtenerReservas() throws SQLException {
        this.reservaList = new ArrayList<>();

        try {
            this.accesoDB = this.conexion.getConexion();
            if (this.accesoDB == null) {
                return this.reservaList; // Salir temprano si la conexión es null
            }
            this.ps = this.accesoDB.prepareStatement(SELECT_FECHA_HORAS);
            this.rs = ps.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setFechaReserva(rs.getDate("fechareserva"));
                reserva.setHoraInicio(rs.getTime("horainicio"));
                reserva.setHoraFin(rs.getTime("horafin"));

                this.reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception to manage it at a higher level.
        } finally {
            this.conexion.cerrarConexiones();
        }

        return this.reservaList;
    }
    
    
  public boolean insertarReserva(Reserva reserva) throws SQLException {
    String query = "INSERT INTO reserva (idcliente, idempleado, idservicio, fechareserva, horainicio, horafin, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, reserva.getCliente().getIdCliente());
        ps.setInt(2, reserva.getEmpleado().getIdEmpleado());
        ps.setInt(3, reserva.getIdServicio());
        ps.setDate(4, reserva.getFechaReserva());
        ps.setTime(5, reserva.getHoraInicio());
        ps.setTime(6, reserva.getHoraFin());
        ps.setString(7, reserva.getEstado());

        int resultado = ps.executeUpdate();
        return resultado > 0;
    } catch (SQLException e) {
        // Logging de la excepción
        System.err.println("Error SQL al insertar reserva: " + e.getMessage());
        throw e;
    }
}

}
