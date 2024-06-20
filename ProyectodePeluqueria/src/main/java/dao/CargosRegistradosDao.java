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

/**
 *
 * @author javier
 */
public class CargosRegistradosDao {
    public static List<Cargo> getTodoCargo(){
        try{
            ArrayList<Cargo> lista = new ArrayList<Cargo>();
            Conexion con  = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement("select * from cargo;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cargo c = new Cargo();
                c.setIdCargo(rs.getInt("idcargo"));
                c.setCargo(rs.getString("cargo"));
                lista.add(c);
            }
            con.cerrarConexiones();
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
