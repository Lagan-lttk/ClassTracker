package com.classtracker.controller;

import com.classtracker.model.Aluno;

import java.sql.SQLException;
import java.util.List;

public interface AlunoDAO {

    public void insertAluno(Aluno aluno) throws SQLException;

    public void deleteAluno (Aluno aluno) throws SQLException;

    public Aluno getAluno(String matricula);

    public void updateAluno(Aluno antigo, Aluno novo);

    public List<Aluno> getAlunos();

}
