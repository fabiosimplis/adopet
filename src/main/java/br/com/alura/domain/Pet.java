package br.com.alura.domain;

import com.google.gson.JsonObject;

public class Pet {

    private String tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Float peso;

    public Pet(String tipo, String nome, String raca, Integer idade, String cor, Float peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    public Pet(JsonObject jsonObject) {
        //this.id = jsonObject.get("id").getAsLong();
        this.tipo = jsonObject.get("tipo").getAsString();
        this.nome = jsonObject.get("nome").getAsString();
        this.raca = jsonObject.get("raca").getAsString();
        this.idade = jsonObject.get("idade").getAsInt();
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public Float getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Pet{" +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade +
                '}';
    }
}
