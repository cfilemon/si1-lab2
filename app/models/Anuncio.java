package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.List;

@Entity
public class Anuncio {
    @Id
    @GeneratedValue
    private long id;

    private String titulo;
    private String descricao;
    private String bairro;
    private String cidade;
    private List<Instrumento> instrumentos;
    private List<Estilo> estilosGosta;
    private List<Estilo> estilosNaoGosta;
    private boolean banda;
    private boolean casual;

    public Anuncio(String titulo, String descricao, String bairro, String cidade, List<Instrumento> instrumentos) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.bairro = bairro;
        this.cidade = cidade;
        this.instrumentos = instrumentos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public List<Estilo> getEstilosGosta() {
        return estilosGosta;
    }

    public List<Estilo> getEstilosNaoGosta() {
        return estilosNaoGosta;
    }

    public boolean isBanda() {
        return banda;
    }

    public boolean isCasual() {
        return casual;
    }
}