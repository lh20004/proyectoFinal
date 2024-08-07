<%-- 
    Document   : Ganacias
    Created on : 17 jun. 2024, 00:07:51
    Author     : Jonathan Flores
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Ganancias</title>
    
    <!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    
    
    <!-- Custom CSS for styling -->
    <style>
        body {
            background-color: #f8bbd0;
            font-family: Arial, sans-serif;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .btn-custom {
            background-color: #81c784;
            border: none;
            color: white;
        }
        .btn-custom:hover {
            background-color: #66bb6a;
        }
        h1 {
            color: #7b1fa2;
        }
        #resultados h3 {
            color: #388e3c;
            background-color: #e0f2f1;
            padding: 10px;
            border-radius: 8px;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table th, table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        table th {
            background-color: #e0f2f1;
            color: #00796b;
        }
        table td {
            background-color: #f1f8e9;
        }
        .form-group label {
            color: #7b1fa2;
        }
        .filter-buttons {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }
        .filter-buttons button {
            background-color: #b2dfdb;
            border: none;
            padding: 10px;
            border-radius: 5px;
            color: #004d40;
        }
        .filter-buttons button:hover {
            background-color: #80cbc4;
        }
        .back-button {
            display: inline-block;
            margin-bottom: 10px;
            background-color: #ffccbc;
            padding: 10px;
            border-radius: 5px;
            color: #bf360c;
            text-decoration: none;
        }
        .back-button:hover {
            background-color: #ffab91;
        }
        .tools-left {
            position: absolute;
            top: 10px;
            left: 10px;
        }
        .tools-right {
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .form-group {
            display: none;
        }
        .form-group.active {
            display: block;
        }
        .image{
            background-image: url("../imagenes/Fondo.jpg");
        }
    </style>
</head>
<body class="image">
    <div class="container">
        <h1 class="text-center">GANANCIAS POR DÍA, SEMANA Y MES</h1>
        <div class="tools-left">
            <a id="regresar" class="back-button">←</a>
        </div>
       
        <div class="tools-right">
            <span></span>
        </div>
        
        <div class="filter-buttons">
            <button onclick="mostrarCampo('dia')">Por Día</button>
            <button onclick="mostrarCampo('semana')">Por Semana</button>
            <button onclick="mostrarCampo('mes')">Por Mes</button>
        </div>
        <form id="formulario_ganancias" action="GananciasController" method="post">
            <div id="campo_dia" class="form-group">
                <label for="fecha">Fecha</label>
                <input type="date" id="fecha" name="fecha" class="form-control">
            </div>
            <div id="campo_semana" class="form-group">
                <label for="semana">Semana</label>
                <input type="week" id="semana" name="semana" class="form-control">
            </div>
            <div id="campo_mes" class="form-group">
                <label for="mes">Mes</label>
                <input type="month" id="mes" name="mes" class="form-control">
            </div>
            <input type="hidden" id="consultar_datos" name="consultar_datos">
            <button type="submit" class="btn btn-custom">Consultar</button>
        </form>
        <br>
        <div id="resultados">
            <h3>Total Ganancias: <span id="total_ganancias">0</span></h3>
            <table id="tabla_ganancias">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Fecha</th>
                        <th>Servicio</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Rows will be added here dynamically -->
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    
    <!-- Custom JS -->
    <script src="../JsdeConsultas/Ganancias.js"></script>
</body>
</html>
