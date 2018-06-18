
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
    
    /* O código será composto por:
        * 1) As 3 primeiras letras do nome do combo
        * 2) Identificador do combo no banco de dados
        * RESTRIÇÃO: Tamanho do indentificador será sempre 7
        * - Completar com 0's a esquerda do identificador quando possuirem menos que 4 digitos
     */
    public String getCodigo() {
        StringBuilder builder = new StringBuilder();
        builder.append("CMB"); // Retira as 3 primeiras letras do nome
        // Insere a quantidade de 0's restantes para formar o código
        if (getId() < 10) {
            builder.append("000");
        } else if (getId() < 100) {
            builder.append("00");
        } else if (getId() < 1000) {
            builder.append("0");
        }
        builder.append(getId()); // Adiciona o identificador
        return builder.toString();
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
