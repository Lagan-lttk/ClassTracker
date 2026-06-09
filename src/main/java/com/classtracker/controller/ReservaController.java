package com.classtracker.controller;

import com.classtracker.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class ReservaController {

    // --- Injeção dos componentes do FXML ---
    @FXML
    private ChoiceBox<String> salaSelect;

    @FXML
    private ChoiceBox<String> turmaSelect;

    @FXML
    private DatePicker dataSelect;

    @FXML
    private ChoiceBox<String> horarioSelect;

    @FXML
    private Button criarReserva;

    // --- Componentes da Tabela ---
    @FXML
    private TableView<Reserva> tabelaReservas;

    @FXML
    private TableColumn<Reserva, String> colSala;
    @FXML
    private TableColumn<Reserva, String> colTurma;
    @FXML
    private TableColumn<Reserva, String> colData;
    @FXML
    private TableColumn<Reserva, String> colHorario;
    @FXML
    private TableColumn<Reserva, String> colResponsavel;
    @FXML
    private TableColumn<Reserva, String> colStatus;

    // Instância do DAO para conversar com o banco de dados
    private ReservaDAO reservaDAO = new ReservaDAOimpl();

    @FXML
    public void initialize() {
        // Vincula a ação de clique do botão ao método de criar reserva
        criarReserva.setOnAction(event -> handleCriarReserva());

        // Popula as ChoiceBoxes
        salaSelect.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        turmaSelect.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        horarioSelect.getItems().addAll("08:00:00", "10:00:00", "13:30:00", "15:30:00");

        // --- CONFIGURAÇÃO DAS COLUNAS DA TABELA ---
        // O texto dentro do PropertyValueFactory deve ser EXATAMENTE igual ao nome da variável na sua classe Reserva
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horário")); // Com acento, igual no seu Model
        colSala.setCellValueFactory(new PropertyValueFactory<>("id_sala"));
        colTurma.setCellValueFactory(new PropertyValueFactory<>("id_turma"));
        colResponsavel.setCellValueFactory(new PropertyValueFactory<>("id_professor"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Carrega os dados na tabela assim que a tela abre
        atualizarTabela();
    }

    // --- MÉTODO PARA ATUALIZAR A TABELA ---
    private void atualizarTabela() {
        try {
            // Nota: Certifique-se de usar o método do seu DAOimpl que retorna List<Reserva>
            // Se o seu método na interface ainda se chamar getReserva(), ajuste o nome aqui.
            List<Reserva> listaDoBanco = ((ReservaDAOimpl) reservaDAO).listarTodasReservas();

            // Converte a lista padrão do Java para ObservableList (exigida pelo JavaFX)
            ObservableList<Reserva> listaObservable = FXCollections.observableArrayList(listaDoBanco);

            // Coloca os itens na tabela
            tabelaReservas.setItems(listaObservable);

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar dados", "Não foi possível atualizar a tabela: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (ClassCastException e) {
            System.out.println("Erro de cast: Verifique se o seu DAO está retornando uma List<Reserva> em vez de List<String>");
        }
    }

    private void handleCriarReserva() {
        try {
            if (salaSelect.getValue() == null || turmaSelect.getValue() == null ||
                    horarioSelect.getValue() == null || dataSelect.getValue() == null) {
                mostrarAlerta("Campos Incompletos", "Por favor, preencha Sala, Turma, Data e Horário.", Alert.AlertType.WARNING);
                return;
            }

            int idSala = Integer.parseInt(salaSelect.getValue());
            int idTurma = Integer.parseInt(turmaSelect.getValue());
            String horario = horarioSelect.getValue();

            // Ajustado para o formato aceito pelo SQL (yyyy-MM-dd)
            String data = dataSelect.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String descricao = "Reserva Padrão";
            int idProfessor = 9; // Certifique-se de que o professor com ID 9 existe no banco!
            String status = "Confirmada";

            Reserva novaReserva = new Reserva(data, descricao, horario, idProfessor, idSala, idTurma, status);

            reservaDAO.createReserva(novaReserva);

            mostrarAlerta("Sucesso", "Reserva criada com sucesso!", Alert.AlertType.INFORMATION);

            // Limpar os campos da tela após criar
            salaSelect.getSelectionModel().clearSelection();
            turmaSelect.getSelectionModel().clearSelection();
            horarioSelect.getSelectionModel().clearSelection();
            dataSelect.setValue(null);

            // --- ATUALIZA A TABELA INSTANTANEAMENTE NA TELA ---
            atualizarTabela();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Formato", "As opções de Sala e Turma devem ser números válidos.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Erro de Banco de Dados", "Ocorreu um erro ao salvar a reserva: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Ocorreu um erro inesperado ao criar a reserva.", Alert.AlertType.ERROR);
        }
    }

    public void trocarTela(String fxml, Event event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void abrirMenuSala(MouseEvent event) throws IOException {
        trocarTela("/view/inserirSala.fxml", event);
    }

    @FXML
    private void abrirMenu(MouseEvent event) throws IOException {
        trocarTela("/view/MainMenu.fxml", event);
    }

    @FXML
    private void abrirMenuTurma(MouseEvent event) throws IOException {
        trocarTela("/view/inserirTurma.fxml", event);
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}