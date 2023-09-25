var form = angular.module('form', [])
form.controller('form_controller', [form_controller]);

function form_controller() {
    fC = this;
    fC.nuevoFormulario = {tipo_ID:'CC'};
    fC.formularios = [];

    fC.agregarFormulario = function() {
        if (
            fC.nuevoFormulario.id &&
            fC.nuevoFormulario.tipo_ID &&
            fC.nuevoFormulario.nombre &&
            fC.nuevoFormulario.apellido &&
            fC.validarCorreo(fC.nuevoFormulario.correo) &&
            fC.nuevoFormulario.direccion &&
            fC.nuevoFormulario.telefono !== undefined
        ) {
            fC.formularios.push(fC.nuevoFormulario);
            fC.nuevoFormulario = {tipo_ID:'CC'};
        } else {
                alert('Por favor, complete todos los campos y asegurese que el correo sea valido');
        }
    };

    fC.deleteFormulario = function(index) {
        fC.formularios.splice(index, 1)
    }

    fC.validarCorreo = function(email){
        var formatoCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(formatoCorreo.test(email) === true) {
            return email;
        }
        else {
            return undefined;
        }
    }

    fC.moverArriba = function(index) {
        if (index > 0) {
            var temp =fC.formularios[index];
            fC.formularios[index] = fC.formularios[index-1];
            fC.formularios[index-1] = temp;
        }
        else {
            alert('No hay suficientes formularios para subir otra posicion')
        }
    }

    fC.moverAbajo = function(index) {
        if (index < fC.formularios.length - 1) {
            var temp = fC.formularios[index];
            fC.formularios[index] = fC.formularios[index + 1];
            fC.formularios[index + 1] = temp;
        }
        else {
            alert('No hay suficientes formularios para bajar otra posicion')
        }  
    };
};