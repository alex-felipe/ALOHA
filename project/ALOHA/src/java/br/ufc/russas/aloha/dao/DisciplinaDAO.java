package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.CursoSemestre;
import br.ufc.russas.aloha.model.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public boolean insert(Disciplina disciplina){
        disciplina.setId(this.gerarIdDisciplina());
        disciplina.setCodigoModelo(disciplina.geraCodigo());
        Connection con = null;
        int tmp;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO `disciplina` (`codigo_modelo`, `codigo_disciplina`, `nome`, `cr_praticos`, `cr_teoricos`, `vagas`, `tipo_sala`) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, disciplina.getCodigoModelo());
            ps.setString(2, disciplina.getCodigo());
            ps.setString(3, disciplina.getNome());
            ps.setInt(4, disciplina.getCrPraticos());
            ps.setInt(5, disciplina.getCrTeoricos());
            ps.setInt(6, disciplina.getVagas());
            ps.setString(7, disciplina.getTipoSala());
            tmp = ps.executeUpdate();
            for(CursoSemestre cs: disciplina.getCursosSemestres()){
                sql = "INSERT INTO `disciplina_curso_semestre` (`id_disciplina`, `curso`, `semestre`) VALUES (?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, disciplina.getId());
                ps.setString(2, cs.getCurso());
                ps.setInt(3, cs.getSemestre());
                tmp = ps.executeUpdate();
            }

            return tmp == 1;
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }

        }
    }

    public ArrayList<Disciplina> selectALL() {
        Connection con = null;
        ArrayList<Disciplina> listaDisciplinas = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM disciplina";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Disciplina disciplina =  map(rs);
                
                disciplina.setCursosSemestres(selectCursoSemestre(disciplina));
                listaDisciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha na execução do SQL", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão", e);
            }
        }
        return listaDisciplinas;
    }
    //(`codigo_modelo`, `codigo_disciplina`, `nome`, `cr_praticos`, `cr_teoricos`, `vagas`, `tipo_sala`
    private Disciplina map(ResultSet rs) throws SQLException {
        Disciplina d = new Disciplina(rs.getInt("id"), rs.getString("codigo_modelo"),rs.getString("codigo_disciplina"), 
                rs.getString("nome"), rs.getInt("cr_praticos"), rs.getInt("cr_teoricos"), rs.getInt("vagas"), rs.getString("tipo_sala"));
        return d;
    }

    public List<CursoSemestre> selectCursoSemestre(Disciplina disciplina) {
        int id_disciplina = disciplina.getId();
        ArrayList<CursoSemestre> listaCursoSemestre = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM disciplina_curso_semestre WHERE id_disciplina = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id_disciplina);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listaCursoSemestre.add(new CursoSemestre(rs.getInt("id"), rs.getString("curso"), rs.getInt("semestre")));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha na execução do SQL", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão", e);
            }
        }
        return listaCursoSemestre;
    }
    
    public int gerarIdDisciplina() {
        Connection con = null;
        int numero = 0;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "select count(*) from disciplina";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                numero = rs.getInt("count(*)") + 1;
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return numero;
    }
    
}
