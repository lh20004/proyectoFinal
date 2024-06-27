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

                    var isValid = true;
                    var errorMessage = "";

                    // Validación del nombre
                    var nombre = $("#servicio").val();
                    if (nombre.length < 10) {
                        isValid = false;
                        errorMessage += "El nombre debe tener al menos 10 caracteres.<br>";
                    }

                    // Validación de la descripción
                    var descripcion = $("#descripcion").val();
                    if (descripcion.length < 25) {
                        isValid = false;
                        errorMessage += "La descripción debe tener al menos 25 caracteres.<br>";
                    }

                    // Validación del precio
                    var precio = $("#precio").val();
                    if (precio < 5) {
                        isValid = false;
                        errorMessage += "El precio no puede ser menor de 5.<br>";
                    }

                    // Validación del estado
                    var estado = $("#estado").val();
                    if (estado.toLowerCase() !== "disponible") {
                        isValid = false;
                        errorMessage += "El estado debe ser 'disponible'.<br>";
                    }

                    // Mostrar errores si los hay
                    if (!isValid) {
                        $("#message").html('<div class="alert alert-danger">' + errorMessage + '</div>');
                        // Limpiar los campos del formulario
                        $("#ServicioServlet")[0].reset();
                        return;
                    }

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
                <h1>Agregar Servicios</h1>
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

