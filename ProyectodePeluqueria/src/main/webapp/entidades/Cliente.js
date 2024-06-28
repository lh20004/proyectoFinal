/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


class Cliente{
    idCliente;
    nombre;
    apellido;
    telefono;
    correo;
    clave;
    reserva;
    
    constructor(){
        this.idCliente = 0;
        this.nombre = '';
        this.apellido = '';
        this.telefono = '';
        this.correo = '';
        this.clave = '';
        this.reserva = [];
    }
    getIdCliente(){
        return this.idCliente;
    }
    setIdCliente(id){
        this.idCliente = id;
    }
    getNombre(){
        return this.nombre;
    }
    setNombre(nombre){
        this.nombre = nombre;
    }
    getApellido(){
        return this.apellido;
    }
    setApellido(apellido){
        this.apellido = apellido;
    }
    getTelefono(){
       return this.telefono; 
    }
    setTelefono(telefono){
        this.telefono = telefono;
    }
    getCorreo(){
        return this.correo;
    }
    setCorreo(correo){
        this.correo = correo;
    }
    getClave(){
        return this.clave;
    }
    setClave(clave){
        this.clave = clave;
    }
    getReserva(){
        return this.reserva;
    }
    setReserva(reserva){
        this.reserva = reserva;
    }
    addReserva(reserva){
        this.reserva.push(reserva);
    }
}