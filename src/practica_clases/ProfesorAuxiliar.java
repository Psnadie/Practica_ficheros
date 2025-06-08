package practica_clases;

public class ProfesorAuxiliar extends Profesor {
    private Profesor profesorSustituido;
    
    public ProfesorAuxiliar(String dni, String nombre, Profesor profesorSustituido) {
        super(dni, nombre, profesorSustituido.getSueldo_hora());
        this.profesorSustituido = profesorSustituido;
    }
    
    @Override
    public void setHoras_trabajo(int horas_trabajo) {
        if (horas_trabajo > 10) {
            throw new IllegalArgumentException("Un profesor auxiliar no puede trabajar más de 10 horas semanales");
        }
        super.setHoras_trabajo(horas_trabajo);
    }
    
    public boolean puedeAsignarAsignatura(Asignaturas asignatura) {
        // Aqui verificamos que haya un profesor asignado, si no, no puede asignar la asignatura
        Profesor profesorActual = asignatura.getProfesor();
        if (profesorActual == null) {
            return false;
        }
        
        // Luego verificamos que sea el profesor que dicta la asignatura sea el mismo que el profesor que sustituye
        //Si no es el mismo, no puede asignar la asignatura (devuelve false)
        return profesorActual.getDni().equals(profesorSustituido.getDni());
    }
    
    public Profesor getProfesorSustituido() {
        return profesorSustituido;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Sustituyendo a: " + profesorSustituido.getNombre();
    }
} 