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
public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String telefono;
    private String dui;
    private String estado;
    private String correo;
    private String clave;
    private Cargo cargo;
    private ArrayList<Reserva> listaReserva;
    
}
