<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="com.google.gson.Gson"%> <%--Libreria para convertir objetos java a formato JSON--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Logica.Universidad"%>
<%@page import="Logica.Facultad"%>
<%@page import="Logica.Escuela"%>
<%@page import="Logica.Estudiante"%>
<%@page contentType="application/json;charset=iso-8859-1" language="java" pageEncoding="iso-8859-1" session="true"%>

<%
    String respuesta = "{";
    
    List<String> procesos = Arrays.asList(new String[]{
        "cargarFacultades",
        "cargarEscuelas",
        "cargarEstudiantes",
        "agregarFacultad",
        "eliminarFacultad",
        "agregarEscuela",
        "eliminarEscuela",
        "agregarEstudiante",
        "eliminarEstudiante"
    });
    
    String proceso = "" + request.getParameter("proceso");
    
    if(procesos.contains(proceso)){
        respuesta += "\"ok\": true,";
        if(proceso.equals("cargarFacultades")) {
            try {
            Universidad u1 = new Universidad(1, "UIS");
            List<Facultad> listaFacultades = u1.getListaFacultades();
            respuesta += "\"" + proceso + "\": true,\"Facultades\":" + new Gson().toJson(listaFacultades);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": false,\"Facultades\":[]";
                Logger.getLogger(Universidad.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else if(proceso.equals("cargarEscuelas")) {
            try {
            Universidad u1 = new Universidad(1, "UIS");
            Facultad facuTemp = u1.getFacultadByName(request.getParameter("nombreFacultad"));
            List<Escuela> listaEscuelas = facuTemp.getListaEscuelas();
            respuesta += "\"" + proceso + "\": true,\"Escuelas\":" + new Gson().toJson(listaEscuelas);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": false,\"Escuelas\":[]";
                Logger.getLogger(Facultad.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(proceso.equals("cargarEstudiantes")) {
            try {
                Universidad u1 = new Universidad(1, "UIS");
                Facultad facuTemp = u1.getFacultadByName(request.getParameter("nombreFacultad"));
                Escuela escuelaTemp = facuTemp.getEscuelaByName(request.getParameter("nombreEscuela"));
                List<Estudiante> listaEsudiantes = escuelaTemp.getListaEstudiantes();
                respuesta += "\"" + proceso + "\": true,\"Estudiantes\":" + new Gson().toJson(listaEsudiantes);
                } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": false,\"Estudiantes\":[]";
                Logger.getLogger(Escuela.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else if(proceso.equals("agregarFacultad")) {
            String nom = request.getParameter("nomNuevaFacultad");
            Universidad u1 = new Universidad(1, "UIS");
            if(u1.agregarFacultad(nom)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if(proceso.equals("eliminarFacultad")) {
            String nom = request.getParameter("nomFacultad");
            Universidad u1 = new Universidad(1, "UIS");
            if(u1.eliminarFacultad(nom)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if(proceso.equals("agregarEscuela")) {
            String nomFacu = request.getParameter("nomFacultad");
            String nomNewEscu = request.getParameter("nomNuevaEscuela");
            Universidad u1 = new Universidad(1, "UIS");
            Facultad facuTemp = u1.getFacultadByName(nomFacu);
            if(facuTemp.agregarEscuela(nomNewEscu)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        }  else if(proceso.equals("eliminarEscuela")) {
            String nomFacu = request.getParameter("nomFacultad");
            String nomEscu = request.getParameter("nomEscuela");
            Universidad u1 = new Universidad(1, "UIS");
            Facultad facuTemp = u1.getFacultadByName(nomFacu);
            if(facuTemp.eliminarEscuela(nomEscu)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        }   else if(proceso.equals("agregarEstudiante")) {
            String nomFacu = request.getParameter("nomFacultad");
            String nomEscu = request.getParameter("nomEscuela");
            
            String nomEstu = request.getParameter("nomEstudiante");
            int semEstu = Integer.parseInt(request.getParameter("semEstudiante"));
            float promEstu = Float.parseFloat(request.getParameter("promEstudiante"));
            
            Universidad u1 = new Universidad(1, "UIS");
            Facultad facuTemp = u1.getFacultadByName(nomFacu);
            Escuela escuTemp = facuTemp.getEscuelaByName(nomEscu);
            
            if(escuTemp.agregarEstudiante(nomEstu, semEstu, promEstu)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if(proceso.equals("eliminarEstudiante")) {
            String nomFacu = request.getParameter("nomFacultad");
            String nomEscu = request.getParameter("nomEscuela");
            
            String nomEstu = request.getParameter("nomEstudiante");
            
            Universidad u1 = new Universidad(1, "UIS");
            Facultad facuTemp = u1.getFacultadByName(nomFacu);
            Escuela escuTemp = facuTemp.getEscuelaByName(nomEscu);
            
            if(escuTemp.eliminarEstudiante(nomEstu)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        }
    } else {
        respuesta += "\"ok\": false,";
        respuesta += "\"error\": \"INVALID\",";
        respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                + " son inválidos. Corrijalos y vuelva a intentar por favor.\""; 
    } 

    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>