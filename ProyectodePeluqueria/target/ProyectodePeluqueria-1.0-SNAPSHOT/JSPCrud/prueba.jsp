<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Controlador Pagos</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>Reservas Confirmadas</h1>
    <label for="clientes">Seleccionar Cliente:</label>
    <select id="clientes"></select>
    <button onclick="cargarReservas()">Cargar Reservas</button>
    
    <h2>Agregar Servicio a Reserva</h2>
    <label for="servicios">Seleccionar Servicio:</label>
    <select id="servicios"></select>
    <label for="idReserva">ID Reserva:</label>
    <input type="text" id="idReserva">
    <button onclick="agregarServicio()">Agregar Servicio</button>
    
    <h2>Reservas Confirmadas</h2>
    <table id="tablaReservas" border="1">
        <thead>
            <tr>
                <th>ID Reserva</th>
                <th>Cliente</th>
                <th>Fecha Reserva</th>
                <th>Servicio</th>
                <th>Precio</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <script>
        $(document).ready(function() {
            $.ajax({
                url: '../ControladorPagos',
                data: {action: 'cargarCombos'},
                type: 'GET',
                success: function(response) {
                    var clientes = response[0].clientes;
                    var servicios = response[0].servicios;
                    $('#clientes').html(clientes);
                    $('#servicios').html(servicios);
                }
            });
        });

        function cargarReservas() {
            var idCliente = $('#clientes').val();
            $.ajax({
                url: '../ControladorPagos',
                data: {action: 'listReservasConfirmadas', idCliente: idCliente},
                type: 'GET',
                success: function(response) {
                    var tbody = $('#tablaReservas tbody');
                    tbody.empty();
                    $.each(response, function(index, reserva) {
                        var row = '<tr>' +
                            '<td>' + reserva.idReserva + '</td>' +
                            '<td>' + reserva.Cliente + '</td>' +
                            '<td>' + reserva.fechaReserva + '</td>' +
                            '<td>' + reserva.servicio + '</td>' +
                            '<td>' + reserva.precio + '</td>' +
                            '</tr>';
                        tbody.append(row);
                    });
                }
            });
        }

        function agregarServicio() {
            var idReserva = $('#idReserva').val();
            var idServicio = $('#servicios').val();
            $.ajax({
                url: '../ControladorPagos',
                data: {
                    action: 'agregarServicio',
                    idReserva: idReserva,
                    idServicio: idServicio
                },
                type: 'POST',
                success: function(response) {
                    if (response.resultado === "exito") {
                        alert("Servicio agregado con éxito");
                        cargarReservas();
                    } else {
                        alert("Error al agregar el servicio");
                    }
                }
            });
        }
    </script>
</body>
</html>
