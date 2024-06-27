<%-- 
    Document   : Consultas
    Created on : 15 jun. 2024, 00:01:22
    Author     : guill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link rel="stylesheet" href="../css/consultas.css" type="text/css"/>
        <title>Consultas</title>
    </head>
    <body class="fondo">
    <center>
        <div class="colordiv">
            <h1 class="display-1">
                <a href="../MenuAdmin.html" style="color: #0e2238">
                    CONSULTAS
                </a>
            </h1>

        </div>
        <div class="gallery">
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/Servicios_Realizados.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Empleados y sus servicios</h6>
                    <a href="../ConsultasJSP/ConsultaEmpleadosServicios.jsp" class="card-link">Ir a</a>
                </div>
            </div>
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/total_ganancias.jpg" />
                <div class="card-body" style="">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Ganancias del Día</h6>
                    <a href="../ConsultasJSP/GananciasHoy.jsp" class="card-link">Ir a</a>
                </div>
            </div>
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/ganancias_mensuales.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Ganancias</h6>
                    <a href="../ConsultasJSP/Ganancias.jsp" class="card-link">Ir a</a>
                </div>
            </div>
        </div>
        <div class="gallery">
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/cliente.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Pagos Realizados Por Clientes</h6>
                    <a href="../ConsultasJSP/PagosRealizados.jsp" class="card-link">Ir a</a>
                </div>
            </div>
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/empleados.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Reservaciones que tiene el Empleado</h6>
                    <a href="../ConsultasJSP/ReservaEmpleado.jsp" class="card-link">Ir a</a>
                </div>
            </div>
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/reservaciones.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Reservaciones pagadas</h6>
                    <a href="../ConsultasJSP/ReservacionesPagadas.jsp" class="card-link">Ir a</a>
                </div>
            </div>
        </div>
        <div class="gallery">
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/servicios_solicitados2.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Reservaciones</h6>
                    <a href="../ConsultasJSP/Reservas.jsp" class="card-link">Ir a</a>
                </div>
            </div>
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/servicios_solicitados2.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Servicio Mas Solicitado</h6>
                    <a href="../ConsultasJSP/ServicioMasSolicitado.jsp" class="card-link">Ir a</a>
                </div>
            </div>
            <div class="card" style="background-color: #fbd5e5">
                <img src="../imagenes/servicios_solicitados2.jpg" />
                <div class="card-body">
                    <h5 class="card-title">Consulta</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Servicios Realizados al Dia</h6>
                    <a href="../ConsultasJSP/ServiciosRealizadosDia.jsp" class="card-link">Ir a</a>
                </div>
            </div>
        </div>





    </center>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>



</body>
</html>
