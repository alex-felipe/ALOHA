
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VariaveisFixasDAO {
    
    public boolean insert(VariaveisFixas varFix) {
        //docente.setCodigoModelo(docente.getCodigo());
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO `variaveis_fixas` (`id_docente`, `id_disciplina`, `id_combo`, `id_horario`, `id_sala`) VALUES ( ?, ?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, varFix.getDocente().getId());
            ps.setInt(2, varFix.getDisciplina().getId());
            ps.setInt(3, varFix.getCombo().getId());
            ps.setInt(4, varFix.getHorario().getId());
            ps.setInt(5, varFix.getSala().getId());
            ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            System.out.println(e);
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
    
     public VariaveisFixas find(int id) throws SQLException{
        Connection con = null;
        VariaveisFixas var = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM `variaveis_fixas` where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                var = map(rs);
            }
            pst.close();
            con.close();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return var;
    }
     
     public int retrunId(VariaveisFixas var){
         Connection con = null;
        
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM `variaveis_fixas` WHERE `id_docente`= ? AND `id_disciplina` = ? AND`id_combo`= ? AND `id_horario`= ? AND `id_sala`= ?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, var.getDocente().getId());
            pst.setInt(2, var.getDisciplina().getId());
            pst.setInt(3, var.getCombo().getId());
            pst.setInt(4, var.getHorario().getId());
            pst.setInt(5, var.getSala().getId());
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
     
     private VariaveisFixas map(ResultSet rs) throws SQLException {
         DocenteDAO doc = new DocenteDAO();
         DisciplinaDAO disc = new DisciplinaDAO();
         ComboDAO c = new ComboDAO();
         HorarioDAO h = new HorarioDAO();
         SalaDAO s = new SalaDAO();
         
         VariaveisFixas v = new VariaveisFixas();
         
         v.setDocente(doc.find(rs.getInt("id_docente")));
         v.setDisciplina(disc.find(rs.getInt("id_disciplina")));
         v.setCombo(c.find(rs.getInt("id_combo")));
         v.setHorario(h.find(rs.getInt("id_horario")));
         v.setSala(s.find(rs.getInt("id_sala")));
                // (rs.getInt("id"), rs.getString("codigo_modelo"), rs.getString("nome"), rs.getInt("cr_minimo"), rs.getInt("cr_maximo"));
        return v;
    }
     
     
     public boolean delete(VariaveisFixas varFix) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "DELETE FROM variaveis_fixas WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, varFix.getId());
            ps.executeUpdate();

            //Executando os comandos
            return ps.executeUpdate() != 0;
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
     
    public boolean update(VariaveisFixas varfixa) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "UPDATE `variaveis_fixas` SET `id_docente` = ?, `id_disciplina` = ?, `id_combo` = ?, `id_horario` = ?, `id_sala` = ? WHERE `variaveis_fixas`.`id` = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, varfixa.getDocente().getId());
            ps.setInt(2, varfixa.getDisciplina().getId());
            ps.setInt(3, varfixa.getCombo().getId());
            ps.setInt(4, varfixa.getHorario().getId());
            ps.setInt(5, varfixa.getSala().getId());
            ps.setInt(6, varfixa.getId());
            //Executando os comandos
            return ps.executeUpdate() == 1;
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
    
}
