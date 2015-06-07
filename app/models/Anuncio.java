package models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Anuncio implements Comparable<Anuncio>{
    @Id
    private long id = 1l;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String instrumentos;
    @Column
    private String estilosGosta;
    @Column
    private String estilosNaoGosta;
    @Column
    private String facebook;
    @Column
    private String email_contato;
    @Column
    private String interesse;
    @Temporal(TemporalType.DATE)
    private Date data_criacao;

    public Anuncio(String titulo, String descricao, String bairro, String cidade, String instrumentos, String interesse) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.bairro = bairro;
        this.cidade = cidade;
        this.instrumentos = instrumentos;
        this.interesse = interesse;
        this.data_criacao = new Date();
    }

    public Anuncio(Map<String, String> args) throws Exception {

        //validaParametros(args);
        setContatos(args);

        this.titulo = args.get("titulo");
        this.descricao = args.get("descricao");
        this.bairro = args.get("bairro");
        this.cidade = args.get("cidade");
        this.instrumentos = args.get("instrumentos");
        this.data_criacao = new Date();
    }

//    private void validaParametros(Map<String, String> args) throws Exception {
//        if (args == null || args.isEmpty() || args.keySet().isEmpty() || args.values().isEmpty())
//            throw new Exception("Whops. Cosntrutor com parametros nulos...");
//        for (String key : args.keySet()) {
//            if (key.matches(PARAMETROS_OBRIGATORIOS)) {
//                Logger.debug(key + " entrou!");
//                Logger.debug(args.get(key));
//                if (args.get(key) == null || args.get(key).trim() == "") {
//                    Logger.debug(key + " entrou de novo!");
//                    throw new Exception("Whops. Argumentos invalidos no construtor...");
//                } else {
//                    throw new Exception("Whops. Argumentos invalidos no construtor...");
//                }
//            }
//        }
//    }

    private void setContatos(Map<String, String> args) throws Exception {
        if ((args.get("facebook") == null) && (args.get("e-mail") == null)) throw new Exception("Eh preciso ter ao menos uma forma de contato!");
        this.facebook = args.get("facebook");
        this.email_contato = args.get("e-mail");
    }

    public Anuncio() {
    }

    public void setEstilosGosta(String estilos) {
        this.estilosGosta = estilos;
    }

    public void setEstilosNaoGosta(String estilos) {
        this.estilosNaoGosta = estilos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getBairro() { return bairro; }

    public String getCidade() { return cidade; }

    public String getInstrumentos() { return instrumentos; }

    public String getEstilosGosta() { return estilosGosta; }

    public String getEstilosNaoGosta() { return estilosNaoGosta; }

    public String getInteresse() { return interesse; }

    public Date getData_criacao() { return data_criacao; }

    @Override
    public int compareTo(Anuncio outro) {
        return this.getData_criacao().compareTo(outro.getData_criacao());
    }
}