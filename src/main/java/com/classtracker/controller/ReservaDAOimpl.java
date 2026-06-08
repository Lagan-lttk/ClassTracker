package com.classtracker.controller;

import com.classtracker.model.Aluno;
import com.classtracker.model.Reserva;
import com.classtracker.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOimpl implements ReservaDAO{

    Connection connection = DBConnection.getConnection();

    @Override
    public void createReserva(Reserva reserva) throws SQLException {

        PreparedStatement ps =
                connection.prepareStatement("INSERT INTO RESERVA VALUES(?,?,?,?,?,?,?,?);");
        ps.setInt(1, reserva.getId_reserva());
        ps.setInt(2, reserva.getId_sala());
        ps.setInt(3, reserva.getId_professor());
        ps.setInt(4, reserva.getId_turma());
        ps.setString(5, reserva.getHorário());
        ps.setString(6, reserva.getDescricao());
        ps.setString(7, reserva.getData());
        ps.setString(8, reserva.getStatus());
        ps.executeUpdate();

    }

    @Override
    public void deleteReserva(Reserva reserva) throws SQLException {
        PreparedStatement ps;
        ps = connection.prepareStatement("DELETE FROM RESERVA WHERE ID_RESERVA = ?");
        ps.setInt(1, reserva.getId_reserva());
        ps.executeUpdate();
    }

    @Override
    public Reserva getReserva(int id_reserva) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESERVA WHERE ID_RESERVA = ?");
        ps.setInt(1, id_reserva);

        ResultSet rs = ps.executeQuery();

        Reserva reserva = null;

        if (rs.next())
            reserva = new Reserva(rs.getString(7),rs.getString(6),rs.getString(5),
                    rs.getInt(3),rs.getInt(2),rs.getInt(4),rs.getString(8));
        return reserva;
    }

    @Override
    public void updateReserva(Reserva antiga, Reserva nova) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE RESERVA SET ID_SALA = ?, ID_PROFESSOR = ?, ID_TURMA = ?, HORÁRIO = ?, DESCRIÇÃO = ?, DIA = ?, DISPONIBILDIDE = ? WHERE ID_RESERVA = ?");


        ps.setInt(1, nova.getId_sala());
        ps.setInt(2, nova.getId_professor());
        ps.setInt(3, nova.getId_turma());
        ps.setString(4, nova.getHorário());
        ps.setString(5, nova.getDescricao());
        ps.setString(6, nova.getData());
        ps.setString(7, nova.getStatus());

        ps.setInt(8, antiga.getId_reserva());

        ps.executeUpdate();

    }

    @Override
    public List<String> getReserva() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESERVA");

        ResultSet rs = ps.executeQuery();
        List<String> listaReservas = new ArrayList<>();

        Reserva reserva;

        while(rs.next()){
            reserva = new Reserva(rs.getString(7),rs.getString(6),rs.getString(5),
                    rs.getInt(3),rs.getInt(2),rs.getInt(4),rs.getString(8));
            listaReservas.add(reserva.getDescricao());
        }

        return  listaReservas;
    }
}
