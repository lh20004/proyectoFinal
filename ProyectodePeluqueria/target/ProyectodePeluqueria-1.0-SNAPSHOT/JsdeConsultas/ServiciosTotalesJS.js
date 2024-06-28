

$(document).ready(function() {
  cargarTabla(); // Call cargarTabla function on page load

  function cargarTabla(estado = 1) {
    mostrar_cargando("procesando solicitud", "Espere mientas se procesa la información");
    var datos = {
      "consultar_datos": "si_consulta",
      "estado": estado
    };
    console.log("esta aqui");
    $.ajax({
      dataType: "json",
      method: "POST",
      url: "../ServiciosTotales",
      data: datos
    }).done(function(json) {
      Swal.close();
      console.log("datos consultados: ", json);
      if (json[0].resultado == "exito") {
        $("#aqui_tabla").empty().html(json[0].tabla);
        document.querySelector("#Servicios_registrados").textContent = json[0].cuantos;
        $("#tabla_totales").DataTable({
          "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
          }
        });
      } else {
        Swal.fire('Accion no completada', 'No pudo obtener los datos', 'error');
      }
    }).fail(function() {
      // Handle AJAX request failure
    }).always(function() {
      // Perform actions always, regardless of success or failure
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
      willClose: () => {}
    }).then((result) => {
      if (result.dismiss === Swal.DismissReason.timer) {
        console.log('I was closed by timer');
      }
    });
  }
});