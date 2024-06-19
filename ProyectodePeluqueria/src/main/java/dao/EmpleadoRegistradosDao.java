/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Cargo;
import modelo.Empleado;

/**
 *
 * @author javier
 */
public class EmpleadoRegistradosDao {
    public static List<Empleado> getTodoEmpleado(){
        try{
            ArrayList<Empleado> lista = new ArrayList<Empleado>();
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("select * from empleado as e inner join cargo as c on c.idcargo = e.idcargo;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleado em = new Empleado();
                em.setIdEmpleado(rs.getInt("idempleado"));
                em.setNombre(rs.getString("nombre"));
//                em.setApellido(rs.getString("apellido"));
                em.setTelefono(rs.getString("telefono"));
                em.setDui(rs.getString("dui"));
                em.setEstado(rs.getString("estado"));
                em.setCorreo(rs.getString("correo"));
                em.setClave(rs.getString("clave"));
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idcargo"));
                cargo.setCargo(rs.getString("cargo"));
                em.setCargo(cargo);
                lista.add(em);
            }
            con.cerrarConexiones();
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean GuardarEmpleado(Empleado em){
        try{
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("insert into empleado (nombre, apellido, telefono, dui, estado, correo, clave, idcargo) values (?,?,?,?,?,?,?,?);");
            ps.setString(1, em.getNombre());
            //ps.setString(2, em.getApellido());
            ps.setString(2, "none");
            ps.setString(3, em.getTelefono());
            ps.setString(4, em.getDui());
            ps.setString(5, em.getEstado());
            ps.setString(6, em.getCorreo());
            ps.setString(7, em.getClave());
            ps.setInt(8, em.getCargo().getIdCargo());
            ps.executeUpdate();
            con.cerrarConexiones();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean actualizarEmpleado(Empleado em){
        try{
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("update empleado set nombre = ?, apellido = ?, telefono = ?, dui=?, estado=?, correo = ?, clave=?, idcargo = ? where idempleado = ?;");
            ps.setString(1, em.getNombre());
            //ps.setString(2, em.getApellido());
            ps.setString(2, "none");
            ps.setString(3, em.getTelefono());
            ps.setString(4, em.getDui());
            ps.setString(5, em.getEstado());
            ps.setString(6, em.getCorreo());
            ps.setString(7, em.getClave());
            ps.setInt(8, em.getCargo().getIdCargo());
            ps.setInt(9, em.getIdEmpleado());
            ps.executeUpdate();
            con.cerrarConexiones();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}