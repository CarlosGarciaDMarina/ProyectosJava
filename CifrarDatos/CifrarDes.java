
//Librerias
import java.util.Scanner;
import javax.crypto.*;

//Clase
public class CifrarDes {

    // Metodo main
    public static void main(String[] args) {

        // Declaramos el Scanner y lo encerramos en un try-catch
        try (Scanner sc = new Scanner(System.in)) {

            // Variables
            String respuesta = "";
            String mensajeEncriptado = "";
            String mensajeDesencriptado = "";

            // Declaramos el generador de claves
            KeyGenerator keyGen = KeyGenerator.getInstance("DES"); // Genero la key simetrica DES
            SecretKey key = keyGen.generateKey(); // Aplico la opacidad
            Cipher desCipher = Cipher.getInstance("DES"); // Activo el cirador DES

            // Cifrar
            // Informamos que vamos a descifrar
            System.out.println("Comienza el proceso de encriptacion.");
            // Preguntamos al usuario que introduzca una palabra para cifrarla con DES
            System.out.println("Introduce la contraseña a cifrar: ");
            respuesta = sc.nextLine(); // Guardamos la respuesta

            // Especificamos que vamos a encriptar y le pasamos la llave
            desCipher.init(Cipher.ENCRYPT_MODE, key);

            // Mostramos el mensaje antes de encriptarlo
            System.out.println("El mensaje que vamos a encriptar es: " + respuesta);

            // Creamos una variable para almacenar el mensaje encriptado transformando las
            // respuesta en bytes para que desCipher pueda tratar con ello
            mensajeEncriptado = new String(desCipher.doFinal(respuesta.getBytes()));

            // Mostramos el mensaje cifrado
            System.out.println("El mensaje cifrado es: " + mensajeEncriptado);

            // Informamos que vamos a descifrar
            System.out.println("Comienza el proceso de desencriptacion.");

            // Descifrar
            desCipher.init(Cipher.DECRYPT_MODE, key);

            // Mostramos el mensaje encriptado
            System.out.println("El mensaje que vamos a desencriptar es: " + mensajeEncriptado);

            // Almacenamos el mensaje desencriptado
            mensajeDesencriptado = new String(desCipher.doFinal(mensajeEncriptado.getBytes()));

            // Mostramos por pantalla
            System.out.println("El mensaje desencriptado es: " + mensajeDesencriptado);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. " + e);

        }

    }

}
