package practica_clases;
public class Alumno {
    private String dni;
    private String nombre;
    private String apellidos;
    private int año;
    private Curso curso;
    private String pago;
    
    public Alumno(){
        dni = "";
        nombre = "";
        apellidos = "";
        año = 0;
        curso  = new Curso();
        pago = "";
    }
    public Alumno(String d, String nom, String ape, int a, String p){
        dni = d;
        nombre = nom;
        apellidos = ape;
        año = a;
        curso  = new Curso();
        pago = p;
    }
    @Override
    public String toString() {
        return "Alumno DNI=" + dni + ", Nombre=" + nombre + ", Apellidos=" + apellidos 
                + ", Año=" + año + ", Tipo de Pago=" + pago;
    }
    
    public void copiarDesde(Alumno otro) {
        if (otro != null) {
            this.dni = otro.dni;
            this.nombre = otro.nombre;
            this.apellidos = otro.apellidos;
            this.año = otro.año;
            this.pago = otro.pago;
            this.curso = otro.curso;

        }
    }
    public boolean Coincide(String d){
        if (d.equals( this.dni)) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean equals(Alumno a){
        if (this.dni.equals(a.dni)) {
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public String getPago(){
        return pago;
    }
    public void setPago(String pago){
        this.pago = pago;
    }
    
    
}
