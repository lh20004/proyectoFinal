<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Servicio</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link rel="stylesheet" href="../css/registro.css" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <style>
            input[type="text"],
            input[type="file"] {
                background-color: #b2d8d8;
            }
        </style>
        <script>
            $(document).ready(function () {
                $("#ServicioServlet").on('submit', function (event) {
                    event.preventDefault(); // Evita la recarga de la página

                    var formData = new FormData(this);

                    $.ajax({
                        url: '../ServicioServlet',
                        type: 'POST',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (response) {
                            $("#message").html(response);
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#message").html("<p>Error al subir la imagen: " + errorThrown + "</p>");
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <div class="container">
            <div class="row mb-3">
                <div class="col-12">
                    <a href="../MenuAdmin.html" class="btn btn-secondary" style="background-color: #b2d8d8; color: black">Regresar</a>
                </div>
            </div>
            <center>
                <h1>AGREGAR SERVICIOS</h1>

            </center>
            <form id="ServicioServlet" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="servicio" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" name="servicio" id="servicio" required>
                </div>
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <input type="text" class="form-control" name="descripcion" id="descripcion" required>
                </div>
                <div class="mb-3">
                    <label for="precio" class="form-label">Precio:</label>
                    <input type="text" class="form-control" name="precio" id="precio" required>
                </div>
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <input type="text" class="form-control" name="estado" id="estado" required>
                </div>
                <div class="mb-3">
                    <label for="file" class="form-label">Imagen:</label>
                    <input type="file" class="form-control" name="file" id="file" required>
                </div>
                <button type="submit" class="btn btn-primary">Subir</button>
            </form>
            <div id="message" class="mt-3"></div>
        </div>
    </body>
</html>

