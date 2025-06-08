package practica_clases;

public class Profesor extends Persona {
    private int sueldo_hora;
    private int horas_trabajo;
    
    public Profesor(String dni, String nombre, int sueldo_hora) {
        super(dni, nombre);
        this.sueldo_hora = sueldo_hora;
        this.horas_trabajo = 0;
    }
    
    public int getSueldo_hora() {
        return sueldo_hora;
    }
    
    public void setSueldo_hora(int sueldo_hora) {
        this.sueldo_hora = sueldo_hora;
    }
    
    public int getHoras_trabajo() {
        return horas_trabajo;
    }
    
    public void setHoras_trabajo(int horas_trabajo) {
        this.horas_trabajo = horas_trabajo;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Sueldo por hora: " + sueldo_hora + "€, Horas de trabajo: " + horas_trabajo;
    }
}
