package com.classtracker.controller;

import com.classtracker.model.Professor;

import java.sql.SQLException;
import java.util.List;

public interface ProfesorDAO {

    public void insertProfessor(Professor professor);

    public void deleteProfessor(Professor professor);

    public Professor getProfessor(String cpf) throws SQLException;

    public void updateProfessor(Professor antigo, Professor novo);

    public List<Professor> getProfessor();

}
