
package calumet1;

public class Estudiante {
    private String nombre;
    private int semestre;
    private float promedio;

    public Estudiante() {
    }

    public Estudiante(String nombre, int semestre, float promedio) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.promedio = promedio;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
    
    public void mostrarEstudiante() {
        System.out.println("Nombre estudiante: " + this.getNombre());
        System.out.println("Semestre estudiante: " + this.getSemestre());
        System.out.println("Promedio estudiante: " + this.getPromedio());
        System.out.println(); 
    }
}
