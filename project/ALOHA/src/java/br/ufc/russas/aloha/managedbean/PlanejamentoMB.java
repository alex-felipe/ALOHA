package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.dao.SalaDAO;
import br.ufc.russas.aloha.model.Disciplina;
import br.ufc.russas.aloha.model.Sala;
import br.ufc.russas.aloha.model.Turmas;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoMB {

    DisciplinaDAO disciplinaDAO;
    SalaDAO salaDAO;
    
    private Turmas turmasTmp;
    private Sala salaTmp;
    
    private ArrayList<Turmas> turmasCadastro;
    private ArrayList<Turmas> turmasOfertadas;
    private ArrayList<Sala> salasAlocadas;

    public PlanejamentoMB() {
        this.turmasCadastro = new ArrayList<>();
        this.disciplinaDAO = new DisciplinaDAO();
        this.salaDAO = new SalaDAO();
        this.turmasOfertadas = new ArrayList<>();
        this.salasAlocadas = new ArrayList<>();
        this.turmasTmp = new Turmas();
        this.salaTmp = new Sala();
                this.salasAlocadas = salaDAO.selectALL();
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

    public ArrayList<Sala> getSalasAlocadas() {

        return this.salasAlocadas;
    }

    public void setSalasAlocadas(ArrayList<Sala> salasAlocadas) {
        this.salasAlocadas = salasAlocadas;
    }

    public Sala getSalaTmp() {
        return salaTmp;
    }

    public void setSalaTmp(Sala salaTmp) {
        this.salaTmp = salaTmp;
    }
    
    
    
    public void addDisciplina(){
        //System.out.println(this.turmasTmp.getDisciplina().getNome()+" "+ this.turmasTmp.getQntTurmas());
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
    
    public void removeSala(){
        System.out.println(this.salasAlocadas.size());
        if(this.salaTmp.getNome()!=null){
            System.out.println(this.salasAlocadas.size());
            this.salasAlocadas.remove(this.salaTmp);
            System.out.println(this.salasAlocadas.size());
            this.salaTmp = new Sala();
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
