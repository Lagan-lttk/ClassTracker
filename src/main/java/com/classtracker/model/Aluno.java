package com.classtracker.model;

public class Aluno {

    private int id_aluno;
    private String nome;
    private String matricula;
    private String data_de_nascimento;
    private String cpf;
    private String curso;

    public Aluno(String cpf, String curso, String data_de_nascimento, String matricula, String nome) {
        this.cpf = cpf;
        this.curso = curso;
        this.data_de_nascimento = data_de_nascimento;
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getData_de_nascimento() {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(String data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
