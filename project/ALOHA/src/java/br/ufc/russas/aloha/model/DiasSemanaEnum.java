
package br.ufc.russas.aloha.model;


public enum DiasSemanaEnum {
    DOMINGO_MANHA(0), DOMINGO_TARDE(1), 
    SEGUNDA_MANHA(2), SEGUNDA_TARDE(3), 
    TERCA_MANHA(4), TERCA_TARDE(5), 
    QUARTA_MANHA(6), QUARTA_TARDE(7),  
    QUINTA_MANHA(8),  QUINTA_TARDE(9),
    SEXTA_MANHA(10), SEXTA_TARDE(11), 
    SABADO_MANHA(12), SABADO_TARDE(13);
    
    private final int num_dia;
    DiasSemanaEnum(int num_dia){
        this.num_dia = num_dia;
    }
    
    public static int get(DiasSemanaEnum d){
        switch(d){
            case DOMINGO_MANHA: return 0;
            case DOMINGO_TARDE: return 1;
            case SEGUNDA_MANHA: return 2;
            case SEGUNDA_TARDE: return 3;
            case TERCA_MANHA: return 4;
            case TERCA_TARDE: return 5;
            case QUARTA_MANHA: return 6;
            case QUARTA_TARDE: return 7;
            case QUINTA_MANHA: return 8;
            case QUINTA_TARDE: return 9;
            case SEXTA_MANHA: return 10;
            case SEXTA_TARDE: return 11;
            case SABADO_MANHA: return 12;  
            case SABADO_TARDE: return 13;  
        }
        return 0;
    }
}
