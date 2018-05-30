
package br.ufc.russas.aloha.model;


public enum DiasSemanaEnum {
    DOMINGO(0), SEGUNDA(1), TERCA(2), QUARTA(3), QUINTA(4), SEXTA(5), SABADO(6);
    
    private final int num_dia;
    DiasSemanaEnum(int num_dia){
        this.num_dia = num_dia;
    }
}
