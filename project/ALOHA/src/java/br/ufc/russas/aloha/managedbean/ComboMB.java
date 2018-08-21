
package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.ComboDAO;
import br.ufc.russas.aloha.model.Combo;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ComboMB {
    private Combo combo;
    private List<Combo> listaCombos;
    ComboDAO comboDAO;
    public ComboMB() {
        this.comboDAO = new ComboDAO();
        this.combo = new Combo();
        this.listaCombos = comboDAO.selectALL();  
    }

    public void adiciona(){
        //salas.add(sala);
        
        try {
            comboDAO.insert(combo);
            FacesContext.getCurrentInstance().getExternalContext().redirect("combos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ComboMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo = new Combo();
        this.listaCombos = comboDAO.selectALL();
    }
    
    public void remove() {
        try {
            if (comboDAO.delete(combo)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("combos.xhtml");
                combo = new Combo();
                this.listaCombos = comboDAO.selectALL();

            } else {
                System.out.println("Deu erro");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public List<Combo> getListaCombos() {
        return listaCombos;
    }

    public void setListaCombos(List<Combo> listaCombos) {
        this.listaCombos = listaCombos;
    }
    public void novoCombo() {
        try {
            combo = new Combo();
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_combo.xhtml");

        } catch (Exception e) {
            e.getMessage();
        }
    }
        
}
