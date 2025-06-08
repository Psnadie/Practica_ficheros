package practica_clases;

import java.util.ArrayList;
import java.util.List;

public abstract class Alumno extends Persona {
    protected String apellidos;
    protected int año;
    //aqui uso list para poder añadir calificaciones sin tener que pasarmela creando y eliminando arrays cada vez que quiera añadir una nota
    //Asi no tengo que hacer un metodo para añadir una calificacion y otro para eliminar una (Ambos siendo mas complejos que con list)
    protected List<Calificacion> calificaciones;
    
    public Alumno(String dni, String nombre, String apellidos, int año) {
        super(dni, nombre);
        this.apellidos = apellidos;
        this.año = año;
        this.calificaciones = new ArrayList<>();
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public int getAño() {
        return año;
    }
    
    public void setAño(int año) {
        this.año = año;
    }
    
    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }
    //Metodo para añadir una calificacion
    //aqui pasa lo mismo que con el StringBuilder. Lo vi, me parecio interesante y lo he usado
    //Tambien es mas facil de leer y entender que con arrays, ya que no hay que crear un array nuevo cada vez que se quiera añadir una nota
    //solo hay que añadir el metodo add y ya esta :D
    public void agregarCalificacion(int codigoAsignatura, double nota) {
        calificaciones.add(new Calificacion(codigoAsignatura, nota));
    }
    
    public abstract String getPago();
    
    @Override
    public String toString() {
        return super.toString() + ", Apellidos: " + apellidos + ", Año: " + año + ", Tipo de pago: " + getPago();
    }
    
    public void copiarDesde(Alumno otro) {
        this.dni = otro.dni;
        this.nombre = otro.nombre;
        this.apellidos = otro.apellidos;
        this.año = otro.año;
        this.calificaciones = new ArrayList<>(otro.calificaciones);
    }
}
