
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
    
    
}
