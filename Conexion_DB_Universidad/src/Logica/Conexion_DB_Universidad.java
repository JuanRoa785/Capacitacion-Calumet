
package Logica;

import Persistencia.ConexionBD;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Diego Roa Porra
 */
public class Conexion_DB_Universidad {

    public static void main(String[] args) {
        Universidad u1 = new Universidad(1, "UIS");
        //NOTA: FALTAN metodos para borrar todas las relaciones del "tiron" (para evitar problemas con la FK)
        //Para una escuela o facultad o Universidad que se quiera borrar.
        boolean done = false;
        int entrada = -1;
        while(done == false ) {
            System.out.println("===================="
                + "\n0. Terminar Operaciones"
                + "\n1. Manejar Facultades"
                + "\n2. Manejar Escuelas"
                + "\n3. Manejar Estudiantes"
                + "\n====================");
        
            Scanner sc = new Scanner(System.in);
            entrada = sc.nextInt();
            if(entrada>=0 && entrada<5){
                switch(entrada) {
                    case 0:
                        done = true;
                        break;
                    case 1: 
                        Facultad f_actual = u1.getFacultadByName();
                        while(entrada != 0){
                        System.out.println("=============================");
                        System.out.println("La facultad seleccionada es: \n");
                        f_actual.mostrarFacultad();
                        System.out.println("===================="
                            + "\n0. Volver al menu anterior"
                            + "\n1. Agregar Escuela"
                            + "\n2. Eliminar Escuela"
                            + "\n3. Mostrar Escuelas"
                            + "\n4. Cambiar de Facultad"    
                            + "\n5. Eliminar Facultad"
                            + "\n====================");
                        entrada = sc.nextInt();
                        switch(entrada) {
                            case 0: 
                                break;
                            case 1:
                                f_actual.agregarEscuela();
                                break;
                            case 2:
                                f_actual.eliminarEscuela();
                                break;
                            case 3:
                                f_actual.mostrarEscuelas();
                                break;
                            case 4:
                                f_actual = u1.getFacultadByName();
                                break;
                            case 5:
                                u1.eliminarFacultad();
                            }
                        }
                        break;
                    case 2:
                        f_actual = u1.getFacultadByName();
                        Escuela e_actual = f_actual.getEscuelaByName();
                        while(entrada != 0){
                        System.out.println("=============================");
                        System.out.println("La facultad seleccionada es: \n");
                        f_actual.mostrarFacultad();
                        System.out.println("La Escuela seleccionada es: \n");
                        e_actual.mostrarEscuela();
                        System.out.println("===================="
                            + "\n0. Volver al menu anterior"
                            + "\n1. Agregar Estudiante"
                            + "\n2. Eliminar Estudiante"
                            + "\n3. Mostrar Estudiantes"
                            + "\n4. Mostrar Estudiantes por semestre"
                            + "\n5. Mostrar promedio de la escuela"    
                            + "\n6. Cambiar de Escuela"    
                            + "\n7. Eliminar Escuela"
                            + "\n====================");
                        entrada = sc.nextInt();
                        switch(entrada) {
                            case 0: 
                                break;
                            case 1:
                                e_actual.agregarEstudiante();
                                break;
                            case 2:
                                e_actual.eliminarEstudiante();
                                break;
                            case 3:
                                e_actual.mostrarEstudiantes();
                                break;
                            case 4:
                                System.out.println("¿De que semestre son los estudiantes?");
                                int semestre = sc.nextInt();
                                e_actual.mostrarEstudiantesPorSemestre(semestre);
                                break;
                            case 5:
                                e_actual.calcularPromedioEstudiantes();
                                break;
                            case 6:
                                int respuesta = JOptionPane.showConfirmDialog(null,
                                "¿Está la escuela en la misma facultad?", "Pregunta", JOptionPane.YES_NO_OPTION);
                                if(respuesta == JOptionPane.YES_OPTION){
                                   e_actual = f_actual.getEscuelaByName();
                                }else {
                                   f_actual = u1.getFacultadByName();
                                   e_actual = f_actual.getEscuelaByName();
                                }
                                break;
                            case 7:
                                f_actual.eliminarEscuela();
                                break;
                            }
                        } 
                    case 3:
                        while(entrada != 0){
                        System.out.println("===================="
                            + "\n0. Volver al menu anterior"
                            + "\n1. Agregar Estudiante a una escuela especifica"
                            + "\n2. Crear un estudiante (SIN asociarlo a una carrera)"
                            + "\n3. Eliminar Estudiante"
                            + "\n====================");
                         entrada = sc.nextInt();
                         switch(entrada) {
                             case 0:
                                 break;
                             case 1:
                                 f_actual = u1.getFacultadByName();
                                 e_actual = f_actual.getEscuelaByName();
                                 e_actual.agregarEstudiante();
                             case 2 :
                                 int id_temp = 0; //irrelevante porque es serial
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
                                     Estudiante temp = new Estudiante(nom, sem, prom, id_temp);
                                     temp.agregarEstudiante();
                                 }
                                 break;
                             case 3:
                                int respuesta = JOptionPane.showConfirmDialog(null,
                                "¿Está el estudiante en alguna escuela?", "Pregunta", JOptionPane.YES_NO_OPTION);
                                if(respuesta == JOptionPane.YES_OPTION){
                                   f_actual = u1.getFacultadByName();
                                   e_actual = f_actual.getEscuelaByName();
                                   e_actual.eliminarEstudiante();
                                } else {
                                   Estudiante temp = new Estudiante();
                                   temp.borrarEstudiante();
                                }
                                break;
                            }
                        }
                    }
            }
        }
    }
}
