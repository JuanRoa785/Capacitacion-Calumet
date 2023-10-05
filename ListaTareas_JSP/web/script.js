
var app = angular.module('app', [])
app.controller('homework_controller', ['$http', homework_controller]);

function homework_controller($http) {
    hC = this;

    hC.Tareas = [];

    hC.agregarTarea = function () {
        var parametros = {
            proceso: 'Agregar',
            contenido: hC.nuevaTarea
        };

        $http({
            method: 'POST',
            url: 'peticionesTareas.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.Agregar === true) {
                    console.log("Correcto");
                    window.location.reload();
                } else {
                    console.log("Error");
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    hC.agregarConEnter = function (event) {
        if (event.keyCode === 13) {
            hC.agregarTarea();
        }
    };

    hC.listarTareas = function () {
        var parametros = {
            proceso: 'Listar'
        };
        $http({
            method: 'POST',
            url: 'peticionesTareas.jsp',
            params: parametros
        }).then(function (res) {
            hC.Tareas = res.data.Tareas;
        });
    };

    hC.eliminarTarea = function (index) {
        var parametros = {
            proceso: 'Eliminar',
            contenido: hC.Tareas[index].contenido
        };

        $http({
            method: 'POST',
            url: 'peticionesTareas.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.Eliminar === true) {
                    console.log("Correcto");
                    window.location.reload();
                } else {
                    console.log("Error");
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    window.onload = hC.listarTareas;
}
;


