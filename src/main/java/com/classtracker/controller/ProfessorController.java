package com.classtracker.controller;

import com.classtracker.model.Professor; // Certifique-se de que a classe Professor existe
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

public class ProfessorController {

    // Injeção dos campos do seu FXML
    @FXML private TextField txtNome;
    @FXML private TextField txtFormacao;
    @FXML private TextField txtCPF;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;


    private final ProfessorDAOimpl professorDAO = new ProfessorDAOimpl();


    public void trocarTela(String fxml, Event event) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource(fxml))
        );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void abrirMenu(MouseEvent event) throws IOException {
        trocarTela("/view/MainMenu.fxml", event);
    }

    @FXML
    private void abrirMenuEditar(MouseEvent event) throws IOException {
        trocarTela("/view/editarProfessor.fxml", event);
    }

    @FXML
    private void abrirMenuAdmin(MouseEvent event) throws IOException {
        trocarTela("/view/inserirProfessor.fxml", event);
    }

    @FXML
    private void abrirMenuInserir(MouseEvent event) throws IOException {
        trocarTela("/view/inserirProfessor.fxml", event);
    }

    @FXML
    private void abrirMenuExcluir(MouseEvent event) throws IOException {
        trocarTela("/view/excluirProfessor.fxml", event);
    }

    // --- Métodos de Limpeza ---
    @FXML
    private void LimparFormulario() {
        txtNome.clear();
        txtFormacao.clear();
        txtCPF.clear();
        txtEmail.clear();
        txtTelefone.clear();
    }

    @FXML
    private void LimparFormularioExcluir() {
        txtCPF.clear();
    }

    // --- Métodos de CRUD ---

    @FXML
    private void InserirProfessor() {
        Professor professor = new Professor(
                txtCPF.getText(),
                txtEmail.getText(),
                txtFormacao.getText(),
                txtNome.getText(),
                txtTelefone.getText()
        );

        professorDAO.insertProfessor(professor);
        LimparFormulario();

        mostrarAlerta("Sucesso", "Professor inserido com sucesso!", Alert.AlertType.INFORMATION);

    }

    @FXML
    private void excluirProfessor() {
        try {
            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Deseja realmente excluir este professor?",
                    ButtonType.YES,
                    ButtonType.NO
            );

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
                // Busca o professor pelo CPF antes de excluir
                Professor professor = professorDAO.getProfessor(txtCPF.getText());

                if (professor != null) {
                    professorDAO.deleteProfessor(professor);
                    LimparFormularioExcluir();
                    mostrarAlerta("Sucesso", "Professor excluído.", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlerta("Aviso", "Professor não encontrado com este CPF.", Alert.AlertType.WARNING);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao excluir professor.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editarProfessor() {
        try {

            Professor professorNovo = new Professor(
                    txtNome.getText(),
                    txtFormacao.getText(),
                    txtCPF.getText(),
                    txtEmail.getText(),
                    txtTelefone.getText()
            );


            Professor professorAntigo = professorDAO.getProfessor(txtCPF.getText());

            if (professorAntigo != null) {
                professorDAO.updateProfessor(professorAntigo, professorNovo);
                LimparFormulario();
                mostrarAlerta("Sucesso", "Dados do professor atualizados.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Aviso", "Professor não encontrado com este CPF.", Alert.AlertType.WARNING);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao atualizar professor.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }


}