
package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.DocenteDAO;
import br.ufc.russas.aloha.model.Docente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class DocenteMB {
    private Docente docente;
    private List<Docente> listaDocentes;
    DocenteDAO docenteDAO;
    public DocenteMB() {
        this.docenteDAO = new DocenteDAO();
        this.docente = new Docente();
        this.listaDocentes = docenteDAO.selectALL();  
    }

    public void adiciona(){
        System.out.println("aldjalskdj");
        docenteDAO.insert(docente);
        docente = new Docente();
        this.listaDocentes = docenteDAO.selectALL();
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
    
    
}
