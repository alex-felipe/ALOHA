package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.model.Disciplina;
import br.ufc.russas.aloha.model.Turmas;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoMB {

    private ArrayList<Turmas> turmasCadastro;
    DisciplinaDAO disciplinaDAO;
    private ArrayList<Turmas> turmasOfertadas;
    private Turmas turmasTmp;

    public PlanejamentoMB() {
        this.turmasCadastro = new ArrayList<Turmas>();
        this.disciplinaDAO = new DisciplinaDAO();
        this.turmasOfertadas = new ArrayList<Turmas>();
        this.turmasTmp = new Turmas();
        //System.out.println("ccc");
        
    }


    public ArrayList<Turmas> getTurmasCadastro() {
        this.turmasCadastro = new ArrayList<Turmas>();
        for(Disciplina d :this.disciplinaDAO.selectALL()){
            this.turmasCadastro.add(new Turmas(d, 0));
        }
        return turmasCadastro;
    }

    public void setTurmasCadastro(ArrayList<Turmas> turmasCadastro) {
        this.turmasCadastro = turmasCadastro;
    }
    

    public ArrayList<Turmas> getTurmasOfertadas() {
        return turmasOfertadas;
    }

    public void setTurmasOfertadas(ArrayList<Turmas> turmasOfertadas) {
        this.turmasOfertadas = turmasOfertadas;
    }

    public Turmas getTurmasTmp() {
        return turmasTmp;
    }

    public void setTurmasTmp(Turmas turmasTmp) {
        this.turmasTmp = turmasTmp;
    }
    
    
    public void addDisciplina(){
        System.out.println(this.turmasTmp.getDisciplina().getNome()+" "+ this.turmasTmp.getQntTurmas());
        if (this.turmasTmp.getDisciplina() != null) {
            for(Turmas t: this.getTurmasOfertadas()){
                if(t.getDisciplina().getNome().equals(this.turmasTmp.getDisciplina().getNome()) && t.getDisciplina().getCodigo().equals(this.turmasTmp.getDisciplina().getCodigo())){
                    
                    int i = this.turmasOfertadas.indexOf(t);
                    int qnt = this.turmasOfertadas.get(i).getQntTurmas();
                    
                    this.turmasOfertadas.get(i).setQntTurmas(++qnt);
                    this.turmasTmp=null;
                    break;
                }
            }
            if(this.turmasTmp!=null){
               this.turmasTmp.setQntTurmas(1);
               this.turmasOfertadas.add(this.turmasTmp);
            }
            
            this.turmasTmp = new Turmas();
            
        }
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
