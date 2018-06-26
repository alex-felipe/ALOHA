
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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(int preferencia) {
        this.preferencia = preferencia;
    }
    
    
    
}
