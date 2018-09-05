
package br.ufc.russas.aloha.model;

import java.util.Objects;

public class VariaveisFixas {
    private Docente docente;
    private Disciplina disciplina;
    private Horario horario;
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
        this.docente = docente;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
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
        this.horario = new Horario();
         this.combo = new Combo();
    }

    @Override
    public String toString() {
        return "VariaveisFixas{" + "docente=" + docente.getNome() + ", disciplina=" + disciplina.getNome() + ", horario=" + horario + ", sala=" + sala.getNome() + ", combo=" + combo.getDiasEstendido() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.docente);
        hash = 89 * hash + Objects.hashCode(this.disciplina);
        hash = 89 * hash + Objects.hashCode(this.horario);
        hash = 89 * hash + Objects.hashCode(this.sala);
        hash = 89 * hash + Objects.hashCode(this.combo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariaveisFixas other = (VariaveisFixas) obj;
        if (!Objects.equals(this.docente, other.docente)) {
            return false;
        }
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        if (!Objects.equals(this.sala, other.sala)) {
            return false;
        }
        if (!Objects.equals(this.combo, other.combo)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
