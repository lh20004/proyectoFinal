/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
/**
 *
 * @author Jonathan Flores
 */
public class SemanaConverter {

    public static void main(String[] args) {
        String semana = "2024-W24";
        try {
            Date[] fechas = convertirSemanaAFechas(semana);
            System.out.println("Fecha de inicio: " + fechas[0]);
            System.out.println("Fecha de fin: " + fechas[1]);
        } catch (Exception e) {
            System.out.println("Error al convertir semana a fechas: " + e.getMessage());
        }
    }

    public static Date[] convertirSemanaAFechas(String semana) {
        // Obtener año y número de semana del formato "YYYY-WWW"
        int año = Integer.parseInt(semana.substring(0, 4));
        int numeroSemana = Integer.parseInt(semana.substring(6));

        // Obtener el primer día de la semana
        LocalDate primerDiaSemana = LocalDate.of(año, 1, 1)
                .with(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(), numeroSemana)
                .with(java.time.DayOfWeek.MONDAY);

        // Obtener el último día de la semana (sumar 6 días al primer día)
        LocalDate ultimoDiaSemana = primerDiaSemana.plusDays(6);

        // Convertir LocalDate a java.sql.Date
        Date fechaInicio = Date.valueOf(primerDiaSemana);
        Date fechaFin = Date.valueOf(ultimoDiaSemana);

        // Devolver un array de fechas
        return new Date[]{fechaInicio, fechaFin};
    }
}
