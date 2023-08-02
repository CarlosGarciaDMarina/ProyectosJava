package FicherosTexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestionFicheros {

    // Funciones

    // Funcion main
    public static void main(String[] args) {
        // Variables
        int menu = 0;
        String direccion = "";
        String mensaje = "";
        String lineaTexto = "";
        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        FileReader fr = null;
        BufferedReader br = null;

        // Encerramos el scanner en un try
        try (Scanner sc = new Scanner(System.in)) {

            // Abrimos bucle do-while para interactuar con el menu
            do {

                // Creamos el menu que va a ver el usuario
                System.out.println("|-----------------------------|");
                System.out.println("|-----Gestor-de-Ficheros------|");
                System.out.println("|-----------------------------|");
                System.out.println("|1.- Crear un fichero.        |");
                System.out.println("|2.- Editar un fichero.       |");
                System.out.println("|3.- Leer un fichero.         |");
                System.out.println("|4.- Borrar un fichero.       |");
                System.out.println("|5.- Crear una carpeta.       |");
                System.out.println("|6.- Borrar una carpeta.       |");
                System.out.println("|-----------------------------|");
                System.out.println("Selecciona una opcion del menu:");
                menu = sc.nextInt();

                // Creamos un switch que va a hacer de menu para interactuar con las distintas
                // opciones
                switch (menu) {
                    case 1:
                        // Crear un fichero nuevo
                        // Le pedimos una direccion donde crearlo y capturamos su respuesta
                        System.out.println("Has seleccionado la opcion de crear un fichero nuevo.");
                        System.out.println("Introduce la ruta completa donde deseas crear el fichero: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        f = new File(direccion);

                        if (!f.exists()) {
                            // Creamos el documento
                            fw = new FileWriter(f); // Usamos la funcion de escritura
                            fw.write(""); // Mensaje en blanco para crear el archivo
                            fw.close(); // Cerramos la funcion

                            // Mensaje de que el archivo ha sido creado
                            System.out.println("El archivo ha sido creado con exito.");

                        } else {
                            // Hay fichero
                            System.out.println(
                                    "La ruta introducida pertenece a un fichero existente. Por favor, introduzca una ruta valida.");
                        }
                        break;
                    case 2:
                        // Editar un fichero de texto
                        System.out.println("Has seleccionado la opcion de editar un fichero de texto.");
                        System.out.println("Introduce la ruta completa del fichero que deseas editar: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        f = new File(direccion);

                        if (f.exists()) {

                            // Editamos
                            fw = new FileWriter(f, true); // Activamos el append para conservar lo que ya haya escrito
                                                          // en el archivo
                            System.out.println("Introduce el mensaje que quieres escribir en el fichero:");
                            mensaje = sc.nextLine();// capturamos el mensaje del usuario
                            fw.write(mensaje); // Escribimos lo que hemos capturado
                            fw.close(); // Cerramos la funcion de escritura

                            // Mensaje de exito
                            System.out.println("Archivo editado con exito.");

                        } else {
                            // No hay fichero
                            System.out.println("No hay ningun fichero que poder editar aqui.");
                        }
                        break;
                    case 3:
                        // Leer
                        System.out.println("Has seleccionado la opcion de leer un fichero.");
                        System.out.println("Introduce la ruta completa del fichero que deseas leer: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        f = new File(direccion);

                        if (f.exists()) {
                            // Leemos
                            fr = new FileReader(f);
                            br = new BufferedReader(fr); // Buffer es para ir capturando la informacion linea a linea
                            System.out.println(""); // salto de linea para mejorar la legibilidad

                            try {
                                // Creamos un bucle que mientras que haya lineas de texto que leer las vaya
                                // escribiendo.
                                while ((lineaTexto = br.readLine()) != null) {
                                    System.out.println(lineaTexto);
                                }

                            } catch (Exception e) {
                                System.out.println("Hay un problema de permisos con el fichero." + e.getMessage());
                            }

                            fr.close(); // Cerramos la funcion de lectura

                        } else {
                            // No hay fichero
                            System.out.println("No hay ningun fichero que podamos leer en esta ruta.");
                        }

                        break;
                    case 4:
                        // Borramos un fichero de texto
                        System.out.println("Has seleccionado la opcion de borrar un fichero.");
                        System.out.println("Introduce la ruta completa del fichero que deseas borrar: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        f = new File(direccion);
                        if (f.exists()) {
                            //Si existe borramos
                            f.delete(); // Borramos el fichero
                            System.out.println("Fichero eliminado con exito."); // Mensaje de exito
                        } else {
                            //No hay fichero
                            System.out.println("No hya ningun fichero que se pueda borrar en la ruta que has introducido.");
                        }
                        break;
                    case 5:
                        //Crear una carpeta
                        System.out.println("Has seleccionado la opcion de crear una carpeta.");
                        System.out.println("Introduce la ruta completa donde deseas crear la carpeta: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        if (!f.exists()) {
                            //No existe asique podemos crear una carpeta
                            if (f.mkdir()) {
                                System.out.println("La carpeta ha sido creada correctamente.");
                            } else {
                                System.out.println("Ha ocurrido un error al crear la carpeta.");
                            }
                        } else {
                            //Existe asique error
                            System.out.println("Esta carpeta ya existe.");
                        }
                        break;
                    case 6:
                        //Borrar carpeta
                        System.out.println("Has seleccionado la opcion de borrar una carpeta.");
                        System.out.println("Introduce la ruta completa de la carpeta que deseas borrar: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        if (f.exists()) {
                            if(f.delete()){
                                System.out.println("La carpeta se ha borrado correctamente.");
                            } else {
                                System.out.println("La carpeta no se ha podido borrar.");
                            }
                        } else {
                            System.out.println("La carpeta no existe.");
                        }
                        break;

                    default:
                        System.out.println("Has introducido un caracter erroneo.");
                        break;
                }

            } while ((menu < 0) || (menu > 6));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error con el scanner." + e.getMessage());
        }
    }

}
