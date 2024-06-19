$(document).ready(function () {
    // Cargar combos al cargar la página
    cargarCombos();
    
    // Cargar tabla al cargar la página
    cargarTabla();
    
    // Manejar el envío del formulario
    $('#formulario_reservacion').on('submit', function (e) {
        e.preventDefault();
        insertarReserva();
    });
});

function cargarTabla() {
    var datos = {"consultar_datos": "si_consulta"};
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../ReservaServlet",
        data: datos
    }).done(function (json) {
        var html = json[0].tabla;
        $("#aqui_tabla").empty().html(html);
        $("#tabla_reservas").DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            }
        });
    }).fail(function () {
        Swal.fire('Error', "Ha ocurrido un error al procesar la solicitud", "error");
    });
}

function cargarCombos() {
    var datos = {"consultar_datos": "cargarCombos"};
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../ReservaServlet",
        data: datos
    }).done(function (json) {
        if (json[0].resultado === "exito") {
            $("#empleado").html('<option value="" disabled selected>Seleccione un empleado</option>' + json[0].empleados);
            $("#servicio").html('<option value="" disabled selected>Seleccione un servicio</option>' + json[0].servicios);
            console.log("Datos de empleados y servicios cargados correctamente");
        } else {
            console.log("Error cargando datos: " + json[0].resultado);
        }
    }).fail(function (xhr, status, error) {
        console.log("Error en la petición AJAX: " + error);
    });
}

function insertarReserva() {
    var datos = {
        "consultar_datos": "insertar",
        "cliente": $("#cliente").val(),
        "empleado": $("#empleado").val(),
        "servicio": $("#servicio").val(),
        "fecha_reservacion": $("#fecha_reservacion").val(),
        "hora_inicio": $("#hora_inicio").val(),
        "hora_fin": $("#hora_fin").val()
    };

    console.log("Datos a enviar:", datos);

    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../ReservaServlet",
        data: datos
    }).done(function (json) {
        console.log("Respuesta del servidor:", json);
        if (json.resultado === "exito") {
            Swal.fire('Éxito', 'La reserva se insertó correctamente.', 'success');
            $('#modalReserva').modal('hide');
            cargarTabla();
        } else {
            Swal.fire('Error', json.mensaje, 'error');
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Error en la solicitud AJAX:", textStatus, errorThrown);
        Swal.fire('Error', "Ha ocurrido un error al procesar la solicitud", "error");
    });
}
