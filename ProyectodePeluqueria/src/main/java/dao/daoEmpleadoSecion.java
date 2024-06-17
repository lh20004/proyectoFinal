/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cargo;
import modelo.Cliente;
import modelo.Empleado;

/**
 *
 * @author javier
 */
public class daoEmpleadoSecion {
    public static Empleado getEmpleadoPorCorreo(String correo){
        try{
            Empleado em = null;
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("select * from empleado as e inner join cargo as c on c.idcargo = e.idcargo where e.correo = ?;");
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                em = new Empleado();
                em.setIdEmpleado(rs.getInt("idempleado"));
                em.setNombre(rs.getString("nombre"));
                em.setTelefono(rs.getString("telefono"));
                em.setDui(rs.getString("dui"));
                em.setEstado(rs.getString("estado"));
                em.setCorreo(rs.getString("correo"));
                em.setClave(rs.getString("clave"));
                Cargo ca = new Cargo();
                ca.setIdCargo(rs.getInt("idcargo"));
                ca.setCargo(rs.getString("cargo"));
                em.setCargo(ca);
            }
            con.cerrarConexiones();
            return em;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
