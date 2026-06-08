package com.classtracker.controller;

import com.classtracker.model.Aluno;
import com.classtracker.model.Turma;
import com.classtracker.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAOimpl implements TurmaDAO{

    Connection connection = DBConnection.getConnection();


    @Override
    public void insertTurma(Turma turma) throws SQLException {

        PreparedStatement ps =
                connection.prepareStatement("INSERT INTO TURMA VALUES(?,?,?,?);");
        ps.setInt(1, turma.getId_turma());
        ps.setString(2, turma.getCurso());
        ps.setInt(3, turma.getTamanho());
        ps.setString(4, turma.getTurno());

        ps.executeUpdate();

    }

    @Override
    public void deleteAluno(Turma turma) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("DELETE FROM TURMA WHERE ID_TURMA = ?");
        ps.setInt(1, turma.getId_turma());
        ps.executeUpdate();
    }

    @Override
    public Turma getTurma(String idTurma) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM  TURMA WHERE ID_TURMA = ?");
        ps.setString(1, idTurma);

        ResultSet rs = ps.executeQuery();

        Turma turma = null;

        if (rs.next())
            turma = new Turma(rs.getString(2),rs.getInt(1),rs.getInt(3),
                    rs.getString(4));
        return turma;
    }

    @Override
    public void updateTurma(Turma antiga, Turma nova) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("UPDATE TURMA SET CURSO = ?, TAMANHO = ?, TURNO = ? WHERE TURMA_ID = ?");

        ps.setString(1, nova.getCurso());
        ps.setInt(2, nova.getTamanho());
        ps.setString(3, nova.getTurno());
        ps.setInt(4, antiga.getId_turma()); // ID_TURMA por isso utilizar o atributo antiga ao invés do novo.

        ps.executeUpdate();
    }

    @Override
    public List<Integer> getTurmas() throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM TURMA");

        ResultSet rs = ps.executeQuery();
        List<Integer> listaTurmas = new ArrayList<>();

        Turma turma;

        while(rs.next()){
            turma = new Turma(rs.getString(2),rs.getInt(1),rs.getInt(3),
                    rs.getString(4));
            listaTurmas.add(turma.getId_turma());
        }

        return listaTurmas;
    }
}