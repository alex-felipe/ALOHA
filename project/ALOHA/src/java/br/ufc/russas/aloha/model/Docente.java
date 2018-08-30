package br.ufc.russas.aloha.model;

import br.ufc.russas.aloha.model.exception.NomeInvalidoException;
import br.ufc.russas.aloha.model.exception.QuantidadeCreditosInvalidoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Docente implements Serializable {

    private int id;
    private String codigoModelo;
    private String nome;
    private int crMin;
    private int crMax;
    private List<Horario> diasSemana;
    private List<Preferencia> preferencias;

    
    public Docente() {
        this.diasSemana = new ArrayList<Horario>();
        this.preferencias = new ArrayList<Preferencia>();
    }

    public Docente(String nome, int crMin, int crMax) {
        this();
        this.nome = nome;
        this.crMin = crMin;
        this.crMax = crMax;
    }

    public Docente(int id, String codigoModelo, String nome, int crMin, int crMax) {
        this(nome, crMin, crMax);
        this.id = id;
        this.codigoModelo = codigoModelo;
        setNome(nome);
        setCrMin(crMin);
        setCrMax(crMax);
    }

    /* O código será composto por:
        * 1) As 3 primeiras letras do nome do combo
        * 2) Identificador do combo no banco de dados
        * RESTRIÇÃO: Tamanho do indentificador será sempre 7
        * - Completar com 0's a esquerda do identificador quando possuirem menos que 4 digitos
     */
    public String getCodigo() {
        StringBuilder builder = new StringBuilder();
        builder.append("DOC"); // Retira as 3 primeiras letras do nome
        // Insere a quantidade de 0's restantes para formar o código
        if (getId() < 10) {
            builder.append("000");
        } else if (getId() < 100) {
            builder.append("00");
        } else if (getId() < 1000) {
            builder.append("0");
        }
        builder.append(getId()); // Adiciona o identificador
        return builder.toString();
    }

    public void addDisciplina(Preferencia disciplina) {
        preferencias.add(disciplina);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(String codigoModelo) {
        this.codigoModelo = codigoModelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)    {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } 
    }

    public int getCrMin() {
        return crMin;
    }


    public void setCrMin(int crMin) {
        this.crMin = crMin;
    }

    /*public void setCrMin(int crMin) throws QuantidadeCreditosInvalidoException {
    System.out.println("CrMIn " + crMin + "| Crmax " + crMax);
    if (crMin < 0) {
    throw new QuantidadeCreditosInvalidoException("Quantidade de créditos mínimos não pode ser negativo");
    } else {
    this.crMin = crMin;
    }
    }*/
    public void setCrMax(int crMax) {
        this.crMax = crMax;
    }

    public int getCrMax() {
        return crMax;
    }

    /*public void setCrMax(int crMax) throws QuantidadeCreditosInvalidoException {
        if ((crMax < 0)) {
            throw new QuantidadeCreditosInvalidoException("Quantidade de créditos máximos não pode ser negativo");
        } else if (crMax < getCrMin()) {
            throw new QuantidadeCreditosInvalidoException("Quantidade de créditos máximos não pode ser menor que a quantidade de créditos mínimos");
        } else {
            this.crMax = crMax;
        }
    }*/

    public List<Horario> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        for(String dia: diasSemana){
            int d = Integer.parseInt(dia);
            switch(d){
                case 0: this.diasSemana.add(new Horario(0, "Domingo", "Manhã")); break;
                case 1: this.diasSemana.add(new Horario(1, "Domingo", "Tarde")); break;
                case 2: this.diasSemana.add(new Horario(2, "Segunda", "Manhã")); break;
                case 3: this.diasSemana.add(new Horario(3, "Segunda", "Tarde")); break;
                case 4: this.diasSemana.add(new Horario(4, "Terça", "Manhã")); break;
                case 5: this.diasSemana.add(new Horario(5, "Terça", "Tarde")); break;
                case 6: this.diasSemana.add(new Horario(6, "Quarta", "Manhã"));break;
                case 7: this.diasSemana.add(new Horario(7, "Quarta", "Tarde")); break;
                case 8: this.diasSemana.add(new Horario(8, "Quinta", "Manhã")); break;
                case 9: this.diasSemana.add(new Horario(9, "Quinta", "Tarde")); break;
                case 10: this.diasSemana.add(new Horario(10, "Sexta", "Manhã")); break;
                case 11: this.diasSemana.add(new Horario(11, "Sexta", "Tarde")); break;
                case 12: this.diasSemana.add(new Horario(12, "Sábado", "Manhã")); break;
                case 13: this.diasSemana.add(new Horario(13, "Sábado", "Tarde")); break;
            }
        }
    }

    public void setHorario(List<Horario> horarios) {
        this.diasSemana = horarios;
    }

    



    public List<Preferencia> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Preferencia> preferencias) {
        this.preferencias = preferencias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.codigoModelo);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + this.crMin;
        hash = 47 * hash + this.crMax;
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
        final Docente other = (Docente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.crMin != other.crMin) {
            return false;
        }
        if (this.crMax != other.crMax) {
            return false;
        }
        if (!Objects.equals(this.codigoModelo, other.codigoModelo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Docente{" + "id=" + id + ", codigoModelo=" + codigoModelo + ", nome=" + nome + ", crMin=" + crMin + ", crMax=" + crMax + ", diasSemana=" + diasSemana + ", preferencias=" + preferencias + '}';
    }

    
}
