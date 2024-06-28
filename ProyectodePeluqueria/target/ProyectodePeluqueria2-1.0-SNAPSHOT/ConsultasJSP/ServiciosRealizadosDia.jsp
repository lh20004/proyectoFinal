<%-- 
    Document   : ServiciosRealizadosDia
    Created on : 22 jun 2024, 21:48:04
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios Realizados</title>
        <!-- Inicio para que funcione class='dropdown m-b-10' -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-
                DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" 
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" 
                integrity="sha384-
                9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" 
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" 
                integrity="sha384-
                w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" 
        crossorigin="anonymous"></script>
        <!-- Fin para que funcione class='dropdown m-b-10' -->
        <!-- Inicio para que funcione sweetalert2@11 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Fin para que funcione class='Mensajes sweetalert2@11 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://parsleyjs.org/dist/parsley.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-
              rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" 
              crossorigin="anonymous">
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-
            kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" 
        crossorigin="anonymous"></script>

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
        <div class="container">
            <center><h2>TOTAL SERVICIOS REALIZADOS AL DÍA</h2>
            </center>
            <div class="row pt-2">
                <div class="col-12">
                    <div class="card m-b-20">
                        <div class="card-body">
                            <div id="aqui_tabla"></div>
                        </div>
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


    </body>
    <script src="../JsdeConsultas/ServiciosTotalesJS.js" type="text/javascript"></script>
<script>
                        function goBack() {
                            window.history.back();
                        }
        </script>
</html>