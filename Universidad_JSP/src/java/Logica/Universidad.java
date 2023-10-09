
package Logica;

import Persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Universidad implements Logica.Interface_Universidad{
    private int id;
    private String nombre;
    private ArrayList<Facultad> listaFacultades = new ArrayList();

    public Universidad() {
    }

    public Universidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.setListaFacultades(cargarFacultades());
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getId() {
        return id;
    }

    public ArrayList<Facultad> getListaFacultades() {
        return listaFacultades;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(int id) {
        this.id = id;
        this.setListaFacultades(cargarFacultades());
    }

    public void setListaFacultades(ArrayList<Facultad> listaFacultades) {
        this.listaFacultades = listaFacultades;
    }
    
    public boolean añadirUniversidad() {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.universidad (nombre) VALUES('"
                + this.getNombre()+ "');";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                //Si el insert se hizo adecuadamente
                int id_encontrado = this.obtenerId();
                if(id_encontrado != -1){
                   this.setId(id_encontrado);
                   System.out.println("Universidad agregada Exitosamente!");
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
    
    private boolean agregarFacultadToUniversidad(Facultad facu) {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.facultad_universidad (id_facultad, id_universidad) VALUES("
                + facu.getId() +"," + this.getId()+ ");";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                this.setListaFacultades(cargarFacultades()); //actualizamos la lista
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
    
    public boolean agregarFacultad(String nom) {
        boolean exito = false;
        int id=0; //el id es irrelevante en el insert porque es serial
        if(nom == null || nom.equals("")){
           System.out.println("Digite el nombre de una facultad valida");
           System.out.println();
        }
        else {
            Facultad facu = new Facultad(id,nom);
            facu.añadirFacultad(); //subimos la facultad a la DB
            //al añadirla si todo sale bien se actualiza el id automaticamente con el de la DB
            this.agregarFacultadToUniversidad(facu); //Creamos la relacion facultad_Universidad
            exito = true;
        }
        return exito;
    }
    
    public boolean eliminarFacultad(String nombre_Facultad) {
        Facultad facu = this.getFacultadByName(nombre_Facultad);
        boolean exito = false;
        if(facu != null && eliminarFacultad_Universidad(facu)) {
           ConexionBD conexion = new ConexionBD();
           String sql = "DELETE FROM prueba.facultad where id = " + facu.getId() + ";";
                if (conexion.setAutoCommitBD(false)) {
                if (conexion.borrarBD(sql)) {
                    conexion.commitBD();
                    System.out.println("Facultad eliminada con exito!");
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
    
    private boolean eliminarFacultad_Universidad(Facultad facu){
       boolean exito = false;
       if(facu != null) {
           ConexionBD conexion = new ConexionBD();
           String sql = "DELETE FROM prueba.facultad_universidad where id_facultad = " + facu.getId() 
                   + " and id_universidad = " + this.getId() + ";";
                if (conexion.setAutoCommitBD(false)) {
                if (conexion.borrarBD(sql)) {
                    conexion.commitBD();
                    exito = true;
                    this.setListaFacultades(cargarFacultades()); //actualizamos la lista
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
    
    public  ArrayList<Facultad> cargarFacultades() {
        ArrayList<Facultad> facultadesCargadas = new ArrayList();
        ConexionBD conexion = new ConexionBD();
        String sql = "select f.id as id, f.nombre as nombre\n" +
                    "from prueba.facultad_universidad fu, prueba.facultad f \n" +
                    "where fu.id_facultad = f.id \n" +
                    "and fu.id_universidad ="+ this.id +";";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Facultad f;
            while (rs.next()) {
                f = new Facultad();
                f.setId(rs.getInt("id"));
                f.setNombre(rs.getString("nombre"));
                facultadesCargadas.add(f);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return facultadesCargadas;
    }
    
    @Override
    public int obtenerId() {
       int id_encontrado = -1; //en caso de error por defecto
       ConexionBD con = new ConexionBD();
       String sql = "SELECT e.id as id from prueba.universidad u where u.nombre= '" + this.getNombre() + "';"; 
       ResultSet rs = con.consultarBD(sql);
       try {
            if(rs.next()){
                id_encontrado = rs.getInt("id");
            }   
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            
        }
        return id_encontrado;
    }
    
    
    public void mostrarUniversidad() {
        System.out.println("Nombre de la universiad: " + this.getNombre());
        int cantFacultades = getListaFacultades().size();
        System.out.println("Actualmente la Universidad esta compuesta por " + 
                cantFacultades + " Facultades. ");
        System.out.println();
    }
    
    public void mostrarFacultades(){
        int cantFacu = getListaFacultades().size();
        System.out.println("Las Facultades de la Universidad '" + this.getNombre() + "' son las siguientes: ");
        for (int i=0; i<cantFacu; i++){
            getListaFacultades().get(i).mostrarFacultad();
        }
        System.out.println();
    }
    
    public Facultad getFacultadByName(String nom) {
        Facultad facu = null;
        boolean encontrado = false;
        int id_facultad = 0;
        
        for (Facultad facultad : this.getListaFacultades()){
            if (this.getListaFacultades().get(id_facultad).getNombre().equalsIgnoreCase(nom))
            {
                encontrado = true;
                facu = getListaFacultades().get(id_facultad);
                return facu;
            }
            id_facultad++;
        }
        if(encontrado == false){
            System.out.println("No se encontro la facultad de nombre " + nom );
            System.out.println();
            facu = null;
            return facu;
        }
        return facu;
    }
}
