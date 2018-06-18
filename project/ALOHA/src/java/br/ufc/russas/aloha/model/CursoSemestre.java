
package br.ufc.russas.aloha.model;

public class CursoSemestre {
    private int id;
    private String curso;
    private int semestre;

    public CursoSemestre(int id, String curso, int semestre) {
        this.id = id;
        this.curso = curso;
        this.semestre = semestre;
    }

    public CursoSemestre() {
    }
    

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    
    
}
