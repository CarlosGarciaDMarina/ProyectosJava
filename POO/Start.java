package POO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Start {
    // Funciones
    // Funcion para saber si un fichero existe
    public static boolean existe(String direccion) {
        // Try-catch para gestionar errores
        try {
            // Llamamos a la clase para trabajar con ficheros
            File f = new File(direccion);
            // Creamos los condicionales para saber si existe o no
            if (f.exists()) {
                // Si existe
                return true;
            } else {
                // Si no existe
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado." + e.getMessage());
            return false;
        }
    }

    // Funcion para escribir en ficheros binarios
    public static Boolean escribirFichero(String direccion, boolean sobreescribir, String nombre, String apellidos,
            String ciudad, String nacionalidad, int edad) {

        // encerramos en un try-catch para gestionar errores
        try {
            // Clase para tratar con ficheros
            File f = new File(direccion);

            // Clases para escribir en ficheros
            FileWriter fw = new FileWriter(f, sobreescribir);
            PrintWriter pw = new PrintWriter(fw);
            pw.write("Nombre: " + nombre + ". \n");
            pw.write("Apellidos: " + apellidos + ".\n");
            pw.write("Ciudad: " + ciudad + ".\n");
            pw.write("Nacionalidad: " + nacionalidad + ".\n");
            pw.write("Edad: " + edad + ".\n");
            pw.write("\n"); // Salto de linea para mejorar la legibilidad del documento

            // Cerramos la escritura
            pw.close();
            fw.close();

            return true;

        } catch (Exception e) {
            System.out.println("Ha ocurrrido un error inesperado. " + e.getMessage());
            return false;
        }
    }

    // Funcion para leer el fichero
    public static boolean leerFichero(String direccion) {
        // try-catch para gestionar errores
        try {
            // Variable
            String lineaTexto = "";

            // Pasamos la direccion a la clase que trata con los ficheros
            File f = new File(direccion);

            // Pasamos los parametros a las clases que se encargan de leer los ficheros
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            System.out.println(""); // Salto dee linea para mejorar la legibilidad

            // Creamos un bucle para que lea linea a linea
            while ((lineaTexto = br.readLine()) != null) {
                System.out.println(lineaTexto); // Imprimimos lo que vamos leyendo por pantalla
            }

            fr.close(); // Cerramos la funcion
            return true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
            return false;
        }
    }

    // Funcion Main
    public static void main(String[] args) {

        // Variables
        String direccion = "";
        boolean sobreescribir = true;
        int menu = 0;

        // Variables del constructor
        Persona p1 = new Persona("Luna", "Himemori", "Kansai", "Japon", 12);
        Persona p2 = new Persona("Pekora", "Usada", "Peko", "Pekoland", 3);
        Persona p3 = new Persona("Kanata", "Amane", "Tenshi", "Japon", 13);

        // Imprimimos la informacion por la pantalla
        p1.print();
        System.out.println(""); // Salto de linea para mejorar la legibilidad
        p2.print();
        System.out.println(""); // Salto de linea para mejorar la legibilidad
        p3.print();
        System.out.println(""); // Salto de linea para mejorar la legibilidad

        // Escribimos en un fichero
        System.out.println("Vamos a exportar los datos de las personas a un fichero.");

        direccion = "D:/ProyectosJava/POO/prueba/Personas.txt";

        do {

            try (Scanner sc = new Scanner(System.in)) {

                System.out.println("|-------------------------------------|");
                System.out.println("|0. Salir del programa.               |");
                System.out.println("|1. Crear o Editar un fichero.        |");
                System.out.println("|2. Leer un fichero.                  |");
                System.out.println("|-------------------------------------|");
                System.out.println("Introduce una opcion del menu: ");
                menu = sc.nextInt();

                switch (menu) {
                    case 0:
                    //Salir del programa
                        System.out.println("Ha finalizado el programa.");
                        break;
                    case 1:
                        // Escribir en fichero
                        // Comprobamos si el fichero existe
                        if (existe(direccion) == true) {
                            // SI existe escribimos
                            escribirFichero(direccion, sobreescribir, p3.getNombre(), p3.getApellidos(), p3.getCiudad(),
                                    p3.getNacionalidad(), p3.getEdad());
                            System.out.println("La insercion ha sido un exito.");
                        } else {
                            // Si NO existe lo creamos
                            escribirFichero(direccion, sobreescribir, p3.getNombre(), p3.getApellidos(), p3.getCiudad(),
                                    p3.getNacionalidad(), p3.getEdad());
                            System.out.println("El documento ha sido creado con exito y la insercion se ha realizado.");
                        }
                        break;
                    case 2:
                        // Leer en fichero
                        // Comprobamos si el fichero existe
                        if (existe(direccion) == true) {
                            // SI existe leemos
                            System.out.println("Aqui empieza el fichero:");
                            System.out.println(""); //Salto de linea para mejorar la legibilidad
                            leerFichero(direccion); // Funcion del ectura del fichero
                            System.out.println(""); //Salto de linea para mejorar la legibilidad
                            System.out.println("Final del fichero.");
                        } else {
                            // Si NO existe avisamos de que no hay nada que leer
                            System.out.println("No hay ningun documento con ese nombre en la direccion proporcionada.");
                        }
                        break;
                    default:
                        System.out.println("Ha introducido un caracter inválido.");
                        break;
                }

            } catch (Exception e) {
                // Error con el Scanner
                System.out.println("Ha ocurrido un error con el Scanner." + e.getMessage());
            }
        } while ((menu < 0) && (menu > 2));

    }
}
