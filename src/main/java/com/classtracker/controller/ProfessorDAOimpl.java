package com.classtracker.controller;

import com.classtracker.model.Aluno;
import com.classtracker.model.Professor;
import com.classtracker.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDAOimpl implements ProfesorDAO {

    Connection conn = DBConnection.getConnection();

    @Override
    public void insertProfessor(Professor professor) {
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("INSERT INTO PROFESSOR VALUES (?, ?, ?, ?, ?, ?);");

            ps.setInt(1, professor.getId_professor());
            ps.setString(2, professor.getNome());
            ps.setString(3, professor.getFormacao());
            ps.setString(4, professor.getCpf());
            ps.setString(5, professor.getEmail());
            ps.setString(6, professor.getTelefone());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProfessorDAOimpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void deleteProfessor(Professor professor) {
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("DELETE FROM PROFESSOR WHERE CPF = ?");
            ps.setString(1, professor.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProfessorDAOimpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Professor getProfessor(String cpf) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM  ALUNO WHERE MATRICULA = ?");
        ps.setString(1, cpf);

        ResultSet rs = ps.executeQuery();

        Professor professor = null;

        if (rs.next())
            professor = new Professor(rs.getString(4),rs.getString(5),rs.getString(3),
                    rs.getString(2),rs.getString(6));
        return professor;
    }

    @Override
    public void updateProfessor(Professor antigo, Professor novo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Professor> getProfessor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
