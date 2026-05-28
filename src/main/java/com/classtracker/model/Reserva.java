package com.classtracker.model;

public class Reserva {

    private int id_reserva;
    private String descricao;
    private String horário;
    private String data;
    private String status;

    public Reserva(String data, String descricao, String horário, int id_reserva, String status) {
        this.data = data;
        this.descricao = descricao;
        this.horário = horário;
        this.id_reserva = id_reserva;
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHorário() {
        return horário;
    }

    public void setHorário(String horário) {
        this.horário = horário;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
