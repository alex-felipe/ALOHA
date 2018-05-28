
package br.ufc.russas.aloha.model;


public class Sala {

    //Identificador para o banco
    private int id;
    //Código para o arquivo .dat
    private String codigoModelo;    
    private String nome;
    private String tipo;
    private int capacidade;
    private String bloco;

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
    
    
    

    public Sala() {
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

    
    
}
