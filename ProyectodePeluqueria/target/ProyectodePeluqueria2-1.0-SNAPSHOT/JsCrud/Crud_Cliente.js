$(document).ready(function () {
    $('#formulario_registro').parsley();


    cargarTabla();


    $(document).on("click", "#registrar_cliente", function (e) {
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        $("#llave_persona").val("");
        $("#consultar_datos").val("si_registro");
        $("#md_registrar_cliente").modal("show");
    });


    $(document).on("submit", "#formulario_registro", function (e) {
        e.preventDefault();
        mostrar_cargando("Procesando Solicitud", "Espere mientras se almacenan los datos");


        var datos = $(this).serialize();


        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function (json) {
            Swal.close();
            if (json[0].resultado === "exito") {
                Swal.fire('Éxito', 'Cliente Registrado', 'success');
                $("#md_registrar_cliente").modal("hide");
                cargarTabla(); // Actualizar tabla después de registro
            } else {
                Swal.fire('Acción no completada', 'No se pudo registrar el cliente', 'error');
            }
        }).fail(function (xhr, status, error) {
            Swal.fire('Error', 'Hubo un error al procesar la solicitud', 'error');
        });
    });


    $(document).on("click", ".btn_editar", function (e) {
        e.preventDefault();
        mostrar_cargando("Espere", "Obteniendo datos");

        var id = $(this).attr("data-id");


        var datos = {
            "consultar_datos": "si_cliente_especifico",
            "id": id
        };


        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function (json) {
            if (json[0].resultado === "exito") {
                $("#formulario_registro").trigger("reset");
                $("#llave_persona").val(json[0].id_persona);
                $("#consultar_datos").val("si_actualizalo");
                $("#nombrecliente").val(json[0].nombrecliente);
                $("#apellidocliente").val(json[0].apellidocliente);
                $("#telefono").val(json[0].telefono);
                $("#correo").val(json[0].correo);
                $("#contrasenia").val(json[0].contrasenia);

                $("#md_registrar_cliente").modal("show");
            }
        }).fail(function () {
            // Manejar errores
        }).always(function () {

        });
    });


    $(document).on("click", "#btn_cerrar, #close", function (e) {
        e.preventDefault();
        $("#md_registrar_cliente").modal("hide");
    });


    function cargarTabla(estado = 1) {
        mostrar_cargando("Procesando Solicitud", "Espere mientras se procesa la información");

        var datos = {
            "consultar_datos": "si_consulta",
            "estado": estado
        };


        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function (json) {
            Swal.close();
            if (json[0].resultado === "exito") {
                $("#aqui_tabla").empty().html(json[0].tabla);
                $("#Clientes_registradas").text(json[0].cuantos);
                $("#tabla_clientes").DataTable({
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                    }
                });
            } else {
                Swal.fire('Acción no completada', 'No se puede registrar el cliente', 'error');
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
            }
        }).then((result) => {
            if (result.dismiss === Swal.DismissReason.timer) {
                console.log('I was closed by timer');
            }
        });
    }
});

$(document).ready(function() {
    $('#regresar').click(function(event) {
        event.preventDefault(); // Evita el comportamiento por defecto del enlace

        // Regresa a la página anterior en el historial del navegador
        window.history.back();
    });
});