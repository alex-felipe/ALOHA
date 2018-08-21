package br.ufc.russas.aloha.managedbean;

import br.ufc.russas.aloha.dao.ConexaoFactory;
import br.ufc.russas.aloha.dao.DAOException;
import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.dao.DocenteDAO;
import br.ufc.russas.aloha.model.Disciplina;
import br.ufc.russas.aloha.model.Docente;
import br.ufc.russas.aloha.model.Preferencia;
import br.ufc.russas.aloha.model.exception.NomeInvalidoException;
import br.ufc.russas.aloha.model.exception.QuantidadeCreditosInvalidoException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class DocenteMB implements Serializable {

    private Docente docente;
    private List<Docente> listaDocentes;
    private Disciplina[] disciplinasSelecionadas;
    private List<Preferencia> preferencias;
    private List<String> diasSemana;
    DisciplinaDAO disciplinaDAO;
    DocenteDAO docenteDAO;

    private String feedback;

    public DocenteMB() {
        this.docenteDAO = new DocenteDAO();
        this.docente = new Docente();
        this.listaDocentes = docenteDAO.selectALL();
        this.disciplinaDAO = new DisciplinaDAO();
        this.preferencias = new ArrayList<>();
        for (Disciplina d : disciplinaDAO.selectALL()) {
            Preferencia p = new Preferencia(docente, d, 0);
            preferencias.add(p);
        }
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

                    if (false/*docenteDAO.update(docente)*/) {
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                            docente = new Docente();
                            this.listaDocentes = docenteDAO.selectALL();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    this.docente.setPreferencias(preferencias);
                    this.docente.setDiasSemana(diasSemana);
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
        } else{
            atualizaFeedback();
        }
    }

    public boolean remove() {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "DELETE FROM `docente` WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, docente.getId());
            //Executando os comandos
            if (ps.executeUpdate() == 1) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("docentes.xhtml");
                    this.docente = new Docente();
                    this.listaDocentes = docenteDAO.selectALL();
                    return true;
                } catch (IOException ex) {
                    Logger.getLogger(DocenteMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao executar operação", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return false;
    }

    public void inserePreferencia() {

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

    public List<Preferencia> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(ArrayList<Preferencia> preferencias) {
        this.preferencias = preferencias;
    }

    public String getFeedback() {
        return feedback;
    }

    public List<String> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }



    private boolean validaDocente() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (docente.getCrMax() <= docente.getCrMin()) {
            feedback = "A quantidade de créditos máximos deve ser MAIOR que a quantidade de créditos mínimos";  
            context.addMessage(null, new FacesMessage("Aviso", feedback));
            
        }
        return false;
    }

    public void atualizaFeedback() {
        


    }

}
