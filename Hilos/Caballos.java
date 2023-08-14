package Hilos;

public class Caballos implements Runnable {

    // Atributos
    protected String nombre;
    protected int id;
    protected int segundos = 5;
    protected long tiempoFinalizacion;

    // Constructor
    public Caballos(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        //this.segundos = segundos;
    }

    // Métodos
    // Este metodo hace que los caballos empiecen a correr
    public void run() {
        // Encerramos en try-catch para gestionar errores
        try {
            // Con esto asignamos un numero aleatorio a segundos
            segundos = (int) (Math.random() * 20);

            if (id == 1) {
                // Avisamos que sale el caballo
                System.out.println("Sale " + nombre);

                // Introducimos la pausa del hilo
                Thread.sleep(segundos * 100);

                // Avisamos del resultado
                System.out.println(nombre + " ha tardado " + segundos + " segundos.");

            } else if (id == 2) {
                // Avisamos que sale el caballo
                System.out.println("Sale " + nombre);

                // Introducimos la pausa del hilo
                Thread.sleep(segundos * 100);

                // Avisamos del resultado
                System.out.println(nombre + " ha tardado " + segundos + " segundos.");

            } else if (id == 3) {
                // Avisamos que sale el caballo
                System.out.println("Sale " + nombre);

                // Introducimos la pausa del hilo
                Thread.sleep(segundos * 100);

                // Avisamos del resultado
                System.out.println(nombre + " ha tardado " + segundos + " segundos.");

            } else {
                // Control de errores
                System.out.println("Id no valida.");

            }

            // Almacenamos el tiempo de finalizacion
            tiempoFinalizacion = System.currentTimeMillis();

        } catch (Exception e) {
            // Control de errores
            System.out.println("Ha ocurrido un error inesperado." + e.getMessage());
        }
    }

    // Getters & Setters
    // Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Segundos
    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    // Tiempo de finalizacion
    public long getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

}
