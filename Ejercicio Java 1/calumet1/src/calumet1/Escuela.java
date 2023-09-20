
package calumet1;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Escuela {
    private String nombre;
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList();

    public Escuela() {
    }

    public Escuela(String nombre) {
        this.nombre = nombre;
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
    
    public Estudiante getEstudianteById(){
        Estudiante estu = null;
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre del estudiante a buscar:");
        boolean encontrado = false;
        int id = 0;
        for (Estudiante estudiante : this.getListaEstudiantes()){
            if (this.getListaEstudiantes().get(id).getNombre().equalsIgnoreCase(nom))
            {
                encontrado = true;
                estu = getListaEstudiantes().get(id);
                return estu;
            }
            id++;
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
                + "tiene un promedio de " + getListaEstudiantes().get(i).getPromedio());
            }
        }
        System.out.println();     
    }
    
    public void calcularPromedioEstudiantes(){
        int cantEstu = getListaEstudiantes().size();
        float suma = 0;
        float promedio = 0;
        
        for (int i=1; i<cantEstu; i++){
            suma = suma + getListaEstudiantes().get(i).getPromedio();
        }
        
        promedio = suma/cantEstu;
        
        System.out.println("El promedio de los estudiantes de la escuela '" + this.getNombre() + "' es de: "+ 
                promedio + " con " + cantEstu + " estudiantes.");
        System.out.println();   
    }
    
    public void agregarEstudiante(Estudiante estu) {
        listaEstudiantes.add(estu);
    }
    
    public void agregarEstudiante() {
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre del estudiante:");
        int sem = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el semestre del estudiante:"));
        float prom = 0;
        if(sem > 1){
            prom = Float.parseFloat(JOptionPane.showInputDialog("Por favor, ingrese el promedio del estudiante:"));
        }
        Estudiante estu = new Estudiante(nom, sem, prom);
        listaEstudiantes.add(estu);
        System.out.println("Estudiante agregado exitosamente!");
        System.out.println();
    }
    
}


