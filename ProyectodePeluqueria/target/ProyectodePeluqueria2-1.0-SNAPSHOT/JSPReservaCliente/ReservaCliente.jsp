<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reservas</title>
        <!-- Incluye los estilos de DataTables y SweetAlert2 -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="./css/cssReservasCliente.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Lista de Reservas</h1>

            <!-- Botón para agregar reservación -->
            <div class="d-flex justify-content-end my-3">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAlquiler">
                    Agregar Reservación
                </button>
            </div>

            <!-- Aquí irá la tabla de reservaciones -->
            <div class="row">
                <div class="col-12">
                    <div class="card m-b-20">
                        <div class="card-body">
                            <div id="aqui_tabla"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal para agregar reservación -->
            <div class="modal fade" id="modalAlquiler" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar Reservación</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form name="formulario_reservacion" id="formulario_reservacion">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Cliente *</label>
                                            <input type="text" id="cliente" name="cliente" class="form-control" value="${sessionScope.cliente.nombre}" required readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Empleado *</label>
                                            <select id="empleado" name="empleado" class="form-control bg-light" required>
                                                <option value="" disabled selected>Seleccione un empleado</option>
                                                <!-- Las opciones se deben llenar dinámicamente con los datos de los empleados -->
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Fecha de reservación *</label>
                                            <input type="date" id="fecha_reservacion" name="fecha_reservacion" class="form-control" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Hora de inicio *</label>
                                            <input type="time" id="hora_inicio" name="hora_inicio" class="form-control" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Hora de fin *</label>
                                            <input type="time" id="hora_fin" name="hora_fin" class="form-control" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Tipo de Servicio *</label>
                                            <select id="servicio" name="servicio" class="form-control bg-light" required>
                                                <option value="" disabled selected>Seleccione un servicio</option>
                                                <!-- Las opciones se deben llenar dinámicamente con los datos de los servicios disponibles -->
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Servicios seleccionados</label>
                                            <div class="form-control bg-light listaServicios">
                                                <ul id="listaForm">
                                                    
                                                </ul>
                                            </div>
                                            <input type="button" value="Quitar" id="btnQuitar" class="btn btn-primary">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Scripts -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.6/dist/sweetalert2.all.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.6/dist/sweetalert2.min.css" rel="stylesheet">
        
        <!-- Archivo JavaScript externo -->
        <script src="../JSReservaCliente/Reservas.js" type="text/javascript"></script>


    </body> 
</html>
