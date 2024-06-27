
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#ServicioServlet").on('submit', function(event) {
                event.preventDefault(); // Evita la recarga de la página

                var formData = new FormData(this);
                
                $.ajax({
                    url: '../ServicioServlet',
                    type: 'POST',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function(response) {
                        $("#message").html(response);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        $("#message").html("<p>Error al subir la imagen: " + errorThrown + "</p>");
                    }
                });
            });
        });
    </script>
    </head>
    <body>
        <div class="row mb-3">
                                <div class="col-12">
                                    <a href="../MenuAdmin.html" class="btn btn-secondary" style="background-color: #b2d8d8; color: black">Regresar</a>
                                </div>
                            </div>
        <h1>Agregar Servicios</h1>
    <form id="ServicioServlet" method="post" enctype="multipart/form-data">
        <label for="servicio">Nombre:</label>
        <input type="text" name="servicio" id="servicio" required><br><br>
        <label for="descripcion">Descripción:</label>
        <input type="text" name="descripcion" id="descripcion" required><br><br>
        <label for="precio">Precio:</label>
        <input type="text" name="precio" id="precio" required><br><br>
        <label for="estado">Estado:</label>
        <input type="text" name="estado" id="estado" required><br><br>
        <label for="file">Imagen:</label>
        <input type="file" name="file" id="file" required><br><br>
        <button type="submit">Subir</button>
    </form>
    <div id="message"></div>
    </body>
</html>
