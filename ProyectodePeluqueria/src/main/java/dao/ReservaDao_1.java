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
import java.sql.Time;
import java.util.ArrayList;
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
        String query = "INSERT INTO reserva (idcliente, idempleado, fechareserva, horainicio, horafin, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, reserva.getCliente().getIdCliente());
            ps.setInt(2, reserva.getEmpleado().getIdEmpleado());
            //ps.setInt(3, reserva.getIdServicio());
            ps.setDate(3, reserva.getFechaReserva());
            ps.setTime(4, reserva.getHoraInicio());
            ps.setTime(5, reserva.getHoraFin());
            ps.setString(6, reserva.getEstado());

            int resultado = ps.executeUpdate();
            this.conexion.cerrarConexiones();
            return true;
        } catch (SQLException e) {
            // Logging de la excepción
            System.err.println("Error SQL al insertar reserva: " + e.getMessage());
            return false;
        }
    }
    public boolean existeReservaClienteEnFecha(int clienteId, Date fechaReserva) throws SQLException {
    String sql = "SELECT COUNT(*) FROM reserva WHERE idcliente = ? AND fechareserva = ?";
    try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, clienteId);
        ps.setDate(2, fechaReserva);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    }
    return false;
}
    public Reserva getUltimaReserva(){
        try{
            Reserva reserva = null;
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("select * from reserva as r where r.idreserva = (select max(rr.idreserva) from reserva as rr);");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idreserva"));
                reserva.setFechaReserva(rs.getDate("fechareserva"));
                reserva.setHoraInicio(rs.getTime("horainicio"));
                reserva.setHoraFin(rs.getTime("horafin"));
                reserva.setEstado(rs.getString("estado"));
            }
            con.cerrarConexiones();
            return reserva;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean insertarDetalleReserva(int idReserva, int idServicio){
        try{
            PreparedStatement ps = this.conexion.getConexion().prepareStatement("insert into detallereserva (idreserva, idservicio) values (?,?);");
            ps.setInt(1, idReserva);
            ps.setInt(2, idServicio);
            ps.executeUpdate();
            this.conexion.cerrarConexiones();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean existeReservaDuplicada(int empleadoId, Date fecha, Time horaInicio, Time horaFin) throws SQLException {
    String sql = "SELECT COUNT(*) FROM reserva WHERE idempleado = ? AND fechareserva = ? AND ((? BETWEEN horainicio AND horafin) OR (? BETWEEN horainicio AND horafin) OR (horainicio BETWEEN ? AND ?) OR (horafin BETWEEN ? AND ?))";
    try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, empleadoId);
        ps.setDate(2, fecha);
        ps.setTime(3, horaInicio);
        ps.setTime(4, horaFin);
        ps.setTime(5, horaInicio);
        ps.setTime(6, horaFin);
        ps.setTime(7, horaInicio);
        ps.setTime(8, horaFin);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    }
    return false;
}


}
