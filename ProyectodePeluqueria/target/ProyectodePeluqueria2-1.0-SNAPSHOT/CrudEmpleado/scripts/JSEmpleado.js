var tabla = new DataTable('#taEmpledo'); // Instancia de la tabla DataTable
var empleados = [];
var selecionado;

$(function () {
    cargarDatos();
    cargarComboCargo();
    formularioVisibiliti(false);
    $('#bAgregar').click(evetAgregarEmpledo);
    $('#bCerrar').click(evetCerrarFormulario);
    $('#bEditar').click(evtActualizarEmpleado);
    $('#bGuardar').click(eventGuardar);
    $('#tTelefono').keyup(eventTelKeyup);
    $('#tDui').keyup(eventDuiKeyup);

    tabla.on('click', 'tbody tr', (e) => {
        let classList = e.currentTarget.classList;
        if (classList.contains('selected')) {
            classList.remove('selected');
        } else {
            tabla.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
            classList.add('selected');
        }
    });
});

function eventTelKeyup(evt) {
    var numeros = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', 'Backspace'];
    if (!numeros.includes(evt.key)) {
        var length = evt.target.value.length;
        var value = evt.target.value;
        evt.target.value = value.substr(0, length - 1);
    }
}

function eventDuiKeyup(evt) {
    var numeros = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', 'Backspace'];
    if (!numeros.includes(evt.key)) {
        var length = evt.target.value.length;
        var value = evt.target.value;
        evt.target.value = value.substr(0, length - 1);
    }
}

function evetAgregarEmpledo(evt) {
    formularioVisibiliti(true);
}

function evtActualizarEmpleado(evt) {
    var selectTable = tabla.row('.selected').data();
    if (selectTable != null) {
        $('#bGuardar').val('Actualizar');
        selecionado = empleados.find(data => data.getCorreo() === selectTable[5]);
        if (selecionado) {
            $.ajax({
                url: '../RegEmpleado',
                dataType: 'json',
                type: 'POST',
                data: {"opcion": 5, "password": selecionado.getClave()}
            }).done(function (json) {
                $('#cEstado').val(selecionado.getEstado());
                $('#cCargo').val(selecionado.getCargo().getIdcargo());
                if (selecionado.getCargo().getCargo() === 'Admin') {
                    $('#cCargo').prop('disabled', true);
                }
                $('#tNombre').val(selecionado.getNombre());
                $('#tApellido').val(selecionado.getApellido());
                $('#tTelefono').val(selecionado.getTelefono());
                $('#tDui').val(selecionado.getDui());
                $('#eCorreo').val(selecionado.getCorreo());
                $('#pPassword').val(json.password);
                formularioVisibiliti(true);
            }).fail(function () {
                swal.fire('Error', 'Ocurrió un error al intentar actualizar los datos', 'error');
            });
        }
    }
}

function evetCerrarFormulario(evt) {
    formularioVisibiliti(false);
    $('#fEmpleado')[0].reset();
    $('#bGuardar').val('Guardar');
    $('#cCargo').prop('disabled', false);
}

function eventGuardar(evt) {
    evt.preventDefault();
    if (validarFormulario()) {
        var datos = {
            "opcion": $('#bGuardar').val() === 'Guardar' ? 3 : 4,
            "idempleado": selecionado ? selecionado.getIdempleado() : null,
            "nombre": $('#tNombre').val(),
            "apellido": $('#tApellido').val(),
            "telefono": $('#tTelefono').val(),
            "dui": $('#tDui').val(),
            "estado": $('#cEstado').val(),
            "correo": $('#eCorreo').val(),
            "clave": $('#pPassword').val(),
            "cargo": $('#cCargo').val()
        };
        $.ajax({
            url: "../RegEmpleado",
            dataType: "json",
            type: "POST",
            data: datos
        }).done(function (json) {
            if (json.result) {
                swal.fire('Éxito', 'Se guardaron los datos correctamente', 'info');
                cargarDatos();
                formularioVisibiliti(false);
                $('#fEmpleado')[0].reset();
                $('#bGuardar').val('Guardar');
            } else {
                swal.fire('Error', 'No se pudieron guardar los datos', 'error');
            }
        }).fail(function () {
            swal.fire('Error', 'Ocurrió un error al intentar guardar los datos', 'error');
        });
    }
}

function cargarDatos() {
    $.ajax({
        url: "../RegEmpleado",
        dataType: "json",
        type: "POST",
        data: {"opcion": 1}
    }).done(function (json) {
        empleados = json.map(datos => {
            var e = new Empleado();
            e.setIdempleado(datos.idempleado);
            e.setNombre(datos.nombre);
            e.setApellido(datos.apellido);
            e.setTelefono(datos.telefono);
            e.setDui(datos.dui);
            e.setEstado(datos.estado);
            e.setCorreo(datos.correo);
            e.setClave(datos.clave);
            var c = new Cargo();
            c.setIdcargo(datos.cargo.idcargo);
            c.setCargo(datos.cargo.cargo);
            e.setCargo(c);
            return e;
        });
        cargarTabla();
    }).fail(function () {
        swal.fire('Error', 'Ocurrió un error al extraer los empleados', 'error');
    });
}

function cargarComboCargo() {
    $.ajax({
        url: "../RegEmpleado",
        dataType: "json",
        type: "POST",
        data: {"opcion": 2}
    }).done(function (json) {
        var html = json.map(datos => `<option value="${datos.idcargo}">${datos.cargo}</option>`).join('');
        $('#cCargo').html(html);
    }).fail(function () {
        swal.fire('Error', 'Ocurrió un error al intentar cargar los cargos', 'error');
    });
}

function cargarTabla() {
    tabla.clear().draw();
    empleados.forEach(function (datos) {
        tabla.row.add([
            datos.getNombre(),
            datos.getApellido(),
            datos.getTelefono(),
            datos.getDui(),
            datos.getEstado(),
            datos.getCorreo(),
            '********',
            datos.getCargo().getCargo()
        ]).draw(false);
    });
}

function formularioVisibiliti(opcion) {
    if (opcion) {
        $('.a1_1').css('z-index', '-1');
        $('.a2_1').css('display', 'block');
    } else {
        $('.a1_1').css('z-index', '1');
        $('.a2_1').css('display', 'none');
    }
}

function validarFormulario() {
    var nombre = $('#tNombre').val().trim();
    var apellido = $('#tApellido').val().trim();
    var telefono = $('#tTelefono').val().trim();
    var dui = $('#tDui').val().trim();
    var correo = $('#eCorreo').val().trim();
    var clave = $('#pPassword').val().trim();
    var estado = $('#cEstado').val().trim();
    var cargo = $('#cCargo').val().trim();

    if (nombre === '' || apellido === '' || telefono === '' || dui === '' || correo === '' || clave === '' || estado === '' || cargo === '') {
        swal.fire('Error', 'Todos los campos son obligatorios', 'error');
        return false;
    }

    // Validación del nombre
    if (nombre.length < 3 || nombre.length > 50 || !/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(nombre)) {
        swal.fire('Error', 'El nombre debe tener entre 3 y 50 caracteres y solo letras', 'error');
        return false;
    }

    // Validación del apellido
    if (apellido.length < 3 || apellido.length > 50 || !/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(apellido)) {
        swal.fire('Error', 'El apellido debe tener entre 3 y 50 caracteres y solo letras', 'error');
        return false;
    }

    // Validación del teléfono
    var telefonoPattern = /^\d{8,15}$/;
    if (!telefonoPattern.test(telefono)) {
        swal.fire('Error', 'Teléfono no válido (debe tener entre 8 y 15 dígitos)', 'error');
        return false;
    }

    // Validación del DUI
    var duiPattern = /^\d{8}-\d{1}$/;
    if (!duiPattern.test(dui)) {
        swal.fire('Error', 'DUI no válido (formato esperado: xxxxxxxx-x)', 'error');
        return false;
    }

    // Validación del correo electrónico
    var correoPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!correoPattern.test(correo)) {
        swal.fire('Error', 'Correo electrónico no válido', 'error');
        return false;
    }

    // Validación de la clave
    if (clave.length < 4 || clave.length > 50) {
        swal.fire('Error', 'La clave debe tener entre 4 y 50 caracteres', 'error');
        return false;
    }

    // Validación del estado y cargo seleccionados
    if (!estado) {
        swal.fire('Error', 'Debe seleccionar un estado', 'error');
        return false;
    }

    if (!cargo) {
        swal.fire('Error', 'Debe seleccionar un cargo', 'error');
        return false;
    }

    return true;
}

