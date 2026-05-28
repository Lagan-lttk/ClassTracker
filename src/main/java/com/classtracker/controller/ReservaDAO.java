package com.classtracker.controller;

import com.classtracker.model.Reserva;

import java.util.List;

public interface ReservaDAO {

    public void createReserva(Reserva reserva);

    public void deleteReserva(Reserva reserva);

    public Reserva getReserva(int id_reserva);

    public void updateReserva(Reserva antiga, Reserva nova);

    public List<Reserva> getReserva();

}
