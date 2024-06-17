$(document).ready(function(){
    $.ajax({
        url: "../ReservasConsul",
        dataType: 'json',
        type: 'GET',
        cache: false,
        success: function(data){
            console.log("Datos recibidos:", data);

            if (data && Array.isArray(data) && data.length > 0) {
                var event_data = '';
                for (let value of data) {
                    event_data += '<tr>';
                    event_data += '<td>' + value.Fecha + '</td>';
                    event_data += '<td>' + value.horainicio + '</td>';
                    event_data += '<td>' + value.horafin + '</td>';
                    event_data += '<td>' + value.estado + '</td>';
                    event_data += '<td>' + value.nombrecliente + '</td>';
                    event_data += '<td>' + value.nombreempleado + '</td>';
                    event_data += '<td>' + value.servicio + '</td>';
                    event_data += '</tr>';
                }
                $("#list_table_json").html(event_data);
            } else {
                console.error("Error al procesar los datos: La respuesta no es un array válido o está vacía.");
            }
        },
        error: function(){
            console.error("Error en la solicitud AJAX.");
            alert("404 espere hasta que se cargue el archivo");
        }
    });
});
