
package br.ufc.russas.aloha.model;

public class VariaveisFixas {
    private Docente docente;
    private Disciplina disciplina;
    private String horario;
    private Sala sala;
    private Combo combo;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        System.out.println("ADD docente");
        this.docente = docente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public VariaveisFixas() {
        this.docente = new Docente();
        this.disciplina = new Disciplina();
        this.sala = new Sala();
        this.combo = new Combo();
    }

    @Override
    public String toString() {
        return "VariaveisFixas{" + "docente=" + docente.getNome() + ", disciplina=" + disciplina.getNome() + ", horario=" + horario + ", sala=" + sala.getNome() + ", combo=" + combo.getDiasEstendido() + '}';
    }
    
    
    
    
    
}
