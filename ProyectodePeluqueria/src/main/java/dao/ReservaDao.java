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
import modelo.Empleado;
import modelo.Servicio;
import modelo.Reserva;

/**
 *
 * @author Jonathan Flores
 */
public class ReservaDao {

    Conexion conexion = null;
    private ArrayList<Reserva> reservaList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private Reserva reserva;

    private static final String MOSTRAR_CONFIRMADOS = "SELECT cl.nombre, cl.correo, re.estado\n"
            + "FROM cliente Cl\n"
            + "INNER JOIN reserva re ON cl.idcliente = re.fk_cliente\n"
            + "WHERE re.estado = 'Confirmada';";
    
      private static final String SELECT_ALL = "SELECT r.fechareserva As Fecha,r.horainicio,r.horafin,r.estado,CONCAT(c.nombre, ' ', c.apellido) AS Cliente, CONCAT(e.nombre, ' ', e.apellido) AS Empleado,s.servicio AS Servicio FROM detallereserva dr  JOIN reserva r ON dr.idreserva = r.idreserva JOIN cliente c ON r.idcliente = c.idcliente JOIN empleado e ON r.idempleado = e.idempleado JOIN servicio s ON dr.idservicio = s.idservicio";
     
      private static final String Reservaporempleado = "SELECT r.fechareserva As Fecha,r.horainicio,r.horafin,r.estado,CONCAT(c.nombre, ' ', c.apellido) AS Cliente, CONCAT(e.nombre, ' ', e.apellido) AS Empleado,s.servicio AS Servicio FROM detallereserva dr  JOIN reserva r ON dr.idreserva = r.idreserva JOIN cliente c ON r.idcliente = c.idcliente JOIN empleado e ON r.idempleado = e.idempleado JOIN servicio s ON dr.idservicio = s.idservicio WHERE  r.estado='pendiente' AND e.idempleado= ?";
      
      public  ReservaDao () throws SQLException, ClassNotFoundException{
        this.conexion = new Conexion();
    }
    
    public ArrayList<Reserva> selectReserva () throws SQLException, ClassNotFoundException{
        this.reservaList = new ArrayList<>();
        
        try{
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(MOSTRAR_CONFIRMADOS);
            this.rs = ps.executeQuery();
            
            Reserva obj = null;
            Cliente ob = null;
            while (this.rs.next()){
                ob = new Cliente();
                ob.setNombre(rs.getString("nombre"));
                ob.setCorreo(rs.getString("correo"));
                obj = new Reserva();
                obj.setCliente(ob);
                obj.setEstado(rs.getString("estado"));
                
                this.reservaList.add(obj);
            }
            this.conexion.cerrarConexiones();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.reservaList;
    }
    
    public ArrayList<Reserva> obtenerReservasConDetalles() {
       this.reservaList = new ArrayList<>();

        try {            
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SELECT_ALL);
            this.rs = ps.executeQuery();
                
            while (rs.next()) {
                Reserva reserva = new Reserva();
           
                reserva.setFechaReserva(rs.getDate("Fecha"));
                reserva.setHoraInicio(rs.getTime("horainicio"));
                reserva.setHoraFin(rs.getTime("horafin"));
                reserva.setEstado(rs.getString("estado"));

                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString("Cliente"));
                Empleado empleado = new Empleado();
                
                empleado.setNombre(rs.getString("Empleado"));
                Servicio corte = new Servicio();
                corte.setServicio(rs.getString("Servicio"));

                reserva.setCliente(cliente);
                reserva.setEmpleado(empleado);
                reserva.setServicio(corte);
                this.reservaList.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.reservaList;
    }
    
     public ArrayList<Reserva> obtenerReservasempleados(int id) {
       this.reservaList = new ArrayList<>();

        try {            
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(Reservaporempleado);
              ps.setInt(1, id);
            this.rs = ps.executeQuery();
                
            while (rs.next()) {
                Reserva reserva = new Reserva();
           
                reserva.setFechaReserva(rs.getDate("Fecha"));
                reserva.setHoraInicio(rs.getTime("horainicio"));
                reserva.setHoraFin(rs.getTime("horafin"));
                reserva.setEstado(rs.getString("estado"));

                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString("Cliente"));
                Empleado empleado = new Empleado();
                
                empleado.setNombre(rs.getString("Empleado"));
                Servicio corte = new Servicio();
                corte.setServicio(rs.getString("Servicio"));

                reserva.setCliente(cliente);
                reserva.setEmpleado(empleado);
                reserva.setServicio(corte);
                this.reservaList.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.reservaList;
    }


}
