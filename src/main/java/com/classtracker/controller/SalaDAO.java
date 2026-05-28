package com.classtracker.controller;

import com.classtracker.model.Sala;

import java.util.List;

public interface SalaDAO {

    public void insertSala(Sala sala);

    public void deleteSala (Sala sala);

    public Sala getSala(int numero);

    public void updateSala(Sala antiga, Sala novo);

    public List<Sala> getSalas();

}
