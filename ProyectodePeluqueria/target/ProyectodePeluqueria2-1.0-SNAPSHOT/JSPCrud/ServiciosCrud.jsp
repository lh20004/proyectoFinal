<%-- 
    Document   : ServiciosCrud
    Created on : 22 jun 2024, 20:51:28
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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
        <link href="../css/mantenimiento.css" rel="stylesheet">
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
                                <div class="col-12">
                                    <a href="../MenuAdmin.html" class="btn btn-secondary" style="background-color: #b2d8d8; color: black">Regresar</a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 col-xl-6">
                                    <div class="mini-stat clearfix bg-white">
                                        <span class="mini-stat-icon bg-blue-grey mr-0 float-right"><i class="mdi mdi-black-mesa"></i></span>
                                        <div class="mini-stat-info">
                                            <span  id="Servicios_registrados" class="counter text-blue-grey">0</span>
                                            <h3>Servicios Registrados</h3>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>

                                <div class="col-md-6 col-xl-6" id="registrar_servicios" style="cursor: pointer;" onclick="window.location.href = '../JSPCrud/Servicios.jsp'">
                                    <div class="mini-stat clearfix bg-white">
                                        <span class="mini-stat-icon bg-teal mr-0 float-right">
                                            <i class="mdi mdi-account"></i>
                                        </span>
                                        <div class="mini-stat-info">
                                            <h3>Registrar Servicios</h3>
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

            <div class="modal fade" id="md_registrar_servicio" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                            <label id="serviciolbl">Servicio *</label>
                                            <input type="text" autocomplete="off" name="servicio" maxlength="100" data-parsley-error-message="Campo requerido" id="servicio" class="form-control" required placeholder="Ingrese el servicio"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Descripción  *</label>
                                            <input type="text" autocomplete="off" name="descripcion" maxlength="255" id="descripcion" class="form-control" placeholder="Ingrese la descripción"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Precio *</label>
                                            <input type="number" autocomplete="off" name="precio" id="precio" class="form-control"
                                                   min="5" step="0.01"
                                                   pattern="^\d+(\.\d{1,2})?$"
                                                   title="Ingrese un número válido (mayor o igual a 5 y máximo dos decimales)" 
                                                   data-parsley-error-message="Ingrese un número válido (mayor o igual a 5 y máximo dos decimales)"
                                                   required 
                                                   placeholder="Ingrese el precio"/>
                                        </div>
                                    </div>


                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Estado *</label>
                                            <select name="estado" id="estado" class="form-control" required>
                                                <option value="disponible">Disponible</option>
                                                <option value="inactivo">Inactivo</option>
                                            </select>
                                        </div>
                                    </div>


                                    <div class="col-md-6">
                                        <div class="form-group" hidden>
                                            <input type="text" autocomplete="off" name="idservicio" maxlength="100" id="idservicio" class="form-control" placeholder="Ingrese el servicio"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" id="btn_cerrar" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- wrapper -->
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
    <script src="../JsdeConsultas/ServiciosJS.js"></script>
</html>
