import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {

        // Declaramos un scanner
        try (Scanner sc = new Scanner(System.in)) {
            // Variables
            int i = 0;
            String respuesta = "";
            boolean esEntero = true;
            boolean esFloat = true;
            int entero = 0;
            float flotante = 0.0f;

            //Pedimos al usuario que ingrese un mensaje
            System.out.println("Ingresa un mensaje: ");
            respuesta = sc.nextLine();

            //Vamos a determinar que tipo de dato a introducido
            for(i=0;i<respuesta.length();i++)
            {
                //Si al menos 1 caracter es un numero devuelve false y, por lo tanto, no es un entero.
                if (!Character.isDigit(respuesta.charAt(i))) {
                    esEntero = false;
                    break;
                }
            }
            
            //Vamos a comprobar si es flotante
            try {
                Float.parseFloat(respuesta);
            } catch (NumberFormatException e) {
                esFloat = false;
            }

            //Dependiendo del tipo de dato que sea devuelve un mensaje
            if (esEntero) {
                entero = Integer.parseInt(respuesta);
                System.out.println("El mensaje que has introducido es: " + entero + " y es de tipo entero.");
            }
            else if(esFloat)
            {
                flotante = Float.parseFloat(respuesta);
                System.out.println("El mensaje que has introducido es: " + flotante + " y es de tipo flotante.");
            }
            else
            {
                System.out.println("El mensaje que has introducido es: " + respuesta + " y es de tipo string.");
            }


        } catch (Exception e) {
            System.out.println("Ha ocurrido un error " + e);
        }

    }

}
