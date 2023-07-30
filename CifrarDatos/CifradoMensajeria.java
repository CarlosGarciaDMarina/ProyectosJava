import java.util.Scanner;

public class CifradoMensajeria {

    public static void main(String[] args) {
        // Declaramos un scanner para poder recoger los datos
        try (Scanner sc = new Scanner(System.in)) {
            // Variables
            String mensaje = "";
            String encriptado = "";
            String desencriptado = "";
            int i = 0;

            // Mostramos los mensajes al usuario
            System.out.println("Este programa se encarga de cifrar una palabra que introduzcas.");
            System.out.println("Introduce una palabra: ");
            mensaje = sc.nextLine();
            System.out.println("");

            // mostramos la palabra introducida por el usuario
            System.out.println("La palabra que has introducido es: " + mensaje);
            System.out.println("");

            // Almacenamos la palabra en un array de tipo char para tratar letra a letra
            char cadena[] = mensaje.toCharArray();

            // Creamos un bucle que recorra la cadena caracter a caracter
            for (i = 0; i < cadena.length; i++) {
                // Vamos cambiando las letras
                if (cadena[i] == 'a') {
                    cadena[i] = 'i';
                } else if (cadena[i] == 'e') {
                    cadena[i] = 'o';
                } else if (cadena[i] == 'i') {
                    cadena[i] = 'u';
                } else if (cadena[i] == 'o') {
                    cadena[i] = 'e';
                } else if (cadena[i] == 'u') {
                    cadena[i] = 'a';
                }

            }

            // Mensaje de que ha sido un exito
            System.out.println("El mensaje ha sido encriptado correctamente");
            System.out.println("");

            // Lo transformamos a tipo string
            encriptado = String.valueOf(cadena);

            // Mostramos el mensaje encriptado
            System.out.println("El mensaje cifrado es: ");
            System.out.println(encriptado);
            System.out.println("");

            // Ahora vamos a descifrar el mensaje
            System.out.println("Ahora vamos a devolverlo a su estado original");
            System.out.println("");

            // Transformamos el string encriptado en tipo char
            char cadenaDes[] = encriptado.toCharArray();

            i = 0;

            // Creamos un bucle que recorra la cadena caracter a caracter
            for (i = 0; i < cadenaDes.length; i++) {
                // Vamos cambiando las letras
                if (cadenaDes[i] == 'i') {
                    cadenaDes[i] = 'a';
                } else if (cadenaDes[i] == 'o') {
                    cadenaDes[i] = 'e';
                } else if (cadenaDes[i] == 'u') {
                    cadenaDes[i] = 'i';
                } else if (cadenaDes[i] == 'e') {
                    cadenaDes[i] = 'o';
                } else if (cadenaDes[i] == 'a') {
                    cadenaDes[i] = 'u';
                }
            }
            // Informamos al usuario
            System.out.println("La desencriptacion ha sido un exito.");
            System.out.println("");

            // Lo transformamos a tipo string
            desencriptado = String.valueOf(cadenaDes);

            // Mostramos el mensaje desencriptado
            System.out.println("El mensaje desencriptado es: ");
            System.out.println(desencriptado);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado" + e);
        }

    }
}