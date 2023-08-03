package FicherosTexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestionFicherosModular {
    // Funciones
    // Funcion que comprueba si una carpeta o documento existe
    public static boolean existe(String direccion) {
        // Encerramos la funcion en un try-catch para gestionar los errores
        try {
            File f = new File(direccion); // Pasamos la direccion a la funcion de fichero

            // Creamos un condicional que determina si existe o no la ruta pasada a la
            // funcion
            if (!f.exists()) {
                // Si no existe
                return true;
            } else {
                // Si existe
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado." + e.getMessage());
            return false;
        }
    }

    // Funcion para crear un fichero de texto nuevo
    public static boolean crearFichero(String direccion) {
        try {
            // Pasamos la direccion a la funcion que trabaja con archivos
            File f = new File(direccion);

            // Creamos el documento
            FileWriter fw = new FileWriter(f); // Usamos la funcion de escritura
            fw.write(""); // Mensaje en blanco para crear el archivo
            fw.close(); // Cerramos la funcion

            // Mensaje de que el archivo ha sido creado
            System.out.println("El archivo ha sido creado con exito.");
            return true;

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado " + e.getMessage());
            return false;
        }
    }

    // Funcion para editar en un fichero de texto
    public static boolean editarFichero(String direccion, String mensaje, boolean sobreescribir) {
        // Try-catch para gestionar errores
        try {
            // Pasamos la direccion a la funcion para manejar archivos
            File f = new File(direccion);
            // Editamos
            FileWriter fw = new FileWriter(f, sobreescribir); // Pasamos el append con la preferencia del usuario
            PrintWriter pw = new PrintWriter(fw); // Utilizamos la funcion de escritura sin formato para mejorar la
                                                  // escritura
            pw.write("\n " + mensaje); // Escribimos lo que hemos capturado
            pw.close(); // Cerramos la funcion de escritura
            fw.close(); // Cerramos la funcion de escritura

            // Mensaje de exito
            System.out.println("Archivo editado con exito.");
            return true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado " + e.getMessage());
            return false;
        }
    }

    // Funcion para leer ficheros de texto
    public static boolean leerFichero(String direccion) {
        // Try-catch para gestionar errores
        try {
            // Variables
            String lineaTexto = "";

            // Pasamos la direccion a la funcion para manejar archivos
            File f = new File(direccion);

            // Leemos
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); // Buffer es para ir capturando la informacion linea a linea
            System.out.println(""); // salto de linea para mejorar la legibilidad

            // Creamos un bucle para leer
            while ((lineaTexto = br.readLine()) != null) {
                System.out.println(lineaTexto);
            }

            fr.close(); // Cerramos la funcion de lectura
            return true;

        } catch (Exception e) {
            // Error
            System.out.println("Ha ocurrido un error inesperado" + e.getMessage());
            return false;
        }
    }

    // Funcion para borrar un fichero
    public static boolean borrarFichero(String direccion) {
        // Gestionamos errores con try-catch
        try {
            // Pasamos la direccion a la funcion para tratar con archivos
            File f = new File(direccion);

            // Borramos el fichero
            f.delete();
            System.out.println("Fichero eliminado con exito."); // Mensaje de exito
            return true;

        } catch (Exception e) {
            // Error
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
            return false;
        }
    }

    // Funcion para crear una carpeta
    public static boolean crearCarpeta(String direccion) {
        // Gestionamos errores con un try-catch
        try {
            // Pasamos la direccion a la funcion que trata los archivos
            File f = new File(direccion);

            if (f.mkdir()) {
                // Si se crea
                System.out.println("La carpeta ha sido creada correctamente.");
                return true;

            } else {
                // Si no se crea
                System.out.println("Ha ocurrido un error al crear la carpeta.");
                return false;
            }
        } catch (Exception e) {
            // Error
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
            return false;
        }
    }

    // Funcion main
    public static void main(String[] args) {
        // Variables
        int menu = 0;
        String direccion = "";
        String mensaje = "";
        boolean sobreescribir = true;

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
                System.out.println("|6.- Borrar una carpeta.      |");
                System.out.println("|7.- Salir.                   |");
                System.out.println("|-----------------------------|");
                System.out.println("Selecciona una opcion del menu:");
                menu = sc.nextInt();

                // Creamos un switch que va a hacer de menu para interactuar con las distintas
                // opciones
                switch (menu) {
                    case 1:
                        // Crear un fichero
                        // Le pedimos una direccion donde crearlo y capturamos su respuesta
                        System.out.println("Has seleccionado la opcion de crear un fichero nuevo.");
                        System.out.println("Introduce la ruta completa donde deseas crear el fichero: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        // Comprobamos si existe
                        if (existe(direccion) == true) {
                            // SI se puede crear un nuevo fichero
                            crearFichero(direccion); // Creamos el documento
                        } else {
                            // Si NO se puede
                            // Mostramos el contenido del documento
                            System.out.println("Este es el contenido del fichero: ");
                            leerFichero(direccion);
                            System.out.println(""); // Salto de linea para mejorar la legibilidad

                            // Preguntamos si quiere sobreescribirlo
                            System.out.println("Desea sobreescribir el fichero? (Y/N)");
                            System.out.println("ATENCION!");
                            System.out.println("Si su respuesta es afirmativa va a borrar el contenido del fichero.");
                            String respuesta = sc.nextLine();

                            respuesta = respuesta.toLowerCase(); // transformamos la respuesta en minúsculas para
                                                                 // controlar errores

                            if (respuesta.equals("y")) {
                                // Si
                                System.out.println("Ha respondido que si.");

                                // Pedimos el mensaje
                                System.out.println("Introduce el mensaje que quieres escribir en el fichero:");
                                mensaje = sc.nextLine(); // Capturamos el mensaje del usuario

                                // como se va a sobreescribir el contenido del documento ponemos en false el
                                // append
                                sobreescribir = false;

                                // Llamamos a la funcion
                                editarFichero(direccion, mensaje, sobreescribir);

                                // Mensaje de exito
                                System.out.println("El fichero ha sido sobreescrito con exito.");

                            } else if (respuesta.equals("n")) {
                                // No
                                System.out.println("EL programa ha finalizado.");
                            } else {
                                // Manejo de errores
                                System.out.println("Ha introducido un caracter no valido.");
                            }
                        }
                        break;
                    case 2:
                        // Editar un fichero de texto
                        // Pedimos la direccion al usuario
                        System.out.println("Has seleccionado la opcion de editar un fichero de texto.");
                        System.out.println("Introduce la ruta completa del fichero que deseas editar: ");
                        sc.nextLine();
                        direccion = sc.nextLine(); // capturamos la direccion del usuario

                        // Llamamos a la funcion para comprobar si el fichero existe o no
                        if (existe(direccion) == false) {
                            // Si es FALSE es porque existe y se puede editar
                            // Enseñamos lo que hay escrito en el documento
                            System.out.println("Esto es lo que contiene el documento: ");
                            leerFichero(direccion);
                            System.out.println(""); // Salto de linea para mejorar la legibilidad

                            // Pedimos el mensaje
                            System.out.println("Introduce el mensaje que quieres escribir en el fichero:");
                            mensaje = sc.nextLine(); // capturamos el mensaje del usuario
                            editarFichero(direccion, mensaje, sobreescribir); // Editamos
                        } else {
                            // Si es TRUE es por que no existe y, por lo tanto, no se puede editar
                            System.out.println("No hay ningun documento en la ruta que has introducido.");
                        }
                        break;
                    case 3:
                        // Leer
                        // Pedimos los datos al usuario
                        System.out.println("Has seleccionado la opcion de leer un fichero.");
                        System.out.println("Introduce la ruta completa del fichero que deseas leer: ");
                        sc.nextLine();
                        direccion = sc.nextLine(); // Capturamos

                        // Comprobamos si existe o no
                        if (existe(direccion) == false) {
                            // Si es FALSE es por que hay un archivo asique procedemos a leerlo
                            leerFichero(direccion);
                        } else {
                            // Si es TRUE es por que no hay ningun archivo
                            System.out.println("No hay ningun archivo que se pueda leer aqui.");
                        }
                        break;
                    case 4:
                        // Borramos un fichero de texto
                        System.out.println("Has seleccionado la opcion de borrar un fichero.");
                        System.out.println("Introduce la ruta completa del fichero que deseas borrar: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        // Comprobamos si existe
                        if (existe(direccion) == false) {
                            // Si es FALSE es por que existe
                            borrarFichero(direccion); // Borramos
                        } else {
                            // Si es TRUE es porque existe
                            System.out.println("No existe ningun documento que borrar aqui.");
                        }
                        break;
                    case 5:
                        // Crear una carpeta
                        System.out.println("Has seleccionado la opcion de crear una carpeta.");
                        System.out.println("Introduce la ruta completa donde deseas crear la carpeta: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        if (existe(direccion) == true) {
                            // Si es TRUE es porque no existe y, por lo tanto, podemos crear la carpeta
                            crearCarpeta(direccion); // Creamos la carpeta
                        } else {
                            // Si es FALSE es porque esa carpeta ya existe
                            // Informamos al usuario
                            System.out.println("Esa carpeta ya existe.");
                        }
                        break;
                    case 6:
                        // Borrar carpeta
                        System.out.println("Has seleccionado la opcion de borrar una carpeta.");
                        System.out.println("Introduce la ruta completa de la carpeta que deseas borrar: ");
                        sc.nextLine();
                        direccion = sc.nextLine();

                        if (existe(direccion) == false) {
                            // Existe y, por lo tanto, puede borrarse
                            borrarFichero(direccion);
                        } else {
                            // No existe
                            System.out.println("No existe ninguna carpeta con ese nombre.");
                        }
                        break;
                    case 7:
                        // Salir
                        System.out.println("Ha finalizado el programa.");
                        break;

                    default:
                        System.out.println("Has introducido un caracter erroneo.");
                        break;
                }

            } while ((menu < 0) || (menu > 7));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error con el scanner." + e.getMessage());
        }
    }
}
