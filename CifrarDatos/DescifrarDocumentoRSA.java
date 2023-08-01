import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

public class DescifrarDocumentoRSA {

    // Función para leer la clave privada desde el archivo en formato base64
    public static PrivateKey leerClavePrivada() {
        try {
            try (FileInputStream fis = new FileInputStream("D:/pruebas/ClavePrivada")) {
                byte[] privateKeyBytes = fis.readAllBytes();
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                return keyFactory.generatePrivate(privateKeySpec);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Función para descifrar el archivo utilizando la clave privada
    public static boolean descifrarArchivo(PrivateKey privateKey) {
        try {
            Cipher desCipher = Cipher.getInstance("RSA");
            desCipher.init(Cipher.DECRYPT_MODE, privateKey);

            try (FileInputStream fis = new FileInputStream("D:/pruebas/FicheroCifrado");
                    FileOutputStream fos = new FileOutputStream("D:/pruebas/FicheroDescifrado", false)) {

                byte[] buffer = new byte[256]; // Tamaño del buffer aumentado para mejorar rendimiento
                int bytesLeidos;

                // Leemos y desciframos el contenido del archivo por fragmentos
                while ((bytesLeidos = fis.read(buffer)) != -1) {
                    byte[] cbuf = desCipher.doFinal(buffer,0,bytesLeidos);
                    fos.write(cbuf);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            // Leer la clave privada desde el archivo
            PrivateKey privateKey = leerClavePrivada();

            if (privateKey != null) {
                // Descifrar utilizando la clave privada
                if (descifrarArchivo(privateKey)) {
                    System.out.println("El archivo ha sido descifrado con éxito.");
                } else {
                    System.out.println("Error al descifrar el archivo.");
                }
            } else {
                System.out.println("Error al leer la clave privada.");
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado. " + e.getMessage());
        }
    }
}

