package com.classtracker.controller;

import java.sql.SQLException;
import java.util.List;

public interface ConsultaDAO {

    //  Consultas por Alunos

    String buscarSalaPorAluno(String matricula) throws SQLException;

    String buscarBlocoDaSalaPorAluno(String matricula) throws SQLException;

    int buscarCapacidadeDaSalaPorAluno(String matricula) throws SQLException;

    String buscarCursoDaTurmaDoAluno(String matricula) throws SQLException;

    int buscarQuantidadeAlunosDaTurmaDoAluno(String matricula) throws SQLException;

    String buscarProfessorDaTurmaDoAluno(String matricula) throws SQLException;

    String buscarDataDaTurmaDoAluno(String matricula) throws SQLException;

    String buscarHorarioDaTurmaDoAluno(String matricula) throws SQLException;

    String buscarTurnoDaTurmaDoAluno(String matricula) throws SQLException;

    List<String> listarProximasAulas(String matricula) throws SQLException;

    List<String> buscarReservaPorAluno(String matricula) throws SQLException;

    //  Consultas por Professor

    List<String> buscarSalasPorProfessor(int idProfessor) throws SQLException;

    List<String> buscarTurmasPorProfessor(int idProfessor) throws SQLException;

    List<String> buscarHorariosPorProfessor(int idProfessor) throws SQLException;

    //  Consultas por Turma

    String buscarProfessorPorTurma(int idTurma) throws SQLException;

}
