$(document).ready(function() {

    // Evento al hacer clic en el botón de envío del formulario
    $('#submit').click(function(event) {
        // Evitar que el formulario se envíe de forma tradicional
        event.preventDefault();

        // Validar el formulario antes de enviar los datos
        if (!validarFormulario()) {
            return; // Si la validación falla, no se envía el formulario
        }

        // Obtener los datos del formulario
        var nombre = $('#nombre').val();
        var apellido = $('#apellido').val();
        var telefono = $('#telefono').val();
        var correo = $('#correo').val();
        var clave = $('#clave').val();

        // Crear un objeto con los datos del usuario
        var usuario = {
            nombre: nombre,
            apellido: apellido,
            telefono: telefono,
            correo: correo,
            clave: clave
        };

        // Hacer una solicitud AJAX para enviar los datos al servidor
        $.ajax({
            type: 'POST',
            url: '../RegistrarUsuarioServlet', // Ajustar la URL según la ruta de tu proyecto
            data: usuario,
            success: function(response) {
                // Manejar la respuesta del servidor aquí
                console.log(response);
                alert('Usuario registrado con exito.');
                limpiarCampos();
                window.location.href = '../Menu_Cliente.html';
            },
            error: function(xhr, status, error) {
                // Manejar errores de la solicitud AJAX aquí
                console.error(xhr.responseText);
                alert('Error al registrar el usuario. Por favor, intentalo de nuevo.');
            }
        });
    });

    // Función para validar el formulario antes de enviarlo
    function validarFormulario() {
        var nombre = $('#nombre').val().trim();
        var apellido = $('#apellido').val().trim();
        var telefono = $('#telefono').val().trim();
        var correo = $('#correo').val().trim();
        var clave = $('#clave').val().trim();

        // Validación para asegurarse de que todos los campos no estén vacíos
        if (nombre === '' || apellido === '' || telefono === '' || correo === '' || clave === '') {
            alert('Todos los campos son obligatorios');
            return false; // Evita que el formulario se envíe
        }
        return true; // Permite que el formulario se envíe
    }

    function limpiarCampos() {
        $('#nombre').val('');
        $('#apellido').val('');
        $('#telefono').val('');
        $('#correo').val('');
        $('#clave').val('');
    }

});

