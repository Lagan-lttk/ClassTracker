package com.classtracker.controller;

import com.classtracker.model.Aluno;
import com.classtracker.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAOImpl implements AlunoDAO {

    Connection connection = DBConnection.getConnection();

    @Override
    public void insertAluno(Aluno aluno) throws SQLException {

        PreparedStatement ps =
                connection.prepareStatement("INSERT INTO ALUNO VALUES(?,?,?,?,?,?);");
        ps.setInt(1, aluno.getId_aluno());
        ps.setString(2, aluno.getNome());
        ps.setString(3, aluno.getMatricula());
        ps.setString(4, aluno.getData_de_nascimento());
        ps.setString(5, aluno.getCpf());
        ps.setString(6, aluno.getCurso());
        ps.executeUpdate();
    };

    @Override
    public void deleteAluno(Aluno aluno) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("DELETE FROM ALUNO WHERE MATRICULA = ?");
        ps.setString(1, aluno.getMatricula());
        ps.executeUpdate();

    }

    @Override
    public Aluno getAluno(String matricula) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM  ALUNO WHERE MATRICULA = ?");
        ps.setString(1, matricula);

        ResultSet rs = ps.executeQuery();

        Aluno aluno = null;

        if (rs.next())
            aluno = new Aluno(rs.getString(5),rs.getString(6),rs.getString(4),
                    rs.getString(3),rs.getString(2));
        return aluno;
    };

    @Override
    public void updateAluno(Aluno antigo, Aluno novo) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("UPDATE ALUNO SET NOME = ?, MATRICULA = ?, DATA_DE_NASCIMENTO = ?, CPF = ?, CURSO = ? WHERE CPF = ?");

        ps.setString(1, novo.getNome());
        ps.setString(2, novo.getMatricula());
        ps.setString(3, novo.getData_de_nascimento());
        ps.setString(4, novo.getCpf());
        ps.setString(5, novo.getCurso());

        ps.setString(6, antigo.getCpf());

        ps.executeUpdate();
    }

    @Override
    public List<String> getAlunos() throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALUNO");

        ResultSet rs = ps.executeQuery();
        List<String> listaAlunos = new ArrayList<>();

        Aluno aluno;

        while(rs.next()){
            aluno = new Aluno(rs.getString(5),rs.getString(6),rs.getString(4),
                    rs.getString(3),rs.getString(2));
            listaAlunos.add(aluno.getNome());
        }

        return  listaAlunos;
    }
}
