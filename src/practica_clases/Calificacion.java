package practica_clases;

public class Calificacion {
    private int codigoAsignatura;
    private double nota;
    
    public Calificacion(int codigoAsignatura, double nota) {
        this.codigoAsignatura = codigoAsignatura;
        this.nota = nota;
    }
    
    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }
    
    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }
    
    public double getNota() {
        return nota;
    }
    
    public void setNota(double nota) {
        this.nota = nota;
    }
    
    @Override
    public String toString() {
        return "Asignatura: " + codigoAsignatura + ", Nota: " + nota;
    }
} 