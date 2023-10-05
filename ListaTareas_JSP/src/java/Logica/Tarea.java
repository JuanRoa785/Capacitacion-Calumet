package Logica;

/**
 *
 * @author Juan Diego Roa Porra
 */
public class Tarea {
    private int id;
    private String contenido;

    public Tarea() {
    }

    public Tarea(int id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
