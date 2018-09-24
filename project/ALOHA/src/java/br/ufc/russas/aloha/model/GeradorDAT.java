/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model;

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

    public GeradorDAT() {
        docentes = new ArrayList<>();
        disciplinas = new ArrayList<>();
        salas = new ArrayList<>();
        combos = new ArrayList<>();
        horarios = new ArrayList<>();
        semestres = new ArrayList<>();
        cursos = new ArrayList<>();
    }
    
     
    public String adicionaCabecalho(){
        StringBuilder builder = new StringBuilder();
        builder.append("data;").append("\n");
        
        // Adicionando os códigos dos docentes
        builder.append("set DOCENTE :=");
        for(Docente docente: docentes){
            builder.append(" ").append(docente.getCodigoModelo());
        }
        builder.append(";").append("\n");
        
        // Adicionando os códigos das disciplinas
        builder.append("set DISCIPLINA :=");
        for (Disciplina disciplina : disciplinas) {
            builder.append(" ").append(disciplina.getCodigoModelo());
        }
        builder.append(";").append("\n");
        
        // Adicionando os códigos das salas
        builder.append("set SALA :=");
        for (Sala sala : salas) {
            builder.append(" ").append(sala.getCodigoModelo());
        }
        builder.append(";").append("\n");
        
        // Adicionando os códigos dos combos
        builder.append("set COMBO :=");
        for (Combo combo : combos) {
            builder.append(" ").append(combo.getCodigo_modelo());
        }
        builder.append(";").append("\n");
        
        // Adicionando os códigos dos horários
        builder.append("set HORARIO := ");
        for (Horario horario : horarios) {
            builder.append(" ").append(horario.getCodigo_modelo());
        }
        builder.append(";").append("\n");
        
        // Adicionando os códigos dos semestres
        builder.append("set SEMESTRE := ");
        for (String semestre : semestres) {
            builder.append(" ").append(semestre);
        }
        builder.append(";").append("\n");
        
        // Adicionando os códigos dos cursos
        builder.append("set CURSO := ");
        for (Curso curso : cursos) {
            builder.append(" ").append(curso);
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
        
        docentes.add(new Docente(0, "DOCENTE001", "Dmontier Clone 1", 10, 20));
        docentes.add(new Docente(1, "DOCENTE002", "Dmontier Clone 2", 10, 20));
        docentes.add(new Docente(2, "DOCENTE003", "Dmontier Clone 3", 10, 20));
        
        geradorDAT.docentes = docentes;
        
        System.out.println();
        
         try {
             BufferedWriter bufferedWrite = new BufferedWriter(new FileWriter("/home/projetosti/Documentos/teste.dat"));
             bufferedWrite.append(geradorDAT.adicionaCabecalho());
             bufferedWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(GeradorDAT.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
