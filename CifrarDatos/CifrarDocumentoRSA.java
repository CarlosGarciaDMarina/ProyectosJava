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

    // Funciones
    // Función para generar un par de claves pública y privada utilizando el
    // generador de claves RSA
    public static KeyPair generarKey() throws Exception {
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        return key.generateKeyPair();
    }

    // Función para cifrar el archivo de entrada utilizando la clave privada del par
    // de claves
    public static boolean archivoEncriptado(File f, KeyPair key) {
        try 
        {
            // Creamos un objeto de cifrado utilizando el algoritmo RSA
            Cipher desCipher = Cipher.getInstance("RSA");
            desCipher.init((Cipher.ENCRYPT_MODE), key.getPrivate());

            try (FileInputStream fis = new FileInputStream(f);
                    FileOutputStream fos = new FileOutputStream("D:/pruebas/FicheroCifrado", false)) {
                byte[] buffer = new byte[64];
                int byteLeidos = fis.read(buffer);

                // Leemos y ciframos el contenido del archivo por fragmentos
                while (byteLeidos != -1) {
                    byte[] cbuf = desCipher.doFinal(buffer, 0, byteLeidos);
                    fos.write(cbuf);
                    byteLeidos = fis.read(buffer);

                }
            }

            // Generamos y guardamos la clave publica en un archivo
            generarPublic(key);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Funcion para generar y guardar la clave publica en un archivo
    public static void generarPublic(KeyPair key) {
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = keyFac.getKeySpec(key.getPublic(), RSAPublicKeySpec.class);

            try (FileOutputStream cos = new FileOutputStream("D:/pruebas/ClavePublica");
                    PrintWriter pw = new PrintWriter(cos)) {
                pw.println(publicKeySpec.getModulus());
                pw.println(publicKeySpec.getPublicExponent());
            }
            System.out.println("Se ha generado el otro par de llaves con exito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion main
    public static void main(String[] args) {

        // Variables
        String direccion = "";

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Este programa sirve para cifrar documentos de texto.");
            System.out.println("Introduce la direccion del fichero que quieras: ");
            direccion = sc.nextLine();

            File f = new File(direccion);

            if (f.exists()) {
                // Si la carpeta existe generamos un par de claves publica y privada
                KeyPair key = generarKey();

                if (key != null) {
                    // Ciframos el qarchivo utilizando el par de llaves
                    if (archivoEncriptado(f, key)) {
                        System.out.println("El archivo ha sido cifrado con exito.");
                    } else {
                        System.out.println("Error al cifrar el archivo.");
                    }
                } else {
                    System.out.println("Error al generar el par de llaves.");
                }

            } else {
                System.out.println("El fichjero no existe. ");
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
        }
    }
}