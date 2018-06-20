
package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.SalaDAO;
import br.ufc.russas.aloha.model.Sala;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class SalaMB {
    private Sala sala;
    private List<Sala> salas;
    SalaDAO salaEntidy;
    public SalaMB() {
        this.salaEntidy = new SalaDAO();
        this.sala = new Sala();
        this.salas = salaEntidy.selectALL();  
    }

    public void adicionar(){
        //salas.add(sala);
        //sala.setCodigoModelo(sala.geraCodigo());
        //System.out.println(sala.getCodigoModelo());
        salaEntidy.insert(sala);
        sala = new Sala();
        this.salas = salaEntidy.selectALL();
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
