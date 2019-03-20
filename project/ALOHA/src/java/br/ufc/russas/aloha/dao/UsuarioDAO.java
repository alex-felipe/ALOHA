/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Curso;
import br.ufc.russas.aloha.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Felipe
 */
public class UsuarioDAO {
    public boolean insert(Usuario usuario) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO curso (nome, login, senha) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "" + usuario.getNome());
            pst.setString(2, "" + usuario.getLogin());
            pst.setString(3, "" + usuario.getSenha());
            return pst.executeUpdate() == 1;
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

    public boolean delete(Usuario usuario) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "DELETE FROM usuario WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();

            //Executando os comandos
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada.", e);
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

    public Usuario select(String login, String senha) {
        Connection con = null;
        Usuario user = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
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
        return user;
    }   
}
