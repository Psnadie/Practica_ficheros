package practica_clases;

public class AlumnoPUnico extends Alumno {
    
    public AlumnoPUnico(String dni, String nombre, String apellidos, int año) {
        super(dni, nombre, apellidos, año);
    }
    
    @Override
    public String getPago() {
        return "Completo";
    }
} 