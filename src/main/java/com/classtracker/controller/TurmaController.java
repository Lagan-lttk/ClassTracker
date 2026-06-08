package com.classtracker.controller;

import com.classtracker.model.Turma;
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

public class TurmaController {

    @FXML private TextField txtCurso;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTurno;

    TurmaDAOimpl turmaDAOimpl = new TurmaDAOimpl();

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
    private void LimparFormulario() {

        txtCurso.clear();
        txtTamanho.clear();
        txtTurno.clear();

    }

    @FXML
    private void inserirTurma() throws SQLException {

        Turma turma = new Turma(
                txtCurso.getText(),
                Integer.parseInt(txtTamanho.getText()),
                txtTurno.getText()
        );

        turmaDAOimpl.insertTurma(turma);

        LimparFormulario();
    }

    @FXML
    private void excluirTurma() throws SQLException {

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja realmente excluir?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {

            Turma turma = turmaDAOimpl.getTurma(txtCurso.getText());

            turmaDAOimpl.deleteTurma(turma);

            LimparFormulario();
        }
    }

    @FXML
    private void editarTurma() throws SQLException {

        Turma turmaNova = new Turma(
                txtCurso.getText(),
                Integer.parseInt(txtTamanho.getText()),
                txtTurno.getText()
        );

        Turma turmaAntiga = turmaDAOimpl.getTurma(
                txtCurso.getText()
        );

        turmaDAOimpl.updateTurma(turmaAntiga, turmaNova);

        LimparFormulario();
    }

    @FXML
    private void abrirMenuEditar(MouseEvent event) throws IOException {
        trocarTela("/view/editarTurma.fxml", event);
    }

    @FXML
    private void abrirMenuInserir(MouseEvent event) throws IOException {
        trocarTela("/view/inserirTurma.fxml", event);
    }

    @FXML
    private void abrirMenuExcluir(MouseEvent event) throws IOException {
        trocarTela("/view/excluirTurma.fxml", event);
    }
}
