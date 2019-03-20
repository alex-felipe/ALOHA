/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model;

import java.io.Serializable;

/**
 *
 * @author projetosti
 */
public class DiaSemana implements Serializable{
    
    private int id;
    private String dia;
    private String turno;

    public DiaSemana(int id, String dia, String turno) {
        this.id = id;
        this.dia = dia;
        this.turno = turno;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    
}
