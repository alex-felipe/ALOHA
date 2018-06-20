
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
        combo.setId(this.geraIdCombo());
        combo.setCodigo_modelo(combo.getCodigo());
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO combo (codigo_modelo) VALUES (?)" ; 
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, combo.getCodigo_modelo());
            if(pst.executeUpdate() == 1){
                sql = "SELECT * FROM combo WHERE codigo_modelo = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, combo.getCodigo_modelo());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    combo.setId(rs.getInt("id"));
                }
                System.out.println("OK");
                ArrayList<String> listaDias = new ArrayList<>(combo.getDias());
                for(String dia: listaDias){
                    sql = "INSERT INTO combo_dias (id_combo, id_dia) VALUES (?, ?)";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, combo.getId());
                    pst.setString(2, dia);
                    pst.executeUpdate();
                }
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
                combo.setCodigo_modelo(rs.getString("codigo_modelo"));
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
            String sql = "SELECT id_dia FROM combo_dias WHERE id_combo = ?";
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
    
    public int geraIdCombo() {
        Connection con = null;
        int numero = 0;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT count(*) from combo";
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
    public static void main(String[] args) throws SQLException {
        ComboDAO cbd = new ComboDAO();
        ArrayList<DiasSemanaEnum> dias = new ArrayList<>();
        dias.add(DiasSemanaEnum.DOMINGO);
        dias.add(DiasSemanaEnum.TERCA);
        dias.add(DiasSemanaEnum.QUINTA);
        Combo c = new Combo();
        c.setCodigo_modelo("COMBO002");
        c.setDias(dias);
        System.out.println(cbd.insert(c));
    }
}
