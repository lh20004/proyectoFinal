<%-- 
    Document   : Crud_Cliente
    Created on : 12 mar. 2024, 20:30:06
    Author     : Jonathan Flores
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento Clientes</title>



        <!-- Inicio para que funcione class='dropdown m-b-10' -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>

        <!-- Fin para que funcione class='dropdown m-b-10' -->

        <!-- Inicio para que funcione sweetalert2@11 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Fin para que funcione class='Mensajes sweetalert2@11 -->

        <script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

        <script src="http://parsleyjs.org/dist/parsley.js"></script>



        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <style>
            body {
                background-image: url('../imagenes/Fondo.jpg'); /* Ajusta la ruta de tu imagen de fondo */
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                font-family: Arial, sans-serif;
                
            }

            .container-fluid {
                padding: 20px;
            }

            .mini-stat {
                border-radius: 8px;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                display: flex;
                align-items: center;
            }

            .mini-stat-icon {
                background-color: #b2d8d8; /* Color verde para los íconos */
                border-radius: 50%;
                padding: 15px;
                color: white;
                display: flex;
                justify-content: center;
                align-items: center;
                width: 50px;
                height: 50px;
                margin-right: 15px;
            }

            .mini-stat-info {
                font-size: 18px;
                color: #333;
                flex-grow: 1;
            }

            .card {
                border: none;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: rgba(255, 255, 255, 0.9); /* Fondo blanco con opacidad */
            }

            .card-body {
                padding: 20px;
            }

            .card-header {
                background-color: #007BFF; /* Color azul para el encabezado de la tarjeta */
                color: white;
                border-bottom: none;
                border-radius: 8px 8px 0 0;
                padding: 15px;
            }

            .modal-header {
                background-color: wheat; /* Color azul para el encabezado del modal */
                color: white;
                border-bottom: none;
                border-radius: 8px 8px 0 0;
                padding: 15px;
            }

            .modal-content {
                border-radius: 8px;
            }

            .form-control {
                border-radius: 4px;
                border: 1px solid #ccc;
                padding: 10px;
                font-size: 16px;
            }

            .table {
                background-color: white;
                border-radius: 8px;
                overflow: hidden;
            }

            .table th, .table td {
                padding: 15px;
                border: none;
            }

            .table thead {
                background-color: #007BFF; /* Color azul para el encabezado de la tabla */
                color: white;
            }

            .table tbody tr:nth-child(odd) {
                background-color: #f9f9f9;
            }

            .table tbody tr:hover {
                background-color: #f1f1f1;
            }

            .btn-primary {
                background-color: #007BFF; /* Color azul para el botón de guardar */
                border: none;
                border-radius: 4px;
                padding: 10px 20px;
                color: white;
            }

            .btn-primary:hover {
                background-color: #0056b3; /* Color azul más oscuro al pasar el mouse */
            }

            .btn-secondary {
                background-color: #6c757d; /* Color gris para el botón de cerrar */
                border: none;
                border-radius: 4px;
                padding: 10px 20px;
                color: white;
            }

            .btn-secondary:hover {
                background-color: #5a6268; /* Color gris más oscuro al pasar el mouse */
            }

        </style>


    </head>

    <body class="fixed-left">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Start right Content here -->
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <!-- Top Bar Start -->

                    <!-- Top Bar End -->
                    <!-- ==================
                    PAGE CONTENT START
                    ================== -->
                    <div class="page-content-wrapper">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-6 col-xl-6">
                                    <div class="mini-stat clearfix bg-white">
                                        <span class="mini-stat-icon bg-blue-grey mr-0 float-right"><i class="mdi mdi-black-mesa"></i></span>
                                        <div class="mini-stat-info">
                                            <span id="Clientes_registradas" class="counter text-blue-grey">0</span>
                                            Clientes Registrados
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>


                                <div class="col-md-6 col-xl-6" id="registrar_cliente" style="cursor: pointer;">
                                    <div class="mini-stat clearfix bg-white">
                                        <span class="mini-stat-icon bg-teal mr-0 float-right"><i
                                                class="mdi mdi-account"></i></span>
                                        <div class="mini-stat-info">                                            
                                            <H3>Registrar Cliente</H3>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>


                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="card m-b-20">
                                        <div class="card-body">
                                            <div id="aqui_tabla"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- container -->
                    </div> <!-- Page content Wrapper -->
                </div> <!-- content -->

            </div>
            <!-- End Right content here -->




            <div class="modal fade" id="md_registrar_cliente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">

                            <sub>Campos marcados con * son obligatorios</sub>

                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>


                        </div>
                        <div class="modal-body">
                            <form name="formulario_registro" id="formulario_registro">
                                <input type="hidden" id="llave_persona" name="llave_persona" value="">
                                <input type="hidden" id="consultar_datos" name="consultar_datos" value="si_registro">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Nombre</label>
                                            <input type="text" autocomplete="off" name="nombrecliente" maxlength="60"
                                                   data-parsley-error-message="Campo requerido" id="nombrecliente"
                                                   class="form-control"
                                                   required placeholder="Ingrese el nombre"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Apellidos</label>
                                            <input type="text" autocomplete="off" name="apellidocliente" maxlength="35"
                                                   data-parsley-error-message="Campo requerido" id="apellidocliente"
                                                   class="form-control"
                                                   required placeholder="Ingrese el apellido "/>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Telefono</label>
                                            <input type="text" autocomplete="off" name="telefono" maxlength="40"
                                                   data-parsley-error-message="Campo requerido" id="telefono"
                                                   class="form-control"
                                                   required placeholder="Ingrese un telefono "/>
                                        </div>
                                    </div> 
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Correo</label>
                                            <input type="email" autocomplete="off" name="correo" maxlength="40"
                                                   data-parsley-error-message="Campo requerido" id="correo"
                                                   class="form-control"
                                                   required placeholder="Ingrese un correo"/>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Contraseña</label>
                                            <input type="password"  autocomplete="off" name="contrasenia" maxlength="40"
                                                   data-parsley-error-message="Campo requerido" id="contrasenia"
                                                   class="form-control"
                                                   required placeholder="Ingrese una contraseña"/>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="btn_cerrar" class="btn btn-secondary " data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary ">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>
    <script src="../JsCrud/Crud_Cliente.js"></script>
</html>


