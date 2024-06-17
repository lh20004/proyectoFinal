$(function() {
    // Inicialización de Parsley en el formulario
    $('#formulario_ganancias').parsley();

    // Cargar la tabla al cargar la página
    cargarTabla();

    // Manejar el evento de envío del formulario
    $(document).on("submit", "#formulario_ganancias", function(e) {
        e.preventDefault();
        mostrar_cargando("Procesando Solicitud", "Espere mientras se obtienen los datos");

        // Serializar los datos del formulario
        var datos = $(this).serialize();

        // Realizar la petición AJAX
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../GananciasController",
            data: datos
        }).done(function(json) {
            Swal.close();
            if (json.resultado == "exito") {
                Swal.fire('Éxito', 'Datos obtenidos', 'success');
                actualizarTabla(json.data); // Actualiza la tabla con los datos obtenidos
                $("#total_ganancias").text(json.totalGanancias); // Actualiza el total de ganancias
            } else {
                Swal.fire('Acción no completada', 'No se pudieron obtener los datos', 'error');
            }
        }).fail(function(xhr, status, error) {
            Swal.fire('Error', 'Hubo un error al procesar la solicitud', 'error');
        });
    });
});

function cargarTabla(estado = 1) {
    mostrar_cargando("Procesando solicitud", "Espere mientras se procesan los datos");

    // Datos para la carga inicial de la tabla
    var datos = {
        "consultar_datos": "por_dia",
        "estado": estado
    };

    // Realizar la petición AJAX
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../GananciasController",
        data: datos
    }).done(function(json) {
        Swal.close();
        if (json.resultado === "exito") {
            actualizarTabla(json.data);
            $("#total_ganancias").text(json.totalGanancias); // Actualiza el total de ganancias
        } else {
            Swal.fire('Acción no completada', "No se pueden obtener los datos", "error");
        }
    }).fail(function() {
        // Manejar el fallo de la petición AJAX si es necesario
        Swal.fire('Error', 'Hubo un error al procesar la solicitud', 'error');
    });
}

function actualizarTabla(datos) {
    var html = "";
    datos.forEach(function(item) {
        html += "<tr>";
        html += "<td>" + item.cliente + "</td>";
        html += "<td>" + item.fecha + "</td>";
        html += "<td>" + item.servicio + "</td>";
        html += "<td>" + item.total + "</td>";
        html += "</tr>";
    });
    $("#tabla_ganancias tbody").html(html);
}

function mostrar_cargando(titulo, mensaje = "") {
    Swal.fire({
        title: titulo,
        html: mensaje,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading();
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            console.log('Cerrado por temporizador');
        }
    });
}
