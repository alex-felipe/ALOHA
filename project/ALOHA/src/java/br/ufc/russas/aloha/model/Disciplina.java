
package br.ufc.russas.aloha.model;

import java.util.List;

public class Disciplina {

    //Implementar os tratamentos
    private String id;
    private String codigo_modelo;
    private String codigo;
    private String nome;
    private int crPraticos;
    private int crTeoricos;
    private int vagas;
    private String tipo_sala;
    private List<CursoSemestre> cursos_semestres;
    

    public Disciplina() {
    
    }

    public Disciplina(String codigo, String nome, int crPraticos, int crTeoricos, int vagas) {
        this(codigo, nome, vagas);
        setCrPraticos(crPraticos);
        setCrTeoricos(crTeoricos);
        
    }

    public Disciplina(String codigo, String nome, int vagas) {
        setCodigo(codigo);
        setNome(nome);
        setVagas(vagas);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if(codigo !=null)this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome!= null)this.nome = nome;
    }

    public int getCrPraticos() {
        return crPraticos;
    }

    public void setCrPraticos(int crPraticos) {
        if(crPraticos >0 && crPraticos <6) this.crPraticos = crPraticos;
    }

    public int getCrTeoricos() {
        return crTeoricos;
    }

    public void setCrTeoricos(int crTeoricos) {
        if(crTeoricos >0 && crTeoricos <6)this.crTeoricos = crTeoricos;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        if (vagas >0) this.vagas = vagas;
    }
    
    
    
    
}
