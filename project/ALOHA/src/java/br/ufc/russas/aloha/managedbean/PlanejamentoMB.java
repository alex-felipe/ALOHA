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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoMB {

    //Variáveis para manusear o BD
    DisciplinaDAO disciplinaDAO;
    SalaDAO salaDAO;
    DocenteDAO docenteDAO;
    ComboDAO comboDAO;
    HorarioDAO horarioDAO;
    PlanejamentoDAO planejamentoDAO;

//Variáveis para Auxiliares
    private Turmas turmasTmp;
    private Sala salaTmp;
    private Docente docenteTemp;
    private VariaveisFixas varFix;
    private String combo;

    private boolean skip;
    private String nome;

//Listas para gerar modelo
    private List<Docente> docentesDisponiveis;
    private List<Turmas> turmasOfertadas;
    private List<Turmas> filteredDiscip;
    private List<Sala> salasAlocadas;
    private List<VariaveisFixas> variaveisFixas;
    private List<Combo> combos;
    private List<Horario> horarios;

//listaRemovidos
    private List<Docente> docentesOut;
    private List<Turmas> disciplinasOut;
    private List<Sala> salasOut;
    
    private Planejamento planejamentoEdit;

    public Planejamento getPlanejamentoEdit() {
        return planejamentoEdit;
    }

    public void setPlanejamentoEdit(Planejamento planejamentoEdit) {
        this.planejamentoEdit = planejamentoEdit;
        this.nome = this.planejamentoEdit.getNome();
        
        this.docentesDisponiveis.removeAll(planejamentoEdit.getDocentes());
        this.docentesOut = this.docentesDisponiveis;
        this.docentesDisponiveis = planejamentoEdit.getDocentes();
        
        this.turmasOfertadas.removeAll(planejamentoEdit.getTurmas());
        this.disciplinasOut = this.turmasOfertadas;
        this.turmasOfertadas = planejamentoEdit.getTurmas();
        
        this.salasAlocadas.removeAll(planejamentoEdit.getSalas());
        this.salasOut = this.salasAlocadas;
        this.salasAlocadas = planejamentoEdit.getSalas();
        
        this.variaveisFixas = planejamentoEdit.getVariaveisFixas();
        
    }
 
    

    public PlanejamentoMB() {
        
        this.disciplinaDAO = new DisciplinaDAO();
        this.salaDAO = new SalaDAO();
        this.docenteDAO = new DocenteDAO();
        this.comboDAO = new ComboDAO();
        this.horarioDAO = new HorarioDAO();
        this.planejamentoDAO = new PlanejamentoDAO();
        this.varFix = new VariaveisFixas();
        this.disciplinasOut = new ArrayList<>();
        this.docentesOut = new ArrayList<>();
        this.salasOut = new ArrayList<>();
        this.salasAlocadas = new ArrayList<>();
        this.variaveisFixas = new ArrayList<>();

        this.turmasOfertadas = new ArrayList<>();
        try {
            this.combos = comboDAO.selectALL();
            this.docentesDisponiveis = docenteDAO.selectALL();
            this.salasAlocadas = salaDAO.selectALL();
            for (Disciplina d : this.disciplinaDAO.selectALL()) {
                this.turmasOfertadas.add(new Turmas(d, 1));
            }
            this.horarios = this.horarioDAO.selectALL();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.turmasTmp = new Turmas();
        this.salaTmp = new Sala();
        this.docenteTemp = new Docente();
        planejamentoEdit = new Planejamento();

    }

    public void addDisciplina() {
        if (this.turmasOfertadas.contains(this.turmasTmp)) {
            int i = this.turmasOfertadas.indexOf(this.turmasTmp);
            this.turmasOfertadas.get(i).setQntTurmas(this.turmasTmp.getQntTurmas() + 1);
            this.turmasTmp = new Turmas();
        } else {
            this.disciplinasOut.remove(this.turmasTmp);
            if (this.turmasTmp.getQntTurmas() == 0) {
                this.turmasTmp.setQntTurmas(1);
            }
            this.turmasOfertadas.add(this.turmasTmp);
        }

    }

    public void removeDisciplina() {
        if (this.turmasOfertadas.contains(this.turmasTmp)) {
            if (this.turmasTmp.getQntTurmas() <= 1) {
                this.turmasOfertadas.remove(this.turmasTmp);
                this.turmasTmp.setQntTurmas(0);
                this.disciplinasOut.add(turmasTmp);
                this.turmasTmp = new Turmas();
            } else {
                int i = this.turmasOfertadas.indexOf(this.turmasTmp);
                this.turmasOfertadas.get(i).setQntTurmas(this.turmasTmp.getQntTurmas() - 1);
                this.turmasTmp = new Turmas();
            }
        }
    }

    public void removeSala() {
        if (this.salaTmp.getNome() != null) {
            this.salasAlocadas.remove(this.salaTmp);
            this.salasOut.add(this.salaTmp);
            this.salaTmp = new Sala();
        }
    }

    public void addSala() {
        if (!this.salasAlocadas.contains(this.salaTmp)) {
            this.salasAlocadas.add(this.salaTmp);
            this.salasOut.remove(this.salaTmp);
            this.salaTmp = new Sala();
        }

    }

    public void removeDocente() {
        if (this.docenteTemp != null && this.docentesDisponiveis.contains(this.docenteTemp)) {
            this.docentesDisponiveis.remove(this.docenteTemp);
            this.docentesOut.add(this.docenteTemp);
            this.docenteTemp = new Docente();
        }
    }

    public void addDocente() {
        if (!this.docentesDisponiveis.contains(this.docenteTemp)) {
            this.docentesDisponiveis.add(this.docenteTemp);
            this.docentesOut.remove(this.docenteTemp);
            this.docenteTemp = new Docente();
        }

    }

    public void addVarFixa() {
        if (this.varFix != null) {
            this.variaveisFixas.add(varFix);
            this.varFix = new VariaveisFixas();
        }
    }

    public void removeVarFixa() {
        System.out.println("aqui");
        if (this.varFix != null && this.variaveisFixas.contains(this.varFix)) {
            this.variaveisFixas.remove(varFix);
            this.varFix = new VariaveisFixas();
        }
    }

    public void salvar() {
        Planejamento current = new Planejamento(this.nome, this.turmasOfertadas, this.docentesDisponiveis, this.salasAlocadas, this.variaveisFixas, false);
        planejamentoDAO.insert(current);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("planejamentos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PlanejamentoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        System.out.println(nome);
        this.nome = nome;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
    }

    public VariaveisFixas getVarFix() {
        return varFix;
    }

    public void setVarFix(VariaveisFixas varFix) {
        this.varFix = varFix;
    }

    public List<VariaveisFixas> getVariaveisFixas() {
        return variaveisFixas;
    }

    public void setVariaveisFixas(ArrayList<VariaveisFixas> variaveisFixas) {
        this.variaveisFixas = variaveisFixas;
    }

    //---------------------------------------------------------------------------
    public List<Docente> getDocentesDisponiveis() {
        return docentesDisponiveis;
    }

    public void setDocentesDisponiveis(ArrayList<Docente> docentesDisponiveis) {
        this.docentesDisponiveis = docentesDisponiveis;
    }

    //---------------------------------------------------------------------------
    public List<Turmas> getTurmasOfertadas() {
        return turmasOfertadas;
    }

    public void setTurmasOfertadas(ArrayList<Turmas> turmasOfertadas) {
        this.turmasOfertadas = turmasOfertadas;
    }

    //---------------------------------------------------------------------------
    public List<Docente> getDocentesOut() {
        return docentesOut;
    }

    public void setDocentesOut(ArrayList<Docente> docentesOut) {
        this.docentesOut = docentesOut;
    }

    //---------------------------------------------------------------------------
    public List<Turmas> getDisciplinasOut() {
        return disciplinasOut;
    }

    public void setDisciplinasOut(ArrayList<Turmas> disciplinasOut) {
        this.disciplinasOut = disciplinasOut;
    }

    //---------------------------------------------------------------------------
    public List<Sala> getSalasOut() {
        return salasOut;
    }

    public void setSalasOut(ArrayList<Sala> salasOut) {
        this.salasOut = salasOut;
    }

    //---------------------------------------------------------------------------
    public List<Sala> getSalasAlocadas() {
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

    public List<Turmas> getFilteredDiscip() {
        return filteredDiscip;
    }

    public void setFilteredDiscip(ArrayList<Turmas> filteredDiscip) {
        this.filteredDiscip = filteredDiscip;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

}
