package com.classtracker.controller;

import com.classtracker.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAOImpl implements ConsultaDAO {

    Connection connection = DBConnection.getConnection();

    private String buscarString(String sql, String valor) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, valor);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    private int buscarInt(String sql, String valor) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, valor);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    private List<String> buscarListaPorProfessor(String sql, int idProfessor) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idProfessor);

        ResultSet rs = ps.executeQuery();

        List<String> resultados = new ArrayList<>();

        while (rs.next()) {
            resultados.add(rs.getString(1));
        }

        return resultados;
    }

    //  Consultas por Aluno

    @Override
    public String buscarSalaPorAluno(String matricula) throws SQLException {
        String sql = """
            SELECT S.NUMERO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            JOIN SALA S ON S.ID_SALA = R.ID_SALA
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public String buscarBlocoDaSalaPorAluno(String matricula) throws SQLException {
        String sql = """
            SELECT S.BLOCO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            JOIN SALA S ON S.ID_SALA = R.ID_SALA
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public int buscarCapacidadeDaSalaPorAluno(String matricula) throws SQLException {
        String sql = """
            SELECT S.CAPACIDADE
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            JOIN SALA S ON S.ID_SALA = R.ID_SALA
            WHERE A.MATRICULA = ?
            """;

        return buscarInt(sql, matricula);
    }

    @Override
    public String buscarCursoDaTurmaDoAluno(String matricula) throws SQLException {
        String sql = """
            SELECT T.CURSO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public int buscarQuantidadeAlunosDaTurmaDoAluno(String matricula) throws SQLException {
        String sql = """
            SELECT COUNT(A2.ID_ALUNO)
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN ALUNO A2 ON A2.CURSO = T.CURSO
            WHERE A.MATRICULA = ?
            """;

        return buscarInt(sql, matricula);
    }

    @Override
    public String buscarProfessorDaTurmaDoAluno(String matricula) throws SQLException {
        String sql = """
            SELECT P.NOME
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            JOIN PROFESSOR P ON P.ID_PROFESSOR = R.ID_PROFESSOR
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public String buscarDataDaTurmaDoAluno(String matricula) throws SQLException {
        String sql = """
            SELECT R.DIA
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public String buscarHorarioDaTurmaDoAluno(String matricula) throws SQLException {
        String sql = """
            SELECT R.HORÁRIO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public String buscarTurnoDaTurmaDoAluno(String matricula) throws SQLException {
        String sql = """
            SELECT T.TURNO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            WHERE A.MATRICULA = ?
            """;

        return buscarString(sql, matricula);
    }

    @Override
    public List<String> listarProximasAulas(String matricula) throws SQLException {
        String sql = """
            SELECT R.DIA, R.HORÁRIO, S.NUMERO, P.NOME, R.DESCRIÇÃO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            JOIN SALA S ON S.ID_SALA = R.ID_SALA
            JOIN PROFESSOR P ON P.ID_PROFESSOR = R.ID_PROFESSOR
            WHERE A.MATRICULA = ?
            ORDER BY R.DIA, R.HORÁRIO
            """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, matricula);

        ResultSet rs = ps.executeQuery();

        List<String> aulas = new ArrayList<>();

        while (rs.next()) {
            String aula = "Data: " + rs.getString(1)
                    + " | Hora: " + rs.getString(2)
                    + " | Sala: " + rs.getString(3)
                    + " | Professor: " + rs.getString(4)
                    + " | Descrição: " + rs.getString(5);

            aulas.add(aula);
        }

        return aulas;
    }

    @Override
    public List<String> buscarReservaPorAluno(String matricula) throws SQLException {
        String sql = """
            SELECT R.DIA, R.HORÁRIO, T.TURNO
            FROM ALUNO A
            JOIN TURMA T ON T.CURSO = A.CURSO
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            WHERE A.MATRICULA = ?
            ORDER BY R.DIA, R.HORÁRIO
            """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, matricula);

        ResultSet rs = ps.executeQuery();

        List<String> reservas = new ArrayList<>();

        while (rs.next()) {
            String reserva = "Data: " + rs.getString(1)
                    + " | Hora: " + rs.getString(2)
                    + " | Turno: " + rs.getString(3);

            reservas.add(reserva);
        }

        return reservas;
    }

    // =========================
    // CONSULTAS POR PROFESSOR
    // =========================

    @Override
    public List<String> buscarSalasPorProfessor(int idProfessor) throws SQLException {
        String sql = """
            SELECT DISTINCT CONCAT(S.BLOCO, ' - Sala ', S.NUMERO)
            FROM PROFESSOR P
            JOIN RESERVA R ON R.ID_PROFESSOR = P.ID_PROFESSOR
            JOIN SALA S ON S.ID_SALA = R.ID_SALA
            WHERE P.ID_PROFESSOR = ?
            ORDER BY S.BLOCO, S.NUMERO
            """;

        return buscarListaPorProfessor(sql, idProfessor);
    }

    @Override
    public List<String> buscarTurmasPorProfessor(int idProfessor) throws SQLException {
        String sql = """
            SELECT DISTINCT CONCAT(T.CURSO, ' - ', T.TURNO)
            FROM PROFESSOR P
            JOIN RESERVA R ON R.ID_PROFESSOR = P.ID_PROFESSOR
            JOIN TURMA T ON T.ID_TURMA = R.ID_TURMA
            WHERE P.ID_PROFESSOR = ?
            ORDER BY T.CURSO, T.TURNO
            """;

        return buscarListaPorProfessor(sql, idProfessor);
    }

    @Override
    public List<String> buscarHorariosPorProfessor(int idProfessor) throws SQLException {
        String sql = """
                SELECT DISTINCT R.HORÁRIO
                FROM PROFESSOR P
                JOIN RESERVA R ON R.ID_PROFESSOR = P.ID_PROFESSOR
                WHERE P.ID_PROFESSOR = ?
                ORDER BY R.HORÁRIO
                """;

        return buscarListaPorProfessor(sql, idProfessor);

    }

    // CONSULTAS POR TURMA

    @Override
    public String buscarProfessorPorTurma(int idTurma) throws SQLException {
        String sql = """
            SELECT P.NOME
            FROM TURMA T
            JOIN RESERVA R ON R.ID_TURMA = T.ID_TURMA
            JOIN PROFESSOR P ON P.ID_PROFESSOR = R.ID_PROFESSOR
            WHERE T.ID_TURMA = ?
            """;

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idTurma);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }
}