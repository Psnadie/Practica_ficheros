package practica_clases;

public class AlumnoPPlazos extends Alumno {
    
    public AlumnoPPlazos(String dni, String nombre, String apellidos, int año) {
        super(dni, nombre, apellidos, año);
    }
    
    @Override
    public String getPago() {
        return "Plazos";
    }
} 