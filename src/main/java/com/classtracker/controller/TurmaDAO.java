package com.classtracker.controller;

import com.classtracker.model.Turma;

import java.sql.SQLException;
import java.util.List;

public interface TurmaDAO {

    public void insertTurma(Turma turma) throws SQLException;

    public void deleteAluno (Turma turma) throws SQLException;

    public Turma getTurma(String idTurma) throws SQLException;

    public void updateTurma(Turma antiga, Turma nova) throws SQLException;

    public List<Integer> getTurmas() throws SQLException;

}
