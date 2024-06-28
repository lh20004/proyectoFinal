/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


class Servicio{
    idServicio;
    servicio;
    descripcion;
    precio;
    estado;
    imagen;
    
    constructor(){
        this.idServicio = 0;
        this.servicio = '';
        this.descripcion = '';
        this.precio = 0.0;
        this.estado = '';
        this.imagen = new Blob([],{type:"image/jpeg"});
    }
    
    getIdServicio(){
        return this.idServicio;
    }
    setIdServicio(id){
        this.idServicio = id;
    }
    getServicio(){
        return this.servicio;
    }
    setServicio(servicio){
        this.servicio = servicio;
    }
    getDescripcion(){
        return this.descripcion;
    }
    setDescripcion(descripcion){
        this.descripcion = descripcion;
    }
    getPrecio(){
        return this.precio;
    }
    setPrecio(precio){
        this.precio = precio;
    }
    getEstado(){
        return this.estado;
    }
    setEstado(estado){
        this.estado = estado;
    }
    getImagen(){
        return this.imagen;
    }
    setImagen(imagen){
        this.imagen = imagen;
    }
}