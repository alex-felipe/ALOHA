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
public class ConexaoFactory {
    private Connection conexao;
    private final String ip_servidor = "192.168.0.121";
    public Connection abreConexao(){
        if(conexao != null){
            fechaConexao();
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String caminhoServidor = "jdbc:mysql://"+ ip_servidor +"/ALOHA";
            conexao = DriverManager.getConnection(caminhoServidor, "alex", "");
            return conexao;
        }catch(SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro com na identificação do driver JDBC");
        }
        return null;
    }
    
    public void fechaConexao(){
        if(conexao != null){
            try {
                conexao.close();
            } catch (SQLException e) {
               throw new RuntimeException(e);
            }            
        }

    }
}
