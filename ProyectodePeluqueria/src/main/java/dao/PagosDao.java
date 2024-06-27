package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.DetalleReserva;
import modelo.Pago;
import modelo.Reserva;
import modelo.Servicio;

public class PagosDao {

    Conexion conexion = null;
    private ArrayList<Pago> listpago;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private Pago pago;
    private ArrayList<Servicio> listservi;
    private ArrayList<Cliente> listCliente;
    private ArrayList<DetalleReserva> listDetalle;

    private static final String MOSTRAR_CLIENTES = "SELECT c.idcliente, concat(c.nombre, ' ', c.apellido) AS Cliente\n"
            + "FROM cliente AS c\n"
            + "INNER JOIN reserva AS r ON c.idcliente = r.idcliente\n"
            + "WHERE r.estado = 'confirmada' AND r.fechareserva = CURRENT_DATE;";

    private static final String MOSTRAR_RESERVAS_CONFIRMADAS = "SELECT r.idreserva, CONCAT(c.nombre, ' ', c.apellido) AS Cliente,r.fechareserva AS fecha, s.servicio,\n"
            + "s.precio\n"
            + "FROM detallereserva dr \n"
            + "JOIN reserva r ON dr.idreserva = r.idreserva\n"
            + "JOIN cliente c ON r.idcliente = c.idcliente\n"
            + "JOIN servicio s ON dr.idservicio = s.idservicio\n"
            + "WHERE r.estado = 'confirmada' AND c.idcliente = ? AND r.fechareserva= CURRENT_DATE;";

    private static final String MOSTRAR_SERVICIOS = "SELECT s.idservicio, s.servicio FROM servicio AS s ORDER BY s.idservicio ASC;";

    private static final String INSERTAR_EXTRAS = "INSERT INTO detallereserva(idreserva, idservicio) VALUES (?,?);";

    private static final String PAGOS = "INSERT INTO pago(idcliente, fechapago, total) VALUES (?,?,?)";

    public PagosDao() {
        this.conexion = new Conexion();
    }

    public String insertPago(Pago pagito) throws SQLException, ClassNotFoundException {

        String resultado;
        int resultado_insertar;
        try {
            this.conexion = new Conexion();
            this.conexion.getConexion();
            this.accesoDB = conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(PAGOS);

            this.ps.setObject(1, pagito.getCliente());
            this.ps.setDate(2, pagito.getFechaPago());
            this.ps.setDouble(3, pagito.getTotal());

//            System.out.println("autor_insertar" + autor);
            resultado_insertar = this.ps.executeUpdate();
            this.conexion.cerrarConexiones();
            if (resultado_insertar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_insertar_Pagos";
            }
        } catch (SQLException e) {
            resultado = "error_excepcion";
            System.out.println("fallo insertar" + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public String insertExtras(DetalleReserva detalle) throws SQLException, ClassNotFoundException {
        String resultado;
        int resultado_insertar;
        try {
            this.conexion = new Conexion();
            this.conexion.getConexion();
            this.accesoDB = conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(INSERTAR_EXTRAS);

            this.ps.setInt(1, detalle.getReserva().getIdReserva());
            this.ps.setInt(2, detalle.getServicio().getIdServicio());

            resultado_insertar = this.ps.executeUpdate();
            this.conexion.cerrarConexiones();
            if (resultado_insertar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_insertar_extras";
            }
        } catch (SQLException e) {
            resultado = "error_excepcion";
            System.out.println("fallo insertar" + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ArrayList<Cliente> selectCliente() throws SQLException, ClassNotFoundException {
        this.listCliente = new ArrayList<>();

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(MOSTRAR_CLIENTES);
            this.rs = ps.executeQuery();

            Cliente ob = null;
            while (this.rs.next()) {
                ob = new Cliente();
                ob.setIdCliente(rs.getInt("idcliente"));
                ob.setNombre(rs.getString("Cliente"));

                this.listCliente.add(ob);
            }
            this.conexion.cerrarConexiones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listCliente;
    }

    public ArrayList<DetalleReserva> selectReservasConfirmadas(int idCliente) throws SQLException, ClassNotFoundException {
        this.listDetalle = new ArrayList<>();

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(MOSTRAR_RESERVAS_CONFIRMADAS);
            this.ps.setInt(1, idCliente);
            this.rs = ps.executeQuery();

            while (this.rs.next()) {
                Reserva resev = new Reserva();
                Cliente cliente = new Cliente();
                Servicio servi = new Servicio();
                DetalleReserva detalle = new DetalleReserva();

                resev.setIdReserva(rs.getInt("idreserva"));
                cliente.setNombre(rs.getString("Cliente"));
                resev.setCliente(cliente);
                resev.setFechaReserva(rs.getDate("fecha"));
                detalle.setReserva(resev);
                servi.setServicio(rs.getString("servicio"));
                servi.setPrecio(rs.getDouble("precio"));
                detalle.setServicio(servi);

                this.listDetalle.add(detalle);
            }
            this.conexion.cerrarConexiones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listDetalle;
    }

    public ArrayList<Servicio> selectServicios() throws SQLException, ClassNotFoundException {
        this.listservi = new ArrayList<>();

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(MOSTRAR_SERVICIOS);
            this.rs = ps.executeQuery();

            Servicio servi = new Servicio();
            while (this.rs.next()) {
                servi.setIdServicio(rs.getInt("idservicio"));
                servi.setServicio(rs.getString("servicio"));

                this.listservi.add(servi);
            }
            this.conexion.cerrarConexiones();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.listservi;
    }

}
