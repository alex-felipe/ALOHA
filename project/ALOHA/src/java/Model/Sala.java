
package Model;


public class Sala {

    //Identificador para o banco
    private int id;
    //Código para o arquivo .dat
    private String codigoModelo;
    
    private int capacidade;
    private String nome;
    private String tipo;
    private String bloco;

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
    

    public Sala(int capacidade, String nome, String tipo, String bloco) {
        this.capacidade = capacidade;
        this.nome = nome;
        this.tipo = tipo;
        this.bloco = bloco;
    }

    public Sala() {
    }
    
    public String gerarCodigo(){
        String cod;
        if(id<10) cod = this.nome.substring(0,2)+"000"+this.id;
        else if(id<100) cod = this.nome.substring(0,2)+"00"+this.id;
        else if(id<1000) cod = this.nome.substring(0,2)+"0"+this.id;
        else cod = this.nome.substring(0,2)+this.id;
        return cod;
    }
    
    
}
