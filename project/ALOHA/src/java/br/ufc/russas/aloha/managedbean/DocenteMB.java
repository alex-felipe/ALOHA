package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.dao.DocenteDAO;
import br.ufc.russas.aloha.model.Disciplina;
import br.ufc.russas.aloha.model.Docente;
import br.ufc.russas.aloha.model.Preferencia;
import br.ufc.russas.aloha.model.DiaSemana;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class DocenteMB implements Serializable {

    private Docente docente;
    private List<Docente> listaDocentes;
    private Disciplina[] disciplinasSelecionadas;
    DisciplinaDAO disciplinaDAO;
    DocenteDAO docenteDAO;
    private List<DiaSemana> horarios;

    private List<String> horariosSelecionados; // preselected

    private List<SelectItem> horariosCheckbox;


    
    
    public DocenteMB() {
        this.docenteDAO = new DocenteDAO();
        this.docente = new Docente();
        this.listaDocentes = docenteDAO.selectALL();
        this.horarios = new ArrayList<>();
        this.disciplinaDAO = new DisciplinaDAO();
        for (Disciplina d : disciplinaDAO.selectALL()) {
            System.out.println(d.getNome());
            Preferencia p = new Preferencia(docente, d, 0);
            docente.getPreferencias().add(p);
        }
        addDiasSemana();
        this.horariosCheckbox = new ArrayList<>();
        addItensHorarios();
        
    }

    public void novoDocente() {
        try {
            docente = new Docente();
            for (Disciplina d : disciplinaDAO.selectALL()) {
                System.out.println(d.getNome());
                Preferencia p = new Preferencia(docente, d, 0);
                docente.getPreferencias().add(p);
            }
            horariosSelecionados = new ArrayList<>();
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_docente.xhtml");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adiciona() {
        if (validaDocente()) {
            try {
                this.docente.setDiasSemana(horariosSelecionados);
                if (docenteDAO.find(docente.getId()) != null) {

                    if (docenteDAO.update(docente)) {
                        try {
                            docente = new Docente();
                            this.listaDocentes = docenteDAO.selectALL();
                            horariosSelecionados = new ArrayList<>();
                            FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                            enviaFeedBack("Operação realizada com sucesso", "O cadastro do docente foi realizado com sucesso!", 'i');
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    if (docenteDAO.insert(this.docente)) {
                        try {
                            docente = new Docente();
                            this.listaDocentes = docenteDAO.selectALL();
                            horariosSelecionados = new ArrayList<>();
                            FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");

                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro na inserção de Docente (SQL)");
            }
        }
    }

    public void edita() {
        try {
            

            this.horariosSelecionados = new ArrayList<>();
            for (DiaSemana h : docente.getDiasSemana()) {
                horariosSelecionados.add("" + h.getId());
            }

            
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_docente.xhtml");

            
            

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void remove() {
        if(docenteDAO.remove(docente)){
            try {
                this.docente = new Docente();
                this.listaDocentes = docenteDAO.selectALL();
                FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                enviaFeedBack("Operação realizada com sucesso!", "Os dados do docente foram removidos com sucesso!", 'i');
            } catch (IOException ex) {
                Logger.getLogger(DocenteMB.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }else{
            enviaFeedBack("Erro!", "Não foi possível realizar o cadastro do docente!", 'e');
        }
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Docente> getListaDocentes() {
        return listaDocentes;
    }

    public void setListaDocentes(List<Docente> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    public Disciplina[] getDisciplinasSelecionadas() {
        return disciplinasSelecionadas;
    }

    public void setDisciplinasSelecionadas(Disciplina[] disciplinasSelecionadas) {
        this.disciplinasSelecionadas = disciplinasSelecionadas;
    }

    public List<DiaSemana> getHorarios() {
        return horarios;
    }

    public List<String> getHorariosSelecionados() {
        return horariosSelecionados;
    }

    public void setHorariosSelecionados(List<String> horariosSelecionados) {
        this.horariosSelecionados = horariosSelecionados;
    }

    public List<SelectItem> getHorariosCheckbox() {
        return horariosCheckbox;
    }

    public void setHorariosCheckbox(List<SelectItem> horariosCheckbox) {
        this.horariosCheckbox = horariosCheckbox;
    }


    

    private boolean validaDocente() {
        if (docente.getCrMax() <= docente.getCrMin()) {
            enviaFeedBack("Créditos Máximos Inválidos", "A quantidade de créditos máximos deve ser MAIOR que a quantidade de créditos mínimos", 'e');  
            return false;
        }
        return true;
    }
    
    
    private void addDiasSemana(){
        this.horarios.add(new DiaSemana(0, "Domingo", "Manhã"));
        this.horarios.add(new DiaSemana(1, "Domingo", "Tarde"));
        this.horarios.add(new DiaSemana(2, "Segunda", "Manhã"));
        this.horarios.add(new DiaSemana(3, "Segunda", "Tarde"));
        this.horarios.add(new DiaSemana(4, "Terça", "Manhã"));
        this.horarios.add(new DiaSemana(5, "Terça", "Tarde"));
        this.horarios.add(new DiaSemana(6, "Quarta", "Manhã"));
        this.horarios.add(new DiaSemana(7, "Quarta", "Tarde"));
        this.horarios.add(new DiaSemana(8, "Quinta", "Manhã"));
        this.horarios.add(new DiaSemana(9, "Quinta", "Tarde"));
        this.horarios.add(new DiaSemana(10, "Sexta", "Manhã"));
        this.horarios.add(new DiaSemana(11, "Sexta", "Tarde"));
        this.horarios.add(new DiaSemana(12, "Sábado", "Manhã"));
        this.horarios.add(new DiaSemana(13, "Sábado", "Tarde"));

    }

    private void addItensHorarios() {
        for(DiaSemana h: horarios){
            this.horariosCheckbox.add(new SelectItem(h.getId(), h.getDia() + " (" + h.getTurno() + ")"));
        }

    }

    
    public void enviaFeedBack(String titulo, String msg, char severidade) {
        FacesContext context = FacesContext.getCurrentInstance();
        Severity s = null;
        switch(severidade){
            case 'i': s = FacesMessage.SEVERITY_INFO; break;
            case 'a': s = FacesMessage.SEVERITY_WARN; break;
            case 'e': s = FacesMessage.SEVERITY_ERROR; break;
            case 'f': s = FacesMessage.SEVERITY_FATAL; break;
            default: s = FacesMessage.SEVERITY_INFO; break;
        }
        context.addMessage(null, new FacesMessage(s, titulo, msg));
    }

}
