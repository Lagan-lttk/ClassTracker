package com.classtracker.model;

public class Reserva {

    private int id_reserva;
    private int id_sala;
    private int id_professor;
    private int id_turma;
    private String descricao;
    private String horário;
    private String data;
    private String status;

    public Reserva(String data, String descricao, String horário, int id_professor, int id_sala, int id_turma, String status) {
        this.data = data;
        this.descricao = descricao;
        this.horário = horário;
        this.id_professor = id_professor;
        this.id_sala = id_sala;
        this.id_turma = id_turma;
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

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
