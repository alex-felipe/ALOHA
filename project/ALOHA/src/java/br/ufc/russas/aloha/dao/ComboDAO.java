
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Combo;
import br.ufc.russas.aloha.model.DiasSemanaEnum;
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
            String sql = "INSERT INTO combo_dia (id) VALUES (?, ?)" ; 
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
                combo.setDias(selectDiasDaSemanaDoCombo(combo));
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
    
    public ArrayList<DiasSemanaEnum> selectDiasDaSemanaDoCombo(Combo combo){
        int id_combo = combo.getId();
        ArrayList<DiasSemanaEnum> listaDias = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT id_dia FROM combo_dia WHERE id_combo = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id_combo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listaDias.add(DiasSemanaEnum.values()[rs.getInt("id_dia")]);
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
        return listaDias;       
    }
    
    public static void main(String[] args) throws SQLException {
        ComboDAO cbd = new ComboDAO();
        ArrayList<Combo> resultados = cbd.selectALL();
        for(Combo c: resultados){
            System.out.print(c.getId() + ": ");
            ArrayList<DiasSemanaEnum> dse = new ArrayList(c.getDias());
             System.out.print("[");
            for(DiasSemanaEnum d: dse){
                System.out.print(d + ",");
            }
            System.out.print("]");
        }
    }
}
