package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.ComboDAO;
import br.ufc.russas.aloha.model.Combo;
import br.ufc.russas.aloha.model.DiasSemanaEnum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.catalina.tribes.util.Arrays;

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

    public void adiciona() {
        try {
            for (Combo cmb : listaCombos) {
                ArrayList<Integer> listaDias_cmb = new ArrayList<>();
                for (Object dd : cmb.getDias().toArray()) {
                    if (dd.toString().equals("DOMINGO")) {
                        listaDias_cmb.add(0);
                    } else if (dd.toString().equals("SEGUNDA")) {
                        listaDias_cmb.add(1);
                    } else if (dd.toString().equals("TERCA")) {
                        listaDias_cmb.add(2);
                    } else if (dd.toString().equals("QUARTA")) {
                        listaDias_cmb.add(3);
                    } else if (dd.toString().equals("QUINTA")) {
                        listaDias_cmb.add(4);
                    } else if (dd.toString().equals("SEXTA")) {
                        listaDias_cmb.add(5);
                    } else if (dd.toString().equals("SABADO")) {
                        listaDias_cmb.add(6);
                    }
                }
                //System.out.println(Arrays.toString(listaDias_cmb.toArray()));
                //System.out.println(Arrays.toString(combo.getDias().toArray()));
                if (Arrays.toString(listaDias_cmb.toArray()).equals(Arrays.toString(combo.getDias().toArray()))) {
                    enviaFeedBack("Alerta", "Este combo já foi cadastrado", 'a');
                    return;
                }
            }

            comboDAO.insert(combo);
            FacesContext.getCurrentInstance().getExternalContext().redirect("combos.xhtml");
            combo = new Combo();
            this.listaCombos = comboDAO.selectALL();

        } catch (IOException ex) {
            Logger.getLogger(ComboMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove() {
        try {
            if (comboDAO.delete(combo)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("combos.xhtml");
                combo = new Combo();
                this.listaCombos = comboDAO.selectALL();

            } else {
                enviaFeedBack("Erro", "Não foi possível remover este combo!", 'e');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enviaFeedBack(String titulo, String msg, char severidade) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage.Severity s = null;
        switch (severidade) {
            case 'i':
                s = FacesMessage.SEVERITY_INFO;
                break;
            case 'a':
                s = FacesMessage.SEVERITY_WARN;
                break;
            case 'e':
                s = FacesMessage.SEVERITY_ERROR;
                break;
            case 'f':
                s = FacesMessage.SEVERITY_FATAL;
                break;
            default:
                s = FacesMessage.SEVERITY_INFO;
                break;
        }
        context.addMessage(null, new FacesMessage(s, titulo, msg));
    }

}
