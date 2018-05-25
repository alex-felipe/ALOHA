/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.dao;

import Model.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alexf
 */
public class SalaDAO extends ConexaoSQL{
    public SalaDAO(Connection conexao) {
        super(conexao);
    }
    
    public boolean cadastra(Sala sala) throws SQLException{
        String sql = "INSERT INTO sala (nome, tipo, capacidade, bloco, codigoModelo) VALUES (?, ?, ?, ?, ?)" ; 
        PreparedStatement ps = super.getConexao().prepareStatement(sql);
        
        ps.setString(1, sala.getNome());
        ps.setString(2, sala.getTipo());
        ps.setInt(3, sala.getCapacidade());
        ps.setString(4, sala.getBloco());
        ps.setString(5, sala.getCodigo());
        
        return ps.executeUpdate() == 1;
    }  

}
