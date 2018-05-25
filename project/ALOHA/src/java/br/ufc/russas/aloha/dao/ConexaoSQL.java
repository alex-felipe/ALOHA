/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.dao;
import java.sql.*;
/**
 *
 * @author alexf
 */
public abstract class ConexaoSQL {
    private Connection conexao;
    public ConexaoSQL(Connection conexao){
        setConexao(conexao);
    }
   
    public Connection getConexao(){
        return this.conexao;
    }
    
    public void setConexao(Connection conexao){
        if(conexao != null)
            this.conexao = conexao;
    }
     
}
