/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cliente;

/**
 *
 * @author javier
 */
public class daoClienteSecion {
    public static Cliente getClientePorCorreo(String correo){
        try{
            Cliente cl = null;
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("select * from cliente as cl where cl.correo = ?;");
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cl = new Cliente();
                cl.setIdCliente(rs.getInt("idcliente"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setCorreo(rs.getString("correo"));
                cl.setClave(rs.getString("clave"));
            }
            con.cerrarConexiones();
            return cl;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
