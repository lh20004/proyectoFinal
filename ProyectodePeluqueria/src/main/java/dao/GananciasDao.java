/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Pago;
import modelo.Servicio;
import org.json.JSONObject;

public class GananciasDao {
    
    private final Conexion conexion;
    private ArrayList<Pago> pagoList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private Pago pago;
    
    public static final String Gananciaspordia = "SELECT c.nombre AS cliente, p.fechapago AS fecha, s.servicio AS servicio, p.total AS total FROM pago p JOIN cliente c ON p.idcliente = c.idcliente JOIN detallepago dp ON p.idpago = dp.idpago JOIN servicio s ON dp.idservicio = s.idservicio WHERE p.fechapago = ?;";
    public static final String Gananciasporsemana = "SELECT c.nombre AS cliente, p.fechapago AS fecha, s.servicio AS servicio, p.total AS total FROM pago p JOIN cliente c ON p.idcliente = c.idcliente JOIN detallepago dp ON p.idpago = dp.idpago JOIN servicio s ON dp.idservicio = s.idservicio WHERE p.fechapago BETWEEN DATE_TRUNC('week', CAST(? AS DATE)) AND (DATE_TRUNC('week', CAST(? AS DATE)) + INTERVAL '6 days');";
    public static final String Gananciaspormes = "SELECT c.nombre AS cliente, p.fechapago AS fecha, s.servicio AS servicio, p.total AS total FROM pago p JOIN cliente c ON p.idcliente = c.idcliente JOIN detallepago dp ON p.idpago = dp.idpago JOIN servicio s ON dp.idservicio = s.idservicio WHERE TO_CHAR(p.fechapago, 'YYYY-MM') = ?";

    public GananciasDao() {
        this.conexion = new Conexion();
    }

    public GananciasDao(Pago pago) {
        this.pago = pago;
        this.conexion = new Conexion();
    }

    public ArrayList<Pago> getGananciasPorDia(String fecha) throws SQLException {
        this.pagoList = new ArrayList<>();
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(Gananciaspordia);
            ps.setString(1, fecha);
            this.rs = ps.executeQuery();
            while (this.rs.next()) {
                this.pago = new Pago();
                Cliente cliente = new Cliente();
                cliente.setNombre(this.rs.getString("cliente"));
                this.pago.setFechaPago(this.rs.getDate("fecha"));
                Servicio servicio = new Servicio();
                servicio.setServicio(this.rs.getString("servicio"));
                this.pago.setTotal(this.rs.getDouble("total"));
                this.pago.setCliente(cliente);
                this.pago.setServicio(servicio);
                this.pagoList.add(this.pago);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (accesoDB != null) accesoDB.close();
        }
        return this.pagoList;
    }

    public ArrayList<Pago> getGananciasPorSemana(String semana) throws SQLException {
        this.pagoList = new ArrayList<>();
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(Gananciasporsemana);
            ps.setString(1, semana);
            ps.setString(2, semana);
            this.rs = ps.executeQuery();
            while (this.rs.next()) {
                this.pago = new Pago();
                Cliente cliente = new Cliente();
                cliente.setNombre(this.rs.getString("cliente"));
                this.pago.setFechaPago(this.rs.getDate("fecha"));
                Servicio servicio = new Servicio();
                servicio.setServicio(this.rs.getString("servicio"));
                this.pago.setTotal(this.rs.getDouble("total"));
                this.pago.setCliente(cliente);
                this.pago.setServicio(servicio);
                this.pagoList.add(this.pago);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (accesoDB != null) accesoDB.close();
        }
        return this.pagoList;
    }

    public ArrayList<Pago> getGananciasPorMes(String mes) throws SQLException {
        this.pagoList = new ArrayList<>();
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(Gananciaspormes);
            ps.setString(1, mes);
            this.rs = ps.executeQuery();
            while (this.rs.next()) {
                this.pago = new Pago();
                Cliente cliente = new Cliente();
                cliente.setNombre(this.rs.getString("cliente"));
                this.pago.setFechaPago(this.rs.getDate("fecha"));
                Servicio servicio = new Servicio();
                servicio.setServicio(this.rs.getString("servicio"));
                this.pago.setTotal(this.rs.getDouble("total"));
                this.pago.setCliente(cliente);
                this.pago.setServicio(servicio);
                this.pagoList.add(this.pago);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (accesoDB != null) accesoDB.close();
        }
        return this.pagoList;
    }
}
