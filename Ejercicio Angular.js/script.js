var app = angular.module('app', [])
app.controller('homework_controller',[homework_controller]);

function homework_controller() {
    hC = this;
    hC.Tareas = [];

    hC.agregarTarea = function() {
        if(hC.nuevaTarea){
            hC.Tareas.push(hC.nuevaTarea);
            hC.nuevaTarea = '';
        }
        else {
            alert("Digite una Tarea valida");
        }
    };

    hC.agregarConEnter = function(event) {
        if(event.keyCode === 13) {
            hC.agregarTarea();
        }
    };

   hC.eliminarTarea = function(index) {
        hC.Tareas.splice(index, 1);
    };
};