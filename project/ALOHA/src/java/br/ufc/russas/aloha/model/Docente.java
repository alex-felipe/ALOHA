
package br.ufc.russas.aloha.model;

import java.util.ArrayList;
import java.util.List;

public class Docente {
    
    private int id;
    private String codigo_modelo;
    private String nome;
    private int crMin;
    private int crMax;
    private List<Combo> combos;
    private List<Preferencia> preferencias;

    public Docente() {
        this.combos = new ArrayList<Combo>();
        this.preferencias = new ArrayList<Preferencia>();
    }

    public Docente(String nome, int crMin, int crMax) {
        this.nome = nome;
        this.crMin = crMin;
        this.crMax = crMax;
    }
    
    public void selecionarCombos(List dias){
        
    }
    
    public String gerarCodigo(){
        return null;
    }
    
    public void addDisciplina(Preferencia disciplina){
        preferencias.add(disciplina);
    }
    public void addCombo(Combo combo){
        combos.add(combo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_modelo() {
        return codigo_modelo;
    }

    public void setCodigo_modelo(String codigo_modelo) {
        this.codigo_modelo = codigo_modelo;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome !=null) this.nome = nome;
    }

    public int getCrMin() {
        return crMin;
    }

    public void setCrMin(int crMin) {
        if(crMin>0) this.crMin = crMin;
    }

    public int getCrMax() {
        return crMax;
    }

    public void setCrMax(int crMax) {
        if(crMax >0) this.crMax = crMax;
        
        
    }

    public List<Combo> getCombos() {
        return combos;
    }

    private  void setCombos(List<Combo> combos) {
        this.combos = combos;
    }

    public List<Preferencia> getPreferencias() {
        return preferencias;
    }

    private void setPreferencias(List<Preferencia> preferencias) {
        this.preferencias = preferencias;
    }
    
    
    
    
    
}
