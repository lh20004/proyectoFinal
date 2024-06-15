/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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
public class Servicio {
    private int idServicio;
    private String servicio;
    private String descripcion;
    private double precio;
    private ArrayList<Reserva> listaReserva;
    private ArrayList<Pago> listaPago;
    
}
