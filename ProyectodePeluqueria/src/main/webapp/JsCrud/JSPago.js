/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
var clientes = [];
var servicios = [];
var extras = [];

var tabla = new DataTable(document.querySelector('#tabla'));

var totalPagar = 0;


$(function () {
    cargarComboCliente();
    cargarComboServicio();
    document.querySelector('#cClientes').addEventListener('change', evtComboCliente, false);
    document.querySelector('#cServicios').addEventListener('change', evtComboServicio, false);
    document.querySelector('#btnPagar').addEventListener('click', evtBtnPagar, false);
});

function evtBtnPagar(evt) {
    var fecha = new Date();
    var idCliente = document.querySelector('#cClientes').value;
    var cliente = clientes.find(clientes => clientes.getIdCliente() == idCliente);
    var idReservas = [];
    var idExtas = [];
    cliente.getReserva().forEach(function (reservas) {
        var idServicio = [];
        reservas.servicios.forEach(function (servicio) {
            idServicio.push(servicio.getIdServicio());
        });
        idReservas.push({"idreserva": reservas.getIdReserva(), "idservicios": idServicio, "idcliente": idCliente});
    });

    extras.forEach(function (servicio) {
        var datas = {"idreserva": cliente.getReserva()[cliente.getReserva().length - 1].getIdReserva(), "idservicio": servicio.getIdServicio()};
        idExtas.push(datas);
    });
    var datos = {"opcion": 4, "total": totalPagar, "fecha": fecha.getTime(), "idreservas": JSON.stringify(idReservas), "extras": JSON.stringify(idExtas)};
    $.ajax({
        url: "../ServiciosPago",
        dataType: "json",
        type: "POST",
        data: datos
    }).done(function (json) {
        if (json.result) {
            cargarServiciosReservados();
            cargarComboCliente();
        } else {
            swal.fire('Error', 'Ocurrio un error en el proceso de pago', 'error');
        }
    }).fail(function () {
        swal.fire('Error', 'Ocurrio un error al tratar de realizar el pago', 'error');
    });
}

function evtComboCliente(evt) {
    cargarServiciosReservados();
}

function evtComboServicio(evt) {
    var index = document.querySelector('#cServicios').value;
    var servicio = servicios.find(servicios => servicios.getIdServicio() == index);
    var indexCliente = document.querySelector('#cClientes').value
    var cliente = clientes.find(clientes => clientes.getIdCliente() == indexCliente);
    var id = cliente.getReserva()[cliente.getReserva().length - 1].getIdReserva();
    var fecha = new Date();
    extras.push(servicio);
    cliente.getReserva()[cliente.getReserva().length - 1].addServicio(servicio)
    tabla.row.add([
        id,
        fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900),
        servicio.getServicio(),
        servicio.getPrecio()
    ]).draw();
    totalPagar += servicio.getPrecio();
    document.querySelector('#lTotal').innerHTML = 'Total: ' + totalPagar;
}

function cargarComboServicio() {
    $.ajax({
        url: "../ServiciosPago",
        dataType: "json",
        type: "POST",
        data: {"opcion": 2}
    }).done(function (json) {
        if (json.result) {
            var object = document.querySelector('#cServicios');
            var html = '';
            servicios = [];
            json.servicios.forEach(function (data) {
                var servicio = new Servicio();
                servicio.setIdServicio(data.id);
                servicio.setServicio(data.servicio);
                servicio.setPrecio(data.precio);
                servicios.push(servicio);
                html += `<option value="${data.id}">${data.servicio}</option>`;
            });
            object.innerHTML = html;
        } else {
            swal.fire('Error', 'Ocurrio un error al intentar procesar los servicios', 'error');
        }
    }).fail(function () {
        swal.fire('Error', 'Ocurrio un error al tratar de cargar los servicios', 'error');
    });
}

function cargarComboCliente() {
    $.ajax({
        url: "../ServiciosPago",
        dataType: 'json',
        type: "POST",
        data: {"opcion": 1}
    }).done(function (json) {
        if (json.result) {
            var object = document.querySelector('#cClientes');
            var html = '';
            clientes = [];
            json.clientes.forEach(function (data) {
                var cliente = new Cliente();
                cliente.setIdCliente(data.id);
                cliente.setNombre(data.nombre);
                clientes.push(cliente);
                html += `<option value="${data.id}">${data.nombre}</option>`;
            });
            object.innerHTML = html;
            cargarServiciosReservados();
        } else {
            swal.fire('Error', 'Ocurrio un error al procesar los clientes', 'error');
        }
    }).fail(function () {
        swal.fire('Erro', 'Ocurrio un erro al buscar los clientes', 'error');
    });
}

function cargarServiciosReservados() {
    var id = document.querySelector('#cClientes').value||-1;
    if (id>=0) {
        $.ajax({
            url: "../ServiciosPago",
            dataType: 'json',
            type: "POST",
            data: {"opcion": 3, "id": id}
        }).done(function (json) {
            var object = document.querySelector('#cClientes');
            var cliente = clientes.find(clientes => clientes.getIdCliente() == object.value);
            cliente.setReserva([]);
            var tmpReserva = new Reserva();
            tmpReserva.setIdReserva(json.idreserva);
            tmpReserva.setFecha(new Date(json.fecha));
            json.servicios.forEach(function (data) {
                var servicio = new Servicio();
                servicio.setIdServicio(data.id);
                servicio.setServicio(data.servicio);
                servicio.setPrecio(data.precio);
                tmpReserva.addServicio(servicio);
            });
            cliente.addReserva(tmpReserva);
            cargarTabla();
        }).fail(function () {
            swal.fire('Error', 'Ocurrio un error al intentar cargar las reservas', 'error');
        });
    }
}

function cargarTabla() {
    var object = document.querySelector('#cClientes');
    var cliente = clientes.find(clientes => clientes.getIdCliente() == object.value);
    tabla.clear().draw();
    totalPagar = 0;
    cliente.getReserva().forEach(function (reserva) {
        reserva.getSetvicio().forEach(function (servicio) {
            tabla.row.add([
                reserva.getIdReserva(),
                reserva.getFecha().getDate() + "/" + (reserva.getFecha().getMonth() + 1) + "/" + (reserva.getFecha().getYear() + 1900),
                servicio.getServicio(),
                servicio.getPrecio()
            ]).draw();
            totalPagar += servicio.getPrecio();
        });
    });
    document.querySelector('#lTotal').innerHTML = 'Total: ' + totalPagar;
}