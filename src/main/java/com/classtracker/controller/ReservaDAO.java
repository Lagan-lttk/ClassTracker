package com.classtracker.controller;

import com.classtracker.model.Reserva;

import java.sql.SQLException;
import java.util.List;

public interface ReservaDAO {

    public void createReserva(Reserva reserva) throws SQLException;

    public void deleteReserva(Reserva reserva) throws SQLException;

    public Reserva getReserva(int id_reserva) throws SQLException;

    public void updateReserva(Reserva antiga, Reserva nova) throws SQLException;

    public List<String> getReserva() throws SQLException;

}
