
package Model;

public class Preferencia {
    private Docente professor;
    private Disciplina disciplina;
    private int preferencia;

    public Preferencia(){
        professor = new Docente();
        disciplina = new Disciplina();
    }
    
    public Preferencia(Docente professor, Disciplina disciplina, int preferencia) {
        this();
        this.professor = professor;
        this.disciplina = disciplina;
        this.preferencia = preferencia;
    }
    
    
    
}
