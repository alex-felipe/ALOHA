/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.aloha.model.data_for_solver;

/**
 *
 * @author projetosti
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public abstract class Compactador {
    
   //Constantes
   public static final String PATH = System.getProperty("user.dir");
   static final int TAMANHO_BUFFER = 100000; // 100kb
    
   //método para compactar arquivo
   public static void compactarParaZip(String arqSaida,ArrayList<File> arquivos) throws IOException{
       int cont;
       byte[] dados = new byte[TAMANHO_BUFFER];
                    
       BufferedInputStream origem = null;
       FileInputStream streamDeEntrada = null;
       FileOutputStream destino = null;
       ZipOutputStream saida = null;
       ZipEntry entry = null;
               
       try {
            destino = new FileOutputStream(new File(arqSaida));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));
            for(File file: arquivos){
                streamDeEntrada = new FileInputStream(file);
                origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
                entry = new ZipEntry(file.getName());
                saida.putNextEntry(entry);

                while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                    saida.write(dados, 0, cont);
                }                
            }

            origem.close();
            saida.close();
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
   }
   
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        ArrayList<File> arquivos = new ArrayList<>();
        //arquivos.add(new File(new GeradorDAT().geraArquivo(planejamento)));
        arquivos.add(new File(System.getProperty("user.dir") + "/src/java/br/ufc/russas/aloha/model/data_for_solver/PlanejamentoAcademico.mod"));
        Compactador.compactarParaZip("/home/projetosti/Documentos/teste2.zip", arquivos);
    }
}