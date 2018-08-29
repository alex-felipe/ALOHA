package br.ufc.russas.aloha.managedbean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConfiguracoesMB {

    private String[] cursos = {"Engenharia de Software", "Ciência da Computação"};
    private String[] horarios = {"08:00 - 10:00", "10:00 - 12:00"};
    
    public String[] getCursos() {
        return cursos;
    }

    public void setCursos(String[] cursos) {
        this.cursos = cursos;
    }

    public String[] getHorarios() {
        return horarios;
    }

    public void setHorarios(String[] horarios) {
        this.horarios = horarios;
    }
    
    

}
