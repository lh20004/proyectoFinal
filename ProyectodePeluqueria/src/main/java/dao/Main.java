/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Date; // Importa java.sql.Date para trabajar con fechas SQL
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pago;

public class Main {

    public static void main(String[] args) {
        GananciasDao gananciasService = new GananciasDao();

        // Ejemplo de prueba para obtener ganancias por día
        Date fecha = Date.valueOf("2024-06-16"); // Crear un objeto Date a partir de un String
        try {
            ArrayList<Pago> pagosPorDia = gananciasService.getGananciasPorDia(fecha);
            imprimirPagos(pagosPorDia);
        } catch (SQLException e) {
            System.out.println("Error al obtener las ganancias por día: " + e.getMessage());
        }
        
        // Ejemplo de prueba para obtener ganancias por semana
        Date inicioSemana = Date.valueOf("2024-06-10"); // Inicio de la semana de ejemplo
        Date finSemana = Date.valueOf("2024-06-16"); // Fin de la semana de ejemplo
        try {
            ArrayList<Pago> pagosPorSemana = gananciasService.getGananciasPorSemana(inicioSemana, finSemana);
            imprimirPagos(pagosPorSemana);
        } catch (SQLException e) {
            System.out.println("Error al obtener las ganancias por semana: " + e.getMessage());
        }
    }

    
    private static void imprimirPagos(ArrayList<Pago> pagos) {
    double gananciasTotales = 0.0;

    if (pagos != null && !pagos.isEmpty()) {
        for (Pago pago : pagos) {
            System.out.println("Cliente: " + pago.getCliente().getNombre());
            System.out.println("Fecha de Pago: " + pago.getFechaPago());
            System.out.println("Servicio: " + pago.getServicio().getServicio());
            System.out.println("Total: " + pago.getTotal());

            gananciasTotales += pago.getTotal(); // Sumar al total de ganancias
            System.out.println("---------------------------------------");
        }
        System.out.println("Ganancias Totales: " + gananciasTotales); // Imprimir ganancias totales al final
    } else {
        System.out.println("No se encontraron datos de pagos.");
    }
}

}
