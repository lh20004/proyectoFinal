<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>



        <title>Acerca de Peluquería "El Pelón"</title>
        <style>
            /* Estilos CSS */
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #b2d8d8; /* Color de fondo agregado */
            }
            #container {
                width: 80%;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            header {
                text-align: center;
                margin-bottom: 20px;
            }
            h1, h2 {
                color: #333;
                text-align: center;
            }
            section {
                margin-bottom: 30px;
                padding: 20px;
                background-color: #f9f9f9; /* Fondo de sección */
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .social-icons {
                list-style-type: none;
                padding: 0;
                text-align: center;
                margin-top: 10px;
            }
            .social-icons li {
                display: inline-block;
                margin: 0 10px;
            }
            .social-icons li img {
                width: 50px; /* Tamaño de los logos */
                height: auto;
            }
            footer {
                text-align: center;
                margin-top: 30px;
                padding-top: 10px;
                border-top: 1px solid #ccc;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <header>
                <h1>Peluquería "El Pelón"</h1>
                <div class="row pt-3">
                    <div class="col-12">
                        <button onclick="goBack()" class="btn btn-primary" style="background-color: #b2d8d8; color: black;">Regresar</button>
                    </div>
                </div>
            </header>
            <main>

                <section>
                    <h2>Historia</h2>
                    <p>Peluquería "El Pelón" abrió sus puertas en 2005 en el corazón de la ciudad, fundada por Juan Pérez con la visión de ofrecer servicios de calidad y atención personalizada a nuestros clientes.</p>
                </section>
                <section>
                    <h2>Misión</h2>
                    <p>Nuestra misión es proporcionar servicios de peluquería de primera calidad, enfocados en la satisfacción total del cliente mediante un equipo profesional y el uso de productos de alta calidad.</p>
                </section>
                <section>
                    <h2>Visión</h2>
                    <p>Nuestra visión es ser reconocidos como la peluquería líder en la región, conocida por nuestra excelencia en el servicio al cliente y por establecer tendencias en el mundo de la belleza.</p>
                </section>
                <section>
                    <h2>Contacto</h2>
                    <p>Estamos ubicados en la Calle Principal #123, Ciudad Principal, El Salvador.</p>
                    <p>Teléfono: +503 2222-3333</p>
                    <p>Email: info@elpelon.com</p>
                </section>
                <section>
                    <h2>Redes Sociales</h2>
                    <ul class="social-icons">
                        <li>
                            <img src="../imagenes/facebook.jpg" alt="Facebook">
                            <p>Facebook: Peluquería El Pelón</p>
                        </li>
                        <li>
                            <img src="../imagenes/twitter.jpg" alt="Twitter">
                            <p>Twitter: @elpelon</p>
                        </li>
                        <li>
                            <img src="../imagenes/insta.jpg" alt="Instagram">
                            <p>Instagram: @elpelon_official</p>
                        </li>
                    </ul>
                </section>
            </main>
            <footer>
                &copy; 2024 Peluquería "El Pelón". Todos los derechos reservados.
            </footer>
        </div>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
