
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Disciplina;
import br.ufc.russas.aloha.model.Docente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteDAO {

    public boolean insert(Docente docente) {
        docente.setId(this.geraIdDocente());
        docente.setCodigo_modelo(docente.getCodigo());
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO `docente` (`codigo_modelo`, `nome`, `cr_minimo`, `cr_maximo`) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, docente.getCodigo_modelo());
            ps.setString(2, docente.getNome());
            ps.setInt(3, docente.getCrMin());
            ps.setInt(4, docente.getCrMax());

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
    
    public ArrayList<Docente> selectALL() {
        Connection con = null;
        ArrayList<Docente> listaDocentes = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM docente";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setId(rs.getInt("id"));
                docente.setCodigo_modelo(rs.getString("codigo_modelo"));
                docente.setNome(rs.getString("nome"));
                docente.setCrMin(rs.getInt("cr_minimo"));
                docente.setCrMax(rs.getInt("cr_maximo"));
                listaDocentes.add(docente);
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
        return listaDocentes;
    }

    public int geraIdDocente() {
        Connection con = null;
        int numero = 0;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT count(*) from docente";
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
}
