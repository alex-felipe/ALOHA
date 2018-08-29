package br.ufc.russas.aloha.managedbean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConfiguracoesMB {

    private String[] cursos = {"Engenharia de Software", "Ciências da Computação"};

    public String[] getCursos() {
        return cursos;
    }

    public void setCursos(String[] cursos) {
        this.cursos = cursos;
    }
    
    

}
