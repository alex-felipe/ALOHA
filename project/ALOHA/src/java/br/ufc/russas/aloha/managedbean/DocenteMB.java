package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.dao.DocenteDAO;
import br.ufc.russas.aloha.model.Disciplina;
import br.ufc.russas.aloha.model.Docente;
import br.ufc.russas.aloha.model.Preferencia;
import br.ufc.russas.aloha.model.Horario;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class DocenteMB implements Serializable {

    private Docente docente;
    private List<Docente> listaDocentes;
    private Disciplina[] disciplinasSelecionadas;
    DisciplinaDAO disciplinaDAO;
    DocenteDAO docenteDAO;
    private List<Horario> horarios;
    private List<String> teste;


    public DocenteMB() {
        this.docenteDAO = new DocenteDAO();
        this.docente = new Docente();
        this.listaDocentes = docenteDAO.selectALL();
        this.horarios = new ArrayList<>();
        this.disciplinaDAO = new DisciplinaDAO();
        this.teste = new ArrayList<>();
        for (Disciplina d : disciplinaDAO.selectALL()) {
            Preferencia p = new Preferencia(docente, d, 0);
            docente.getPreferencias().add(p);
        }
        addDiasSemana();
    }

    public void novoDocente() {
        try {
            docente = new Docente();
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_docente.xhtml");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adiciona() {
        if (validaDocente()) {
            try {
                if (docenteDAO.find(docente.getId()) != null) {

                    if (docenteDAO.update(docente)) {
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                            docente = new Docente();
                            this.listaDocentes = docenteDAO.selectALL();
                            enviaFeedBack("Operação realizada com sucesso", "O cadastro do docente foi realizado com sucesso!", 'i');
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    if (docenteDAO.insert(this.docente)) {
                        try {

                            FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                            this.docente = new Docente();
                            this.listaDocentes = docenteDAO.selectALL();

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
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_docente.xhtml");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void remove() {
        if(docenteDAO.remove(docente)){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                enviaFeedBack("Operação realizada com sucesso!", "Os dados do docente foram removidos com sucesso!", 'i');
            } catch (IOException ex) {
                Logger.getLogger(DocenteMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.docente = new Docente();
            this.listaDocentes = docenteDAO.selectALL();        
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

    public List<Horario> getHorarios() {
        return horarios;
    }


    

    private boolean validaDocente() {
        if (docente.getCrMax() <= docente.getCrMin()) {
            enviaFeedBack("Créditos Máximos Inválidos", "A quantidade de créditos máximos deve ser MAIOR que a quantidade de créditos mínimos", 'e');  
            return false;
        }
        return true;
    }
    
    
    private void addDiasSemana(){
        this.horarios.add(new Horario(0, "Domingo", "Manhã"));
        this.horarios.add(new Horario(1, "Domingo", "Tarde"));
        this.horarios.add(new Horario(2, "Segunda", "Manhã"));
        this.horarios.add(new Horario(3, "Segunda", "Tarde"));
        this.horarios.add(new Horario(4, "Terça", "Manhã"));
        this.horarios.add(new Horario(5, "Terça", "Tarde"));
        this.horarios.add(new Horario(6, "Quarta", "Manhã"));
        this.horarios.add(new Horario(7, "Quarta", "Tarde"));
        this.horarios.add(new Horario(8, "Quinta", "Manhã"));
        this.horarios.add(new Horario(9, "Quinta", "Tarde"));
        this.horarios.add(new Horario(10, "Sexta", "Manhã"));
        this.horarios.add(new Horario(11, "Sexta", "Tarde"));
        this.horarios.add(new Horario(12, "Sábado", "Manhã"));
        this.horarios.add(new Horario(13, "Sábado", "Tarde"));

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
