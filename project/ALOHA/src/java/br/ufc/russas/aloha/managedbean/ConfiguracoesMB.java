package br.ufc.russas.aloha.managedbean;


import br.ufc.russas.aloha.dao.CursoDAO;
import br.ufc.russas.aloha.dao.HorarioDAO;
import br.ufc.russas.aloha.dao.UsuarioDAO;
import br.ufc.russas.aloha.model.Curso;
import br.ufc.russas.aloha.model.Horario;
import br.ufc.russas.aloha.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ConfiguracoesMB implements Serializable{

    private String urlDoServidor;
    private String nomeDoBD, login, senha;
    private List<Horario> horarios;
    private List<Curso> cursos;
    
    private HorarioDAO horarioDAO;
    private String novoHorario1, novoHorario2;
    private Horario horarioSelecionado;
    
    private CursoDAO cursoDAO;
    private String nomeNovoCurso;
    private Curso cursoSelecionado;

    private UsuarioDAO usuarioDAO;
    private Usuario user;
    
    private boolean habCampoLogin = true;
    public ConfiguracoesMB() {
        urlDoServidor = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("urlDoServidor");
        nomeDoBD = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("nomeDoBD");
        login = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("login");
        senha = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("senha");
        
        horarioDAO = new HorarioDAO();
        horarios = horarioDAO.selectALL();
        horarioSelecionado = new Horario();
        
        cursoDAO = new CursoDAO();
        cursos = cursoDAO.selectALL();
        cursoSelecionado = new Curso();
        
        usuarioDAO = new UsuarioDAO();
        user = usuarioDAO.select("user", "123");
    }
    
    
    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public String getUrlDoServidor() {
        return urlDoServidor;
    }

    public void setUrlDoServidor(String urlDoServidor) {
        this.urlDoServidor = urlDoServidor;
        if((urlDoServidor != null) && (! urlDoServidor.isEmpty())){
            FacesContext.getCurrentInstance().getExternalContext().getInitParameter("urlDoServidor");
        }
    }

    public String getNovoHorario1() {
        return novoHorario1;
    }

    public void setNovoHorario1(String novoHorario1) {
        this.novoHorario1 = novoHorario1;
    }

    public String getNovoHorario2() {
        return novoHorario2;
    }

    public void setNovoHorario2(String novoHorario2) {
        this.novoHorario2 = novoHorario2;
    }

    public Horario getHorarioSelecionado() {
        return horarioSelecionado;
    }

    public void setHorarioSelecionado(Horario horarioSelecionado) {
        this.horarioSelecionado = horarioSelecionado;
    }



    public String getNomeDoBD() {
        return nomeDoBD;
    }

    public void setNomeDoBD(String nomeDoBD) {
        this.nomeDoBD = nomeDoBD;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isHabCampoLogin() {

        return habCampoLogin;
    }

    public void setHabCampoLogin(boolean habCampoLogin) {
        this.habCampoLogin = habCampoLogin;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getNomeNovoCurso() {
        return nomeNovoCurso;
    }

    public void setNomeNovoCurso(String nomeNovoCurso) {
        this.nomeNovoCurso = nomeNovoCurso;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
        


    public void adicionaHorario(){
        if((novoHorario1 != null) && (! novoHorario1.isEmpty()) && (novoHorario2 != null) && (! novoHorario2.isEmpty())){
            String descricao = novoHorario1 + " - " + novoHorario2;
            Horario novoHorario = new Horario();
            novoHorario.setDescricao(descricao);
            if(novoHorario.getDescricao() == null){
                
            }else{
                horarioDAO.insert(novoHorario);
                novoHorario1 = null;
                novoHorario2 = null;
                horarios = horarioDAO.selectALL();
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("configuracoes.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ConfiguracoesMB.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }

        }
    }
    
    public void removeHorario(){
        System.out.println(horarioSelecionado.getId());
        try {
            if (! horarioDAO.delete(horarioSelecionado)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("configuracoes.xhtml");
                horarioSelecionado = new Horario();
                this.horarios = horarioDAO.selectALL();

            } else {
                System.out.println("Erro Não foi possível remover este combo!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void adicionaCurso() {
        if ((nomeNovoCurso != null) && (!nomeNovoCurso.isEmpty())) {
            Curso novoCurso = new Curso();
            novoCurso.setNome(nomeNovoCurso);
            cursoDAO.insert(novoCurso);
            nomeNovoCurso = null;
            cursos = cursoDAO.selectALL();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("configuracoes.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracoesMB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void removeCurso() {
        try {
            if (!cursoDAO.delete(cursoSelecionado)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("configuracoes.xhtml");
                cursoSelecionado = new Curso();
                this.cursos = cursoDAO.selectALL();

            } else {
                System.out.println("Erro Não foi possível remover este combo!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
