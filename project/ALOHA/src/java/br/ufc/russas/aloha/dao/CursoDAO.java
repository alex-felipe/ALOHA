/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alex Felipe
 */
public class CursoDAO {
    public boolean insert(Curso curso) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO curso (nome) VALUES (?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "" + curso.getNome());
            if (pst.executeUpdate() == 1) {
                sql = "SELECT * FROM curso WHERE nome = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, curso.getNome());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    curso.setId(rs.getInt("id"));
                }

                sql = "UPDATE `curso` SET `codigo_modelo`= ? WHERE id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, curso.getCodigo());
                pst.setInt(2, curso.getId());

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

    public boolean delete(Curso curso) {
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "DELETE FROM curso WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, curso.getId());
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

    public ArrayList<Curso> selectALL() {
        Connection con = null;
        ArrayList<Curso> listaCursos = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM curso";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodigo_modelo(rs.getString("codigo_modelo"));
                curso.setNome(rs.getString("nome"));
                listaCursos.add(curso);
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
        return listaCursos;
    }
    
}
