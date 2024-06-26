var a;

$(document).ready(function () {
    a = new DataTable(document.querySelector('#tabla'));
    cargarInformacion();
});

function cargarInformacion() {
    $.ajax({
        url: "../ServicioImagenServlet",
        dataType: 'json',
        type: "POST"
    }).done(function (json) {
        if (json.result) {
            json.imagenes.forEach(function (datos) {
                console.log(datos);
                var element = document.createElement('img');
                element.id = "img" + datos.id;
                element.style = "width: 150px; height: 150px;";
                a.row.add([datos.id, datos.servicio, datos.descripcion, datos.precio, datos.estado, element]).draw();
                extraerImagen(datos.id, element.id);
            });
        }
    }).fail(function () {
        alert('Error al cargar la información');
    });
}

function extraerImagen(idImg, idObject) {
    $.ajax({
        url: "../ServicioGetImganServlet",
        type: "post",
        data: {"id": idImg},
        xhrFields: {
            responseType: 'blob'
        }
    }).done(function (data) {
        var object = document.querySelector('#' + idObject);
        console.log(data);  
        //var blob = new Blob([data], {type: 'image/jpeg'});
        
        if (data.size > 100) {
            object.src = URL.createObjectURL(data);
        }
        //console.log(blob);

    }).fail(function () {
        console.log('Error al extraer la imagen');
    })
}
