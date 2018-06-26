
package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.SalaDAO;
import br.ufc.russas.aloha.model.Sala;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


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
        
        if(salaEntidy.insert(sala)){
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("salas.xhtml");
                sala = new Sala();
                this.salas = salaEntidy.selectALL();
            }catch(Exception e){
                e.getMessage();
            }
        }
        
    }
    public void editar(){
        try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("add_sala.xhtml");
                
            }catch(Exception e){
                e.getMessage();
            }
    }
    public void novaSala() {
        try {
            sala = new Sala();
            FacesContext.getCurrentInstance().getExternalContext().redirect("add_sala.xhtml");

        } catch (Exception e) {
            e.getMessage();
        }
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
