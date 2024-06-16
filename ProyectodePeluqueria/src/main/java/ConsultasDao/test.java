/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasDao;

import conexion.Conexion;
import entidades.Cargo;
import entidades.Empleado;
import java.sql.Connection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author javier
 */
public class test {

    public static void main(String args[]) {
        Empleado empleado = daoReserva.getEmpleadoPorIdCargo(1);/**Extrae un empleado que tenga asociado un cargo por el id*/
        System.out.println("Nombre: "+empleado.getNombre()+", Estado: "+empleado.getEstado());/**Se imprime la informacion de ese empleado*/
    }
}
