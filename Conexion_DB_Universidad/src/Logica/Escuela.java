
package Logica;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import Persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Escuela implements Logica.Interface_Universidad{
    private int id;
    private String nombre;
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList();

    public Escuela() {
        
    }

    public Escuela(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.setListaEstudiantes(this.cargarEstudiantes());
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(int id) {
        this.id = id;
        this.setListaEstudiantes(this.cargarEstudiantes());
    }
    
    public void setListaEstudiantes(ArrayList<Estudiante> lista) {
        this.listaEstudiantes = lista;
    }
    
    public boolean agregarEscuela() {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.escuela (nombre) VALUES('"
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
                   System.out.println("Escuela agregada Exitosamente!");
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
    
    public void agregarEstudiante() {
        int id_estu=0; //el id es irrelevante en el insert porque es serial
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre del estudiante:");
        if(nom == null || nom.equals("")) {
            System.out.println("Digite un nombre valido");
            System.out.println();
        }else {
            int sem = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el semestre del estudiante:"));
            float prom = 0;
            if(sem > 1){
            prom = Float.parseFloat(JOptionPane.showInputDialog("Por favor, ingrese el promedio del estudiante:"));
            }
            Estudiante estu = new Estudiante(nom, sem, prom, id_estu);
            estu.agregarEstudiante(); //subimos el estudiante a la DB
            //al añadirlo si todo sale bien se actualiza el id automaticamente con el de la DB
            this.añadirEstudianteToEscuela(estu); //Creamos la relacion estudiante_Escuela
        }
        System.out.println();
    }
    
    private boolean añadirEstudianteToEscuela(Estudiante estu) {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.escuela_estudiante (id_estudiante, id_escuela) VALUES("
                + estu.getId() +"," + this.getId()+ ");";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                this.setListaEstudiantes(this.cargarEstudiantes()); //actualizamos la lista
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
    
    public void eliminarEstudiante() {
        Estudiante estu = this.getEstudianteByName();
        if(estu != null && eliminarEstudiante_Escuela(estu)) {
           ConexionBD conexion = new ConexionBD();
           String sql = "DELETE FROM prueba.estudiante where id = " + estu.getId() + ";";
                if (conexion.setAutoCommitBD(false)) {
                if (conexion.borrarBD(sql)) {
                    conexion.commitBD();
                    System.out.println("Estudiante eliminado con exito!");
                    System.out.println();
                    conexion.cerrarConexion();
                } else {
                    conexion.rollbackBD();
                    conexion.cerrarConexion();
                }
            } else {
            conexion.cerrarConexion();
            }
        }
    }
    
    private boolean eliminarEstudiante_Escuela(Estudiante estu){
       boolean exito = false;
       if(estu != null) {
           ConexionBD conexion = new ConexionBD();
           String sql = "DELETE FROM prueba.escuela_estudiante where id_estudiante = " + estu.getId() 
                   + " and id_escuela = " + this.getId() + ";";
                if (conexion.setAutoCommitBD(false)) {
                if (conexion.borrarBD(sql)) {
                    conexion.commitBD();
                    exito = true;
                    this.setListaEstudiantes(this.cargarEstudiantes()); //actualizamos la lista
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
    
/* 
    Metodo para alteriar el serial en caso de que se elimine el ultimo valor 
    (me dio pereza hacer las comprobaciones de si es el ultimo)
    
    private void alterSerial(int valor_Serial) {
        ConexionBD conexion = new ConexionBD();
        String sql = "ALTER TABLE prueba.estudiante AUTO_INCREMENT = " + valor_Serial + ";";
        if (conexion.setAutoCommitBD(false)) {
                if (conexion.actualizarBD(sql)) {
                    conexion.commitBD();
                    conexion.cerrarConexion();
                } else {
                    conexion.rollbackBD();
                    conexion.cerrarConexion();
                }
        } else {
            conexion.cerrarConexion();
        }
    }
*/
    
    @Override
    public int obtenerId() {
       int id_encontrado = -1; //en caso de error por defecto
       ConexionBD con = new ConexionBD();
       String sql = "SELECT e.id as id from prueba.escuela e where nombre= '" + this.getNombre() + "';"; 
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
    
    public  ArrayList<Estudiante> cargarEstudiantes() {
        ArrayList<Estudiante> estudiantesCargados = new ArrayList();
        ConexionBD conexion = new ConexionBD();
        String sql = "select e.id as id, e.nombre as nombre, e.semestre as semestre, e.promedio as promedio \n" +
                    "from prueba.escuela_estudiante ee, prueba.estudiante e \n" +
                    "where ee.id_estudiante = e.id \n" +
                    "and ee.id_escuela ="+ this.id +";";
        ResultSet rs = conexion.consultarBD(sql);
        try {
            Estudiante e;
            while (rs.next()) {
                e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setSemestre(rs.getInt("semestre"));
                e.setPromedio(rs.getFloat("promedio"));
                estudiantesCargados.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
        return estudiantesCargados;
    }
    
    public void mostrarEscuela(){
        System.out.println("Nombre de la escuela: " + this.getNombre());
        int cantEstu = getListaEstudiantes().size();
        System.out.println("Actualmente la escuela tiene " + cantEstu + " estudiantes. ");
        System.out.println(); 
    }
    
    public void mostrarEstudiantes(){
        int cantEstu = getListaEstudiantes().size();
        System.out.println("Los estudiantes de la escuela '" + this.getNombre() + "' son los siguientes: ");
        for (int i=0; i<cantEstu; i++){
            getListaEstudiantes().get(i).mostrarEstudiante();
        }
        System.out.println();
    }
    
    public Estudiante getEstudianteByName(){
        Estudiante estu = null;
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre del estudiante a buscar:");
        boolean encontrado = false;
        int id_estu = 0;
        for (Estudiante estudiante : this.getListaEstudiantes()){
            if (this.getListaEstudiantes().get(id_estu).getNombre().equalsIgnoreCase(nom))
            {
                encontrado = true;
                estu = getListaEstudiantes().get(id_estu);
                return estu;
            }
            id_estu++;
        }
        if(encontrado == false){
            System.out.println("No se encontro el estudiante de nombre " + nom );
            System.out.println();
            return null;
        }
        return estu;
    }
    
    public void mostrarEstudiantesPorSemestre(int semestre){
        int cantEstu = getListaEstudiantes().size();
        System.out.println("Los estudiantes de la escuela '" + this.getNombre() + "' del "+ 
                semestre + " semestre son los siguientes: ");
        for (int i=0; i<cantEstu; i++){
            if(getListaEstudiantes().get(i).getSemestre() == semestre){
                System.out.println("El estudiante "+ getListaEstudiantes().get(i).getNombre() 
                + " tiene un promedio de " + getListaEstudiantes().get(i).getPromedio());
            }
        }
        System.out.println();     
    }
    
    public void calcularPromedioEstudiantes(){
        int cantEstu = getListaEstudiantes().size();
        float suma = 0;
        float promedio = 0;
        
        for (int i=0; i<cantEstu; i++){
            if(getListaEstudiantes().get(i).getPromedio()==0.0){
                cantEstu--;
            }
            suma = suma + getListaEstudiantes().get(i).getPromedio();
        }
        promedio = suma/cantEstu;
        
        System.out.println("El promedio de los estudiantes de la escuela '" + this.getNombre() + "' es de: "+ 
                promedio + " con " + cantEstu + " estudiantes de 2do o mas semestre.");
        System.out.println();   
    }
}