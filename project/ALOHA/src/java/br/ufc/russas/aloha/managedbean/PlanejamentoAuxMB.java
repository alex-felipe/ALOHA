package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.*;
import br.ufc.russas.aloha.model.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoAuxMB {

    private List<Planejamento> planejamentos;
    private PlanejamentoDAO planejamentoDAO;
    private Planejamento planejamento;

    public PlanejamentoAuxMB() {
        planejamentoDAO = new PlanejamentoDAO();
        planejamento = new Planejamento();
        try {
            planejamentos = planejamentoDAO.selectALL();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Planejamento> getPlanejamentos() {
        return planejamentos;
    }

    public void setPlanejamentos(List<Planejamento> planejamentos) {
        this.planejamentos = planejamentos;
    }

    public Planejamento getPlanejamento() {
        return planejamento;
    }

    public void setPlanejamento(Planejamento planejamento) {
        this.planejamento = planejamento;
    }




    

}
