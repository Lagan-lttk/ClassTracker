package com.classtracker.controller;

import com.classtracker.model.Sala;
import com.classtracker.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAOimpl implements SalaDAO {

    Connection connection = DBConnection.getConnection();

    @Override
    public void insertSala(Sala sala) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("INSERT INTO SALA (BLOCO, CAPACIDADE, NUMERO, TIPO, DISPONIBILIDADE) VALUES (?, ?, ?, ?, ?)");

        ps.setString(1, sala.getBloco());
        ps.setInt(2, sala.getCapacidade());
        ps.setInt(3, sala.getNumero());
        ps.setString(4, sala.getTipo());
        ps.setString(5, sala.getDisponibilidade());

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void deleteSala(Sala sala) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("DELETE FROM SALA WHERE NUMERO = ?");

        ps.setInt(1, sala.getNumero());

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public Sala getSala(int numero) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM SALA WHERE NUMERO = ?");

        ps.setInt(1, numero);

        ResultSet rs = ps.executeQuery();

        Sala sala = null;

        if (rs.next()) {
            sala = new Sala(
                    rs.getString("BLOCO"),
                    rs.getInt("NUMERO"),
                    rs.getString("TIPO"),
                    rs.getString("DISPONIBILIDADE"),
                    rs.getInt("CAPACIDADE")
            );
        }

        rs.close();
        ps.close();

        return sala;
    }

    @Override
    public void updateSala(Sala antiga, Sala novo) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("UPDATE SALA SET BLOCO = ?, CAPACIDADE = ?, NUMERO = ?, TIPO = ? WHERE NUMERO = ?");

        ps.setString(1, novo.getBloco());
        ps.setInt(2, novo.getCapacidade());
        ps.setInt(3, novo.getNumero());
        ps.setString(4, novo.getTipo());

        ps.setInt(5, antiga.getNumero());

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Integer> getSalas() throws SQLException {

        List<Integer> salas = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM SALA");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Sala sala = new Sala(
                    rs.getString("BLOCO"),
                    rs.getInt("NUMERO"),
                    rs.getString("TIPO"),
                    rs.getString("DISPONIBILIDADE"),
                    rs.getInt("CAPACIDADE")
            );

            salas.add(sala.getNumero());
        }

        rs.close();
        ps.close();

        return salas;
    }
}
