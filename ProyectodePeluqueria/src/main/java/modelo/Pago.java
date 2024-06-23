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

public class Pago {
    private int idPago;
    private Cliente cliente;
    private Date fechaPago;
    private Double total;
    private Servicio servicio;
    private ArrayList<Servicio> listaServicio;

    public Pago() {
    }

    public Pago(int idPago, Cliente cliente, Date fechaPago, Double total, Servicio servicio, ArrayList<Servicio> listaServicio) {
        this.idPago = idPago;
        this.cliente = cliente;
        this.fechaPago = fechaPago;
        this.total = total;
        this.servicio = servicio;
        this.listaServicio = listaServicio;
    }
    
    

    public Pago(Date fechaPago, Double total, Servicio servicio) {
        this.fechaPago = fechaPago;
        this.total = total;
        this.servicio = servicio;
    }

    public Pago(Double total) {
        this.total = total;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ArrayList<Servicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(ArrayList<Servicio> listaServicio) {
        this.listaServicio = listaServicio;
    }
    
    
    
    

   
    
    
    
}
