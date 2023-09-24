var app = angular.module('app', [])
app.controller('homework_controller', function($scope){ 
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
        $scope.Tareas.splice(index, 1)
        
    };
});