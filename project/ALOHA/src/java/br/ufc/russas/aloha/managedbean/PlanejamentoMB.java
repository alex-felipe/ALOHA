package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.*;
import br.ufc.russas.aloha.model.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class PlanejamentoMB implements Serializable {

    //Variáveis para manusear o BD
    DisciplinaDAO disciplinaDAO;
    SalaDAO salaDAO;
    DocenteDAO docenteDAO;
    ComboDAO comboDAO;

//Variáveis para Auxiliares
    private Turmas turmasTmp;
    private Sala salaTmp;
    private Docente docenteTemp;
    private Docente docenteFix;
    private VariaveisFixas varFix;
    
    private boolean skip;

//Listas para gerar modelo
    private ArrayList<Docente> docentesDisponiveis;
    private ArrayList<Turmas> turmasOfertadas;
    private ArrayList<Turmas> filteredDiscip;
    private ArrayList<Sala> salasAlocadas;
    private ArrayList<VariaveisFixas> variaveisFixas;
    private ArrayList<Combo> combos;

//listaRemovidos
    private ArrayList<Docente> docentesOut;
    private ArrayList<Turmas> disciplinasOut;
    private ArrayList<Sala> salasOut;
    
//selectItens
    private List<SelectItem> selectDocentes;
    private List<SelectItem> selectDisciplinas;
    private List<SelectItem> selectCombos;
    private List<SelectItem> selectHorarios;
    private List<SelectItem> selectSalas;
    
    private void fillSelectItems(String op) {
        selectDocentes = new ArrayList<SelectItem>();
        selectDisciplinas = new ArrayList<SelectItem>();
        selectCombos = new ArrayList<SelectItem>();
        selectHorarios = new ArrayList<SelectItem>();
        selectSalas = new ArrayList<SelectItem>();
        
        
        switch(op){
            case "d":
                 for (Docente d : docentesDisponiveis) selectDocentes.add(new SelectItem(d, d.getNome()));
                 break;
            case "di":
                 for (Turmas di : turmasOfertadas) selectDisciplinas.add(new SelectItem(di, di.getDisciplina().getNome()));
                break;
            case "c":
                for (Combo c : comboDAO.selectALL()) selectDocentes.add(new SelectItem(c, c.getDiasEstendido()));
                break;
            case "h":
                 //for (Horario d : docentesDisponiveis) selectDocentes.add(new SelectItem(d, d.getNome()));
                break;
            case "s":
                for (Sala s : salasAlocadas) selectSalas.add(new SelectItem(s, s.getNome()));
                break;
            case "all":
                for (Docente d : docentesDisponiveis) selectDocentes.add(new SelectItem(d, d.getNome()));
                for (Turmas di : turmasOfertadas) selectDisciplinas.add(new SelectItem(di, di.getDisciplina().getNome()));
                for (Combo c : comboDAO.selectALL()) selectDocentes.add(new SelectItem(c, c.getDiasEstendido()));
                //for (Horario d : docentesDisponiveis) selectDocentes.add(new SelectItem(d, d.getNome()));
                for (Sala s : salasAlocadas) selectSalas.add(new SelectItem(s, s.getNome()));
                
        }
 
    }
    
    public PlanejamentoMB() {
        this.disciplinaDAO = new DisciplinaDAO();
        this.salaDAO = new SalaDAO();
        this.docenteDAO = new DocenteDAO();
        this.comboDAO = new ComboDAO();

        this.disciplinasOut = new ArrayList<>();
        this.docentesOut = new ArrayList<>();
        this.salasOut = new ArrayList<>();
        this.variaveisFixas = new ArrayList<>();

        this.turmasOfertadas = new ArrayList<>();
        try {
            this.combos = comboDAO.selectALL();
            this.docentesDisponiveis = docenteDAO.selectALL();
            for (Disciplina d : this.disciplinaDAO.selectALL()) {
                this.turmasOfertadas.add(new Turmas(d, 1));
            }
            //System.out.println(this.turmasOfertadas.size());
            this.salasAlocadas = salaDAO.selectALL();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.turmasTmp = new Turmas();
        this.salaTmp = new Sala();
        this.docenteTemp = new Docente();
        this.docenteFix = new Docente();
        this.varFix = new VariaveisFixas();
        fillSelectItems("all");

    }

    public ArrayList<Combo> getCombos() {
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

    public ArrayList<VariaveisFixas> getVariaveisFixas() {
        return variaveisFixas;
    }

    public void setVariaveisFixas(ArrayList<VariaveisFixas> variaveisFixas) {
        this.variaveisFixas = variaveisFixas;
    }

    
    
    public Docente getDocenteFix() {
        return docenteFix;
    }

    public void setDocenteFix(Docente docenteFix) {
        this.docenteFix = docenteFix;
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
            fillSelectItems("di");
        }
        

    }

    public void removeDisciplina() {
        // System.out.println(this.turmasTmp == null);
        if (this.turmasOfertadas.contains(this.turmasTmp)) {
            if (this.turmasTmp.getQntTurmas() <= 1) {
                this.turmasOfertadas.remove(this.turmasTmp);
                this.turmasTmp.setQntTurmas(0);
                this.disciplinasOut.add(turmasTmp);
                this.turmasTmp = new Turmas();
                fillSelectItems("di");
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
            fillSelectItems("s");
        }
    }

    public void addSala() {
        if (!this.salasAlocadas.contains(this.salaTmp)) {
            this.salasAlocadas.add(this.salaTmp);
            this.salasOut.remove(this.salaTmp);
            this.salaTmp = new Sala();
            fillSelectItems("s");
        }

    }

    public void removeDocente() {
        if (this.docenteTemp != null && this.docentesDisponiveis.contains(this.docenteTemp)) {
            this.docentesDisponiveis.remove(this.docenteTemp);
            this.docentesOut.add(this.docenteTemp);
            this.docenteTemp = new Docente();
            fillSelectItems("d");
        }
    }

    public void addDocente() {
        if (!this.docentesDisponiveis.contains(this.docenteTemp)) {
            this.docentesDisponiveis.add(this.docenteTemp);
            this.docentesOut.remove(this.docenteTemp);
            this.docenteTemp = new Docente();
            fillSelectItems("d");
        }

    }
    public void addVarFixa(){
         
        if(this.varFix!=null){          
            this.variaveisFixas.add(varFix);
            System.out.println(this.variaveisFixas.get(0));
            this.varFix = new VariaveisFixas();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {

            return event.getNewStep();
        }
    }

    public List<SelectItem> getSelectDocentes() {
        return selectDocentes;
    }

    public void setSelectDocentes(List<SelectItem> selectDocentes) {
        this.selectDocentes = selectDocentes;
    }

    public List<SelectItem> getSelectDisciplinas() {
        return selectDisciplinas;
    }

    public void setSelectDisciplinas(List<SelectItem> selectDisciplinas) {
        this.selectDisciplinas = selectDisciplinas;
    }

    public List<SelectItem> getSelectCombos() {
        return selectCombos;
    }

    public void setSelectCombos(List<SelectItem> selectCombos) {
        this.selectCombos = selectCombos;
    }

    public List<SelectItem> getSelectHorarios() {
        return selectHorarios;
    }

    public void setSelectHorarios(List<SelectItem> selectHorarios) {
        this.selectHorarios = selectHorarios;
    }

    public List<SelectItem> getSelectSalas() {
        return selectSalas;
    }

    public void setSelectSalas(List<SelectItem> selectSalas) {
        this.selectSalas = selectSalas;
    }
    
    
    
    
}
