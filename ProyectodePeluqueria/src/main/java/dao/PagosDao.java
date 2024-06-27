package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Cliente;
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
    private Servicio servi;
    private Cliente cliente;
    private Reserva reserva;

    private static final String MOSTRAR_CLIENTES = "SELECT CONCAT(c.nombre, ' ', c.apellido) AS Cliente \n"
            + "FROM cliente AS c\n"
            + "INNER JOIN reserva AS r ON c.idcliente = r.idcliente\n"
            + "WHERE r.estado = 'confirmada' AND r.fechareserva = CURRENT_DATE;";

    private static final String MOSTRAR_RESERVAS_CONFIRMADAS = "SELECT CONCAT(c.nombre, ' ', c.apellido) AS Cliente,r.fechareserva AS fecha, s.servicio\n"
            + "FROM detallereserva dr \n"
            + "JOIN reserva r ON dr.idreserva = r.idreserva\n"
            + "JOIN cliente c ON r.idcliente = c.idcliente\n"
            + "JOIN servicio s ON dr.idservicio = s.idservicio\n"
            + "WHERE r.estado = 'confirmada' AND c.idcliente = '1'and r.fechareserva = CURRENT_DATE;";
    
    private static final String hola = ";";

}
