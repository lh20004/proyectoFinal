/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


class Cargo{
    idcargo;
    cargo;
    constructor(){
        this.idcargo = 0;
        this.cargo = '';
    }
    
    getIdcargo(){
        return this.idcargo;
    }
    setIdcargo(id){
        this.idcargo = id;
    }
    getCargo(){
        return this.cargo;
    }
    setCargo(cargo){
        this.cargo = cargo;
    }
}