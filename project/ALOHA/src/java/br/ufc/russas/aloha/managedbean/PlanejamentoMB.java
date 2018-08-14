package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.model.Disciplina;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoMB {

    private ArrayList<Disciplina> disciplinas;
    DisciplinaDAO disciplinaDAO;

    public PlanejamentoMB() {
        this.disciplinas = new ArrayList<Disciplina>();
        this.disciplinaDAO = new DisciplinaDAO();
    }

    public ArrayList<Disciplina> getDisciplinas() {

        return this.disciplinaDAO.selectALL();
    }
    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    private boolean skip;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (!skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}
