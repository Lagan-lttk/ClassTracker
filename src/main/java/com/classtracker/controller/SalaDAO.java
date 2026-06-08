package com.classtracker.controller;

import com.classtracker.model.Sala;

import java.sql.SQLException;
import java.util.List;

public interface SalaDAO {

    public void insertSala(Sala sala) throws SQLException;

    public void deleteSala (Sala sala) throws SQLException;

    public String getSala(int numero) throws SQLException;

    public void updateSala(Sala antiga, Sala novo) throws SQLException;

    public List<Integer> getSalas() throws SQLException;

}
