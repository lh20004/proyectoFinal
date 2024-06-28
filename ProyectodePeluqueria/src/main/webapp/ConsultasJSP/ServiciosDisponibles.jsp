<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Listado de Servicios Disponibles</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

        <script src="../JsdeConsultas/ServiciosDisponibles.js"></script>
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
        </style>
    </head>
    <body class="imagen">

        <header class="text-center">
            <h1>Servicios Disponibles</h1>
            <div class="row pt-3">
                <div class="col-12">
                    <button onclick="goBack()" class="btn btn-primary" style="background-color: #00796b">Regresar</button>
                </div>
            </div>
        </header>


        <main class="container mt-4">
            <div class="table-responsive">
                <table id="tabla" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Servicio</th>
                            <th>Descripción</th>
                            <th>Precio</th>
                            <th>Estado</th>
                            <th>Imagen</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </main>
    </body>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</html>
