
package br.ufc.russas.aloha.model;


public enum DiasSemanaEnum {
    DOMINGO(0), SEGUNDA(1), TERCA(2), QUARTA(3), QUINTA(4), SEXTA(5), SABADO(6);
    
    private final int num_dia;
    DiasSemanaEnum(int num_dia){
        this.num_dia = num_dia;
    }
    
    public static int get(DiasSemanaEnum d){
        switch(d){
            case DOMINGO: return 0;
            case SEGUNDA: return 1;
            case TERCA: return 2;
            case QUARTA: return 3;
            case QUINTA: return 4;
            case SEXTA: return 5;
            case SABADO: return 6;  
        }
        return 0;
    }
}
