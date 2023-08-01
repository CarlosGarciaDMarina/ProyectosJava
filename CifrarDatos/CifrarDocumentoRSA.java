import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

public class CifrarDocumentoRSA {

    // Función para generar un par de claves pública y privada utilizando el generador de claves RSA
    public static KeyPair generarKey() throws Exception {
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        return key.generateKeyPair();
    }

    // Función para cifrar el archivo de entrada utilizando la clave privada del par de claves
    public static boolean archivoEncriptado(File inputFile, KeyPair key) {
        try {
            // Creamos un objeto de cifrado utilizando el algoritmo RSA
            Cipher desCipher = Cipher.getInstance("RSA");
            desCipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());

            try (FileInputStream fis = new FileInputStream(inputFile);
                    FileOutputStream fos = new FileOutputStream("D:/pruebas/FicheroCifrado", false)) {

                byte[] buffer = new byte[256]; // Tamaño del buffer aumentado para mejorar rendimiento
                int bytesLeidos;

                // Leemos y ciframos el contenido del archivo por fragmentos
                while ((bytesLeidos = fis.read(buffer)) != -1) {
                    byte[] cbuf = desCipher.doFinal(buffer, 0, bytesLeidos);
                    fos.write(cbuf);
                }
            }

            // Generamos y guardamos la clave pública en un archivo
            generarPublic(key);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Función para generar y guardar la clave privada en un archivo
    public static void generarClavePrivada(KeyPair keypair) {
        try {
            PrivateKey privateKey = keypair.getPrivate();
            byte[] privateKeyBytes = privateKey.getEncoded();

            try (FileOutputStream fos = new FileOutputStream("D:/pruebas/ClavePrivada")) {
                fos.write(privateKeyBytes);
            }
            System.out.println("Se ha generado y guardado la clave privada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Función para generar y guardar la clave pública en un archivo
    public static void generarPublic(KeyPair key) {
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec publicKeySpec = keyFac.getKeySpec(key.getPublic(), RSAPublicKeySpec.class);

            try (FileOutputStream cos = new FileOutputStream("D:/pruebas/ClavePublica");
                    PrintWriter pw = new PrintWriter(cos)) {
                pw.println(publicKeySpec.getModulus());
                pw.println(publicKeySpec.getPublicExponent());
            }
            System.out.println("Se ha generado el otro par de llaves con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Función main
    public static void main(String[] args) {
        // Variables
        String direccion = "";

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Este programa sirve para cifrar documentos de texto.");
            System.out.println("Introduce la dirección del fichero que quieras: ");
            direccion = sc.nextLine();

            File inputFile = new File(direccion);

            if (inputFile.exists()) {
                // Si el archivo existe, generamos un par de claves pública y privada
                KeyPair key = generarKey();

                if (key != null) {
                    // Ciframos el archivo utilizando el par de claves
                    if (archivoEncriptado(inputFile, key)) {
                        System.out.println("El archivo ha sido cifrado con éxito.");
                    } else {
                        System.out.println("Error al cifrar el archivo.");
                    }

                    // Guardamos la clave privada en un archivo
                    generarClavePrivada(key);

                } else {
                    System.out.println("Error al generar el par de claves.");
                }

            } else {
                System.out.println("El fichero no existe.");
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
        }
    }
}
