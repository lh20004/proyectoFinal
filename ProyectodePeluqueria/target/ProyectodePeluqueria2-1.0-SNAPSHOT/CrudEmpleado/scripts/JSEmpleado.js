/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var tabla = new DataTable('#taEmpledo');// de esta manera se crea la tabla
var empleados = [];
var selecionado;

$(function () {
    cargarDatos();
    cargarComboCargo();
    formularioVisibiliti(false);
    document.querySelector('#bAgregar').addEventListener('click', evetAgregarEmpledo, false);
    document.querySelector('#bCerrar').addEventListener('click', evetCerrarFormulario, false);
    document.querySelector('#bEditar').addEventListener('click', evtActualizarEmpleado, false);
    document.querySelector('#bGuardar').addEventListener('click', eventGuardar, false);
    document.querySelector('#tTelefono').addEventListener('keyup', eventTelKeyup, false);
    document.querySelector('#tDui').addEventListener('keyup', eventDuiKeyup, false);
});

function eventTelKeyup(evt) {
    var numeros = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', 'Backspace'];
    //a.find(a=>a=='4');
    if (numeros.find(numeros => numeros == evt.key) == null) {
        var lenght = evt.target.value.length;
        var value = evt.target.value;
        evt.target.value = value.substr(0, lenght - 1);
    }
}

function eventDuiKeyup(evt) {
    var numeros = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', 'Backspace'];
    //a.find(a=>a=='4');
    if (numeros.find(numeros => numeros == evt.key) == null) {
        var lenght = evt.target.value.length;
        var value = evt.target.value;
        evt.target.value = value.substr(0, lenght - 1);
    }
}

tabla.on('click', 'tbody tr', (e) => {
    let classList = e.currentTarget.classList;

    if (classList.contains('selected')) {
        classList.remove('selected');
    } else {
        tabla.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
        classList.add('selected');
    }
});//esta funcio realiza el marcado de la seleccion de la tabla

//tabla.row('.selected').data(); //esto me debuelbe los datos de la tabla seleccionada

function evetAgregarEmpledo(evt) {
    formularioVisibiliti(true);
}

function evtActualizarEmpleado(evt) {
    var selectTable = tabla.row('.selected').data();
    var select;
    if (selectTable != null) {
        document.querySelector('#bGuardar').value = 'Actualizar';
        empleados.forEach(function (data) {
            if (data.getCorreo() == selectTable[5]) {
                select = data;
            }
        });
        selecionado = select;
        $.ajax({
            url: '../RegEmpleado',
            dataType: 'json',
            type: "POST",
            data: {"opcion": 5, "password": select.getClave()}
        }).done(function (json) {
            for (var i = 0; i < document.querySelector('#cEstado').length; i++) {
                if (document.querySelector('#cEstado').item(i).value == select.getEstado()) {
                    document.querySelector('#cEstado').selectedIndex = i;
                }
            }
            for (var i = 0; i < document.querySelector('#cCargo').length; i++) {
                if (document.querySelector('#cCargo').item(i).value == select.getCargo().getIdcargo()) {
                    document.querySelector('#cCargo').selectedIndex = i;
                }
            }
            document.querySelector('#tNombre').value = select.getNombre();
            document.querySelector('#tApellido').value = select.getApellido();
            document.querySelector('#tTelefono').value = select.getTelefono();
            document.querySelector('#tDui').value = select.getDui();
            document.querySelector('#eCorreo').value = select.getCorreo();
            document.querySelector('#pPassword').value = json.password;
            formularioVisibiliti(true);
        }).fail(function () {
            swal.fire('Error', 'Ocurrio un error al intentar actualizar los datos');
        });
    }
}

function evetCerrarFormulario(evt) {
    formularioVisibiliti(false);
    document.querySelector('#fEmpleado').reset();
    document.querySelector('#bGuardar').value = 'Guardar';
}

function eventGuardar(evt) {
    if (document.querySelector('#fEmpleado').checkValidity()) {
        evt.preventDefault();
        if (document.querySelector('#bGuardar').value == 'Guardar') {
            var datos = {
                "opcion": 3,
                "nombre": document.querySelector('#tNombre').value,
                "apellido": document.querySelector('#tApellido').value,
                "telefono": document.querySelector('#tTelefono').value,
                "dui": document.querySelector('#tDui').value,
                "estado": document.querySelector('#cEstado').value,
                "correo": document.querySelector('#eCorreo').value,
                "clave": document.querySelector('#pPassword').value,
                "cargo": document.querySelector('#cCargo').value

            };
        } else {
            var datos = {
                "opcion": 4,
                "idempleado": selecionado.getIdempleado(),
                "nombre": document.querySelector('#tNombre').value,
                "apellido": document.querySelector('#tApellido').value,
                "telefono": document.querySelector('#tTelefono').value,
                "dui": document.querySelector('#tDui').value,
                "estado": document.querySelector('#cEstado').value,
                "correo": document.querySelector('#eCorreo').value,
                "clave": document.querySelector('#pPassword').value,
                "cargo": document.querySelector('#cCargo').value

            };
        }
        $.ajax({
            url: "../RegEmpleado",
            dataType: "json",
            type: "POST",
            data: datos
        }).done(function (json) {
            if (json.result) {
                swal.fire('Exito', 'Se guardaron los datos correctamente', 'info');
                cargarDatos();
                formularioVisibiliti(false);
                document.querySelector('#fEmpleado').reset();
                document.querySelector('#bGuardar').value = 'Guardar';
            } else {
                swal.fire('Error', 'No se pudieron guardar los datos', 'error');
            }
        }).fail(function () {
            swal.fire('Error', 'Ocurrio un error al intentar guardar los datos', 'error');
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
        empleados = [];
        json.forEach(function (datos) {
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
            empleados.push(e);
        });
        cargarTabla();
    }).fail(function () {
        swal.fire('Error', 'Ocurrio un error al extraer los empleados', 'error');
    });
}

function cargarComboCargo() {
    $.ajax({
        url: "../RegEmpleado",
        dataType: "json",
        type: "POST",
        data: {"opcion": 2}
    }).done(function (json) {
        var html = '';
        json.forEach(function (datos) {
            html += `<option value="${datos.idcargo}">${datos.cargo}</option>`;
        });
        document.querySelector('#cCargo').innerHTML = html;
    }).fail(function () {
        swal.fire('Error', 'Ocurrio un error al intentar cargar los cargos');
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
            datos.getClave(),
            datos.getCargo().getCargo()
        ]);
    });
    tabla.draw();
}

function formularioVisibiliti(opcion) {
    if (opcion) {
        document.querySelector('.a1_1').style.setProperty('z-index', '-1');
        document.querySelector('.a2_1').style.removeProperty('display');
    } else {
        document.querySelector('.a1_1').style.setProperty('z-index', '1');
        document.querySelector('.a2_1').style.setProperty('display', 'none');
    }
}