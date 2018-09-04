
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Combo;
import br.ufc.russas.aloha.model.DiasSemanaEnum;
import br.ufc.russas.aloha.model.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class HorarioDAO {

    public HorarioDAO() {
    }
    public boolean insert(Horario horario) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO horario (descricao) VALUES (?)" ; 
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "" + horario.getDescricao());
            if(pst.executeUpdate() == 1){
                sql = "SELECT * FROM horario WHERE descricao = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, horario.getDescricao());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    horario.setId(rs.getInt("id"));
                }
                
                sql = "UPDATE `horario` SET `codigo_modelo`= ? WHERE id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, horario.getCodigo());
                pst.setInt(2, horario.getId());
                
                pst.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Falha na execução da consulta SQL", e);
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
    
    public boolean delete(Horario horario) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "DELETE FROM horario WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, horario.getId());
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
    
    public ArrayList<Horario> selectALL(){
        Connection con = null;
        ArrayList<Horario> listaHorarios = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM horario";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt("id"));
                horario.setCodigo_modelo(rs.getString("codigo_modelo"));
                horario.setDescricao(rs.getString("descricao"));
                listaHorarios.add(horario);
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
        return listaHorarios;
    }
    

}
