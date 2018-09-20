package br.ufc.russas.aloha.model;

import java.util.Objects;

public class Turmas implements Comparable<Turmas> {

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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.disciplina);

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
        
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Turmas o) {
        if (o == this || o.equals(this)) {
            return 1;    
        }else{
            return 0;
        }
        
    }

}
