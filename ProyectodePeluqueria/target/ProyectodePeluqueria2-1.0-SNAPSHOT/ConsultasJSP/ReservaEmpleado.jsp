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
        <title>Reservaciones de Empleados</title>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </head>
    <style>
        body {
            background-color: #eaf6f6;
            color: black; /* Texto en color negro */
        }
        header {
            background-color: #b2d8d8;
            color: black;
            padding: 1rem 0;
            margin-bottom: 1rem;
        }
        header h1 {
            margin: 0;
        }
        table thead {
            background-color: #b2d8d8;
            color: black;
        }
        table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        table tbody tr:hover {
            background-color: #d9f2f2;
        }
        .table-responsive {
            background-color: #fff;
            padding: 1rem;
            border-radius: 0.5rem;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .imagen{
            background-image: url("../imagenes/Fondo.jpg");
        }
        .container{
            background-color: white;
        }
    </style>
    <body class="imagen">
    <center><h2>RESERVACIÓN QUE TIENE EL EMPLEADO</h2></center>
        <div class="container">
            <!-- Panel para mostrar cuando no hay reservas -->
            <div id="no_reservas" class="alert alert-warning text-center" style="display: none;">
                Este empleado no tiene ninguna reserva pendiente.
            </div>
            <!-- Tabla de reservas -->
            <table id="miTablaPrincipal" class="table table-bordered table-striped">
                <thead>
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
        <div class="row pt-3">
            <div class="col-12">
                <button onclick="goBack()" class="btn btn-primary">Regresar</button>
            </div>
        </div>
    </div>  
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.6/dist/sweetalert2.all.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.6/dist/sweetalert2.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
                    function goBack() {
                        window.history.back();
                    }
    </script>
</body>
</html>
