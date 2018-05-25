
package Model;

import java.util.ArrayList;
import java.util.List;


public class Combo {
    private List<DiasSemanaEnum> dias;

    public Combo(){
        this.dias = new ArrayList<DiasSemanaEnum>();
    }
    public Combo(List dias) {
        this.dias = dias;
    }

    public List getDias() {
        return dias;
    }

    public void setDias(List dias) {
        this.dias = dias;
    }
    
}
