
package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.Docente;
import br.ufc.russas.aloha.model.Preferencia;
import br.ufc.russas.aloha.model.exception.NomeInvalidoException;
import br.ufc.russas.aloha.model.exception.QuantidadeCreditosInvalidoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocenteDAO {

    public boolean insert(Docente docente) {
        docente.setCodigoModelo(docente.getCodigo());
        Connection con = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "INSERT INTO `docente` (`id`, `codigo_modelo`, `nome`, `cr_minimo`, `cr_maximo`) VALUES (?,?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setString(2, "NULL");
            ps.setString(3, docente.getNome());
            ps.setInt(4, docente.getCrMin());
            ps.setInt(5, docente.getCrMax());
            ps.executeUpdate();
            
            docente.setId(find(docente.getNome()));
            
            sql = "UPDATE `docente` SET `codigo_modelo`= ? WHERE `id`= ?;";
            ps = con.prepareStatement(sql);
            ps.setString(1, docente.getCodigo());
            ps.setInt(2, docente.getId());
            ps.executeUpdate();
            
            for (Preferencia preferencia : docente.getPreferencias()) {
                sql = "INSERT INTO `preferencia`(`id_docente`, `id_disciplina`, `preferencia`) VALUES (?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, docente.getId());
                ps.setInt(2, preferencia.getDisciplina().getId());
                ps.setInt(3, preferencia.getPreferencia());
                ps.executeUpdate();
            }
            
            
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } catch (QuantidadeCreditosInvalidoException ex) {
            System.out.println(ex.getMessage());
        } catch (NomeInvalidoException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
    
    public ArrayList<Docente> selectALL(){
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
                docente.setCodigoModelo(rs.getString("codigo_modelo"));
                try {
                    docente.setNome(rs.getString("nome"));
                    docente.setCrMin(rs.getInt("cr_minimo"));
                    docente.setCrMax(rs.getInt("cr_maximo"));
                } catch (NomeInvalidoException ex) {
                    System.out.println("erro nos creditos");
                }
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
    
    public Docente find(int id) throws SQLException, QuantidadeCreditosInvalidoException, NomeInvalidoException {
        Connection con = null;
        Docente s = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * from docente where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                s = map(rs);
            }
            pst.close();
            con.close();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return s;
    }
    
    public int find(String nome) throws SQLException, QuantidadeCreditosInvalidoException, NomeInvalidoException {
        Connection con = null;
        Docente s = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT `id` FROM `docente` WHERE `nome` = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            ResultSet rs = pst.executeQuery();

            rs.next();
            int id = rs.getInt("id");
            pst.close();
            con.close();
            return id;
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return 0;
    }
    private Docente map(ResultSet rs) throws SQLException, QuantidadeCreditosInvalidoException, NomeInvalidoException {
        Docente d = new Docente(rs.getInt("id"), rs.getString("codigo_modelo"), rs.getString("nome"), rs.getInt("cr_minimo"), rs.getInt("cr_maximo"));
        return d;
    }
}
