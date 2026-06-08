package com.classtracker.controller;

import com.classtracker.model.Sala;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class SalaController {

    @FXML private TextField txtTipo;
    @FXML private TextField txtBloco;
    @FXML private TextField txtNumero;
    @FXML private TextField txtDisponibilidade;
    @FXML private TextField txtCapacidade;

    private final SalaDAOimpl salaDAO = new SalaDAOimpl();

    public void trocarTela(String fxml, Event event) throws IOException {

        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource(fxml))
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
    }

    @FXML
    private void abrirMenu(MouseEvent event) throws IOException {
        trocarTela("/view/MainMenu.fxml", event);
    }

    @FXML
    private void abrirMenuEditar(MouseEvent event) throws IOException {
        trocarTela("/view/editarSala.fxml", event);
    }

    @FXML
    private void abrirMenuInserir(MouseEvent event) throws IOException {
        trocarTela("/view/inserirSala.fxml", event);
    }

    @FXML
    private void abrirMenuExcluir(MouseEvent event) throws IOException {
        trocarTela("/view/excluirSala.fxml", event);
    }

    @FXML
    private void LimparFormulario() {

        txtTipo.clear();
        txtBloco.clear();
        txtNumero.clear();
        txtDisponibilidade.clear();
        txtCapacidade.clear();

    }

    @FXML
    private void LimparFormularioExcluir() {

        txtNumero.clear();

    }

    @FXML
    private void inserirSala() throws SQLException {

        Sala sala = new Sala(
                txtBloco.getText(),
                Integer.parseInt(txtNumero.getText()),
                txtTipo.getText(),
                txtDisponibilidade.getText(),
                Integer.parseInt(txtCapacidade.getText())
        );

        salaDAO.insertSala(sala);

        LimparFormulario();
    }

    @FXML
    private void excluirSala() throws SQLException {

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja realmente excluir?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() &&
                resultado.get() == ButtonType.YES) {



            Sala sala = salaDAO.getSala(Integer.parseInt(txtNumero.getText()));


            salaDAO.deleteSala(sala);

            LimparFormularioExcluir();
        }
    }

    @FXML
    private void editarSala() throws SQLException {

        Sala salaNova = new Sala(
                txtBloco.getText(),
                Integer.parseInt(txtNumero.getText()),
                txtTipo.getText(),
                txtDisponibilidade.getText(),
                Integer.parseInt(txtCapacidade.getText())
        );

        Sala salaAntiga = salaDAO.getSala(Integer.parseInt(txtNumero.getText()));

        salaDAO.updateSala(salaAntiga, salaNova);

        LimparFormulario();
    }
}