
package Logica;

import Persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Estudiante implements Logica.Interface_Universidad{
    private int id;
    private String nombre;
    private int semestre;
    private float promedio;

    public Estudiante() {
    }

    public Estudiante(String nombre, int semestre, float promedio, int id) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.promedio = promedio;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getSemestre() {
        return semestre;
    }

    public float getPromedio() {
        return promedio;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
    
    
    public boolean agregarEstudiante() {
        boolean exito = false;
        ConexionBD conexion = new ConexionBD();
        String sql = "INSERT INTO prueba.estudiante (nombre, semestre, promedio) VALUES('"+ this.getNombre() 
                +"',"+ this.getSemestre() +","+ this.getPromedio()+ ");";
        //Comprobaciones para hacer el insert
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sql)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                //Si el insert se hizo adecuadamente
                int id_encontrado = this.obtenerId();
                if(id_encontrado != -1){
                   this.setId(id_encontrado);
                   System.out.println("Estudiante agregado Exitosamente!");
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
    
    public void borrarEstudiante(){
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre del estudiante a eliminar:");
        if(nom == null || nom.equals("")) {
            System.out.println("Digite un nombre valido");
            System.out.println();
        }else {
            ConexionBD conexion = new ConexionBD();
            String sql = "DELETE FROM prueba.estudiante where nombre = '" + nom + "';";
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
    
    @Override
    public int obtenerId() {
       int id_encontrado = -1; //en caso de error por defecto
       ConexionBD con = new ConexionBD();
       String sql = "SELECT e.id as id from prueba.estudiante e where e.nombre= '" + this.getNombre() + "';"; 
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
    
    public void mostrarEstudiante() {
        System.out.println("Nombre estudiante: " + this.getNombre());
        System.out.println("Semestre estudiante: " + this.getSemestre());
        System.out.println("Promedio estudiante: " + this.getPromedio());
        System.out.println(); 
    }  
}
