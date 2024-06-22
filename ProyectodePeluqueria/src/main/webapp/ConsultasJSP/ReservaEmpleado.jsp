<%-- 
    Document   : ReservaEmpleado
    Created on : 22 jun. 2024, 16:21:30
    Author     : Jonathan Flores
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>
    <h1 class="alert alert-success text-center">Reservaciones</h1>
    <div class="container">
        <!-- Panel para mostrar cuando no hay reservas -->
        <div id="no_reservas" class="alert alert-warning text-center" style="display: none;">
            Este empleado no tiene ninguna reserva pendiente.
        </div>
        <!-- Tabla de reservas -->
        <table id="miTablaPrincipal" class="table table-bordered table-striped">
            <thead style="background: cadetblue; color: white">
                <input type="hidden" id="idEmpleado" name="idEmpleado" value="${sessionScope.idEmpleado}">
                <th>Fecha</th>
                <th>Hora Inicio</th>
                <th>Hora Fin</th>
                <th>Estado</th>
                <th>Cliente</th>
                <th>Empleado</th>
                <th>Servicio</th>
            </thead>
            <tbody id="list_table_json"></tbody>
        </table>
    </div>
    <script src="../JsdeConsultas/ReservasEmpleados.js" type="text/javascript"></script>
</body>
</html>
