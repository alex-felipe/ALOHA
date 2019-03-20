package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.SalaDAO;
import br.ufc.russas.aloha.model.Sala;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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

    public void adicionar() {
        try {
            if (salaEntidy.find(sala.getId()) == null) {
                if (salaEntidy.insert(sala)) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("salas.xhtml");
                        sala = new Sala();
                        this.salas = salaEntidy.selectALL();
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }else{
                    enviaFeedBack("Cadastro de salas", "Não foi possível realizar o cadastro a sala. Verifique os dados do formulário e tente reenviar", 'e');
                }
            } else {
                if (salaEntidy.update(sala)) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("salas.xhtml");
                        sala = new Sala();
                        this.salas = salaEntidy.selectALL();
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro no banco e dados:" + ex.getMessage());
        }

    }

    public void editar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("add_sala.xhtml");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void remover() {
        try {
            System.out.println(sala.getId());
            if (salaEntidy.delete(sala)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("salas.xhtml");
                sala = new Sala();
                this.salas = salaEntidy.selectALL();
                
            }else{
                System.out.println("Deu erro");
            }
        } catch (IOException e) {
                e.getMessage();
        }
    }

    public void novaSala() {
        try {
            sala = new Sala();
            FacesContext.getCurrentInstance().getExternalContext().redirect("add_sala.xhtml");

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        System.out.println("Inseriu");
        this.sala = sala;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
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
