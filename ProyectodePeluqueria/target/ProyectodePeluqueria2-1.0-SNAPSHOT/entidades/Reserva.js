/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


class Reserva{
    idReserva;
    fecha;
    horaInicio;
    horaFin;
    estado;
    servicios;
    
    constructor(){
        this.idReserva = 0;
        this.fecha = new Date();
        this.horaInicio = new Date();
        this.horaFin = new Date();
        this.estado = '';
        this.servicios = [];
    }
    
    getIdReserva(){
        return this.idReserva;
    }
    setIdReserva(id){
        this.idReserva = id;
    }
    getFecha(){
        return this.fecha;
    }
    setFecha(fecha){
        this.fecha = fecha;
    }
    getHoraInicio(){
        return this.horaInicio;
    }
    setHoraInicio(hora){
        this.horaInicio = hora;
    }
    getHoraFin(){
        return this.horaFin;
    }
    setHoraFin(hora){
        this.horaFin = hora;
    }
    getEstado(){
        return this.estado;
    }
    setEstado(estado){
        this.estado = estado;
    }
    getSetvicio(){
        return this.servicios;
    }
    setServicio(servicios){
        this.servicios = servicios;
    }
    addServicio(servicio){
        this.servicios.push(servicio);
    }
}