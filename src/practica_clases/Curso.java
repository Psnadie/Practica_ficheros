package practica_clases;
public class Curso {
    private String nombre;
    private Asignaturas[] asig;
    private Alumno[] alumnos;
    private int año;
    private int aula;
    private int matricula;
    
    public Curso(){
        nombre = "";
        asig = new Asignaturas[5];
        alumnos = new Alumno[30];
        año = 0;
        aula = 1000;
    }
    public Curso(String nom, Asignaturas[] a, Alumno[] alu, int añ, int aul, int m){
        nombre = nom;
        asig = a;
        alumnos = alu;
        año = añ;
        aula = aul;
        matricula = m;
    }

    public void imprimirCurso() {
    System.out.println("Curso: " + this.getNombre());
    System.out.println("Matrícula: " + this.getMatricula());
    
    // Imprimir asignaturas
    System.out.println("Asignaturas:");
    if (this.getAsig() == null || this.getAsig().length == 0) {
        System.out.println("  No hay asignaturas en este curso.");
    } else {
        for (Asignaturas asignatura : this.getAsig()) {
            if (asignatura != null) {
                System.out.println("  - " + asignatura.getNombre());
            }
        }
    }

    // Imprimir alumnos
    System.out.println("Alumnos inscritos:");
    if (this.getAlumnos() == null || this.getAlumnos().length == 0) {
        System.out.println("  No hay alumnos inscritos.");
    } else {
        for (Alumno alumno : this.getAlumnos()) {
            if (alumno != null) {
                System.out.println("  - " + alumno.getNombre() + " (" + alumno.getPago() + ")");
            }
        }
    }
}
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Asignaturas[] getAsig() {
        return asig;
    }

    public void setAsig(Asignaturas[] asig) {
        this.asig = asig;
    }

    public Alumno[] getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumno[] alumnos) {
        this.alumnos = alumnos;
    }

    public int getAño() {
        return año;
    }


    public void setAño(int año) {
        this.año = año;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }
    
    public int getMatricula(){
        return this.matricula;
    }
    
    public void setMatricula(int matricula){
        this.matricula = matricula;
    }
}
