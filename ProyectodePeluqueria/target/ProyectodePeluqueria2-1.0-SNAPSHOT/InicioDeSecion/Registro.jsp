<%--
Document : Registro
Created on : 15 mar. 2024, 18:47:38
Author : Jonathan Flores
--%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="stylesheet" href="../css/registro.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Cliente</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Bot�n de "Volver al men�" con una flecha -->
        <div class="tools-left">
            <a href="../Menu_Cliente.html" class="back-button">Regresar</a>
        </div>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card" style="background-color: #b2d8d8">
                        <div class="card-header bg-custom text-white" style="background-color: #b2d8d8">
                            <center>
                                <h4 class="mb-0" style="color: black;">Registro de Cliente</h4>
                            </center>
                        </div>
                        <div class="card-body">
                            <form id="form1" onsubmit="return validarFormulario()" style="background-color: #b2d8d8">
                                <div class="form-group">
                                    <label for="nombre" style="color: black">Nombre:</label>
                                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                                </div>
                                <div class="form-group">
                                    <label for="apellido" style="color: black">Apellido:</label>
                                    <input type="text" class="form-control" id="apellido" name="apellido" required>
                                </div>
                                <div class="form-group">
                                    <label for="telefono" style="color: black">Tel�fono:</label>
                                    <input type="text" class="form-control" id="telefono" name="telefono" maxlength="8" required>
                                </div>
                                <div class="form-group">
                                    <label for="correo" style="color: black">Correo electr�nico:</label>
                                    <input type="email" class="form-control" id="correo" name="correo" required>
                                </div>
                                <div class="form-group">
                                    <label for="clave" style="color: black">Clave:</label>
                                    <input type="password" class="form-control" id="clave" name="clave" required>
                                </div>
                                <div>
                                    <center>
                                        <button type="submit" id="submit" class="btn btn-custom btn-block">Registrarse</button>
                                    </center>
                                </div>    
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./registro.js"></script>
    </body>
</html>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="stylesheet" href="../css/registro.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Cliente</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>