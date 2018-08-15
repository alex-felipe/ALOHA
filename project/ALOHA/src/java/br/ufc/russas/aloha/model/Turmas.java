
package br.ufc.russas.aloha.model;

import java.util.Objects;


public class Turmas {
    private Disciplina disciplina;
    private int qntTurmas;

    public Turmas(Disciplina disciplina, int qntTurmas) {
        this.disciplina = disciplina;
        this.qntTurmas = qntTurmas;
    }

    public Turmas() {
        this.disciplina = new Disciplina();
    }

    
    
    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getQntTurmas() {
        return qntTurmas;
    }

    public void setQntTurmas(int qntTurmas) {
        System.out.println(qntTurmas);
        this.qntTurmas = qntTurmas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.disciplina);
        hash = 59 * hash + this.qntTurmas;
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
        final Turmas other = (Turmas) obj;
        if (this.qntTurmas != other.qntTurmas) {
            return false;
        }
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        return true;
    }
    
    
    
}
