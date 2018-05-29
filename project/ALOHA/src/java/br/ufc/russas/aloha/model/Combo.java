
package br.ufc.russas.aloha.model;

import java.util.ArrayList;
import java.util.List;


public class Combo {
    private int id;
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

    public List getDias() {
        return dias;
    }

    public void setDias(List dias) {
        this.dias = dias;
    }
    
}
