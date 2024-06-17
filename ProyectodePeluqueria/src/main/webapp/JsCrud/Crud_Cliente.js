 $(function(){
    $('#formulario_registro').parsley();
    cargarTabla();

    $(document).on("click", "#registrar_cliente", function(e){
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        $("#md_registrar_cliente").modal("show");
        document.querySelector('#codigocliente').readonly = false;
        
    });
    
    $(document).on("submit", "#formulario_registro", function (e){
    e.preventDefault();
    mostrar_cargando("Procesando Solicitud","Espere mientras se almacena los datos");
    var datos = $("#formulario_registro").serialize();
    console.log(datos);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../RegCliente",
        data: datos
    }).done(function (json){
        Swal.close();
        if (json[0].resultado == "exito") {
            Swal.fire('Éxito', 'Cliente Registrado', 'success');
            $("#md_registrar_cliente").modal("hide");
            cargarTabla(); // Actualiza la tabla después de registrar el cliente
        } else {
            Swal.fire('Acción no completada', 'No se pudo registrar el cliente', 'error');
        }
    }).fail(function (xhr, status, error) {
        Swal.fire('Error', 'Hubo un error al procesar la solicitud', 'error');
    });
  });
});




$(document).on("click", "#btn_cerrar", function (e){
    e.preventDefault();
     $("#md_registrar_cliente").modal("hide");
});

$(document).on("click", "#close", function (e){
    e.preventDefault();
     $("#md_registrar_cliente").modal("hide");
});

$(document).on("click", ".btn_editar", function(e){
    e.preventDefault();
    mostrar_cargando("ESPERE", "Obteniendo datos");
    var id = $(this).attr("data-id");
    var datos = {"consultar_datos" : "si_cliente_especifico", "id" : id};
    $.ajax({
        dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function (json){
            if (json[0].resultado === "exito") {
               $("#formulario_registro").trigger("reset");
               $("#consultar_datos").val("si_actualizalo");
               $("#llave_persona").val(json[0].id_persona);
               $("#nombrecliente").val(json[0].nombrecliente);
               $("#apellidocliente").val(json[0].apellidocliente);
               $("#telefono").val(json[0].telefono);
               $("#correo").val(json[0].correo);
               $("#contrasenia").val(json[0].contrasenia);

               
               $("#md_registrar_cliente").modal("show");
            }
        }).fail(function () {            
        }).always(function() {
        });
    });

function cargarTabla(estado = 1){
    mostrar_cargando("procesando solicitud", "Espere mientras se procesa la informacion");
    var datos = {"consultar_datos": "si_consulta", "estado": estado};
    console.log("esta aqui");
    $.ajax({
        dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function (json){
            Swal.close();
            console.log("datos consultados", json);
            if (json[0].resultado === "exito") {
                $("#aqui_tabla").empty().html(json[0].tabla);
                document.querySelector("#Clientes_registradas").textContent = json[0].cuantos;
                $("#tabla_clientes").DataTable({
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                    }
                });
            }else {
                Swal.fire('Accion no completada', "no se puede registrar el cliente","ERROR");
            }
        }).fail(function () {

        }).always(function() {
        
        });
    }        
    
 function mostrar_cargando(titulo, mensaje = ""){
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
