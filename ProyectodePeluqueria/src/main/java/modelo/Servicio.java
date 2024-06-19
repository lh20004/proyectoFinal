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

public class Servicio {
    private int idServicio;
    private String servicio;
    private String descripcion;
    private double precio;
    private String estado;
    private int totalServicios;
    private double totalganancias;
    private ArrayList<Reserva> listaReserva;
    private ArrayList<Pago> listaPago;
    
    public Servicio() {
    }

    public Servicio( double totalganancias) {
     
        this.totalganancias = totalganancias;
    }

    public double getTotalganancias() {
        return totalganancias;
    }

    public void setTotalganancias(double totalganancias) {
        this.totalganancias = totalganancias;
    }
    

    public Servicio(int idServicio, String servicio, String descripcion, double precio, String estado, ArrayList<Reserva> listaReserva, ArrayList<Pago> listaPago) {
        this.idServicio = idServicio;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.listaReserva = listaReserva;
        this.listaPago = listaPago;
    }

    public Servicio(String servicio, String descripcion, double precio, String estado) {
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

    public Servicio(int idServicio, String servicio, String descripcion, double precio, String estado) {
        this.idServicio = idServicio;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

    public Servicio(String servicio, int totalServicios) {
        this.servicio = servicio;
        this.totalServicios = totalServicios;
    }

    public int getTotalServicios() {
        return totalServicios;
    }

    public void setTotalServicios(int totalServicios) {
        this.totalServicios = totalServicios;
    }
    
    
    
    

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(ArrayList<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    public ArrayList<Pago> getListaPago() {
        return listaPago;
    }

    public void setListaPago(ArrayList<Pago> listaPago) {
        this.listaPago = listaPago;
    }
    
}
