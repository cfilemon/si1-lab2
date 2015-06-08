package models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Entity(name="ANUNCIO")
public class Anuncio implements Comparable<Anuncio>{
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String facebook;
    @Column
    private String email_contato;
    @Column
    private String instrumentos;
    @Column
    private String estilosGosta;
    @Column
    private String estilosNaoGosta;
    @Column
    private String interesse;
    @Temporal(TemporalType.DATE)
    private Date data_criacao;

    protected final String PARAMETROS_OBRIGATORIOS = "titulo,descricao,bairro,cidade,instrumentos,interesse";

    public Anuncio() {
    }

    public Anuncio(Map<String, String> args) throws Exception {

        validaParametros(args);
        setContatos(args);

        this.titulo = args.get("titulo");
        this.descricao = args.get("descricao");
        this.bairro = args.get("bairro");
        this.cidade = args.get("cidade");
        this.instrumentos = args.get("instrumentos");
        this.interesse = args.get("interesse");
        this.data_criacao = new Date();

        if (args.get("estilos") != null)
            this.setEstilosGosta(args.get("estilos"));
        if (args.get("desestilos") != null)
            this.setEstilosNaoGosta(args.get("desestilos"));
    }

    private void validaParametros(Map<String, String> args) throws Exception {
        if (args == null || args.isEmpty() || args.keySet().isEmpty() || args.values().isEmpty())
            throw new Exception("Whops. Cosntrutor com parametros nulos...");

        for (String key : PARAMETROS_OBRIGATORIOS.split(",")){
            if (args.get(key) == null || args.get(key).trim() == "")
                throw new Exception("Whops. O parametro " + key + " nao pode ser nulo!");
        }

    }

    private void setContatos(Map<String, String> args) throws Exception {
        if ((args.get("facebook") == null) && (args.get("e-mail") == null))
            throw new Exception("Eh preciso ter ao menos uma forma de contato!");
        this.facebook = args.get("facebook");
        this.email_contato = args.get("e-mail");
    }

    /**
     * Getters
     */
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

    public String getFacebook() {
        return facebook;
    }

    public String getEmail_contato() {
        return email_contato;
    }

    public String getInstrumentos() {
        return instrumentos;
    }

    public String getEstilosGosta() {
        return estilosGosta;
    }

    public String getEstilosNaoGosta() {
        return estilosNaoGosta;
    }

    public String getInteresse() {
        return interesse;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    /**
     * @return A data de criação em formato textual dd-MM-yyyy
     */
    public String getData_criacao_formatada() {
        return new SimpleDateFormat("dd-MM-yyyy").format(this.getData_criacao());
    }

    /**
     * Setters
     */
    private void setTitulo(String novo) {
        this.titulo = titulo;
    }

    private void setDescricao(String novo) {
        this.descricao = descricao;
    }

    public void setEstilosGosta(String estilos) {
        this.estilosGosta = estilos;
    }

    public void setEstilosNaoGosta(String estilos) {
        this.estilosNaoGosta = estilos;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setEmail_contato(String email_contato) {
        this.email_contato = email_contato;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public void setInstrumentos(String instrumentos) {
        this.instrumentos = instrumentos;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    /**
     * Torna a classe Anuncio comparavel por data_criacao
     * @param o
     * @return
     */
    @Override
    public int compareTo(Anuncio o) {
        return o.getData_criacao().compareTo(this.getData_criacao());
    }
}