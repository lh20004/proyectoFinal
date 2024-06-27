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

/**
 *
 * @author Luis
 */
public class ClienteComboDao {
 



    private Conexion conexion;

    public ClienteComboDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Cliente> obtenerClientes() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT idcliente, nombre, apellido, telefono, correo, clave FROM cliente";

        try (Connection conn = conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setClave(rs.getString("clave"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw e;
        }

        return clientes;
    }
}


