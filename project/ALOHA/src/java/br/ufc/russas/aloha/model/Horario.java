/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model;

/**
 *
 * @author projetosti
 */
public class Horario {
    private int id;
    private String codigo_modelo;
    // Ex: 08:00 - 10:00
    private String descricao;

    
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
}
