
package br.ufc.russas.aloha.model;

import java.util.Objects;


public class Sala {

    //Identificador para o banco
    private int id;
    //Código para o arquivo .dat
    private String codigoModelo;    
    private String nome;
    private String tipo;
    private int capacidade;
    private String bloco;

        
   
    public Sala() {
    }

    public Sala(String nome, String tipo, int capacidade, String bloco) {
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.bloco = bloco;
    }

    

    public Sala(int id, String codigoModelo, String nome, String tipo, int capacidade, String bloco) {
        this(nome, tipo, capacidade, bloco);
        this.id = id;
        this.codigoModelo = codigoModelo;
        
    }
            
    public String geraCodigo(){
        String cod;
        if(id<10) cod = this.nome.substring(0,3)+"000"+this.id;
        else if(id<100) cod = this.nome.substring(0,2)+"00"+this.id;
        else if(id<1000) cod = this.nome.substring(0,2)+"0"+this.id;
        else cod = this.nome.substring(0,2)+this.id;
        return cod;
        
    }
    

    
    public String getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(String codigo) {
        this.codigoModelo = codigo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String Bloco) {
        this.bloco = Bloco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.codigoModelo);
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.tipo);
        hash = 59 * hash + this.capacidade;
        hash = 59 * hash + Objects.hashCode(this.bloco);
        return hash;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacidade != other.capacidade) {
            return false;
        }
        if (!Objects.equals(this.codigoModelo, other.codigoModelo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.bloco, other.bloco)) {
            return false;
        }
        return true;
    }
    
    

    
    
}
