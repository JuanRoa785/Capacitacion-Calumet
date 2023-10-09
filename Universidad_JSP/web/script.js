
var uni = angular.module('uni', []);
uni.controller('uni_controller', ['$http', universidad_controller]);

function universidad_controller($http) {
    uC = this;
    uC.facultades = [];
    uC.facultadesAux = [];
    uC.escuelas = [];
    uC.escuelasAux = [];
    uC.estudiantes = [];
    uC.estudianteActual = {};
    uC.estudiantesAux = [];


    uC.cargarFacultades = function () {
        var parametros = {
            proceso: 'cargarFacultades'
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            uC.facultades = res.data.Facultades;
            if (uC.facultades.length !== 0) {
                uC.facultadActual = uC.facultades[0].nombre;
                uC.facultadAEliminar = uC.facultades[0].nombre;
                uC.facultadAuxiliar = uC.facultades[0].nombre;
            } else {
                var facultadTemp = {
                    nombre: "No hay facultades"
                };
                uC.facultades.push(facultadTemp);
                uC.facultadActual = facultadTemp.nombre;
                uC.facultadAEliminar = facultadTemp.nombre;
                uC.facultadAuxiliar = facultadTemp.nombre;
            }
            uC.cargarEscuelas();
        });
    };

    uC.cargarFacultadesAux = function () {
        var parametros = {
            proceso: 'cargarFacultades'
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            uC.facultadesAux = res.data.Facultades;
            if (uC.facultadesAux.length !== 0) {
                uC.facultadAEliminar = uC.facultadesAux[0].nombre;
                uC.facultadAuxiliar = uC.facultadesAux[0].nombre;
            } else {
                var facultadTemp = {
                    nombre: "No hay facultades"
                };
                uC.facultadesAux.push(facultadTemp);
                uC.facultadAEliminar = facultadTemp.nombre;
                uC.facultadAuxiliar = facultadTemp.nombre;
            }
            uC.cargarEscuelasAux();
        });
    };

    uC.cargarEscuelas = function () {
        var parametros = {
            proceso: 'cargarEscuelas',
            nombreFacultad: uC.facultadActual
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            uC.escuelas = res.data.Escuelas;
            if (uC.escuelas.length !== 0) {
                uC.escuelaActual = uC.escuelas[0].nombre;
            } else {
                var escuelaTemp = {
                    nombre: "No hay escuelas en esta facultad"
                };
                uC.escuelas.push(escuelaTemp);
                uC.escuelaActual = escuelaTemp.nombre;
            }
            uC.cargarEstudiantes();
        });
    };

    uC.cargarEscuelasAux = function () {
        var parametros = {
            proceso: 'cargarEscuelas',
            nombreFacultad: uC.facultadAuxiliar
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            uC.escuelasAux = res.data.Escuelas;
            if (uC.escuelasAux.length !== 0) {
                uC.escuelaAuxiliar = uC.escuelasAux[0].nombre;
                uC.escuelaAEliminar = uC.escuelasAux[0].nombre;
            } else {
                var escuelaTemp = {
                    nombre: "No hay escuelas en esta facultad"
                };
                uC.escuelasAux.push(escuelaTemp);
                uC.escuelaAuxiliar = escuelaTemp.nombre;
                uC.escuelaAEliminar = escuelaTemp.nombre;
            }
            uC.cargarEstudiantesAux();
        });
    };

    uC.cargarEstudiantes = function () {
        var parametros = {
            proceso: 'cargarEstudiantes',
            nombreFacultad: uC.facultadActual,
            nombreEscuela: uC.escuelaActual
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            uC.estudiantes = res.data.Estudiantes;
            if (uC.estudiantes.length !== 0) {
                uC.estudianteActual = uC.estudiantes[0];
            } else {
                var estudianteTemp = {
                    id: -1,
                    nombre: "No hay estudiantes en esta escuela"
                };
                uC.estudiantes.push(estudianteTemp);
                uC.estudianteActual = estudianteTemp;
            }
        });
    };

    uC.cargarEstudiantesAux = function () {
        var parametros = {
            proceso: 'cargarEstudiantes',
            nombreFacultad: uC.facultadAuxiliar,
            nombreEscuela: uC.escuelaAuxiliar
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            uC.estudiantesAux = res.data.Estudiantes;
        if (uC.estudiantesAux.length !== 0) {
            uC.estudianteAEliminar = uC.estudiantesAux[0].nombre; 
        } else {
            var estudianteTemp = {
                nombre: "No hay estudiantes en esta escuela"
            };
            uC.estudiantesAux.push(estudianteTemp);
            uC.estudianteAEliminar = estudianteTemp.nombre;
        }
        });
    };

    uC.agregarFacultad = function () {
        var parametros = {
            proceso: 'agregarFacultad',
            nomNuevaFacultad: uC.NuevaFacultad
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.agregarFacultad === true) {
                    console.log("Correcto");
                    alert('Facultad añadida con exito!');
                    uC.NuevaFacultad = '';
                    uC.cargarFacultades();
                    uC.cargarFacultadesAux();
                } else {
                    console.log("Error");
                    alert('Digite un nombre valido para la facultad a agregar');
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    uC.agregarFacultadConEnter = function (event) {
        if (event.keyCode === 13) {
            uC.agregarFacultad();
        }
    };

    uC.eliminarFacultad = function () {
        var parametros = {
            proceso: 'eliminarFacultad',
            nomFacultad: uC.facultadAEliminar
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.eliminarFacultad === true) {
                    console.log("Correcto");
                    alert('Facultad eliminada con exito!');
                    uC.cargarFacultades();
                    uC.cargarFacultadesAux();
                } else {
                    console.log("Error");
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    uC.agregarEscuela = function () {
        var parametros = {
            proceso: 'agregarEscuela',
            nomFacultad: uC.facultadAuxiliar,
            nomNuevaEscuela: uC.nuevaEscuela
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.agregarEscuela === true) {
                    console.log("Correcto");
                    alert('Escuela añadida con exito!');
                    uC.nuevaEscuela = '';
                    uC.cargarFacultades();
                    uC.cargarFacultadesAux();
                } else {
                    console.log("Error");
                    alert('Digite un nombre valido para la escuela a agregar');
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    uC.agregarEscuelaConEnter = function (event) {
        if (event.keyCode === 13) {
            uC.agregarEscuela();
        }
    };

    uC.eliminarEscuela = function () {
        var parametros = {
            proceso: 'eliminarEscuela',
            nomFacultad: uC.facultadAuxiliar,
            nomEscuela: uC.escuelaAEliminar
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.eliminarEscuela === true) {
                    console.log("Correcto");
                    alert('Escuela eliminada con exito!');
                    uC.cargarFacultades();
                    uC.cargarFacultadesAux();
                } else {
                    console.log("Error");
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    uC.agregarEstudiante = function () {
        var parametros = {
            proceso: 'agregarEstudiante',
            nomFacultad: uC.facultadAuxiliar,
            nomEscuela: uC.escuelaAuxiliar,
            nomEstudiante: uC.nomNuevoEstudiante,
            semEstudiante: uC.semNuevoEstudiante,
            promEstudiante: uC.promNuevoEstudiante
        };

        if (uC.nomNuevoEstudiante !== "" && uC.nomNuevoEstudiante !== undefined && 
                uC.semNuevoEstudiante !== null && uC.semNuevoEstudiante !== undefined &&
                uC.promNuevoEstudiante !== null && uC.promNuevoEstudiante !== undefined)
        {   $http({
                method: 'POST',
                url: 'peticionesUniversidad.jsp',
                params: parametros
            }).then(function (res) {
                if (res.data.ok === true) {
                    if (res.data.agregarEstudiante === true) {
                        console.log("Correcto");
                        alert('Estudiante añadido con exito!');
                        uC.nomNuevoEstudiante = '';
                        uC.semNuevoEstudiante = null;
                        uC.promNuevoEstudiante = null;
                        uC.cargarFacultades();
                        uC.cargarFacultadesAux();
                    } else {
                        console.log("Error");
                        alert('Verifique el nombre, el semeste y/o el promedio del estudiante a agregar');
                    }
                } else {
                    alert(res.data.errorMsg);
                }
            });
        } else {
            alert('Llene todos los campos del formulario por favor!');
        }
    };

    uC.agregarEstudianteConEnter = function (event) {
        if (event.keyCode === 13) {
            uC.agregarEstudiante();
        }
    };

    uC.eliminarEstudiante = function () {
        var parametros = {
            proceso: 'eliminarEstudiante',
            nomFacultad: uC.facultadAuxiliar,
            nomEscuela: uC.escuelaAuxiliar,
            nomEstudiante: uC.estudianteAEliminar
        };

        $http({
            method: 'POST',
            url: 'peticionesUniversidad.jsp',
            params: parametros
        }).then(function (res) {
            if (res.data.ok === true) {
                if (res.data.eliminarEstudiante === true) {
                    console.log("Correcto");
                    alert('Estudiante eliminado con exito!');
                    uC.cargarFacultades();
                    uC.cargarFacultadesAux();
                } else {
                    console.log("Error");
                }
            } else {
                alert(res.data.errorMsg);
            }
        });
    };

    uC.moverArriba = function (index) {
        if (index > 0) {
            var temp = uC.estudiantes[index];
            uC.estudiantes[index] = uC.estudiantes[index - 1];
            uC.estudiantes[index - 1] = temp;
        } else {
            alert('No hay suficientes estudiantes para subir otra posicion');
        }
    };

    uC.moverAbajo = function (index) {
        if (index < uC.estudiantes.length - 1) {
            var temp = uC.estudiantes[index];
            uC.estudiantes[index] = uC.estudiantes[index + 1];
            uC.estudiantes[index + 1] = temp;
        } else {
            alert('No hay suficientes formularios para bajar otra posicion');
        }
    };

    window.onload = uC.cargarFacultades();
    window.onload = uC.cargarFacultadesAux();
};