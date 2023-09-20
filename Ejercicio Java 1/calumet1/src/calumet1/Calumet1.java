
package calumet1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Calumet1 {

    public static void main(String[] args) {
        
       /*Inicializamos variables*/
       Universidad u1 = new Universidad("UIS");
       
       Facultad f1 = new Facultad("Fisicomecanicas");
       Facultad f2 = new Facultad("Humanas");
       
       Escuela e1 = new Escuela("Licenciatura en Español");
       Escuela e2 = new Escuela("Licenciatura en Lenguas Extranjeras");
       Escuela e3 = new Escuela("Historia universal");
       Escuela e4 = new Escuela("Ingenieria de sistemas");
       Escuela e5 = new Escuela("Ingenieria electronica");
       Escuela e6 = new Escuela("Ingenieria civil");
       
       u1.agregarFacultad(f1);
       u1.agregarFacultad(f2);
       
       f1.agregarEscuela(e6);
       f1.agregarEscuela(e5);
       f1.agregarEscuela(e4);
       
       f2.agregarEscuela(e1);
       f2.agregarEscuela(e2);
       f2.agregarEscuela(e3);
       
        Estudiante estu1 = new Estudiante("Louis Castillo",6,4.891f);
        Estudiante estu2 = new Estudiante("Armand Peña",1,4.857f);
        Estudiante estu3 = new Estudiante("Fredy Ojeda",1,3.497f);
        Estudiante estu4 = new Estudiante("Gaudy Navarro",4,4.163f);
        Estudiante estu5 = new Estudiante("Luis Manuel Gómez",4,3.307f);
        Estudiante estu6 = new Estudiante("Amy Parra",7,3.58f);
        Estudiante estu7 = new Estudiante("Alex Herrera",4,3.334f);
        Estudiante estu8 = new Estudiante("Alfonso Suárez",4,3.988f);
        Estudiante estu9 = new Estudiante("Alfredo Aguirre",7,4.157f);
        Estudiante estu10 = new Estudiante("Alonso Giménez",3,4.088f);
        Estudiante estu11 = new Estudiante("Álvaro Gutiérrez",1,4.587f);
        Estudiante estu12 = new Estudiante("Amadeo Pereyra",5,4.584f);
        Estudiante estu13 = new Estudiante("Amado Rojas",3,3.806f);
        Estudiante estu14 = new Estudiante("Amando Molina",6,4.764f);
        Estudiante estu15 = new Estudiante("Ambrosio Castro",2,4.432f);
        Estudiante estu16 = new Estudiante("Amin Ortiz",4,4.972f);
        Estudiante estu17 = new Estudiante("Anastasio Silva",4,4.958f);
        Estudiante estu18 = new Estudiante("Ander Núñez",3,3.539f);
        Estudiante estu19 = new Estudiante("Constantino Luna",10,4.335f);
        Estudiante estu20 = new Estudiante("Crispín Juárez",8,4.939f);
        Estudiante estu21 = new Estudiante("Cristian Cabrera",2,4.454f);
        Estudiante estu22 = new Estudiante("Daniel Ríos",4,3.086f);
        Estudiante estu23 = new Estudiante("Darío Morales",6,4.178f);
        Estudiante estu24 = new Estudiante("David Godoy",4,3.24f);
        Estudiante estu25 = new Estudiante("Desiderio Moreno",4,4.021f);
        Estudiante estu26 = new Estudiante("Diego Ferreyra",10,3.175f);
        Estudiante estu27 = new Estudiante("Dionisio Domínguez",5,3.01f);
        Estudiante estu28 = new Estudiante("Domingo Carrizo",1,3.776f);
        Estudiante estu29 = new Estudiante("Donato Peralta",6,4.212f);
        Estudiante estu30 = new Estudiante("Edgar Castillo",5,3.192f);
        Estudiante estu31 = new Estudiante("Edmundo Ledesma",4,4.967f);
        Estudiante estu32 = new Estudiante("Eduardo Quiroga",10,3.418f);
        Estudiante estu33 = new Estudiante("Elías Vega",10,3.266f);
        Estudiante estu34 = new Estudiante("Eloy Vera",2,4.034f);
        Estudiante estu35 = new Estudiante("Emilio Muñoz",8,4.783f);
        Estudiante estu36 = new Estudiante("Eneko Ojeda",7,4.158f);
        Estudiante estu37 = new Estudiante("Enrique Ponce",6,4.574f);
        Estudiante estu38 = new Estudiante("Eric Villalba",7,3.699f);
        Estudiante estu39 = new Estudiante("Ernesto Cardozo",8,4.69f);
        Estudiante estu40 = new Estudiante("Esteban Navarro",5,3.78f);
        Estudiante estu41 = new Estudiante("Eugenio Coronel",4,4.971f);
        Estudiante estu42 = new Estudiante("Eusebio Vázquez",3,3.079f);
        Estudiante estu43 = new Estudiante("Fabián Ramos",4,3.732f);
        Estudiante estu44 = new Estudiante("Federico Vargas",10,4.793f);
        Estudiante estu45 = new Estudiante("Felipe Cáceres",9,4.061f);
        Estudiante estu46 = new Estudiante("Félix Arias",10,3.516f);
        Estudiante estu47 = new Estudiante("Fermín Figueroa",2,3.511f);
        Estudiante estu48 = new Estudiante("Fernando Córdoba",6,4.284f);
        Estudiante estu49 = new Estudiante("Fidel Correa",8,4.649f);
        Estudiante estu50 = new Estudiante("Francisco Maldonado",10,4.167f);
        Estudiante estu51 = new Estudiante("Marcos Paz",2,3.072f);
        Estudiante estu52 = new Estudiante("Mariano Rivero",9,3.796f);
        Estudiante estu53 = new Estudiante("Mario Miranda",3,3.922f);
        Estudiante estu54 = new Estudiante("Marti Mansilla",1,4.867f);
        Estudiante estu55 = new Estudiante("Martín Farias",3,3.311f);
        Estudiante estu56 = new Estudiante("Mateo Roldán",5,3.225f);
        Estudiante estu57 = new Estudiante("Matías Méndez",2,3.982f);
        Estudiante estu58 = new Estudiante("Mauricio Guzmán",6,3.821f);
        Estudiante estu59 = new Estudiante("Maximiliano Agüero",7,4.646f);
        Estudiante estu60 = new Estudiante("Máximo Hernández",2,3.644f);

        /*Lista auxiliar para que las escuelas no queden con los mismos estudiantes*/
        ArrayList<Estudiante> listaEstudiantes = new ArrayList();
        
        listaEstudiantes.add(estu1);
        listaEstudiantes.add(estu2);
        listaEstudiantes.add(estu3);
        listaEstudiantes.add(estu4);
        listaEstudiantes.add(estu5);
        listaEstudiantes.add(estu6);
        listaEstudiantes.add(estu7);
        listaEstudiantes.add(estu8);
        listaEstudiantes.add(estu9);
        listaEstudiantes.add(estu10);
        listaEstudiantes.add(estu11);
        listaEstudiantes.add(estu12);
        listaEstudiantes.add(estu13);
        listaEstudiantes.add(estu14);
        listaEstudiantes.add(estu15);
        listaEstudiantes.add(estu16);
        listaEstudiantes.add(estu17);
        listaEstudiantes.add(estu18);
        listaEstudiantes.add(estu19);
        listaEstudiantes.add(estu20);
        listaEstudiantes.add(estu21);
        listaEstudiantes.add(estu22);
        listaEstudiantes.add(estu23);
        listaEstudiantes.add(estu24);
        listaEstudiantes.add(estu25);
        listaEstudiantes.add(estu26);
        listaEstudiantes.add(estu27);
        listaEstudiantes.add(estu28);
        listaEstudiantes.add(estu29);
        listaEstudiantes.add(estu30);
        listaEstudiantes.add(estu31);
        listaEstudiantes.add(estu32);
        listaEstudiantes.add(estu33);
        listaEstudiantes.add(estu34);
        listaEstudiantes.add(estu35);
        listaEstudiantes.add(estu36);
        listaEstudiantes.add(estu37);
        listaEstudiantes.add(estu38);
        listaEstudiantes.add(estu39);
        listaEstudiantes.add(estu40);
        listaEstudiantes.add(estu41);
        listaEstudiantes.add(estu42);
        listaEstudiantes.add(estu43);
        listaEstudiantes.add(estu44);
        listaEstudiantes.add(estu45);
        listaEstudiantes.add(estu46);
        listaEstudiantes.add(estu47);
        listaEstudiantes.add(estu48);
        listaEstudiantes.add(estu49);
        listaEstudiantes.add(estu50);
        listaEstudiantes.add(estu51);
        listaEstudiantes.add(estu52);
        listaEstudiantes.add(estu53);
        listaEstudiantes.add(estu54);
        listaEstudiantes.add(estu55);
        listaEstudiantes.add(estu56);
        listaEstudiantes.add(estu57);
        listaEstudiantes.add(estu58);
        listaEstudiantes.add(estu59);
        listaEstudiantes.add(estu60);

        
        Random rand = new Random();
        
        for(int i=0; i<=20; i++){
            int numeroEntero = rand.nextInt(58);
            e1.agregarEstudiante(listaEstudiantes.get(numeroEntero));
            
            numeroEntero = rand.nextInt(58);
            e2.agregarEstudiante(listaEstudiantes.get(numeroEntero));
            
            numeroEntero = rand.nextInt(58);
            e3.agregarEstudiante(listaEstudiantes.get(numeroEntero));
            
            numeroEntero = rand.nextInt(58);
            e4.agregarEstudiante(listaEstudiantes.get(numeroEntero));
            
            numeroEntero = rand.nextInt(58);
            e5.agregarEstudiante(listaEstudiantes.get(numeroEntero));
            
            numeroEntero = rand.nextInt(58);
            e6.agregarEstudiante(listaEstudiantes.get(numeroEntero));
        }
        
        boolean bandera = false;
        
        while(bandera == false) {
            System.out.println("Hola, soy tu auxiliar local, seleccione una de las siguientes opciones:");
            System.out.println("0 - Terminar operaciones");
            System.out.println("1 - Mostrar Universidad");
            System.out.println("2 - Mostrar Facultades");
            System.out.println("3 - Agregar Facultad a la Universidad");
            System.out.println("4 - Mostrar Escuelas de una Facultad");
            System.out.println("5 - Agregar Escuela a una facultad");
            System.out.println("6 - Mostrar Estudiantes de una escuela");
            System.out.println("7 - Agregar Estudiantes a una escuela");
            System.out.println("8 - Calcular el promedio de una escuela");
            System.out.println("9 - Mostrar Facultad especifica");
            System.out.println("10 - Mostrar Escuela especfica");
            System.out.println("11 - Mostrar Estudiante especifico");
            System.out.println();
            
            Scanner sc = new Scanner(System.in);
            
            int entrada = sc.nextInt();
            
            if(entrada>=0 && entrada<=11){
                
                switch (entrada) {
                case 0:
                    bandera = true;
                    break;
                case 1:
                    u1.mostrarUniversidad();
                    break;
                case 2:
                    u1.mostrarFacultades();
                    break;
                case 3:
                    u1.agregarFacultad();
                    break;
                case 4:
                    Facultad facuAux = u1.getFacutadById();
                    if(facuAux != null){
                        facuAux.mostrarEscuelas();
                    }
                    break;
                case 5:
                    Facultad facuAux1 = u1.getFacutadById();
                    if(facuAux1 != null){
                        facuAux1.agregarEscuela();
                    }
                    break;
                case 6:
                    Facultad facuAux2 = u1.getFacutadById();
                    if(facuAux2 != null){
                       Escuela escuAux = facuAux2.getEscuelaByid();
                       if(escuAux != null) {
                           escuAux.mostrarEstudiantes();
                       }
                    }
                    break;
                case 7:
                    Facultad facuAux3 = u1.getFacutadById();
                    if(facuAux3 != null){
                       Escuela escuAux1 = facuAux3.getEscuelaByid();
                       if(escuAux1 != null) {
                           escuAux1.agregarEstudiante();
                       }
                    }
                    break;
                case 8:
                    Facultad facuAux4 = u1.getFacutadById();
                    if(facuAux4 != null){
                       Escuela escuAux2 = facuAux4.getEscuelaByid();
                       if(escuAux2 != null) {
                           escuAux2.calcularPromedioEstudiantes();
                       }
                    }
                    break;
                case 9:
                    Facultad facuAux5 = u1.getFacutadById();
                    if(facuAux5 != null){
                       facuAux5.mostrarFacultad();
                    }
                    break;
                case 10:
                    Facultad facuAux6 = u1.getFacutadById();
                    if(facuAux6 != null){
                       Escuela escuAux3 = facuAux6.getEscuelaByid();
                       if(escuAux3 != null){
                           escuAux3.mostrarEscuela();
                       }
                    }
                    break;
                case 11:
                    Facultad facuAux7 = u1.getFacutadById();
                    if(facuAux7 != null){
                       Escuela escuAux4 = facuAux7.getEscuelaByid();
                       if(escuAux4 != null){
                           Estudiante estuAux = escuAux4.getEstudianteById();
                           if(estuAux !=null){
                               estuAux.mostrarEstudiante();
                           }
                       }
                    }
                    break;
                }
            }
            else{
                System.out.println("Digite un numero valido la proxima vez");
                System.out.println();
            }
        }
    }
}
