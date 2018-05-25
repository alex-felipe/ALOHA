
package br.ufc.russas.aloha.model;

import java.util.List;


public class Planejamento {
    private int ano;
    private int periodo;
    private List<Disciplina> Turmas;
    private List<Docente> professores;
    private List<Sala> salas;

    public Planejamento(int ano, int periodo) {
        this.ano = ano;
        this.periodo = periodo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public List<Disciplina> getTurmas() {
        return Turmas;
    }

    public void setTurmas(List<Disciplina> Turmas) {
        this.Turmas = Turmas;
    }

    public List<Docente> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Docente> professores) {
        this.professores = professores;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
    
    
    
}
