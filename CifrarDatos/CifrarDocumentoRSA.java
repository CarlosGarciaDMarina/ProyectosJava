import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;
import javax.crypto.Cipher;


public class CifrarDocumentoRSA {

    public static void main(String[] args) {

        // Variables que vamos a necesitar
        String direccion = "";

        File f = null;

        //Encerramos en un try el scanner para asegurarnos de que se cierra adecuadamente despues de finalizar su uso
        try (Scanner sc = new Scanner(System.in)) {

            // Declaramos el generador de claves asimétricas RSA
            KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
            
            // Generamos un par de claves pública y privada utilizando el generador de claves
            KeyPair keypair = keygen.generateKeyPair();

            // Creamos un objeto de cifrado utilizando el algoritmo RSA
            Cipher desCipher = Cipher.getInstance("RSA");

            // Inicializamos el objeto de cifrado en modo de cifrado utilizando la clave privada del par de claves
            // Esto nos permitirá cifrar el archivo con el algoritmo RSA
            desCipher.init(Cipher.ENCRYPT_MODE, keypair.getPrivate());

            // Mostramos por pantalla la informacion para el usuario
            System.out.println("Este programa cifra documentos de texto.");
            System.out.println("Introduce la direccion del fichero que quieras cifrar: ");
            direccion = sc.nextLine(); // Capturamos la direccion introducida por el usuario

            // Abrimos el fichero
            f = new File(direccion);

            //Comprobamos si el fichero existe
            if (f.exists()) 
            {
                // Ciframos el fichero
                // Creamos un objeto FileInputStream para leer el contenido del fichero original
                FileInputStream fis = new FileInputStream(f);

                // Creamos un objeto FileOutputStream para escribir el contenido cifrado en el fichero de destino
                try (FileOutputStream fos = new FileOutputStream("D:/pruebas/FicheroCifrado", false)) {

                    // Creamos un buffer de bytes para leer y cifrar los datos por fragmentos
                    byte[] buffer = new byte[64];

                    // Leemos el primer fragmento de bytes del fichero original
                    int byteLeidos = fis.read(buffer);

                    // Iniciamos un bucle para cifrar y escribir los datos por fragmentos
                    while (byteLeidos != -1) {

                        // Ciframos el fragmento actual utilizando el objeto Cipher (desCipher) en modo de cifrado
                        // El método doFinal realiza el cifrado y devuelve los bytes cifrados en el array "cbuf"
                        byte[] cbuf = desCipher.doFinal(buffer, 0, byteLeidos);

                        // Escribimos los bytes cifrados en el fichero de destino utilizando el objeto FileOutputStream
                        fos.write(cbuf);

                        // Leemos el siguiente fragmento de bytes del fichero original para seguir cifrando
                        byteLeidos = fis.read(buffer);
                    }
                }

                System.out.println("El archivo ha sido cifrado con exito.");
                
            } else {

                // Si el fichero no existe, mostramos un mensaje de error por pantalla
                System.out.println("El fichero no existe.");
            }

            // Generamos el otro par der llaves
            KeyFactory keyfac = KeyFactory.getInstance("RSA");
            System.out.println("Generando keyspec");

            // Creamos una especificación de la clave pública a partir de la clave pública generada anteriormente
            RSAPublicKeySpec publicKeySpec = keyfac.getKeySpec(keypair.getPublic(), RSAPublicKeySpec.class);

            // Escribimos la clave pública en un archivo para poder usarla en el proceso de desencriptación posteriormente
            FileOutputStream cos = new FileOutputStream("D:/pruebas/ClavePublica"); // Especificamos la ruta del archivo
            PrintWriter pw = new PrintWriter(cos); // Preparamos el escritor para escribir en el archivo
            pw.println(publicKeySpec.getModulus()); // Escribimos el módulo de la clave pública
            pw.println(publicKeySpec.getPublicExponent()); // Escribimos el exponente público de la clave
            pw.close(); // Cerramos el escritor para finalizar la escritura en el archivo
            System.out.println("Se ha generado el otro par de llaves con exito.");

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
        }
    }
}
