package com.classtracker.controller;

import com.classtracker.model.Turma;

import java.util.List;

public interface TurmaDAO {

    public void insertTurma(Turma turma);

    public void deleteAluno (Turma turma);

    public Turma getTurma(String idTurma);

    public void updateTurma(Turma antiga, Turma nova);

    public List<Turma> getTurmas();

}
