
package Model;


public class Sala {

    private String codigo;
    private int capacidade;
    private String nome;
    private String tipo;
    private String bloco;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Sala(int capacidade, String nome, String tipo, String bloco) {
        this.capacidade = capacidade;
        this.nome = nome;
        this.tipo = tipo;
        this.bloco = bloco;
    }

    public Sala() {
    }
    
    public String gerarCodigo(){
        return null;
    }
    
    
}
