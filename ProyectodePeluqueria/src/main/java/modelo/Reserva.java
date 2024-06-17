/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author guill
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reserva {
   private int idReserva;
   private Date fechaReserva;
   private Time horaInicio;
   private Time horaFin;
   private String estado;
   private Cliente cliente;
   private Empleado empleado;
   private Servicio servicio;
   private ArrayList<Servicio> listaServicio;
   
   
   
}
