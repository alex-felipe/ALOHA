
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
            
            for (String dia: docente.getDiasSemana()) {
                System.out.println("asldkyasdashdla");
                sql = "INSERT INTO `docente_dias_semana`(`id_docente`, `dia_semana`) VALUES (?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, docente.getId());
                switch(dia){
                    case "Domingo": ps.setInt(2, 0); break;
                    case "Segunda": ps.setInt(2, 1); break;
                    case "Terça": ps.setInt(2, 2); break;
                    case "Quarta": ps.setInt(2, 3); break;
                    case "Quinta": ps.setInt(2, 4); break;
                    case "Sexta": ps.setInt(2, 5); break;
                    case "Sábado": ps.setInt(2, 6); break;
                    default: 
                        return false;
                }
                
                ps.executeUpdate();   
            }
            
            
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, e);
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
                docente.setCodigoModelo(rs.getString("codigo_modelo"));
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
    
    public Docente find(int id) throws SQLException{
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
    
    public int find(String nome) throws SQLException{
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
    private Docente map(ResultSet rs) throws SQLException {
        Docente d = new Docente(rs.getInt("id"), rs.getString("codigo_modelo"), rs.getString("nome"), rs.getInt("cr_minimo"), rs.getInt("cr_maximo"));
        return d;
    }
}
