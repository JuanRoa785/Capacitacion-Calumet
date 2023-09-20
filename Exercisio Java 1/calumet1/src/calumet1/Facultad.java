
package calumet1;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Facultad {
    private String nombre;
    private ArrayList<Escuela> listaEscuelas = new ArrayList();

    public Facultad() {
    }

    public Facultad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Escuela> getListaEscuelas() {
        return listaEscuelas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        for (int i=0; i<cantEscu; i++){
            getListaEscuelas().get(i).mostrarEscuela();
        }
        System.out.println();
    }
    
    public Escuela getEscuelaByid() {
        Escuela escu = null;
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre de la escuela a buscar:");
        boolean encontrado = false;
        int id = 0;
        for (Escuela escuela : this.getListaEscuelas()){
            if (this.getListaEscuelas().get(id).getNombre().equalsIgnoreCase(nom))
            {
                encontrado = true;
                escu = getListaEscuelas().get(id);
                return escu;
            }
            id++;
        }
        if(encontrado == false){
            System.out.println("No se encontro la escuela de nombre " + nom );
            System.out.println();
            escu = null;
            return escu;
        }
        return escu;
    }
    
    public void agregarEscuela(Escuela escu){
        listaEscuelas.add(escu);
    }

    
    public void agregarEscuela(){
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre de la Escuela:");
        Escuela escu = new Escuela(nom);
        listaEscuelas.add(escu);
        System.out.println("Escuela agregada exitosamente!");
        System.out.println();
    }
}
