$(document).ready(function() {
    $('#formulario_ganancias').on('submit', function(event) {
        event.preventDefault(); // Evita que el formulario se envíe normalmente

        var formData = $(this).serialize(); // Serializa los datos del formulario

        $.ajax({
            type: 'POST',
            url: '../GananciasController', // Ruta relativa al servlet
            data: formData,
            dataType: 'json',
            success: function(response) {
                if (response.resultado === 'exito') {
                    mostrarResultados(response.data, response.totalGanancias);
                } else if (response.resultado === 'error') {
                    mostrarError('Error: ' + response.mensaje);
                } else if (response.resultado === 'error_sql') {
                    mostrarError('Error SQL: ' + response.error_mostrado);
                } else {
                    mostrarError('Error desconocido');
                }
            },
            error: function(xhr, status, error) {
                mostrarError('Error en la solicitud: ' + error);
            }
        });
    });
});

    function mostrarCampo(filtro) {
    document.getElementById('campo_dia').classList.remove('active');
    document.getElementById('campo_semana').classList.remove('active');
    document.getElementById('campo_mes').classList.remove('active');

    if (filtro === 'dia') {
        document.getElementById('campo_dia').classList.add('active');
        document.getElementById('consultar_datos').value = 'por_dia';
    } else if (filtro === 'semana') {
        document.getElementById('campo_semana').classList.add('active');
        document.getElementById('consultar_datos').value = 'por_semana';
    } else if (filtro === 'mes') {
        document.getElementById('campo_mes').classList.add('active');
        document.getElementById('consultar_datos').value = 'por_mes';
    }
}