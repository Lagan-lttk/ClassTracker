package com.classtracker.model;

public class Sala {

    private int id_sala;
    private String tipo;
    private int capacidade;
    private String bloco;
    private int numero;

    public Sala(String bloco, int capacidade, int numero, String tipo) {
        this.bloco = bloco;
        this.capacidade = capacidade;
        this.numero = numero;
        this.tipo = tipo;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
