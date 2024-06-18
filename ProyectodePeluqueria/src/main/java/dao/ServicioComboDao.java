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
import modelo.Servicio;

/**
 *
 * @author Luis
 */
public class ServicioComboDao {
  

    private Conexion conexion;

    public ServicioComboDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Servicio> obtenerServicios() throws SQLException {
        ArrayList<Servicio> servicios = new ArrayList<>();
        String query = "SELECT idservicio, servicio, descripcion, precio, estado FROM servicio";

        try (Connection conn = conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("idservicio"));
                servicio.setServicio(rs.getString("servicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getDouble("precio"));
                

                servicios.add(servicio);
            }
        } catch (SQLException e) {
            throw e;
        }

        return servicios;
    }
}


