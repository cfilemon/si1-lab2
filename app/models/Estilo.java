package models;

/**
 * Created by Clenimar on 24/05/2015.
 */
public enum Estilo {

    Rock("Rock"),
    BossaNova("Bossa Nova"),
    MPB("MPB");

    private String nome;

    Estilo(String nome) { this.nome = nome; }
    public String getNome() { return this.nome; }

}
