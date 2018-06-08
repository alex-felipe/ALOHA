
package br.ufc.russas.aloha.model;

public class Preferencia {
    private Docente docente;
    private Disciplina disciplina;
    private int preferencia;

    public Preferencia(){
        docente = new Docente();
        disciplina = new Disciplina();
    }
    
    public Preferencia(Docente professor, Disciplina disciplina, int preferencia) {
        this();
        this.docente = professor;
        this.disciplina = disciplina;
        this.preferencia = preferencia;
    }
    
    
    
}
