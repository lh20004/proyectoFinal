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
        var nombre = $('#nombre').val().trim();
        var apellido = $('#apellido').val().trim();
        var telefono = $('#telefono').val().trim();
        var correo = $('#correo').val().trim();
        var clave = $('#clave').val().trim();

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
                alert('Usuario registrado con éxito.');
                limpiarCampos();
                window.location.href = '../Menu_Cliente.html';
            },
            error: function(xhr, status, error) {
                // Manejar errores de la solicitud AJAX aquí
                console.error(xhr.responseText);
                alert('Error al registrar el usuario. Por favor, inténtalo de nuevo.');
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

        // Validar que todos los campos estén completos
        if (nombre === '' || apellido === '' || telefono === '' || correo === '' || clave === '') {
            alert('Todos los campos son obligatorios');
            return false; // Evita que el formulario se envíe
        }

        // Validar formato de correo electrónico
        var correoPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!correoPattern.test(correo)) {
            alert('Ingrese un correo electrónico válido');
            return false;
        }

        // Validar longitud del teléfono (exactamente 8 dígitos)
        if (telefono.length !== 8 || isNaN(telefono)) {
            alert('El teléfono debe tener exactamente 8 dígitos numéricos');
            return false;
        }

        // Validar longitud mínima de la clave (mínimo 4 caracteres)
        if (clave.length < 4) {
            alert('La clave debe tener al menos 4 caracteres');
            return false;
        }

        return true; // Permite que el formulario se envíe
    }

    // Función para limpiar los campos del formulario después de enviarlo
    function limpiarCampos() {
        $('#nombre').val('');
        $('#apellido').val('');
        $('#telefono').val('');
        $('#correo').val('');
        $('#clave').val('');
    }

});
