package br.ufc.russas.aloha.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
                case DOMINGO_MANHA: stb.append("Domingo (Manhã)"); break;
                case DOMINGO_TARDE: stb.append("Domingo (Tarde)"); break;
                case SEGUNDA_MANHA: stb.append("Segunda (Manhã)"); break;
                case SEGUNDA_TARDE: stb.append("Segunda (Tarde)"); break;
                case TERCA_MANHA: stb.append("Terça (Manhã)"); break;
                case TERCA_TARDE: stb.append("Terça (Tarde)"); break;
                case QUARTA_MANHA: stb.append("Quarta (Manhã)"); break;
                case QUARTA_TARDE: stb.append("Quarta (Tarde)"); break;
                case QUINTA_MANHA: stb.append("Quinta (Manhã)"); break;
                case QUINTA_TARDE: stb.append("Quinta (Tarde)"); break;
                case SEXTA_MANHA: stb.append("Sexta (Manhã)"); break;
                case SEXTA_TARDE: stb.append("Sexta (Tarde)"); break;
                case SABADO_MANHA: stb.append("Sábado (Manhã)"); break;
                case SABADO_TARDE: stb.append("Sábado (Tarde)"); break;
            }
            stb.append("; ");
        }
        return stb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.codigo_modelo);
        hash = 23 * hash + Objects.hashCode(this.dias);
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
        final Combo other = (Combo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.codigo_modelo, other.codigo_modelo)) {
            return false;
        }
        if (!Objects.equals(this.dias, other.dias)) {
            return false;
        }
        return true;
    }
    

    
}
