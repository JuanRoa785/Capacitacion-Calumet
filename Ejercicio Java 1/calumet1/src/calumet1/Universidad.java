
package calumet1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Universidad {
    private String nombre;
    private ArrayList<Facultad> listaFacultades = new ArrayList();

    public Universidad() {
    }

    public Universidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Facultad> getListaFacultades() {
        return listaFacultades;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public Facultad getFacutadById() {
        Facultad facu = null;
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre de la facultad a buscar:");
        boolean encontrado = false;
        int id = 0;
        
        for (Facultad facultad : this.getListaFacultades()){
            if (this.getListaFacultades().get(id).getNombre().equalsIgnoreCase(nom))
            {
                encontrado = true;
                facu = getListaFacultades().get(id);
                return facu;
            }
            id++;
        }
        if(encontrado == false){
            System.out.println("No se encontro la facultad de nombre " + nom );
            System.out.println();
            facu = null;
            return facu;
        }
        return facu;
    }
    
    public void agregarFacultad(Facultad facu) {
        listaFacultades.add(facu);
    }
    
    public void agregarFacultad() {
        String nom = JOptionPane.showInputDialog("Por favor, ingrese el nombre de la Facultad:");
        Facultad facu = new Facultad(nom);
        this.getListaFacultades().add(facu);
        System.out.println("Facultad agregada exitosamente!");
        System.out.println();
    }
}
