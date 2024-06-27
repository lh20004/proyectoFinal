$(document).ready(function () {
    // Cargar combos al cargar la página
    cargarCombos();

    // Evento para manejar cambios en el combo de servicio
    $('#servicio').on('change', function () {
        // Aquí puedes agregar lógica adicional si necesitas manejar cambios en el servicio seleccionado
    });
});

function cargarCombos() {
    var datos = { "consultar_datos": "cargarCombos" };
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../PagoServlet",
        data: datos
    }).done(function (json) {
        if (json[0].resultado === "exito") {
            $("#cliente").html('<option value="" disabled selected>Seleccione un cliente</option>' + json[0].clientes);
            $("#servicio").html('<option value="" disabled selected>Seleccione un servicio</option>' + json[0].servicios);
            console.log("Datos de clientes y servicios cargados correctamente");
        } else {
            console.log("Error cargando datos: " + json[0].resultado);
        }
    }).fail(function (xhr, status, error) {
        console.log("Error en la petición AJAX: " + error);
    });
}
