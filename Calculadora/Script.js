var calculator = angular.module('calculator', []);

calculator.controller('calc_controller', [calc_controller]);

function calc_controller() {
    cC = this;
    cC.numero1 = "";
    cC.numero2 = "" ; 
    cC.operador = "";
    cC.resultado = "";
    cC.selectedOption = "p1";
    cC.pantalla = 1;

    cC.cambioPantalla = function(){
        console.log(cC.selectedOption)
        if (cC.selectedOption === "p1"){
            cC.pantalla = 1;
        }else if (cC.selectedOption === "p2") {
            cC.pantalla = 2;
        }
    };

    cC.agregarNumero = function(numero){
        if(cC.pantalla===1) {
            cC.numero1 += numero.toString();
        } else if (cC.pantalla===2) {
            cC.numero2 += numero.toString();
        }
    };

    cC.borrarUltimo = function() {
        if(cC.pantalla===1) {
            cC.numero1 = cC.numero1.slice(0, -1);
        } else if (cC.pantalla===2) {
            cC.numero2 = cC.numero2.slice(0, -1);
        }
    };

    cC.elegirOperador = function(cadena) {
        cC.operador = cadena;
    };

    cC.calcular = function() {
        var bandera = false;
        var resultado = 0;
        if(!cC.numero1  || !cC.numero2){
            alert("Llene todos los parametros");
            bandera = true;
        }
        else if (cC.operador === "+") {
            resultado = parseInt(cC.numero1) + parseInt(cC.numero2);
        } else if (cC.operador === "-") {
            resultado = parseInt(cC.numero1) - parseInt(cC.numero2);
        } else if (cC.operador === "x") {
            resultado = parseInt(cC.numero1) * parseInt(cC.numero2);
        } else if (cC.operador === "/") {
            if(parseInt(cC.numero2) === 0) {
                alert("No se puede dividir por 0");
                bandera = true;
            }
            resultado = parseFloat(cC.numero1)/parseFloat(cC.numero2);
        }
        else {
            alert("Eliga un operador");
            bandera = true;
        }

        if(!bandera){
            cC.resultado = resultado.toString();
        }
    };

    cC.reset = function() {
        cC.numero1 = "";
        cC.numero2 = "" ; 
        cC.operador = "";
        cC.selectedOption = "p1";
        cC.pantalla = 1;
        cC.resultado = "";
    };
}; 

/*function($scope) {
    $scope.numero1 = "";
    $scope.numero2 = "" ; 
    $scope.operador = "";
    $scope.selectedOption = "p1";
    $scope.pantalla = 1;
    $scope.resultado = "";

    $scope.cambioPantalla = function(){
        if ($scope.selectedOption === "p1"){
            $scope.pantalla = 1;
        }else if ($scope.selectedOption === "p2") {
            $scope.pantalla = 2;
        }
    };

    $scope.agregarNumero = function(numero){
        if($scope.pantalla===1) {
            $scope.numero1 += numero.toString();
        } else if ($scope.pantalla===2) {
            $scope.numero2 += numero.toString();
        }
    };

    $scope.borrarUltimo = function() {
        if($scope.pantalla===1) {
            $scope.numero1 = $scope.numero1.slice(0, -1);
        } else if ($scope.pantalla===2) {
            $scope.numero2 = $scope.numero2.slice(0, -1);
        }
    };

    $scope.elegirOperador = function(cadena) {
        $scope.operador = cadena;
    };

    $scope.calcular = function() {
        var bandera = false;
        var resultado = 0;
        if(!$scope.numero1  || !$scope.numero2){
            alert("Llene todos los parametros");
            bandera = true;
        }
        else if ($scope.operador === "+") {
            resultado = parseInt($scope.numero1) + parseInt($scope.numero2);
        } else if ($scope.operador === "-") {
            resultado = parseInt($scope.numero1) - parseInt($scope.numero2);
        } else if ($scope.operador === "x") {
            resultado = parseInt($scope.numero1) * parseInt($scope.numero2);
        } else if ($scope.operador === "/") {
            if(parseInt($scope.numero2) === 0) {
                alert("No se puede dividir por 0");
                bandera = true;
            }
            resultado = parseFloat($scope.numero1)/parseFloat($scope.numero2);
        }
        else {
            alert("Eliga un operador");
            bandera = true;
        }

        if(!bandera){
            $scope.resultado = resultado.toString();
        }
    };

    $scope.reset = function() {
        $scope.numero1 = "";
        $scope.numero2 = "" ; 
        $scope.operador = "";
        $scope.selectedOption = "p1";
        $scope.pantalla = 1;
        $scope.resultado = "";
    }
}); */



