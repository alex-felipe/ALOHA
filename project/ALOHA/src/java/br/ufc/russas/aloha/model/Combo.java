
package br.ufc.russas.aloha.model;

import java.util.ArrayList;
import java.util.List;


public class Combo {
    private int id;
    private String codigo_modelo;
    private List<DiasSemanaEnum> dias;

    public Combo(){
        this.dias = new ArrayList<>();
    }
    public Combo(List dias) {
        this.dias = dias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCodigo_modelo(){
        return this.codigo_modelo;
    }
    
    public void setCodigo_modelo(String codigo_modelo){
        this.codigo_modelo = codigo_modelo;
    }
    
    public List getDias() {
        return dias;
    }

    public void setDias(List dias) {
        this.dias = dias;
    }
    
    public String getDiasEstendido(){
        StringBuilder stb = new StringBuilder();
        for(DiasSemanaEnum dia: dias){
            switch(dia){
                case DOMINGO: stb.append("Domingo"); break;
                case SEGUNDA: stb.append("Segunda-feira"); break;
                case TERCA: stb.append("Terça-feira"); break;
                case QUARTA: stb.append("Quarta-feira"); break;
                case QUINTA: stb.append("Quinta-feira"); break;
                case SEXTA: stb.append("Sexta-feira"); break;
                case SABADO: stb.append("Sabádo"); break;      
            }
            stb.append("; ");
        }
        return stb.toString();
    }
}
