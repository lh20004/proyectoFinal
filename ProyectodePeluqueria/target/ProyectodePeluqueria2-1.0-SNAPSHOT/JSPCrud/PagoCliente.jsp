<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cargar Combos de Cliente y Servicio</title>
<!-- Incluir jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Incluir tu archivo JavaScript con la función cargarCombos -->

<script src="../JsCrud/JSPago.js"></script>
</head>
<body>
    <h2>Seleccionar Cliente y Servicio</h2>
    <form id="formulario_reserva">
        <label for="cliente">Cliente:</label>
        <select id="cliente" name="cliente">
            <option value="" disabled selected>Seleccione un cliente</option>
            <!-- Opciones de clientes cargadas dinámicamente por JavaScript -->
        </select>
        <br><br>
        <label for="servicio">Servicio:</label>
        <select id="servicio" name="servicio">
            <option value="" disabled selected>Seleccione un servicio</option>
            <!-- Opciones de servicios cargadas dinámicamente por JavaScript -->
        </select>
        <br><br>
        <input type="submit" value="Guardar Reserva">
    </form>

    <h2>Detalles de Reserva</h2>
<div id="tabla_reservas">
    <!-- Aquí se mostrará la tabla de detalles de reserva -->
</div>
</body>
</html>
