package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Pago;
import modelo.Servicio;

import java.util.ArrayList;
import modelo.DetalleReserva;
import modelo.Reserva;

public class PagoDao {
    private Conexion conexion;
    private ArrayList<Pago> pagoList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String SELECT_TODOS_LOS_PAGOS = "SELECT idpago, idcliente, fechapago, total FROM pago ORDER BY fechapago ASC;";
    private static final String SELECT_PAGO_POR_ID = "SELECT idpago, idcliente, fechapago, total FROM pago WHERE idpago = ?;";

    private static final String SELECT_DETALLES_POR_CLIENTE =
            "SELECT dr.iddetalle, r.idreserva, r.fechareserva, s.idservicio, s.servicio, s.precio " +
            "FROM detallereserva dr " +
            "JOIN reserva r ON dr.idreserva = r.idreserva " +
            "JOIN servicio s ON dr.idservicio = s.idservicio " +
            "WHERE r.idcliente = ?;";
    
    
    public PagoDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Pago> obtenerPagos() throws SQLException {
        this.pagoList = new ArrayList<>();

        try {
            this.accesoDB = this.conexion.getConexion();
            if (this.accesoDB == null) {
                return this.pagoList; // Salir temprano si la conexión es null
            }
            this.ps = this.accesoDB.prepareStatement(SELECT_TODOS_LOS_PAGOS);
            this.rs = ps.executeQuery();

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("idpago"));
                pago.setCliente(getClienteById(rs.getInt("idcliente")));
                pago.setFechaPago(rs.getDate("fechapago"));
                pago.setTotal(rs.getDouble("total"));
                pago.setServicio(getServicioByPagoId(rs.getInt("idpago")));

                this.pagoList.add(pago);
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception to manage it at a higher level.
        } finally {
            this.conexion.cerrarConexiones();
        }

        return this.pagoList;
    }

    public Pago obtenerPagoPorId(int idPago) throws SQLException {
        Pago pago = null;

        try {
            this.accesoDB = this.conexion.getConexion();
            if (this.accesoDB == null) {
                return null; // Salir temprano si la conexión es null
            }
            this.ps = this.accesoDB.prepareStatement(SELECT_PAGO_POR_ID);
            this.ps.setInt(1, idPago);
            this.rs = ps.executeQuery();

            if (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("idpago"));
                pago.setCliente(getClienteById(rs.getInt("idcliente")));
                pago.setFechaPago(rs.getDate("fechapago"));
                pago.setTotal(rs.getDouble("total"));
                pago.setServicio(getServicioByPagoId(rs.getInt("idpago")));
            }
        } catch (SQLException e) {
            throw e; // Re-throw the exception to manage it at a higher level.
        } finally {
            this.conexion.cerrarConexiones();
        }

        return pago;
    }

    private Cliente getClienteById(int idCliente) throws SQLException {
        // Implementar la lógica para obtener el cliente por ID
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
        try (PreparedStatement ps = accesoDB.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idcliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    // Otros campos del cliente
                    return cliente;
                }
            }
        }
        return null;
    }

    public ArrayList<DetalleReserva> obtenerDetallesReservaPorCliente(int idCliente) throws SQLException {
        ArrayList<DetalleReserva> detalles = new ArrayList<>();

        try (Connection accesoDB = conexion.getConexion();
             PreparedStatement ps = accesoDB.prepareStatement(SELECT_DETALLES_POR_CLIENTE)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleReserva detalle = new DetalleReserva();
                    detalle.setIdDetalle(rs.getInt("iddetalle"));

                    Reserva reserva = new Reserva();
                    reserva.setIdReserva(rs.getInt("idreserva"));
                    reserva.setFechaReserva(rs.getDate("fechareserva"));

                    Servicio servicio = new Servicio();
                    servicio.setIdServicio(rs.getInt("idservicio"));
                    servicio.setServicio(rs.getString("servicio"));
                    servicio.setPrecio(rs.getDouble("precio"));

                    detalle.setReserva(reserva);
                    detalle.setServicio(servicio);

                    detalles.add(detalle);
                }
            }
        }

        return detalles;
    }

    private Servicio getServicioByPagoId(int idPago) throws SQLException {
        // Implementar la lógica para obtener el servicio relacionado con el pago
        String sql = "SELECT s.idservicio, s.servicio, s.descripcion, s.precio, s.estado, s.imagen " +
                     "FROM servicio s " +
                     "JOIN detallereserva dr ON s.idservicio = dr.idservicio " +
                     "JOIN reserva r ON dr.idreserva = r.idreserva " +
                     "JOIN pago p ON r.idreserva = p.idpago " +
                     "WHERE p.idpago = ?";
        try (PreparedStatement ps = accesoDB.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Servicio servicio = new Servicio();
                    servicio.setIdServicio(rs.getInt("idservicio"));
                    servicio.setServicio(rs.getString("servicio"));
                    servicio.setDescripcion(rs.getString("descripcion"));
                    servicio.setPrecio(rs.getDouble("precio"));
                    servicio.setEstado(rs.getString("estado"));
                    servicio.setImagen(rs.getBinaryStream("imagen"));
                    return servicio;
                }
            }
        }
        return null;
    }
}
