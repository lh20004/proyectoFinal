/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

class Empleado{
    idempleado;
    nombre;
    apellido;
    telefono;
    dui;
    estado;
    correo;
    clave;
    cargo;
    constructor(){
        this.idempleado = 0;
        this.nombre = '';
        this.apellido = '';
        this.telefono = '';
        this.dui = '';
        this.estado = '';
        this.correo = '';
        this.clave = '';
        this.cargo = new Cargo();
    }
    getIdempleado(){
        return this.idempleado;
    }
    setIdempleado(id){
        this.idempleado = id;
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
    getDui(){
        return this.dui;
    }
    setDui(dui){
        this.dui = dui;
    }
    getEstado(){
        return this.estado;
    }
    setEstado(estado){
        this.estado = estado;
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
    getCargo(){
        return this.cargo;
    }
    setCargo(cargo){
        this.cargo = cargo;
    }
}