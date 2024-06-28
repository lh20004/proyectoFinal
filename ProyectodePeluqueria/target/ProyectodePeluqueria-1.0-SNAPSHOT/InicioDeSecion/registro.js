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

        $.ajax({
            type: 'POST',
            url: '../RegistrarUsuarioServlet', // Ajustar la URL según la ruta de tu proyecto
            data: usuario,
            success: function(response) {
                // Manejar la respuesta del servidor aquí
                console.log(response);
                Swal.fire({
                    icon: 'success',
                    title: 'Registro exitoso',
                    text: 'Se ha registrado correctamente'
                }).then(function() {
                    limpiarCampos();
                    window.location.href = '../Menu_Cliente.html';
                });
            },
            error: function(xhr, status, error) {
                // Manejar errores de la solicitud AJAX aquí
                console.error(xhr.responseText);
                Swal.fire({
                    icon: 'error',
                    title: 'Error al registrar',
                    text: 'Error al registrarse. Por favor, inténtelo de nuevo.'
                });
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
        if (nombre === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Nombre',
                text: 'El nombre es obligatorio'
            });
            return false; // Evita que el formulario se envíe
        }

        if (apellido === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Apellido',
                text: 'El apellido es obligatorio'
            });
            return false; // Evita que el formulario se envíe
        }

        if (telefono === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Teléfono',
                text: 'El teléfono es obligatorio'
            });
            return false; // Evita que el formulario se envíe
        }

        // Validar formato de correo electrónico
        if (correo === '') {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Correo electrónico',
                text: 'El correo electrónico es obligatorio'
            });
            return false; // Evita que el formulario se envíe
        }

        // Validar que nombre y apellido no contengan caracteres incorrectos
        var letrasPattern = /^[A-Za-z\s]{3,}$/;
        if (!letrasPattern.test(nombre)) {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Nombre',
                text: 'Ingrese un nombre válido (mínimo 3 caracteres)'
            });
            return false;
        }
        
            if (!letrasPattern.test(apellido)) {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Apellido',
                text: 'Ingrese un apellido válido (mínimo 3 caracteres)'
            });
            return false;
        }
        
        
            // Validar longitud del teléfono (exactamente 8 dígitos numéricos)
        var telefonoPattern = /^\d{8}$/;
        if (!telefonoPattern.test(telefono)) {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Teléfono',
                text: 'El teléfono debe tener exactamente 8 dígitos numéricos'
            });
            return false;
        }


        var correoPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!correoPattern.test(correo)) {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Correo electrónico',
                text: 'Ingrese un correo electrónico válido'
            });
            return false;
        }

    

        // Validar longitud mínima de la clave (mínimo 4 caracteres)
        if (clave.length < 4) {
            Swal.fire({
                icon: 'error',
                title: 'Error en el campo Clave',
                text: 'La clave debe tener al menos 4 caracteres'
            });
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

$(document).ready(function() {
    $('#regresar').click(function(event) {
        event.preventDefault(); // Evita el comportamiento por defecto del enlace

        // Regresa a la página anterior en el historial del navegador
        window.history.back();
    });
});
