/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


$(document).ready(function () {
    cargarTabla();
});

function cargarTabla() {
    mostrar_cargando("procesando solicitud", "Espere mientas se procesa la información");
    var datos = {"consultar_datos": "si_consulta"};
    console.log(datos);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "../EmpleadosServiciosServlet",
        data: datos
    }).done(function (json) {
        Swal.close();

        var html = json[0].tabla;

        $("#aqui_tabla").empty().html(html); //viene de la jsp

        $("#tabla_historial_reservaciones").DataTable({//viene del servlet
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            }
        });
    }).fail(function () {
        Swal.fire('Error', "Ha ocurrido un error al procesar la solicitud", "error");
    });
}

function mostrar_cargando(titulo, mensaje = "") {
    Swal.fire({
        title: titulo,
        html: mensaje,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading();
        },
        willClose: () => {
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            console.log('I was closed by the timer');
        }
    });
}
