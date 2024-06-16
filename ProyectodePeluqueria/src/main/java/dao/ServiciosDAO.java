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
import java.util.List;
import modelo.Servicio;

/**
 *
 * @author MINEDUCYT
 */
public class ServiciosDAO {
     private Conexion conexion;
     
      public ServiciosDAO() {
        this.conexion = new Conexion();
    }
      
      public void insertarServicio(String servicio, String descripcion, float precio, String estado) {
        String sql = "INSERT INTO servicio (servicio, descripcion, precio, estado) VALUES (?, ?, ?)";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, servicio);
            ps.setString(2, descripcion);
            ps.setFloat(3, precio);
           
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarServicio(int idservicio, String servicio, String descripcion, float precio, String estado) {
        String sql = "UPDATE servicio SET servicio = ?, descripcion = ?, precio = ? WHERE idservicio = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, servicio);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
           
            ps.setInt(4, idservicio);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarServicio(int idservicio) {
        String sql = "DELETE FROM servicio WHERE idservicio = ?";
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idservicio);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Servicio obtenerServicio(int idservicio) {
        String sql = "SELECT * FROM servicio WHERE idservicio = ?";
        Servicio servicio = null;
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idservicio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio(
                        rs.getInt("idservicio"),
                        rs.getString("servicio"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio")
                       
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio;
    }

    public List<Servicio> obtenerTodosLosServicios() {
        String sql = "SELECT * FROM servicio";
        List<Servicio> servicios = new ArrayList<>();
        try (Connection con = conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Servicio servicio = new Servicio(
                    rs.getInt("idservicio"),
                    rs.getString("servicio"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio")
                   
                );
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicios;
    }
    
    
}
