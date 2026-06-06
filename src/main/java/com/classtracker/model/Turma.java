package com.classtracker.model;

public class Turma {

    private int id_turma;
    private String curso;
    private int tamanho;
    private String turno;

    public Turma(String curso, int id_turma, int tamanho, String turno) {
        this.curso = curso;
        this.id_turma = id_turma;
        this.tamanho = tamanho;
        this.turno = turno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
