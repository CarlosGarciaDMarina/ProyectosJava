import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CifrarAESMejorado {

    // Funciones
    public static byte[] longToBytes(long numero) {
        return ByteBuffer.allocate(8).putLong(numero).array();
    }

    // Metodo main
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // Declaramos el Scanner y lo encerramos en un try-catch
        try (Scanner sc = new Scanner(System.in)) {

            // Variables
            String respuesta = "";
            String mensajeEncriptado = "";
            String mensajeDesencriptado = "";
            int i = 0;
            boolean esEntero = true;
            int respuestaNumerica = 0;
            byte[] numerosCifrados = null;

            // Declaramos el generador de claves
            KeyGenerator keyGen = KeyGenerator.getInstance("AES"); // Genero la key simetrica DES
            SecretKey key = keyGen.generateKey(); // Aplico la opacidad
            Cipher desCipher = Cipher.getInstance("AES"); // Activo el cirador DES

            // Cifrar
            // Informamos que vamos a descifrar
            System.out.println("Comienza el proceso de encriptacion.");
            // Preguntamos al usuario que introduzca una palabra para cifrarla con DES
            System.out.println("Introduce la contraseña a cifrar: ");
            respuesta = sc.nextLine(); // Guardamos la respuesta

            // Ahora vamos a determinar si la constraseña introducida por el usuario es int o string
            for (i = 0; i < respuesta.length(); i++) {
                // Si al menos 1 caracter es un numero devuelve false y, por lo tanto, no es un
                // entero.
                if (!Character.isDigit(respuesta.charAt(i))) {
                    esEntero = false;
                    break;
                }
            }

            if (esEntero) {

                // Transformamos a integer
                respuestaNumerica = Integer.parseInt(respuesta);

                // Mostramos el mensaje antes de encriptarlo
                System.out.println("El mensaje que vamos a encriptar es: " + respuestaNumerica);

                // Usamos la funcion para transformar numeros en bytes
                byte[] numeros = longToBytes(respuestaNumerica);

                // Especificamos que vamos a encriptar y le pasamos la llave
                desCipher.init(Cipher.ENCRYPT_MODE, key);

                // Aplicamos la key a la contraseña y la encriptamos
                numerosCifrados = desCipher.doFinal(numeros);

                // Mostramos la encriptacion por pantalla
                System.out.println("EL mensaje encriptado es: " + numerosCifrados);

            } else {

                // Especificamos que vamos a encriptar y le pasamos la llave
                desCipher.init(Cipher.ENCRYPT_MODE, key);

                // Mostramos el mensaje antes de encriptarlo
                System.out.println("El mensaje que vamos a encriptar es: " + respuesta);

                // Creamos una variable para almacenar el mensaje encriptado transformando las
                // respuesta en bytes para que desCipher pueda tratar con ello
                mensajeEncriptado = new String(desCipher.doFinal(respuesta.getBytes()));

                // Mostramos el mensaje cifrado
                System.out.println("El mensaje cifrado es: " + mensajeEncriptado);

            }

            // Informamos que vamos a descifrar
            System.out.println("Comienza el proceso de desencriptacion.");

            /* IMPORTANTE */
            // Cambiamos al modo de desencriptacion pero lo alojamos fuera del if porque sino generará otro par de llave y no podrá desencriptar
            desCipher.init(Cipher.DECRYPT_MODE, key);

            //Segundo condicional para desencriptar
            if (esEntero) {

                // Mostramos el mensaje encriptado
                System.out.println("El mensaje que vamos a desencriptar es: " + numerosCifrados);

                // Desencriptamos los datos
                byte[] numerosDesencriptados = desCipher.doFinal(numerosCifrados);

                //Transformamos los bytes to long
                long numeroDes = ByteBuffer.wrap(numerosDesencriptados).getLong();

                // Mostramos por pantalla el resultado
                System.out.println("El mensaje desencriptado es: " + numeroDes);

            } else {

                // Mostramos el mensaje encriptado
                System.out.println("El mensaje que vamos a desencriptar es: " + mensajeEncriptado);

                // Almacenamos el mensaje desencriptado
                mensajeDesencriptado = new String(desCipher.doFinal(mensajeEncriptado.getBytes()));

                // Mostramos por pantalla
                System.out.println("El mensaje desencriptado es: " + mensajeDesencriptado);
            }
            

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error. " + e.getMessage());
        }

    }
}
