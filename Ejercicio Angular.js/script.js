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
}

/*function($scope){ 
    $scope.Tareas = [];
    $scope.nuevaTarea = '';

    $scope.agregarTarea = function() {
        if($scope.nuevaTarea){
            $scope.Tareas.push($scope.nuevaTarea);
            $scope.nuevaTarea = '';
        }
        else {
            alert("Digite una Tarea valida");
        }
    };

    $scope.agregarConEnter = function(event) {
        if(event.keyCode === 13) {
            $scope.agregarTarea();
        }
    };

    $scope.eliminarTarea = function(index) {
        $scope.Tareas.splice(index, 1);K
    };
}*/