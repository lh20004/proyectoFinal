/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Machado
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleReserva {
  private int idDetalle;
  private Reserva reserva;
  private Servicio servicio;
  
}
