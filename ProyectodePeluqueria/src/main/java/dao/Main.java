/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Reserva;
import modelo.Servicio;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        EmpleadoComboDao empleadoComboDao = new EmpleadoComboDao();
        
        try {
            System.out.println("Intentando obtener peluqueros...");
            ArrayList<Empleado> peluqueros = empleadoComboDao.obtenerPeluqueros();
            if (peluqueros.isEmpty()) {
                System.out.println("No se encontraron peluqueros.");
            } else {
                for (Empleado peluquero : peluqueros) {
                    System.out.println("ID: " + peluquero.getIdEmpleado());
                    System.out.println("Nombre: " + peluquero.getNombre());
                    System.out.println("Teléfono: " + peluquero.getTelefono());
                    System.out.println("DUI: " + peluquero.getDui());
                    System.out.println("Estado: " + peluquero.getEstado());
                    System.out.println("Correo: " + peluquero.getCorreo());
                    System.out.println("Clave: " + peluquero.getClave());
                    System.out.println("----------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}