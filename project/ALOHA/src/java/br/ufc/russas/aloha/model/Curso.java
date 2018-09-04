/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model;

/**
 *
 * @author Alex Felipe
 */
public class Curso {
    private int id;
    private String codigo_modelo;
    private String nome;

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
        this.nome = nome;
    }
    
    public String getCodigo() {
        StringBuilder builder = new StringBuilder();
        builder.append("CRS"); // Retira as 3 primeiras letras do nome
        // Insere a quantidade de 0's restantes para formar o código
        if (getId() < 10) {
            builder.append("00");
        } else if (getId() < 100) {
            builder.append("0");
        }
        builder.append(getId()); // Adiciona o identificador
        return builder.toString();
    }
}
