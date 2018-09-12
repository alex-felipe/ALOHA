package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlanejamentoDAO {

    public boolean insert(Planejamento planejamento) {
        //docente.setCodigoModelo(docente.getCodigo());
        Connection con = null;
        try {
            
            con = ConexaoFactory.getConnection();
            //Inserção do planejamento
            String sql = "INSERT INTO `planejamento` (`nome`, `status`) VALUES (?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, planejamento.getNome());
            ps.setBoolean(2, planejamento.isFinalizado());
            ps.executeUpdate();

            planejamento.setId(find(planejamento.getNome()));
            //Inserção das disciplinas
            for (Turmas d : planejamento.getTurmas()) {
                for (int i = 0; i < d.getQntTurmas(); i++) {
                    sql = "INSERT INTO `planejamento_disciplina` (`id_planejamento`, `id_disciplina`) VALUES (?, ?);";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, planejamento.getId());
                    ps.setInt(2, d.getDisciplina().getId());
                    ps.executeUpdate();
                }
            }
            //Inserção dos docentes do planejamento
            for (Docente doc : planejamento.getDocentes()) {
                sql = "INSERT INTO `planejamento_docente` (`id_planejamento`, `id_docente`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, doc.getId());
                ps.executeUpdate();
            }
            //Inserção das salas do planejamento
            for (Sala s: planejamento.getSalas()) {
                sql = "INSERT INTO `planejamento_sala` (`id_planejamento`, `id_sala`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, s.getId());
                ps.executeUpdate();
            }
            //Inserção das variáveis fixas do planejamento
            for (VariaveisFixas v: planejamento.getVariaveisFixas()) {
                sql = "INSERT INTO `planejamento_variaveis_fixas` (`id_planejamento`, `id_var_fixa`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, v.getId());
                ps.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public int find(String nome) throws SQLException {
        Connection con = null;
        Planejamento p = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM `planejamento` WHERE  `nome` = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();

            rs.next();
            int id = rs.getInt("id");
            pst.close();
            con.close();
            return id;
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return 0;
    }
    
    public ArrayList<Planejamento> selectALL() {
        Connection con = null;
        ArrayList<Planejamento> listaPlanejamentos = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM planejamento";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Planejamento p =  new Planejamento();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setFinalizado(rs.getBoolean("status"));
                
                
                ResultSet rsAux;
                
                sql = "SELECT * FROM planejamento_disciplina WHERE id_planejamento = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, p.getId());
                rsAux = pst.executeQuery();
                while(rsAux.next()){
                    
                }
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
        return listaPlanejamentos;
    }
}
