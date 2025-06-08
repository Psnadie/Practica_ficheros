package practica_clases;

public class Persona {
    protected String dni;
    protected String nombre;
    
    public Persona(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
    
    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean Coincide(String dni) {
        return this.dni.equals(dni);
    }
    
    @Override
    public String toString() {
        return "DNI: " + dni + ", Nombre: " + nombre;
    }
} 