package models;

/**
 * Created by Clenimar on 24/05/2015.
 */
public enum Instrumento {

    Violao("Violao"),
    Guitarra("Guitarra"),
    Craviola("Craviola"),
    Bandolim("Bandolim"),
    Bateria("Bateria");

    private String nome;

    Instrumento(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}