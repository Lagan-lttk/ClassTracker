package com.classtracker.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

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
    private void abrirMenuInserir(ActionEvent event) throws IOException {
        trocarTela("/view/FormInserirAluno.fxml", event);
    }

    @FXML
    private void abrirMenuDesc(ActionEvent event) throws IOException {
        trocarTela("/view/MatriculaBox.fxml", event);
    }

    @FXML
    private void abrirMenu(ActionEvent event) throws IOException {
        trocarTela("/view/BoxChoice.fxml", event);
    }

}
