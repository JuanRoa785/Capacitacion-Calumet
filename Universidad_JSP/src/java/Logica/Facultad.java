package Logica;

import Persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Facultad implements Logica.Interface_Universidad {

    private int id;
    private String nombre;
    private ArrayList<Escuela> listaEscuelas = new ArrayList();

    public Facultad() {
    }

    public Facultad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.setListaEscuelas(this.cargarEscuelas());
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Escuela> getListaEscuelas() {
        return listaEscuelas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaEscuelas(ArrayList<Escuela> listaEscuelas) {
        this.listaEscuelas = listaEscuelas;
    }

    public void setId(int new_id) {
        this.id = new_id;
        this.setListaEscuelas(this.cargarEscuelas());
    }

    public boolean añadirFacultad() {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.facultad (nombre) VALUES('"
                + this.getNombre() + "');";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                //Si el insert se hizo adecuadamente
                int id_encontrado = this.obtenerId();
                if (id_encontrado != -1) {
                    this.setId(id_encontrado);
                    System.out.println("Facultad agregada Exitosamente!");
                }
                exito = true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
            }
        } else {
            conexion.cerrarConexion();
        }
        return exito;
    }

    private boolean agregarEscuelaToFacultad(Escuela escu) {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.escuela_facultad (id_escuela, id_facultad) VALUES("
                + escu.getId() + "," + this.getId() + ");";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                this.setListaEscuelas(this.cargarEscuelas()); //actualizamos la lista
                conexion.cerrarConexion();
                exito = true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
            }
        } else {
            conexion.cerrarConexion();
        }
        return exito;
    }

    public boolean agregarEscuela(String nom) {
        int id = 0; //el id es irrelevante en el insert porque es serial
        boolean exito = false;
        if (nom == null || nom.equals("")) {
            System.out.println("Digite el nombre de una escuela valida");
            System.out.println();
        } else {
            Escuela escu = new Escuela(id, nom);
            escu.agregarEscuela(); //subimos la escuela a la DB
            //al añadirla si todo sale bien se actualiza el id automaticamente con el de la DB
            this.agregarEscuelaToFacultad(escu); //Creamos la relacion facultad_Escuela
            exito = true;
        }
        return exito;
    }

    public boolean eliminarEscuela(String nombre_Escuela) {
        Escuela escu = this.getEscuelaByName(nombre_Escuela);
        boolean exito = false;
        if (escu != null && eliminarEscuela_Facultad(escu)) {
            ConexionBD conexion = new ConexionBD();
            String sql = "DELETE FROM prueba.escuela where id = " + escu.getId() + ";";
            if (conexion.setAutoCommitBD(false)) {
                if (conexion.borrarBD(sql)) {
                    conexion.commitBD();
                    System.out.println("Escuela eliminada con exito!");
                    exito = true;
                    conexion.cerrarConexion();
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

    private boolean eliminarEscuela_Facultad(Escuela escu) {
        boolean exito = false;
        if (escu != null) {
            ConexionBD conexion = new ConexionBD();
            String sql = "DELETE FROM prueba.escuela_facultad where id_escuela = " + escu.getId()
                    + " and id_facultad = " + this.getId() + ";";
            if (conexion.setAutoCommitBD(false)) {
                if (conexion.borrarBD(sql)) {
                    conexion.commitBD();
                    exito = true;
                    this.setListaEscuelas(this.cargarEscuelas()); //actualizamos la lista
                    conexion.cerrarConexion();
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

    public ArrayList<Escuela> cargarEscuelas() {
        ArrayList<Escuela> escuelasCargadas = new ArrayList();
        ConexionBD conexion = new ConexionBD();
        String sql = "select e.id as id, e.nombre as nombre\n"
                + "from prueba.escuela_facultad ef, prueba.escuela e \n"
                + "where ef.id_escuela = e.id \n"
                + "and ef.id_facultad =" + this.id + ";";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Escuela e;
            while (rs.next()) {
                e = new Escuela();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                escuelasCargadas.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return escuelasCargadas;
    }

    @Override
    public int obtenerId() {
        int id_encontrado = -1; //en caso de error por defecto
        ConexionBD con = new ConexionBD();
        String sql = "SELECT e.id as id from prueba.facultad e where e.nombre= '" + this.getNombre() + "';";
        ResultSet rs = con.consultarBD(sql);
        try {
            if (rs.next()) {
                id_encontrado = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {

        }
        return id_encontrado;
    }

    public void mostrarFacultad() {
        System.out.println("Nombre de la facultad: " + this.getNombre());
        int cantEscuelas = getListaEscuelas().size();
        System.out.println("Actualmente la facultad esta compuesta por " + cantEscuelas + " Escuelas. ");
        System.out.println();
    }

    public void mostrarEscuelas() {
        int cantEscu = getListaEscuelas().size();
        System.out.println("Las Escuelas de la facultad '" + this.getNombre() + "' son las siguientes: ");
        for (int i = 0; i < cantEscu; i++) {
            getListaEscuelas().get(i).mostrarEscuela();
        }
        System.out.println();
    }

    public Escuela getEscuelaByName(String nom) {
        Escuela escu = null;
        boolean encontrado = false;
        int id_escuela = 0;
        for (Escuela escuela : this.getListaEscuelas()) {
            if (this.getListaEscuelas().get(id_escuela).getNombre().equalsIgnoreCase(nom)) {
                encontrado = true;
                escu = getListaEscuelas().get(id_escuela);
                return escu;
            }
            id_escuela++;
        }
        if (encontrado == false) {
            System.out.println("No se encontro la escuela de nombre " + nom);
            System.out.println();
            escu = null;
            return escu;
        }
        return escu;
    }
}
