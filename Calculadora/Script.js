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




