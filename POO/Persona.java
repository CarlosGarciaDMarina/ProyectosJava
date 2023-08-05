package POO;

public class Persona {

    //Atributos
    protected String nombre;
    protected String apellidos;
    protected String ciudad;
    protected String nacionalidad;
    protected int edad;

    //Constructor
    public Persona(String nombre, String apellidos, String ciudad, String nacionalidad, int edad){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
    }

    //Metodos
    public void print(){
        System.out.println("Informacion de la persona:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Ciudad: " + ciudad);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Edad: " + edad);
    }

    //Getters & Setters
    //Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Apellidos
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    //Ciudad
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    //Nacionalidad
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    //Edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }    

}
