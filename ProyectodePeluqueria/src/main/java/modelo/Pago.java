/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
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
public class Pago {
    private int idPago;
    private Cliente cliente;
    private Date fechaPago;
    private Double total;
    private ArrayList<Servicio> listaServicio;
    
}
