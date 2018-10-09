/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model;

import br.ufc.russas.aloha.dao.ComboDAO;
import br.ufc.russas.aloha.dao.CursoDAO;
import br.ufc.russas.aloha.dao.DisciplinaDAO;
import br.ufc.russas.aloha.dao.DocenteDAO;
import br.ufc.russas.aloha.dao.HorarioDAO;
import br.ufc.russas.aloha.dao.SalaDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author projetosti
 */
public class GeradorDAT {
     ArrayList<Docente> docentes;
     ArrayList<Disciplina> disciplinas;
     ArrayList<Sala> salas;
     ArrayList<Combo> combos;
     ArrayList<Horario> horarios;
     ArrayList<String> semestres;
     ArrayList<Curso> cursos;
     ArrayList<String> turnos;

    public GeradorDAT() {
        docentes = new ArrayList<>();
        disciplinas = new ArrayList<>();
        salas = new ArrayList<>();
        combos = new ArrayList<>();
        horarios = new ArrayList<>();
        
        semestres = new ArrayList<>();
        semestres.add("S1");
        semestres.add("S2");
        semestres.add("S3");
        semestres.add("S4");
        semestres.add("S5");
        semestres.add("S6");
        semestres.add("S7");
        semestres.add("S8");
        semestres.add("S9");
        semestres.add("S10");
        
        cursos = new ArrayList<>();
        
        turnos = new ArrayList<>();
        turnos.add("MANHA");
        turnos.add("TARDE");
        turnos.add("NOITE");
        
    }
    
     
    public String adicionaCabecalho(){
        StringBuilder builder = new StringBuilder();
        builder.append("data;").append("\n\n");
        
        // Adicionando os códigos dos docentes
        builder.append("set DOCENTE :=");
        for(Docente docente: docentes){
            builder.append(" ").append(docente.getCodigoModelo());
        }
        builder.append(";").append("\n\n");
        
        // Adicionando os códigos das disciplinas
        builder.append("set DISCIPLINA :=");
        for (Disciplina disciplina : disciplinas) {
            builder.append(" ").append(disciplina.getCodigoModelo());
        }
        builder.append(";").append("\n\n");
        
        // Adicionando os códigos das salas
        builder.append("set SALA :=");
        for (Sala sala : salas) {
            builder.append(" ").append(sala.getCodigoModelo());
        }
        builder.append(";").append("\n\n");
        
        // Adicionando os códigos dos combos
        builder.append("set COMBO :=");
        for (Combo combo : combos) {
            builder.append(" ").append(combo.getCodigo_modelo());
        }
        builder.append(";").append("\n\n");
        
        // Adicionando os códigos dos horários
        builder.append("set HORARIO := ");
        for (Horario horario : horarios) {
            builder.append(" ").append(horario.getCodigo_modelo());
        }
        builder.append(";").append("\n\n");
        
        // Adicionando os códigos dos semestres
        builder.append("set SEMESTRE := ");
        for (String semestre : semestres) {
            builder.append(" ").append(semestre);
        }
        builder.append(";").append("\n\n");
        
        // Adicionando os códigos dos cursos
        builder.append("set CURSO := ");
        for (Curso curso : cursos) {
            builder.append(" ").append(curso.getCodigo_modelo());
        }
        builder.append(";").append("\n\n");
        
        
        // Adicionando os códigos dos turnos
        builder.append("set TURNO := ");
        for (String turno : turnos) {
            builder.append(" ").append(turno);
        }
        builder.append(";").append("\n");
        return builder.toString();
    }
    
    
    public String adicionaParamMinHMaxH(){
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append("\t\t").append("MINHp").append("\t").append("MAXHp").append(":=").append("\n");
        
        for(Docente docente: docentes){
            builder.append(docente.getCodigoModelo()).append("\t\t");
            builder.append(docente.getCrMin()).append("\t\t");
            builder.append(docente.getCrMax()).append("\n");
        }
        
        return builder.toString();
    }
    
    public String adicionaParamZ(){
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append(" ").append("Z").append(" ").append(":=").append("\n");

        for (Docente docente : docentes) {
            builder.append("[");
            builder.append(docente.getCodigoModelo()).append(", *, *, *");
            builder.append("]").append("(tr)").append(":");
            builder.append("\n");
            
            builder.append("\t\t\t\t");
            
            ArrayList<Integer> numPreferencia = new ArrayList<>();
            for(Preferencia preferencia: docente.getPreferencias()){
                if(preferencia.getDisciplina() != null){
                    builder.append("\t").append(preferencia.getDisciplina().getCodigoModelo());
                    numPreferencia.add(preferencia.getPreferencia());                    
                }
            }
            builder.append(":=").append("\n");
            
            for(Combo combo: combos){
                for(String turno: turnos){
                    builder.append(combo.getCodigo_modelo()).append(" ");
                    builder.append(turno);
                    for(Integer preferencia: numPreferencia){
                        builder.append("\t\t").append(preferencia);
                    }
                    builder.append("\n");
                }
            }
            
            builder.append("\n");
        }

        return builder.toString();        
    }
    
    public String adicionaParamNumCreditos(){
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append(" ").append("Nc").append(" ").append(":=");
        
        for(Disciplina disciplina: disciplinas){
            builder.append("\n");
            builder.append(disciplina.getCodigoModelo()).append("\t");
            builder.append(disciplina.getCrPraticos() + disciplina.getCrTeoricos());
        }
        
        builder.append(";").append("\n");
        
        return builder.toString();
    }
    
    public String adicionaSemestre(){
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append(" ").append("Sem").append(" ").append(":=").append("\n");
        
        for(Curso curso: cursos){
            builder.append("[");
            builder.append("*, *,").append(curso.getNome());
            builder.append("]").append(":");
            builder.append("\n").append("\t\t");           
        }

        ArrayList<String> cursadoNesteSemestre = new ArrayList<>();
        for(String semestre: semestres){
            builder.append("\t").append(semestre);
            // Corrigir essa parte
            cursadoNesteSemestre.add("0");
        }
        
        for (Disciplina disciplina : disciplinas) {
            builder.append("\n");
            builder.append(disciplina.getCodigoModelo()).append("\t");
            for(String i: cursadoNesteSemestre){
                builder.append(i).append("\t");
            }
        }

        builder.append(";").append("\n");
        
        return builder.toString();
    }
    
    
    public String adicionaQtdSalas(){
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append(" ").append("Tsalas").append(" ").append(":=").append(" ");
        builder.append(salas.size()).append(";\n");
        return builder.toString();
    }
    
    public String adicionaCapacidadeSalas(){
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append(" ").append("Cap").append(" ").append(":=");
        
        for(Sala sala: salas){
            builder.append("\n");
            builder.append(sala.getCodigoModelo()).append(" ");
            builder.append(sala.getCapacidade());
        }
        builder.append(";").append("\n");
        
        return builder.toString();        
    }
    
    public String adicionaTamTurma() {
        StringBuilder builder = new StringBuilder();
        builder.append("param:").append(" ").append("TamTurma").append(" ").append(":=");

        for (Disciplina disciplina : disciplinas) {
            builder.append("\n");
            builder.append(disciplina.getCodigoModelo()).append(" ");
            builder.append(disciplina.getVagas());
        }
        builder.append(";").append("\n");

        return builder.toString();
    }

    public String adicionaCompTurnoHorario(){
        StringBuilder builder = new StringBuilder();
        builder.append("param").append(" ").append("CompTH:").append("\n").append("\t");
        
        ArrayList<String> compatibilidade = new ArrayList<>();
        for(Horario horario: horarios){
            builder.append("\t").append(horario.getCodigo_modelo());
            if(horario.isManha()){
                compatibilidade.add("MANHA");
            } else if(horario.isTarde()){
                compatibilidade.add("TARDE");
            } else{
                compatibilidade.add("NOITE");
            }
        }
        builder.append(":=");
        
        for(String turno: turnos){
            builder.append("\n").append(turno);
            for(String i: compatibilidade){
                builder.append("\t\t");
                if(i.equals(turno)){
                    builder.append("1");
                }else{
                    builder.append("0");
                }
                
            }
           
        }
        builder.append(";").append("\n");
        
        
        return builder.toString();
    }
    
    public String adicionaCompHorarioCombo() {
        StringBuilder builder = new StringBuilder();
        builder.append("param").append(" ").append("CompHC:").append("\n").append("\t");

        ArrayList<String> compatibilidade = new ArrayList<>();
        for (Horario horario : horarios) {
            builder.append("\t").append(horario.getCodigo_modelo());
            compatibilidade.add("0");
        }
        builder.append(":=");

        for (Combo combo : combos) {
            builder.append("\n").append(combo.getCodigo_modelo());
            for (String i : compatibilidade) {
                builder.append("\t\t").append(i);
            }

        }
        builder.append(";").append("\n");
        return builder.toString();
    }
    
    public String adicionaSobH() {
        StringBuilder builder = new StringBuilder();
        builder.append("param").append(" ").append("Sobh:").append("\n").append("\t");

        ArrayList<String> compatibilidade = new ArrayList<>();
        for (Horario horario : horarios) {
            builder.append("\t").append(horario.getCodigo_modelo());
            compatibilidade.add("0");
        }
        builder.append(":=");

        for (Horario horario : horarios) {
            builder.append("\n").append(horario.getCodigo_modelo());
            for (String i : compatibilidade) {
                builder.append("\t\t").append(i);
            }

        }
        builder.append(";").append("\n");

        return builder.toString();
    }
    
    public String adicionaSob() {
        StringBuilder builder = new StringBuilder();
        builder.append("param").append(" ").append("Sob:").append("\n").append("\t");

        ArrayList<String> compatibilidade = new ArrayList<>();
        for (Combo combo : combos) {
            builder.append("\t").append(combo.getCodigo_modelo());
            compatibilidade.add("0");
        }
        builder.append(":=");

        for (Combo combo : combos) {
            builder.append("\n").append(combo.getCodigo_modelo());
            for (String i : compatibilidade) {
                builder.append("\t\t").append(i);
            }

        }
        builder.append(";").append("\n");

        return builder.toString();
    }
    
    public static void main(String[] args) {
        GeradorDAT geradorDAT = new GeradorDAT();
        
        ArrayList<Docente> docentes = new ArrayList<>();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        ArrayList<Sala> salas = new ArrayList<>();
        ArrayList<Combo> combos = new ArrayList<>();
        ArrayList<Horario> horarios = new ArrayList<>();
        ArrayList<String> semestres = new ArrayList<>();
        ArrayList<Curso> cursos = new ArrayList<>();    
        
        DocenteDAO docenteDAO = new DocenteDAO();
        ComboDAO comboDAO = new ComboDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        CursoDAO cursoDAO = new CursoDAO();
        SalaDAO salaDAO = new SalaDAO();
        HorarioDAO horarioDAO = new HorarioDAO();
        
        geradorDAT.docentes = docenteDAO.selectALL();
        geradorDAT.combos = comboDAO.selectALL();
        geradorDAT.disciplinas = disciplinaDAO.selectALL();
        geradorDAT.cursos = cursoDAO.selectALL();
        geradorDAT.salas = salaDAO.selectALL();
        geradorDAT.horarios = horarioDAO.selectALL();
        
        
         try {
             BufferedWriter bufferedWrite = new BufferedWriter(new FileWriter("/home/projetosti/Documentos/teste.dat"));
             bufferedWrite.append(geradorDAT.adicionaCabecalho());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaParamMinHMaxH());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaParamZ());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaParamNumCreditos());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaSemestre());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaQtdSalas());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaCapacidadeSalas());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaTamTurma());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaCompTurnoHorario());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaCompHorarioCombo());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaSobH());
             bufferedWrite.append("\n");
             bufferedWrite.append(geradorDAT.adicionaSob());
             
             bufferedWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(GeradorDAT.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
