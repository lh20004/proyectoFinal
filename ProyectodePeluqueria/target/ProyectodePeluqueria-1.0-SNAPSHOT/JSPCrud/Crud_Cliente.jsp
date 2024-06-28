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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://parsleyjs.org/dist/parsley.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../css/mantenimiento.css" type="text/css"/>
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
                            <div class="row mb-3">
                                <div class="col-12"class="btn btn-secondary">
                                    <a id="regresar"  class="btn btn-secondary" style="background-color: #b2d8d8; color: black">Regresar</a>
                                </div>
                            </div>
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
                            
                            
                        </div>
                        <div class="modal-body">
                            <form name="formulario_registro" id="formulario_registro">
                                <input type="hidden" id="llave_persona" name="llave_persona" value="">
                                <input type="hidden" id="consultar_datos" name="consultar_datos" value="si_registro">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="nombrecliente">Nombre</label>
                                            <input type="text" autocomplete="off" name="nombrecliente" maxlength="50"
                                                   pattern="[A-Za-z\s]{3,50}" 
                                                   data-parsley-error-message="El nombre debe contener solo letras y un minimo de 3 caracteres" 
                                                   id="nombrecliente"
                                                   class="form-control"
                                                   required placeholder="Ingrese el nombre" 
                                                   title="El nombre debe contener solo letras y un minimo de 3 caracteres"/>
                                        </div>


                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="apellidocliente">Apellidos</label>
                                            <input type="text" autocomplete="off" name="apellidocliente" maxlength="50"
                                                   pattern="[A-Za-z\s]{3,50}" 
                                                   data-parsley-error-message="El apellido debe contener solo letras y un minimo de 3 caracteres." 
                                                   id="apellidocliente"
                                                   class="form-control"
                                                   required placeholder="Ingrese el apellido" 
                                                   title="El apellido debe contener solo letras y un minimo de 3 caracteres."/>
                                        </div>

                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="telefono">Teléfono</label>
                                            <input type="text" autocomplete="off" name="telefono" maxlength="8"
                                                   pattern="[0-9]{8}"
                                                   data-parsley-error-message="El teléfono debe contener exactamente 8 dígitos." 
                                                   id="telefono"
                                                   class="form-control"
                                                   required placeholder="Ingrese un teléfono de 8 dígitos" 
                                                   title="El teléfono debe contener exactamente 8 dígitos."/>
                                        </div>

                                    </div> 
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="correo">Correo</label>
                                            <input type="email" autocomplete="off" name="correo" maxlength="50"
                                                   data-parsley-error-message="Ingrese un correo válido." 
                                                   id="correo"
                                                   class="form-control"
                                                   required placeholder="Ingrese un correo válido" 
                                                   title="Ingrese un correo válido."/>
                                        </div>

                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="contrasenia">Contraseña</label>
                                            <input type="password" autocomplete="off" name="contrasenia" maxlength="50"
                                                   pattern=".{4,50}" 
                                                   data-parsley-error-message="La contraseña debe tener entre 4 y 50 caracteres." 
                                                   id="contrasenia"
                                                   class="form-control"
                                                   required placeholder="Ingrese una contraseña" 
                                                   title="La contraseña debe tener entre 4 y 50 caracteres."/>
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
            <!-- Botón de regreso -->
            <div class="row pt-3">
                <div class="col-12">
                    <button onclick="goBack()" class="btn btn-primary">Regresar</button>
                </div>
            </div>
    </body>
     <script>
                        function goBack() {
                            window.history.back();
                        }
        </script>
    <script src="../JsCrud/Crud_Cliente.js"></script>
</html>

