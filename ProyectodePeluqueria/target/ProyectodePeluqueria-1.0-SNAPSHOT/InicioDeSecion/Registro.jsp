<%-- 
    Document   : Registro
    Created on : 15 mar. 2024, 18:47:38
    Author     : Jonathan Flores
--%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Cliente</title>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    
    <style>
        body, html {
            height: 100%;
            margin: 0;
        }
        body {
            background-image: url('../imagenes/Fondo.jpg'); /* Ajusta la ruta de tu imagen de fondo */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
        }
        .container {
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .btn-custom {
            background-color: #81c784;
            border: none;
            color: white;
        }
        .btn-custom:hover {
            background-color: #66bb6a;
        }
        h4 {
            color: #7b1fa2;
        }
        .form-group label {
            color: #7b1fa2;
        }
        .back-button {
            display: inline-block;
            margin-bottom: 10px;
            background-color: #ffccbc;
            padding: 10px;
            border-radius: 5px;
            color: #bf360c;
            text-decoration: none;
        }
        .back-button:hover {
            background-color: #ffab91;
        }
        .tools-left {
            position: absolute;
            top: 10px;
            left: 10px;
        }
    </style>
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
