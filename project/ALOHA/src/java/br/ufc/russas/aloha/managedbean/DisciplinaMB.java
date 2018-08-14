package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.model.CursoSemestre;
import br.ufc.russas.aloha.model.Disciplina;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class DisciplinaMB {

    private Disciplina disciplina;
    private ArrayList<Disciplina> disciplinas;
    private CursoSemestre cursoSemCurrent;
    DisciplinaDAO disciplinaDAO;

    public DisciplinaMB() {
        this.disciplina = new Disciplina();
        this.disciplinas = new ArrayList<Disciplina>();
        this.disciplinaDAO = new DisciplinaDAO();
        this.cursoSemCurrent = new CursoSemestre();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public ArrayList<Disciplina> getDisciplinas() {

        return this.disciplinaDAO.selectALL();
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public CursoSemestre getCursoSemCurrent() {
        return cursoSemCurrent;
    }

    public void setCursoSemCurrent(CursoSemestre cursoSemCurrent) {
        this.cursoSemCurrent = cursoSemCurrent;
    }

    public void adicionar() {
        try {
            System.out.println(disciplina.getId());
            if (disciplinaDAO.find(disciplina.getId()) != null) {  
                
                if (disciplinaDAO.update(disciplina)) {
                    try {
                        
                        FacesContext.getCurrentInstance().getExternalContext().redirect("disciplinas.xhtml");
                        disciplina = new Disciplina();
                        this.disciplinas = disciplinaDAO.selectALL();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            } else {
                System.out.println(this.disciplina.getCursosSemestres().size());
                if (disciplinaDAO.insert(this.disciplina)) {
                    
                    try {
                       
                        FacesContext.getCurrentInstance().getExternalContext().redirect("disciplinas.xhtml");
                        this.disciplina = new Disciplina();
                        this.disciplinas = disciplinaDAO.selectALL();

                    } catch (Exception e) {
                        System.out.println("Erro");
                        e.getMessage();
                    }
                }
                else{
                    System.out.println("Aqui 2");
                }
            }
        } catch (Exception ex) {

        }

    }

    public void editar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_disciplina.xhtml");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void remover(){
        remove();
    }
    public void remove() {
        try {
            System.out.println(disciplina.getId());
            if (disciplinaDAO.delete(disciplina)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("disciplinas.xhtml");
                disciplina = new Disciplina();
                this.disciplinas = disciplinaDAO.selectALL();

            } else {
                System.out.println("Deu erro");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void novaDisciplina() {
        try {
            disciplina = new Disciplina();
            FacesContext.getCurrentInstance().getExternalContext().redirect("adicionar_disciplina.xhtml");

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void inserirCursoSemetre() {
        
        if (this.cursoSemCurrent.getCurso() != null && this.cursoSemCurrent.getSemestre() > 0) {
            
            this.disciplina.getCursosSemestres().add(this.cursoSemCurrent);
            this.cursoSemCurrent = new CursoSemestre();
        }

    }
    public void removerCursoSemetre() {

        if (this.cursoSemCurrent.getCurso() != null && this.cursoSemCurrent.getSemestre() > 0) {

            this.disciplina.getCursosSemestres().remove(this.cursoSemCurrent);
            this.cursoSemCurrent = new CursoSemestre();
        }

    }

}
