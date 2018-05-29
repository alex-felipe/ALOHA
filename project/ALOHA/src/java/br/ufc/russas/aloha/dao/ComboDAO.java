
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Combo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComboDAO {

    public ComboDAO() {
    }
    public boolean insert(Combo combo) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO combo (id) VALUES (?, ?)" ; 
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setString(1, combo.getId());
            return ps.executeUpdate() == 1;
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
    }

    public ArrayList<Combo> selectALL(){
        Connection con = null;
        ArrayList<Combo> listaCombos = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM combo";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Combo combo = new Combo();
                combo.setId(rs.getInt("id"));
                // Lista de dias ENUM
                listaCombos.add(combo);
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
        return listaCombos;
    }
}
