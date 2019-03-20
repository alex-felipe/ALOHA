/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author projetosti
 */
public class Horario {
    private int id;
    private String codigo_modelo;
    private String descricao;

    public Horario() {
    }

    public boolean isManha(){
        String novaDescricao = descricao.replaceAll(" ", "");
        int meio = novaDescricao.indexOf("-");
        String[] horario1 = novaDescricao.substring(0, meio).split(":");
        String[] horario2 = novaDescricao.substring(meio + 1, novaDescricao.length()).split(":");

        int hora1 = Integer.parseInt(horario1[0]);
        int hora2 = Integer.parseInt(horario2[0]);
        return (hora1 > 6 && hora1 < 12);
    }

    public boolean isTarde() {
        String novaDescricao = descricao.replaceAll(" ", "");
        int meio = novaDescricao.indexOf("-");
        String[] horario1 = novaDescricao.substring(0, meio).split(":");
        String[] horario2 = novaDescricao.substring(meio + 1, novaDescricao.length()).split(":");

        int hora1 = Integer.parseInt(horario1[0]);
        int hora2 = Integer.parseInt(horario2[0]);
        return (hora1 >= 12 && hora1 < 18);
    }

    public boolean isNoite() {
        String novaDescricao = descricao.replaceAll(" ", "");
        int meio = novaDescricao.indexOf("-");
        String[] horario1 = novaDescricao.substring(0, meio).split(":");
        String[] horario2 = novaDescricao.substring(meio + 1, novaDescricao.length()).split(":");

        int hora1 = Integer.parseInt(horario1[0]);
        int hora2 = Integer.parseInt(horario2[0]);
        return ((hora1 >= 18 && hora1 < 23) || (hora1 >= 0 && hora2 < 6));
    }
    
    public boolean isContidoNoIntervalo(Horario horario){
        int horaInicial = getHorarioInicial()[0];
        int minInicial = getHorarioInicial()[1];
        
        int horaFinal = getHorarioFinal()[0];
        int minFinal = getHorarioFinal()[1];
        
        if(this.getHorarioFinal()[0] < horario.getHorarioInicial()[0]){
            return false;
        } else if((this.getHorarioFinal()[0].equals(horario.getHorarioInicial()[0])) && (this.getHorarioFinal()[1] <= horario.getHorarioInicial()[1])){
            return false;
        } else if(this.getHorarioInicial()[0] > horario.getHorarioFinal()[0]){
            return false;
        } else if((this.getHorarioInicial()[0].equals(horario.getHorarioFinal()[0])) && (this.getHorarioInicial()[1] >= horario.getHorarioFinal()[1])){
            return false;
        } else{
            return true;
        }
        
    }
    
    public static void main(String[] args) {
        Horario a = new Horario();
        Horario b = new Horario();
        a.setDescricao("08:00-10:00");
        b.setDescricao("08:00-10:00");
        System.out.println(a.isContidoNoIntervalo(b));
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

    public String getDescricao() {
        return descricao;
    }

    public Integer[] getHorarioInicial(){
        String novaDescricao = descricao.replaceAll(" ", "");
        int meio = novaDescricao.indexOf("-");
        String[] horarioString = novaDescricao.substring(0, meio).split(":");
        Integer[] horarioInteger = new Integer[2];
        for(int i = 0;i < 2;i++){
            horarioInteger[i] = Integer.parseInt(horarioString[i]);
        }
        return horarioInteger;        
    }
    
    public Integer[] getHorarioFinal(){
        String novaDescricao = descricao.replaceAll(" ", "");
        int meio = novaDescricao.indexOf("-");
        String[] horarioString = novaDescricao.substring(meio + 1, novaDescricao.length()).split(":");
        Integer[] horarioInteger = new Integer[2];
        for (int i = 0; i < 2; i++) {
            horarioInteger[i] = Integer.parseInt(horarioString[i]);
        }
        return horarioInteger;     
    }
    
    public void setDescricao(String descricao) {
        String novaDescricao = descricao.replaceAll(" ", "");
        int meio = novaDescricao.indexOf("-");
        String[] horario1 = novaDescricao.substring(0, meio).split(":");
        String[] horario2 = novaDescricao.substring(meio + 1, novaDescricao.length()).split(":");
        
        int hora1 = Integer.parseInt(horario1[0]);
        int hora2 = Integer.parseInt(horario2[0]);
        
        int min1 = Integer.parseInt(horario1[1]);
        int min2 = Integer.parseInt(horario2[1]);
        if((hora1 >= 0) && (hora1 < 24) && (hora2 >= 0) && (hora2 < 24)){
            if((hora1 < hora2) || ((hora1 == hora2) && (min1 < min2))){
                this.descricao = descricao;
            }
        }
    }
    
    

    /* O código será composto por:
        * 1) As 3 primeiras letras do nome do horario
        * 2) Identificador do horario no banco de dados
        * RESTRIÇÃO: Tamanho do indentificador será sempre 6
        * - Completar com 0's a esquerda do identificador quando possuirem menos que 3 digitos
     */
    public String getCodigo() {
        StringBuilder builder = new StringBuilder();
        builder.append("HOR"); // Retira as 3 primeiras letras do nome
        // Insere a quantidade de 0's restantes para formar o código
        if (getId() < 10) {
            builder.append("00");
        } else if (getId() < 100) {
            builder.append("0");
        }
        builder.append(getId()); // Adiciona o identificador
        return builder.toString();
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.codigo_modelo);
        hash = 53 * hash + Objects.hashCode(this.descricao);
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
        final Horario other = (Horario) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.codigo_modelo, other.codigo_modelo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }
    
    
}
