
package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.model.CursoSemestre;
import br.ufc.russas.aloha.model.Disciplina;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DisciplinaMB {
    private Disciplina disciplina;
    private ArrayList<Disciplina> disciplinas;
    private CursoSemestre cursoSemCurrent;
    DisciplinaDAO disciplinaDAO;
    
    public DisciplinaMB(){
        this.disciplina = new Disciplina();
        this.disciplinas = new ArrayList<Disciplina>();
        this.disciplinaDAO  = new DisciplinaDAO();
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
    
    public void adicionar(){
        disciplinaDAO.insert(this.disciplina);
        this.disciplina = new Disciplina();
        this.disciplinas = disciplinaDAO.selectALL();
    }
    

    public void inserirCursoSemetre(){
        if(this.cursoSemCurrent.getCurso() !=null && this.cursoSemCurrent.getSemestre()>0){
           this.disciplina.getCursosSemestres().add(this.cursoSemCurrent);
           this.cursoSemCurrent =  new CursoSemestre();
        }
        
        
    }
    
    
    
    
}
