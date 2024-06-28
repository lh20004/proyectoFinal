/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 *
 * @author javier
 */
public class test {
    public static void main(String args[]){
        Date fecha = new Date(Calendar.getInstance().getTime().getTime());
        Time tiempo = new Time(fecha.getTime());
        System.out.println(tiempo);
    }
}
