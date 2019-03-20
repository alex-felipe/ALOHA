package br.ufc.russas.aloha.dao;

import br.ufc.russas.aloha.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanejamentoDAO {

    public boolean insert(Planejamento planejamento) {
        //docente.setCodigoModelo(docente.getCodigo());
        Connection con = null;
        try {

            con = ConexaoFactory.getConnection();
            //Inserção do planejamento
            String sql = "INSERT INTO `planejamento` (`nome`, `status`) VALUES (?, ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, planejamento.getNome());
            ps.setBoolean(2, planejamento.isFinalizado());
            ps.executeUpdate();

            planejamento.setId(find(planejamento.getNome()));
            //____________________________________________________________________________________________________________________
            //Inserção das disciplinas
            for (Turmas d : planejamento.getTurmas()) {
                for (int i = 0; i < d.getQntTurmas(); i++) {
                    sql = "INSERT INTO `planejamento_disciplina` (`id_planejamento`, `id_disciplina`) VALUES (?, ?);";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, planejamento.getId());
                    ps.setInt(2, d.getDisciplina().getId());
                    ps.executeUpdate();
                }
            }
            //____________________________________________________________________________________________________________________
            //Inserção dos docentes do planejamento
            for (Docente doc : planejamento.getDocentes()) {
                sql = "INSERT INTO `planejamento_docente` (`id_planejamento`, `id_docente`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, doc.getId());
                ps.executeUpdate();
            }
            //____________________________________________________________________________________________________________________
            //Inserção das salas do planejamento
            for (Sala s : planejamento.getSalas()) {
                sql = "INSERT INTO `planejamento_sala` (`id_planejamento`, `id_sala`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, s.getId());
                ps.executeUpdate();
            }
            //____________________________________________________________________________________________________________________
            //Inserção das variáveis fixas do planejamento
            VariaveisFixasDAO varDAO = new VariaveisFixasDAO();
            for (VariaveisFixas v : planejamento.getVariaveisFixas()) {

                try {
                    varDAO.insert(v);

                } catch (Exception e) {
                    System.out.println("Erro ao inserir variável fixa");

                }
                if (varDAO.retrunId(v) != 0) {
                    v.setId(varDAO.retrunId(v));
                    sql = "INSERT INTO `planejamento_variaveis_fixas` (`id_planejamento`, `id_var_fixa`) VALUES (?, ?);";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, planejamento.getId());
                    ps.setInt(2, v.getId());
                    ps.executeUpdate();
                } else {
                    System.out.println("Não foi possivel recuperar os dados da variável fixa");
                }

            }
            //____________________________________________________________________________________________________________________

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public int find(String nome) throws SQLException {
        Connection con = null;
        Planejamento p = null;
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM `planejamento` WHERE  `nome` = ?";
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

    public Planejamento find(int id) throws SQLException {
        Connection con = null;
        Planejamento p = null;
        DocenteDAO doc = new DocenteDAO();
        DisciplinaDAO disc = new DisciplinaDAO();
        SalaDAO s = new SalaDAO();
        VariaveisFixasDAO v = new VariaveisFixasDAO();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM planejamento";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            p = new Planejamento();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setFinalizado(rs.getBoolean("status"));

            ResultSet rsAux;

            sql = "SELECT * FROM planejamento_disciplina WHERE id_planejamento = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.getId());
            rsAux = pst.executeQuery();
            Disciplina d;
            while (rsAux.next()) {
                d = disc.find(rsAux.getInt("id_disciplina"));
                if (p.getTurmas().contains(new Turmas(d, 1))) {
                    int i = p.getTurmas().get(p.getTurmas().indexOf(new Turmas(d, 1))).getQntTurmas();
                    p.getTurmas().get(p.getTurmas().indexOf(new Turmas(d, 1))).setQntTurmas(i++);
                } else {
                    p.getTurmas().add(new Turmas(d, 1));
                }
            }

            sql = "SELECT * FROM planejamento_docente WHERE id_planejamento = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.getId());
            rsAux = pst.executeQuery();
            Docente docente;
            while (rsAux.next()) {
                docente = doc.find(rsAux.getInt("id_docente"));
                p.getDocentes().add(docente);

            }

            sql = "SELECT * FROM planejamento_sala WHERE id_planejamento = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.getId());
            rsAux = pst.executeQuery();
            Sala sala;
            while (rsAux.next()) {
                sala = s.find(rsAux.getInt("id_sala"));
                p.getSalas().add(sala);

            }

            sql = "SELECT * FROM planejamento_variaveis_fixas WHERE id_planejamento = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.getId());
            rsAux = pst.executeQuery();
            VariaveisFixas variavel;
            while (rsAux.next()) {
                variavel = v.find(rsAux.getInt("id_var_fixa"));
                p.getVariaveisFixas().add(variavel);

            }
            return p;

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return null;
    }

    public ArrayList<Planejamento> selectALL() {
        DocenteDAO doc = new DocenteDAO();
        DisciplinaDAO disc = new DisciplinaDAO();
        SalaDAO s = new SalaDAO();
        VariaveisFixasDAO v = new VariaveisFixasDAO();
        Connection con = null;
        ArrayList<Planejamento> listaPlanejamentos = new ArrayList<>();
        try {
            con = ConexaoFactory.getConnection();
            String sql = "SELECT * FROM planejamento";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            Planejamento p;
            while (rs.next()) {
                p = new Planejamento();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setFinalizado(rs.getBoolean("status"));

                ResultSet rsAux;

                sql = "SELECT * FROM planejamento_disciplina WHERE id_planejamento = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, p.getId());
                rsAux = pst.executeQuery();
                Disciplina d;
                while (rsAux.next()) {
                    d = disc.find(rsAux.getInt("id_disciplina"));
                    if (p.getTurmas().contains(new Turmas(d, 1))) {
                        int i = p.getTurmas().get(p.getTurmas().indexOf(new Turmas(d, 1))).getQntTurmas();
                        p.getTurmas().get(p.getTurmas().indexOf(new Turmas(d, 1))).setQntTurmas(i++);
                    } else {
                        p.getTurmas().add(new Turmas(d, 1));
                    }
                }

                sql = "SELECT * FROM planejamento_docente WHERE id_planejamento = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, p.getId());
                rsAux = pst.executeQuery();
                Docente docente;
                while (rsAux.next()) {
                    docente = doc.find(rsAux.getInt("id_docente"));
                    p.getDocentes().add(docente);

                }

                sql = "SELECT * FROM planejamento_sala WHERE id_planejamento = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, p.getId());
                rsAux = pst.executeQuery();
                Sala sala;
                while (rsAux.next()) {
                    sala = s.find(rsAux.getInt("id_sala"));
                    p.getSalas().add(sala);

                }

                sql = "SELECT * FROM planejamento_variaveis_fixas WHERE id_planejamento = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, p.getId());
                rsAux = pst.executeQuery();
                VariaveisFixas variavel;
                while (rsAux.next()) {
                    variavel = v.find(rsAux.getInt("id_var_fixa"));
                    p.getVariaveisFixas().add(variavel);

                }
                listaPlanejamentos.add(p);
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
        return listaPlanejamentos;
    }

    public boolean delete(Planejamento planejamento) {
        Connection con = null;
        boolean res;
        try {
            con = ConexaoFactory.getConnection();

            String sql = "DELETE FROM planejamento WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            res = ps.executeUpdate() != 0;
            
            sql = "DELETE FROM planejamento_disciplina WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            res = ps.executeUpdate() != 0;

            sql = "DELETE FROM planejamento_docente WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            res = ps.executeUpdate() != 0;
           
            sql = "DELETE FROM planejamento_sala WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            res = ps.executeUpdate() != 0;
            
            sql = "DELETE FROM planejamento_variaveis_fixas WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            res = ps.executeUpdate() != 0;
            
            VariaveisFixasDAO varDAO = new VariaveisFixasDAO();
            for (VariaveisFixas v : planejamento.getVariaveisFixas()) {
                try {
                    varDAO.delete(v);
                } catch (Exception e) {
                    System.out.println("Erro ao remover variável fixa");
                }
            }

            return res;
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

    public boolean update(Planejamento planejamento) {
        Connection con = null;

        System.out.println(planejamento.getId());

        int tmp = 0;
        try {
            con = ConexaoFactory.getConnection();
            //Inserção do planejamento
            String sql = "UPDATE `planejamento` SET `nome` = ? , `status` = ? WHERE `planejamento`.`id` = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, planejamento.getNome());
            ps.setBoolean(2, planejamento.isFinalizado());
            ps.setInt(3, planejamento.getId());
            ps.executeUpdate();
            
            sql = "DELETE FROM planejamento_disciplina WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            tmp = ps.executeUpdate();

            sql = "DELETE FROM planejamento_docente WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            tmp = ps.executeUpdate();
           
            sql = "DELETE FROM planejamento_sala WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            tmp = ps.executeUpdate();
            
            sql = "DELETE FROM planejamento_variaveis_fixas WHERE id_planejamento = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, planejamento.getId());
            tmp = ps.executeUpdate();
            
            VariaveisFixasDAO varDAO = new VariaveisFixasDAO();
            for (VariaveisFixas v : planejamento.getVariaveisFixas()) {
                try {
                    varDAO.delete(v);
                } catch (Exception e) {
                    System.out.println("Erro ao remover variável fixa");
                }
            }
             //____________________________________________________________________________________________________________________
            //Inserção das disciplinas
            for (Turmas d : planejamento.getTurmas()) {
                for (int i = 0; i < d.getQntTurmas(); i++) {
                    sql = "INSERT INTO `planejamento_disciplina` (`id_planejamento`, `id_disciplina`) VALUES (?, ?);";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, planejamento.getId());
                    ps.setInt(2, d.getDisciplina().getId());
                    ps.executeUpdate();
                }
            }
            //____________________________________________________________________________________________________________________
            //Inserção dos docentes do planejamento
            for (Docente doc : planejamento.getDocentes()) {
                sql = "INSERT INTO `planejamento_docente` (`id_planejamento`, `id_docente`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, doc.getId());
                ps.executeUpdate();
            }
            //____________________________________________________________________________________________________________________
            //Inserção das salas do planejamento
            for (Sala s : planejamento.getSalas()) {
                sql = "INSERT INTO `planejamento_sala` (`id_planejamento`, `id_sala`) VALUES (?, ?);";
                ps = con.prepareStatement(sql);
                ps.setInt(1, planejamento.getId());
                ps.setInt(2, s.getId());
                ps.executeUpdate();
            }
            //____________________________________________________________________________________________________________________
            //Inserção das variáveis fixas do planejamento
            
            for (VariaveisFixas v : planejamento.getVariaveisFixas()) {
                try {
                    varDAO.insert(v);
                } catch (Exception e) {
                    System.out.println("Erro ao inserir variável fixa");
                }
                if (varDAO.retrunId(v) != 0) {
                    v.setId(varDAO.retrunId(v));
                    sql = "INSERT INTO `planejamento_variaveis_fixas` (`id_planejamento`, `id_var_fixa`) VALUES (?, ?);";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, planejamento.getId());
                    ps.setInt(2, v.getId());
                    ps.executeUpdate();
                } else {
                    System.out.println("Não foi possivel recuperar os dados da variável fixa");
                }

            }
            //____________________________________________________________________________________________________________________
            return tmp == 1;
        } catch (Exception e) {
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
}
