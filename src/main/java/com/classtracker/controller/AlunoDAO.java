package com.classtracker.controller;

import com.classtracker.model.Aluno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AlunoDAO {

    public void insertAluno(Aluno aluno) throws SQLException;

    public void deleteAluno (Aluno aluno) throws SQLException;

    public Aluno getAluno(String matricula) throws SQLException;

    public void updateAluno (Aluno antigo, Aluno novo) throws SQLException;

    public List<String> getAlunos() throws SQLException;

}
