package co.edu.uniquindio.poo.desastermanager;

public abstract class Persona {
    private String nombrePersona;
    private String apellidosPersona;
    private int edad;
    private String idPersona;

    // Constructor
    public Persona(String nombrePersona, String apellidosPersona, int edad, String idPersona) {
        this.nombrePersona = nombrePersona;
        this.apellidosPersona = apellidosPersona;
        this.edad = edad;
        this.idPersona = idPersona;
    }

    // Getters
    public String getNombrePersona() {
        return nombrePersona;
    }

    public String getApellidosPersona() {
        return apellidosPersona;
    }

    public int getEdad() {
        return edad;
    }

    public String getIdPersona() {
        return idPersona;
    }

    // Método abstracto
    public abstract void crear();
    public abstract Persona leer();
    public abstract void actualizar();
    public abstract void eliminar();


    @Override
    public String toString() {
        return nombrePersona + " " + apellidosPersona + " (ID: " + idPersona + ", edad: " + edad + ")";
    }
}

