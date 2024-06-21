/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//iniciar();
//function iniciar(){
//    //document.querySelector('#bSecion').addEventListener('click',evtSecion,false);
//    document.querySelector('#bSecion').addEventListener('click',evtSecion,false);
//}

$(function(){
    document.querySelector('#bSecion').addEventListener('click',evtSecion,false);
});

function evtSecion(evt){
    var fValidacion = document.querySelector('#fSecion').checkValidity();
    var datos = {'email':document.querySelector('#eEmail').value,'password':document.querySelector('#pPassword').value};
    if(fValidacion){
        evt.preventDefault();
        $.ajax({
            url:"../controladorInicioDeSecion",
            dataType: "json",
            type: "POST",
            data: datos
        }).done(function(json){
            if(json.result=='succes'){
                location.href=json.url;
            }else if(json.result=='correoError'){
                swal.fire('Correo','No se encontro el correo','info');
            }else if(json.result=='claveError'){
                swal.fire('Contraseña','La contraseña es incorrecta','info');
            }
        }).fail(function(){
            swal.fire('Error','Ocurrio un error al intentar buscar el correo','error');
        });
    }
}