package Hilos;

import java.util.Scanner;
import javax.sound.midi.Soundbank;

public class Procesos {

    // Funciones
public static boolean caballoGanador(){
    
    
    
    return true;
}
    // Funcion main
    public static void main(String[] args) {

        // Variables
        String caballo1 = "";
        String caballo2 = "";
        String caballo3 = "";
        long tiempoCaballo1 = 0;
        long tiempoCaballo2 = 0;
        long tiempoCaballo3 = 0;


        try(Scanner sc = new Scanner(System.in)) {
            
            //Pedimos al usuario que introduzca los nombres de los caballos
            System.out.println("Va a empezar una carrera de caballos, inscribe a 3 participantes: ");
            System.out.println("Como se va a llamar el primer caballo?");
            caballo1 = sc.nextLine();
            System.out.println("Como se va a llamar el segundo caballo?");
            caballo2 = sc.nextLine();
            System.out.println("Como se va a llamar el tercer caballo?");
            caballo3 = sc.nextLine();

            //Creamos los 3 caballos
            Caballos ca1 = new Caballos(caballo1,1);
            Caballos ca2 = new Caballos(caballo2, 2);
            Caballos ca3 = new Caballos(caballo3, 3);
            

            //Usamos la funcion de los hilos
            Thread t = new Thread(ca1);
            Thread t2 = new Thread(ca2);
            Thread t3 = new Thread(ca3);

            //Iniciamos los hilos
            t.start();
            t2.start();
            t3.start();

            //Esperamos a que los hilos terminen
            t.join();
            t2.join();
            t3.join();

            //Obtenemos los tiempos de finalizacion de cada uno de ellos
            tiempoCaballo1 = ca1.getTiempoFinalizacion();
            tiempoCaballo2 = ca2.getTiempoFinalizacion();
            tiempoCaballo3 = ca3.getTiempoFinalizacion();

            //Ahora vamos a ver cual de ellos es el que ha tardado menos
            if ((tiempoCaballo1 < tiempoCaballo2) && (tiempoCaballo1 < tiempoCaballo3) ) {
                System.out.println("EL caballo ganador es " + ca1.getNombre() + ".");

            } else if ((tiempoCaballo2 < tiempoCaballo1) && (tiempoCaballo2 < tiempoCaballo3)) {
                System.out.println("EL caballo ganador es " + ca2.getNombre() + ".");  

            } else if ((tiempoCaballo3 < tiempoCaballo1) && (tiempoCaballo3 < tiempoCaballo2)){
                System.out.println("EL caballo ganador es " + ca3.getNombre() + ".");  

            } else {
                System.out.println("Ha habido un empate en la carrera.");
            }


        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
        }
    }
}