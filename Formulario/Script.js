var form = angular.module('form', [])
form.controller('form_controller', function($scope){

    $scope.nuevoFormulario = {tipo_ID:'CC'};
    $scope.formularios = [];

    $scope.agregarFormulario = function() {
    if (
        $scope.nuevoFormulario.id &&
        $scope.nuevoFormulario.tipo_ID &&
        $scope.nuevoFormulario.nombre &&
        $scope.nuevoFormulario.apellido &&
        $scope.nuevoFormulario.correo &&
        $scope.nuevoFormulario.direccion &&
        $scope.nuevoFormulario.telefono !== undefined
    ) {
        $scope.formularios.push($scope.nuevoFormulario);
        $scope.nuevoFormulario = {tipo_ID:'CC'};
    } else {
            alert('Por favor, complete todos los campos antes de agregar el formulario.');
        }
    }

    $scope.deleteFormulario = function(index) {
        $scope.formularios.splice(index, 1)
    }

    $scope.moverArriba = function(index) {
        if (index > 0) {
            var temp = $scope.formularios[index];
            $scope.formularios[index] = $scope.formularios[index-1];
            $scope.formularios[index-1] = temp;
        }
        else {
            alert('No hay suficientes formularios para subir otra posicion')
        }
    }

    $scope.moverAbajo = function(index) {
        if (index < $scope.formularios.length - 1) {
            var temp = $scope.formularios[index];
            $scope.formularios[index] = $scope.formularios[index + 1];
            $scope.formularios[index + 1] = temp;
        }
        else {
            alert('No hay suficientes formularios para bajar otra posicion')
        }  
    }
});

