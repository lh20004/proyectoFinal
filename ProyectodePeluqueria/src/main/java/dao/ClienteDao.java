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
import java.util.Random;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author Jonathan Flores
 */
public class ClienteDao {

    Conexion conexion;
    private ArrayList<Cliente> clienteList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private Cliente cliente;

    public static final String INSERTAR_CLIENTE = "INSERT INTO public.cliente(\n"
            + "	idcliente, nombre, apellido, telefono, correo, clave)\n"
            + "	VALUES (?, ?, ?, ?, ?, ?);";
    public static final String sql = "SELECT COUNT(*) AS total_clientes FROM cliente";

    public static final String UPDATE_CLIENTE = "UPDATE public.cliente SET nombre=?, apellido=?, telefono=?, correo=?, clave=? WHERE idcliente =?;";

    public static final String SELECT_ALL = " SELECT idcliente, nombre, apellido, telefono, correo, clave FROM public.cliente;";

    public static final String SELECT_BYID = "SELECT idcliente, nombre, apellido, telefono, correo, clave FROM public.cliente Where idcliente= ?; ";

    public ClienteDao() {

        this.conexion = new Conexion();

    }

    public ClienteDao(Cliente cliente) {
        this.cliente = cliente;
    }

    public String insertar(Cliente cliente) throws SQLException {
        String resultado;
        int resultado_insertar;
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = accesoDB.prepareStatement(INSERTAR_CLIENTE);
            this.ps.setInt(1, cliente.getIdCliente());
            this.ps.setString(2, cliente.getNombre());
            this.ps.setString(3, cliente.getApellido());
            this.ps.setString(4, cliente.getTelefono());
            this.ps.setString(5, cliente.getCorreo());
            this.ps.setString(6, cliente.getClave());
            System.out.println(this.ps);
            resultado_insertar = this.ps.executeUpdate();
            this.conexion.cerrarConexiones();

            if (resultado_insertar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_insertar_autor";
            }
        } catch (Exception e) {
            resultado = "error_exception";
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    public String actualizar(Cliente cliente) throws SQLException {
        String resultado;
        int res_actualizar;
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = accesoDB.prepareStatement(UPDATE_CLIENTE);
            this.ps.setString(1, cliente.getNombre());
            this.ps.setString(2, cliente.getApellido());
            this.ps.setString(3, cliente.getTelefono());
            this.ps.setString(4, cliente.getCorreo());
            this.ps.setString(5, cliente.getClave());
            this.ps.setInt(6, cliente.getIdCliente());
            System.out.println(this.ps);
            res_actualizar = this.ps.executeUpdate();
            if (res_actualizar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_actualizar";
            }
        } catch (Exception e) {
            resultado = "error_exception";
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }


    public ArrayList<Cliente> getClientes() throws SQLException {
        this.clienteList = new ArrayList();
        try {

            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SELECT_ALL);
            this.rs = ps.executeQuery();
            while (this.rs.next()) {
                this.cliente = new Cliente();
                this.cliente.setIdCliente(this.rs.getInt("idcliente"));
                this.cliente.setNombre(this.rs.getString("nombre"));
                this.cliente.setApellido(this.rs.getString("apellido"));
                this.cliente.setTelefono(this.rs.getString("telefono"));
                this.cliente.setCorreo(this.rs.getString("correo"));
                this.cliente.setClave(this.rs.getString("clave"));

                this.clienteList.add(this.cliente);
            }
            conexion.cerrarConexiones();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return this.clienteList;
    }

    public ResultSet findById(int quien) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SELECT_BYID);
            System.out.println("sql" + quien + SELECT_BYID);
            this.ps.setInt(1, quien);
            resultSet = this.ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public String generarId() {
        Random v = new Random();
        return String.format("%04d", v.nextInt(10000));
    }

    public int obtenerTotalClientes() throws SQLException {
        int totalClientes = 0;

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(sql);
            this.rs = ps.executeQuery();

            // Verificar si hay resultados y obtener el total de clientes
            if (rs.next()) {
                totalClientes = rs.getInt("total_clientes");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return totalClientes;
    }

}
