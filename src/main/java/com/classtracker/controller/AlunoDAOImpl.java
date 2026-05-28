package com.classtracker.controller;

import com.classtracker.model.Aluno;
import com.classtracker.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDAOImpl implements AlunoDAO {

    Connection connection = DBConnection.getConnection();

    @Override
    public void insertAluno(Aluno aluno) throws SQLException {

        PreparedStatement ps =
                connection.prepareStatement("INSERT INTO ALUNO VALUES(?,?,?,?,?,?,?);");
        ps.setInt(1, aluno.getId_aluno());
        ps.setString(2, aluno.getNome());
        ps.setString(3, aluno.getMatricula());
        ps.setString(4, aluno.getData_de_nascimento());
        ps.setString(5, aluno.getCpf());
        ps.setString(6, aluno.getCurso());

    };

    @Override
    public void deleteAluno(Aluno aluno) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("DELETE FROM ALUNO WHERE CPF = ?");
        ps.setString(1, aluno.getCpf());
        ps.executeUpdate();

    }

    @Override
    public Aluno getAluno(String matricula) {
        return null;
    }

    @Override
    public void updateAluno(Aluno antigo, Aluno novo) {

    }

    @Override
    public List<Aluno> getAlunos() {
        return List.of();
    }
}
