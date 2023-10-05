<%@page import="Logica.ListaTareas"%>
<%@page import="Logica.Tarea"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="com.google.gson.Gson"%> <%--Libreria para convertir objetos java a formato JSON--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/json;charset=iso-8859-1" language="java" pageEncoding="iso-8859-1" session="true"%>

<%
    String respuesta = "{";

    List<String> procesos = Arrays.asList(new String[]{
        "Agregar",
        "Eliminar",
        "Listar"
    });

    String proceso = "" + request.getParameter("proceso");

    if(procesos.contains(proceso)){
        respuesta += "\"ok\": true,";
        if(proceso.equals("Agregar")) {
            String contenido = request.getParameter("contenido");
            ListaTareas lt = new ListaTareas();
            if(lt.agregarTarea(contenido)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if(proceso.equals("Eliminar")) {
            String contenido = request.getParameter("contenido");
            ListaTareas lt = new ListaTareas();
            if(lt.eliminarTarea(contenido)){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        } else if(proceso.equals("Listar")){
            try {
                ListaTareas lt = new ListaTareas();
                List<Tarea> lista = lt.cargarTareas();
                respuesta += "\"" + proceso + "\": true,\"Tareas\":" + new Gson().toJson(lista);
            } catch (Exception ex) {
                respuesta += "\"" + proceso + "\": true,\"Tareas\":[]";
                Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
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