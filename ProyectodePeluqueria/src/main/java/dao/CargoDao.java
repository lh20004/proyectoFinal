/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cargo;

/**
 *
 * @author Machado
 */
public class CargoDao {
    
    Conexion conexion = null;
    private ArrayList<Cargo> CargoList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private Cargo cargo = null;
    

    private static final String INSERT_CARGO = "Insert into cargo(cargo) Values (?)";

    private static final String UPDATE_Cargo = "UPDATE cargo SET cargo=? where idcargo=?";

    private static final String DELETE_CARGO = "DELETE FROM cargo WHERE idcargo = ?";

    private static final String SELECT_Cargo_BY_ID = "SELECT * FROM cargo WHERE idcargo= ?";

    private static final String SELECT_ALL_Cargo = "select * from cargo";

    public CargoDao() {
        this.conexion = new Conexion();
    }

    public String insertAutor(Cargo cargo) throws SQLException, ClassNotFoundException {

        String resultado;
        int resultado_insertar;
        try {
            this.conexion = new Conexion();
            this.conexion.getConexion();
            this.accesoDB = conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(INSERT_CARGO);
            
            this.ps.setString(1, cargo.getCargo());
           
//            System.out.println("autor_insertar" + autor);
            resultado_insertar = this.ps.executeUpdate();
            this.conexion.cerrarConexiones();
            if (resultado_insertar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_insertar_Cargo";
            }
        } catch (SQLException e) {
            resultado = "error_excepcion";
            System.out.println("fallo insertar" + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ArrayList<Cargo> selectAllAutores(Integer estado, String quien) throws SQLException, ClassNotFoundException {

        this.CargoList = new ArrayList();

        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SELECT_ALL_Cargo);
            this.rs = ps.executeQuery();

            Cargo obj = null;
            while (this.rs.next()) {
                obj = new Cargo();
                obj.setIdCargo(rs.getInt("idcargo"));
                obj.setCargo(rs.getString("cargo"));
              
                this.CargoList.add(obj);
            }
            this.conexion.cerrarConexiones();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.CargoList;
    }


    public String updateCargo(Cargo cargo, String codigo) throws SQLException {
        System.out.println(cargo.getIdCargo());
        String resultado;
        int res_actualizar;
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(UPDATE_Cargo);
            this.ps.setString(1, cargo.getCargo());
            this.ps.setInt(2, cargo.getIdCargo());
            
            res_actualizar = this.ps.executeUpdate();
            System.out.println(res_actualizar);

            if (res_actualizar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_actualizar";
            }
        } catch (SQLException e) {
            resultado = "error_exception";
            e.printStackTrace();
        }

        return resultado;
    }
  

    
    public ResultSet findById(int quien) throws SQLException, ClassNotFoundException{
        ResultSet resultSet = null;
        
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(SELECT_Cargo_BY_ID);
            System.out.println("sql" + quien + SELECT_Cargo_BY_ID);
            this.ps.setInt(1, quien);
            resultSet = this.ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
        
    }

    public String deleteAutor(int id)throws SQLException{
    String resultado;
    try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = this.accesoDB.prepareStatement(DELETE_CARGO);
           this.ps.setInt(1, id);
            System.out.println("Cargo_eliminar " + id);
            System.out.println(DELETE_CARGO);
            System.out.println(ps.toString());
            this.ps.executeUpdate();   
            
            this.conexion.cerrarConexiones();
            resultado = "exito";
        } catch (SQLException e) {
            resultado = "error";
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return resultado;
    } 
}
