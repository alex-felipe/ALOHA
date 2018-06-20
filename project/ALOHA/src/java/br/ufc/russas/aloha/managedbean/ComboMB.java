
package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.ComboDAO;
import br.ufc.russas.aloha.model.Combo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
        comboDAO.insert(combo);
        combo = new Combo();
        this.listaCombos = comboDAO.selectALL();
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
        
}
