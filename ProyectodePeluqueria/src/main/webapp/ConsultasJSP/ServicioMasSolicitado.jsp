
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!-- Agrega aquí el CSS de DataTables -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Agrega aquí el JS de DataTables -->
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
        <script src="https://parsleyjs.org/dist/parsley.min.js"></script>
    </head>
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
</style>    
<body class="imagen">
    <div class="container" >
        <center><h2>SERVICIO MAS SOLICITADO</h2></center>
        <div class="row pt-2">
            <div class="col-12">
                <div class="card m-b-20">
                    <div class="card-body">
                        <div id="aqui_tabla"></div>
                    </div>
                </div>
            </div>

            <!-- Botón de regreso -->
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
        <script src="../JsdeConsultas/ServicioMasSolicitado.js" type="text/javascript"></script>
        <script>
                                function goBack() {
                                    window.history.back();
                                }
        </script>
</body>
</html>