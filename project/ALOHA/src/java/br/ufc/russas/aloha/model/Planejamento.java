
package br.ufc.russas.aloha.model;

import java.util.ArrayList;
import java.util.List;


public class Planejamento {
    
    private int id;
    private String nome;
    private List<Turmas> turmas;
    private List<Docente> docentes;
    private List<Sala> salas;
    private List<VariaveisFixas> variaveisFixas;
    private boolean finalizado;

    public Planejamento() {
        this.turmas = new ArrayList<>();
        this.docentes =  new ArrayList<>();
        this.salas = new ArrayList<>();
        this.variaveisFixas = new ArrayList<>();
    }
    public Planejamento(String nome){
        super();
        this.nome = nome;
        this.finalizado = false;
    }

    public Planejamento(String nome, List<Turmas> turmas, List<Docente> docentes, List<Sala> salas, List<VariaveisFixas> variaveisFixas, boolean isFinalizado) {
        this.nome = nome;
        this.turmas = turmas;
        this.docentes = docentes;
        this.salas = salas;
        this.variaveisFixas = variaveisFixas;
        this.finalizado = isFinalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turmas> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turmas> turmas) {
        this.turmas = turmas;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<VariaveisFixas> getVariaveisFixas() {
        return variaveisFixas;
    }

    public void setVariaveisFixas(List<VariaveisFixas> variaveisFixas) {
        this.variaveisFixas = variaveisFixas;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    

  
    
    
    
}
