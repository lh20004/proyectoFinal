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
import modelo.Empleado;

/**
 *
 * @author Luis
 */
public class EmpleadoComboDao {

    private Conexion conexion;

    public EmpleadoComboDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Empleado> obtenerPeluqueros() throws SQLException {
        ArrayList<Empleado> peluqueros = new ArrayList<>();
        String query = "SELECT e.idempleado, e.nombre, e.telefono, e.dui, e.estado, e.correo, e.clave, c.cargo "
                + "FROM empleado e "
                + "JOIN cargo c ON e.idcargo = c.idcargo "
                + "WHERE c.cargo = 'Empleado' AND c.estado = 'disponible'";

        try ( Connection conn = conexion.getConexion();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idempleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setDui(rs.getString("dui"));
                empleado.setEstado(rs.getString("estado"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setClave(rs.getString("clave"));
                // No olvides agregar más atributos si es necesario

                peluqueros.add(empleado);
            }
        } catch (SQLException e) {
            throw e;
        }

        return peluqueros;
    }
}
