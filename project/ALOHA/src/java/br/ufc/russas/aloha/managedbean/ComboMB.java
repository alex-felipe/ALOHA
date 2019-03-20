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
import javax.swing.tree.TreeNode;
import org.apache.catalina.tribes.util.Arrays;

@ManagedBean
@SessionScoped
public class ComboMB {

    private Combo combo;
    private List<Combo> listaCombos;
    ComboDAO comboDAO;
    private TreeNode[] combosSelecionados;

    public ComboMB() {
        this.comboDAO = new ComboDAO();
        this.combo = new Combo();
        this.listaCombos = comboDAO.selectALL();
    }

    public void adiciona() {
        try {
            if (combo.getDias().isEmpty()) {
                enviaFeedBack("", "Nenhum combo foi selecionado", 'e');
                return;
            }
            for (Combo cmb : listaCombos) {
                ArrayList<Integer> listaDias_cmb = new ArrayList<>();
                for (Object dd : cmb.getDias().toArray()) {
                    switch((DiasSemanaEnum)dd){
                        case DOMINGO_MANHA: listaDias_cmb.add(0); break;
                        case DOMINGO_TARDE: listaDias_cmb.add(1); break;
                        case SEGUNDA_MANHA: listaDias_cmb.add(2); break;
                        case SEGUNDA_TARDE: listaDias_cmb.add(3); break;
                        case TERCA_MANHA: listaDias_cmb.add(4); break;
                        case TERCA_TARDE: listaDias_cmb.add(5); break;
                        case QUARTA_MANHA: listaDias_cmb.add(6); break;
                        case QUARTA_TARDE: listaDias_cmb.add(7); break;
                        case QUINTA_MANHA: listaDias_cmb.add(8); break;
                        case QUINTA_TARDE: listaDias_cmb.add(9); break;
                        case SEXTA_MANHA: listaDias_cmb.add(10); break;
                        case SEXTA_TARDE: listaDias_cmb.add(11); break;
                        case SABADO_MANHA: listaDias_cmb.add(12); break;
                        case SABADO_TARDE: listaDias_cmb.add(13); break;
                    }
                }
                //System.out.println(Arrays.toString(listaDias_cmb.toArray()));
                //System.out.println(Arrays.toString(combo.getDias().toArray()));
                if (Arrays.toString(listaDias_cmb.toArray()).equals(Arrays.toString(combo.getDias().toArray()))) {
                    enviaFeedBack("", "Este combo já está cadastrado", 'a');
                    return;
                }
            }

            comboDAO.insert(combo);
            enviaFeedBack("", "Cadastro realizado com sucesso!", 'i');
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

    public TreeNode[] getCombosSelecionados() {
        return combosSelecionados;
    }

    public void setCombosSelecionados(TreeNode[] combosSelecionados) {
        this.combosSelecionados = combosSelecionados;
    }
    
    

}
