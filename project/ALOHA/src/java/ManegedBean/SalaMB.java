
package ManegedBean;

import Model.Sala;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class SalaMB {
    private Sala sala;
    private List<Sala> salas;
    public SalaMB() {
        sala = new Sala();
        salas = new ArrayList<Sala>();
    }

    public void adicionar(){
        salas.add(sala);
        sala = new Sala();
    }
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
    
    
}
