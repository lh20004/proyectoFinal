<%-- 
    Document   : Registro
    Created on : 15 mar. 2024, 18:47:38
    Author     : Jonathan Flores
--%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="stylesheet" href="../css/registro.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Cliente</title>

        <script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Botón de "Volver al menú" con una flecha -->
        <div class="tools-left">
            <a href="../Menu_Cliente.html" class="back-button">&larr;</a>
        </div>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h4 class="mb-0">Registro de Cliente</h4>
                        </div>
                        <div class="card-body">
                            <form id="form1" onsubmit="return validarFormulario()">
                                <div class="form-group">
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                                </div>
                                <div class="form-group">
                                    <label for="apellido">Apellido:</label>
                                    <input type="text" class="form-control" id="apellido" name="apellido" required>
                                </div>
                                <div class="form-group">
                                    <label for="telefono">Teléfono:</label>
                                    <input type="text" class="form-control" id="telefono" name="telefono" maxlength="8" required>
                                </div>
                                <div class="form-group">
                                    <label for="correo">Correo electrónico:</label>
                                    <input type="email" class="form-control" id="correo" name="correo" required>
                                </div>
                                <div class="form-group">
                                    <label for="clave">Clave:</label>
                                    <input type="password" class="form-control" id="clave" name="clave" required>
                                </div>
                                <button type="submit" id="submit" class="btn btn-primary btn-block">Registrarse</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="./registro.js"></script>
    </body>
</html>
