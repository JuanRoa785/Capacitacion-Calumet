package Logica;

import Persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan Diego Roa Porra
 */
public class ListaTareas {

    private int id;
    private ArrayList<Tarea> listaTareas = new ArrayList();

    public ListaTareas() {
        this.setListaTareas(this.cargarTareas());
    }

    public ListaTareas(int id) {
        this.id = id;
        this.setListaTareas(this.cargarTareas());
    }

    public int getId() {
        return id;
    }

    public ArrayList<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setId(int id) {
        this.id = id;
        this.setListaTareas(this.cargarTareas());
    }

    public void setListaTareas(ArrayList<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public boolean agregarTarea(String contenido) {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        if (contenido == null || contenido.equals("")) {

        }else {
            String sql = "INSERT INTO ListaTareas.tarea (contenido) VALUES('" + contenido + "');";
            //Comprobaciones para hacer el insert
            if (conexion.setAutoCommitBD(false)) {
                if (conexion.insertarBD(sql)) {
                    conexion.commitBD();
                    conexion.cerrarConexion();
                    exito = true;
                    this.setListaTareas(this.cargarTareas());
                } else {
                    conexion.rollbackBD();
                    conexion.cerrarConexion();
                }
            } else {
                conexion.cerrarConexion();
            }
        }
        return exito;
    }

    public boolean eliminarTarea(String contenido) {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "DELETE FROM ListaTareas.tarea WHERE contenido = '" + contenido + "';";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.borrarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                exito = true;
                this.setListaTareas(this.cargarTareas());
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
            }
        } else {
            conexion.cerrarConexion();
        }
        return exito;
    }

    public ArrayList<Tarea> cargarTareas() {
        ArrayList<Tarea> tareasCargadas = new ArrayList();
        ConexionBD conexion = new ConexionBD();
        String sql = "select t.id as id, t.contenido as contenido"
                + " from ListaTareas.tarea t order by 1 asc;";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Tarea t;
            while (rs.next()) {
                t = new Tarea();
                t.setId(rs.getInt("id"));
                t.setContenido(rs.getString("contenido"));
                tareasCargadas.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return tareasCargadas;
    }

}
