package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.*;
import br.ufc.russas.aloha.model.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoMB implements Serializable {

    //Variáveis para manusear o BD
    DisciplinaDAO disciplinaDAO;
    SalaDAO salaDAO;
    DocenteDAO docenteDAO;

//Variáveis para Auxiliares
    private Turmas turmasTmp;
    private Sala salaTmp;
    private Docente docenteTemp;
    

//Listas para gerar modelo
    private ArrayList<Docente> docentesDisponiveis;
    private ArrayList<Turmas> turmasOfertadas;
    private ArrayList<Turmas> filteredDiscip;
    private ArrayList<Sala> salasAlocadas;

//listaRemovidos
    private ArrayList<Docente> docentesOut;
    private ArrayList<Turmas> disciplinasOut;
    private ArrayList<Sala> salasOut;

    public PlanejamentoMB() {
        this.disciplinaDAO = new DisciplinaDAO();
        this.salaDAO = new SalaDAO();
        this.docenteDAO = new DocenteDAO();

        this.disciplinasOut = new ArrayList<>();
        this.docentesOut = new ArrayList<>();
        this.salasOut = new ArrayList<>();

        this.turmasOfertadas = new ArrayList<>();
        try {
            this.docentesDisponiveis = docenteDAO.selectALL();
            for (Disciplina d : this.disciplinaDAO.selectALL()) {
                this.turmasOfertadas.add(new Turmas(d, 1));
            }
            System.out.println(this.turmasOfertadas.size());
            this.salasAlocadas = salaDAO.selectALL();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.turmasTmp = new Turmas();
        this.salaTmp = new Sala();
        this.docenteTemp = new Docente();

    }

    //---------------------------------------------------------------------------
    public ArrayList<Docente> getDocentesDisponiveis() {
        return docentesDisponiveis;
    }

    public void setDocentesDisponiveis(ArrayList<Docente> docentesDisponiveis) {
        this.docentesDisponiveis = docentesDisponiveis;
    }

    //---------------------------------------------------------------------------
    public ArrayList<Turmas> getTurmasOfertadas() {
        return turmasOfertadas;
    }

    public void setTurmasOfertadas(ArrayList<Turmas> turmasOfertadas) {
        this.turmasOfertadas = turmasOfertadas;
    }

    //---------------------------------------------------------------------------
    public ArrayList<Docente> getDocentesOut() {
        return docentesOut;
    }

    public void setDocentesOut(ArrayList<Docente> docentesOut) {
        this.docentesOut = docentesOut;
    }

    //---------------------------------------------------------------------------
    public ArrayList<Turmas> getDisciplinasOut() {
        return disciplinasOut;
    }

    public void setDisciplinasOut(ArrayList<Turmas> disciplinasOut) {
        this.disciplinasOut = disciplinasOut;
    }

    //---------------------------------------------------------------------------
    public ArrayList<Sala> getSalasOut() {
        return salasOut;
    }

    public void setSalasOut(ArrayList<Sala> salasOut) {
        this.salasOut = salasOut;
    }

    //---------------------------------------------------------------------------
    public ArrayList<Sala> getSalasAlocadas() {

        return this.salasAlocadas;
    }

    public void setSalasAlocadas(ArrayList<Sala> salasAlocadas) {
        this.salasAlocadas = salasAlocadas;
    }

    //---------------------------------------------------------------------------
    public Turmas getTurmasTmp() {
        return turmasTmp;
    }

    public void setTurmasTmp(Turmas turmasTmp) {
        this.turmasTmp = turmasTmp;
    }

    //---------------------------------------------------------------------------
    public Sala getSalaTmp() {
        return salaTmp;
    }

    public void setSalaTmp(Sala salaTmp) {
        this.salaTmp = salaTmp;
    }

    //---------------------------------------------------------------------------

    public Docente getDocenteTemp() {
        return docenteTemp;
    }

    public void setDocenteTemp(Docente docenteTemp) {
        this.docenteTemp = docenteTemp;
    }

    public ArrayList<Turmas> getFilteredDiscip() {
        return filteredDiscip;
    }

    public void setFilteredDiscip(ArrayList<Turmas> filteredDiscip) {
        this.filteredDiscip = filteredDiscip;
    }
    
    
    
    public void addDisciplina() {
        //System.out.println(this.turmasTmp.getDisciplina().getNome()+" "+ this.turmasTmp.getQntTurmas());
        if (this.turmasOfertadas.contains(this.turmasTmp)) {
            int i = this.turmasOfertadas.indexOf(this.turmasTmp);
            this.turmasOfertadas.get(i).setQntTurmas(this.turmasTmp.getQntTurmas() + 1);
            this.turmasTmp = new Turmas();
        }
           
    }

    public void removeDisciplina() {
        // System.out.println(this.turmasTmp == null);
        if (this.turmasOfertadas.contains(this.turmasTmp)) {
            if (this.turmasTmp.getQntTurmas() <= 1) {
                this.turmasOfertadas.remove(this.turmasTmp);
            } else {
                int i = this.turmasOfertadas.indexOf(this.turmasTmp);
                this.turmasOfertadas.get(i).setQntTurmas(this.turmasTmp.getQntTurmas() - 1);
                this.turmasTmp = new Turmas();
            }

        }

    }

    public void removeSala() {
        System.out.println(this.salasAlocadas.size());
        if (this.salaTmp.getNome() != null) {
            this.salasAlocadas.remove(this.salaTmp);
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
