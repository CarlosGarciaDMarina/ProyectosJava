package POO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

    // Funcion para crear un fichero binario
    public static boolean crearBinario(String direccion, boolean sobreescribir, String nombre, String apellidos,
            String ciudad, String nacionalidad, int edad) {
        // Gestionamos errores con Try-Catch
        try {

            // Array de strings
            String[] arrayDeStrings = { "Nombre: " + nombre + "\n", "Apellidos: " + apellidos + "\n",
                    "Ciudad: " + ciudad + "\n", "Nacionalidad: " + nacionalidad + "\n", "Edad: " + edad + "\n" };

            // Convertir el array de strings en bytes usando UTF-8
            Charset charset = Charset.forName("UTF-8");
            CharsetEncoder encoder = charset.newEncoder();

            // Calcular el tamaño total de los bytes necesarios
            int totalBytes = 0;
            for (String str : arrayDeStrings) {
                totalBytes += encoder.encode(CharBuffer.wrap(str)).remaining();
            }

            // Crear un buffer de bytes con el tamaño adecuado
            ByteBuffer buffer = ByteBuffer.allocate(totalBytes);

            // Codificar cada string y agregarlo al buffer
            for (String str : arrayDeStrings) {
                buffer.put(encoder.encode(CharBuffer.wrap(str)));
            }

            // Convertir el buffer de bytes a un array de bytes
            byte[] arrayDeBytes = buffer.array();

            // Escribir el array de bytes en un archivo binario
            try (FileOutputStream fos = new FileOutputStream(direccion)) {
                fos.write(arrayDeBytes);
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }

            return true;

        } catch (Exception e) {
            // Error
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
            return false;
        }
    }

    public static boolean leerBin(String direccion) {
        // Gestionamos los errores que se puedan producir con TRY-CATCH
        try {
            // Variables
            int numBytesLeidos = 0;
            byte[] capturaDatosBin = new byte[1000];

            // Pasamos la direccion a la clase que trata los archivos binarios
            FileInputStream fis = new FileInputStream(direccion);
            BufferedInputStream bis = new BufferedInputStream(fis);

            numBytesLeidos = bis.read(capturaDatosBin); // Captura la info que hay en el fichero bin

            // Mientras el numero de bytes leidos sea mayor de 0 va a seguir capturando
            // bytes
            while (numBytesLeidos > 0) {
                /*
                 * Si el archivo fuese muy grande deberiamos hacer otro array de bytes con
                 * capacidad de 10000 para ir metiendole de hay los datos que vamos capturando
                 */
                numBytesLeidos = bis.read(capturaDatosBin); // DEsde la posicion 0 hasta la 999 se van a ir llenando
                                                            // posiciones.

                for (int i = 0; i < capturaDatosBin.length; i++) {
                    // Vamos sacando cada uno de los bytes por pantalla
                    System.out.println(capturaDatosBin[i]);
                }
            }
            bis.close(); //Cerramos

            return true;
        } catch (Exception e) {
            // Error inesperado
            System.out.println("Ha ocurrido un error inesperado." + e.getMessage());
            return false;
        }

    }

    // Funcion Main
    public static void main(String[] args) {

        // Variables
        String direccion = "";
        String direccionBin = "";
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

        // Almacenamos la direccion del fichero txt que vamos a utilizar
        // No utilizo la clase scanner para no tener que estar todo el tiempo
        // escribiendo la direccion manual
        // Pero solo habria que preguntarle la direccion al usuario con sysout y
        // almacenarlo aqui
        direccion = "D:/ProyectosJava/POO/prueba/Personas.txt";

        // Almacenamos la direccion del fichero bin que vamos a utilizar
        // No utilizo la clase scanner para no tener que estar todo el tiempo
        // escribiendo la direccion manual
        // Pero solo habria que preguntarle la direccion al usuario con sysout y
        // almacenarlo aqui
        direccionBin = "D:/ProyectosJava/POO/prueba/Personas.bin";

        do {

            try (Scanner sc = new Scanner(System.in)) {

                System.out.println("|-------------------------------------|");
                System.out.println("|0. Salir del programa.               |");
                System.out.println("|1. Crear o Editar un fichero.        |");
                System.out.println("|2. Leer un fichero.                  |");
                System.out.println("|3. Crear o Editar un fichero bin.    |");
                System.out.println("|4. Leer un fichero binario.          |");
                System.out.println("|-------------------------------------|");
                System.out.println("Introduce una opcion del menu: ");
                menu = sc.nextInt();

                switch (menu) {
                    case 0:
                        // Salir del programa
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
                            System.out.println(""); // Salto de linea para mejorar la legibilidad
                            leerFichero(direccion); // Funcion del ectura del fichero
                            System.out.println(""); // Salto de linea para mejorar la legibilidad
                            System.out.println("Final del fichero.");
                        } else {
                            // Si NO existe avisamos de que no hay nada que leer
                            System.out.println("No hay ningun documento con ese nombre en la direccion proporcionada.");
                        }
                        break;
                    case 3:
                        // Crear o editar un binario
                        // Comprobamos si el fichero existe
                        if (existe(direccionBin)) {
                            // SI existe
                            crearBinario(direccionBin, sobreescribir, p3.getNombre(), p3.getApellidos(), p3.getCiudad(),
                                    p3.getNacionalidad(), p3.getEdad());
                            System.out.println("La insercion ha sido un exito.");

                        } else {
                            // No existe
                            crearBinario(direccionBin, sobreescribir, p3.getNombre(), p3.getApellidos(), p3.getCiudad(),
                                    p3.getNacionalidad(), p3.getEdad());
                            System.out.println("El documento se ha creado con exito.");
                        }

                        break;
                    case 4:
                        // Leer binario
                        // Comprobamos si el fichero existe
                        if (existe(direccionBin)) {
                            // SI existe
                            leerBin(direccionBin);
                        } else {
                            // Si NO existe
                            System.out.println("No hay ningun archivo binario que poder leer.");
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
