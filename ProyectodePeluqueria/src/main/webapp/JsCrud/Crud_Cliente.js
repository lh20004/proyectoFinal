 $(document).ready(function() {
    $('#formulario_registro').parsley(); // Inicializa Parsley para validación de formulario
    
    // Cargar tabla inicialmente
    cargarTabla();
    
    // Abrir modal para registrar nuevo cliente
    $(document).on("click", "#registrar_cliente", function(e) {
        e.preventDefault();
        $("#formulario_registro").trigger("reset"); // Limpiar formulario
        $("#llave_persona").val(""); // Limpiar campo oculto de ID de persona
        $("#consultar_datos").val("si_registro"); // Establecer modo de registro
        $("#md_registrar_cliente").modal("show"); // Mostrar modal
    });
    
    // Enviar formulario de registro
    $(document).on("submit", "#formulario_registro", function(e) {
        e.preventDefault();
        mostrar_cargando("Procesando Solicitud", "Espere mientras se almacenan los datos");
        
        // Serializar datos del formulario
        var datos = $(this).serialize();
        
        // Realizar petición AJAX
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function(json) {
            Swal.close();
            if (json[0].resultado === "exito") {
                Swal.fire('Éxito', 'Cliente Registrado', 'success');
                $("#md_registrar_cliente").modal("hide");
                cargarTabla(); // Actualizar tabla después de registro
            } else {
                Swal.fire('Acción no completada', 'No se pudo registrar el cliente', 'error');
            }
        }).fail(function(xhr, status, error) {
            Swal.fire('Error', 'Hubo un error al procesar la solicitud', 'error');
        });
    });
    
    // Cargar datos para editar cliente
    $(document).on("click", ".btn_editar", function(e) {
        e.preventDefault();
        mostrar_cargando("Espere", "Obteniendo datos");
        
        var id = $(this).attr("data-id");
        
        // Preparar datos para la consulta
        var datos = {
            "consultar_datos": "si_cliente_especifico",
            "id": id
        };
        
        // Realizar petición AJAX para obtener datos del cliente
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function(json) {
            if (json[0].resultado === "exito") {
                $("#formulario_registro").trigger("reset"); // Limpiar formulario
                $("#llave_persona").val(json[0].id_persona); // Establecer ID de persona
                $("#consultar_datos").val("si_actualizalo"); // Establecer modo de actualización
                $("#nombrecliente").val(json[0].nombrecliente); // Establecer nombre
                $("#apellidocliente").val(json[0].apellidocliente); // Establecer apellidos
                $("#telefono").val(json[0].telefono); // Establecer teléfono
                $("#correo").val(json[0].correo); // Establecer correo
                $("#contrasenia").val(json[0].contrasenia); // Establecer contraseña
                
                $("#md_registrar_cliente").modal("show"); // Mostrar modal de registro
            }
        }).fail(function() {
            // Manejar errores
        }).always(function() {
            // Realizar acciones adicionales si es necesario
        });
    });
    
    // Cerrar modal de registro de cliente
    $(document).on("click", "#btn_cerrar, #close", function(e) {
        e.preventDefault();
        $("#md_registrar_cliente").modal("hide"); // Ocultar modal
    });
    
    // Función para cargar tabla de clientes
    function cargarTabla(estado = 1) {
        mostrar_cargando("Procesando Solicitud", "Espere mientras se procesa la información");
        
        var datos = {
            "consultar_datos": "si_consulta",
            "estado": estado
        };
        
        // Realizar petición AJAX para cargar tabla
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "../RegCliente",
            data: datos
        }).done(function(json) {
            Swal.close();
            if (json[0].resultado === "exito") {
                $("#aqui_tabla").empty().html(json[0].tabla); // Actualizar contenido de tabla
                $("#Clientes_registradas").text(json[0].cuantos); // Actualizar contador de clientes
                $("#tabla_clientes").DataTable({
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                    }
                });
            } else {
                Swal.fire('Acción no completada', 'No se puede registrar el cliente', 'error');
            }
        }).fail(function() {
            // Manejar errores
        }).always(function() {
            // Realizar acciones adicionales si es necesario
        });
    }
    
    // Función para mostrar mensaje de carga
    function mostrar_cargando(titulo, mensaje = "") {
        Swal.fire({
            title: titulo,
            html: mensaje,
            timer: 2000,
            timerProgessBar: true,
            didOpen: () => {
                Swal.showLoading();
            }
        }).then((result) => {
            if (result.dismiss === Swal.DismissReason.timer) {
                console.log('I was closed by timer');
            }
        });
    }
});
