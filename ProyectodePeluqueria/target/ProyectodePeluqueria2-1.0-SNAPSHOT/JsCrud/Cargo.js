/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$(function () {
    $('#formulario_registro').parsley();
    cargarTabla();
    $(document).on("click", "#registrar_autor", function (e) {
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        $("#md_registrar_cargo").modal("show");
        document.querySelector('#idcargo').readOnly = false;
    });
    $(document).on("submit", "#formulario_registro", function (e) {
        e.preventDefault();
        mostrar_cargando("Procesando Solicitud", "Espere mientras se almacenan los datos");
        var datos = $("#formulario_registro").serialize();
        console.log(datos);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../CargoServlet",
            data: datos
        }).done(function (json) {
            Swal.close();
            if (json[0].resultado == "exito") {
                Swal.fire('Exito', 'Cargo Registrado', 'success');
                $("#md_registrar_cargo").modal("hide");
                cargarTabla();
                location.reload();
            } else {
                Swal.fire('Accion no completada', "No se puede registrar el Cargo", "error");
            }
        }).fail(function () {
            // Manejo de error en caso de que la solicitud falle
            Swal.fire('Error', "Ha ocurrido un error al procesar la solicitud", "error");
        }).always(function () {
            // Código a ejecutar siempre, independientemente del resultado de la solicitud
        });


    });
});

$(document).on("click",".btn_editar", function (e) {
    e.preventDefault();
    mostrar_cargando("Espere", "Obteniendo datos");
    var id = $(this).attr("data-id");
    var datos = {"consultar_datos": "si_cliente_especifico","id": id};
    console.log("datos enviados " + datos);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../CargoServlet",
        data: datos
    }).done(function (json) {
        if (json[0].resultado === "exito") {
            console.log(datos);
            $("#formulario_registro").trigger("reset");
            $("#consultar_datos").val("si_actualizalo");                     
            $('#idcargo').val(json[0].idcargo);                       
            $('#cargo').val(json[0].cargo);  
            $("#md_registrar_cargo").modal("show");
        }
    }).fail(function () {
    }).always(function () {
    });
});

$(document).on("click", ".btn_eliminar", function (e) {
    e.preventDefault();
    Swal.fire({
        title: '¿Desea eliminar el registro',
        text: 'Al continuar, no podrà ser revertido y los datos serån borrados completamente',
        showDenyButton: true,
        showCancelButton: false,
        confirmButton: 'si',
        denyButton: 'NO'
    }).then((result) => {
        if (result.isConfirmed) {
          eliminar($(this).attr('data-id'));
        }else if(result.isDenied){
            Swal.fire("Opcion cancelada por el usuario",'', 'info');
        }
    });
});
$(document).on("click", "#btn_cerrar, #close", function(e) {
        e.preventDefault();
        $("#md_registrar_cargo").modal("hide"); 
    });

function cargarTabla(estado = 1) {
    mostrar_cargando("procesando solicitud", "Espere mientas se procesa la información");
    var datos = {"consultar_datos": "si_consulta", "estado": estado};
    console.log("esta aqui");
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../CargoServlet",
        data: datos
    }).done(function (json) {
        Swal.close();
        console.log("datos consultados: ", json);
        if (json[0].resultado == "exito") {
            $("#aqui_tabla").empty().html(json[0].tabla);
            document.querySelector("#Autores_registradas").textContent = json[0].cuantos;
            $("#tabla_autores").DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                }
            });
        } else {

            Swal.fire('Accion no completada', 'No pudo obtener los datos', 'error');
        }
    }).fail(function () {

    }).always(function () {

    });
}




function mostrar_cargando(titulo, mensaje = "") {
    Swal.fire({
        title: titulo,
        html: mensaje,
        timer: 2000,
        timerProgessBar: true,
        didOpen: () => {
            Swal.showLoading();
        },
        willClose: () => {
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {

            console.log('I was closed by timer');
            }

        });
}    

function eliminar(id){
    mostrar_cargando("Preocesando solicitud","Espere mientras se eliminan los datos " +id);
    var datos = {"consultar_datos": "si_eliminalo", "id": id};
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../CargoServlet",
        data: datos
 }).done(function (json){
     Swal.close();
        if (json[0].resultado === "exito"){
            Swal.fire(
                    'Excelente',
                    'El dato fue eliminado',
                    'success'
                    );
            cargarTabla();
        }else{
            Swal.fire(
                    'Error',
                    'No se puedo eliminar el dato intentelo más tarde',
                    'error'
                    ); 
        }
       }).fail(function (){
        
        console.log("Error al eliminar");
       }).always(function() {
           console.log("Error al eliminar");
       });
       }