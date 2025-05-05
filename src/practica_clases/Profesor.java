package practica_clases;
public class Profesor {
    private String dni;
    private String nombre;
    private int sueldo_hora;
    private int horas_trabajo;
    
    public Profesor(){
        dni = "";
        nombre = "";
        sueldo_hora = 0;
        horas_trabajo= 0;
    }
    
    public Profesor(String d, String nom, int sh){
        dni = d;
        nombre = nom;
        sueldo_hora = sh;
        horas_trabajo = 0;
    }

    @Override
    public String toString() {
        return "Profesor DNI=" + dni + ", Nombre=" + nombre + ", Sueldo por Hora=" + sueldo_hora + ", Horas de Trabajo=" + horas_trabajo;
    }
    
    public boolean Coincide(String d){
        if (d.equals( this.dni)) {
            return true;
        }
        else{
            return false;
        }
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
    
    
}
