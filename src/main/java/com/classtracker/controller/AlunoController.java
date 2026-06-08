package com.classtracker.controller;

import com.classtracker.model.Aluno;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class AlunoController {

    public TextField txtNome;
    public TextField txtData;
    public TextField txtCPF;
    public TextField txtEmail;
    public TextField txtMatricula;
    public TextField txtTurma;
    public TextField txtSelecionado;
    public Label alunoNome;
    public Label salaNumero;
    public Label salaBloco;
    public Label salaCapacidade;
    public Label turmaCurso;
    public Label turmaQtndAlunos;
    public Label turmaProfessor;
    public Label agendaData;
    public Label agendaHora;
    public Label agendaTurno;
    public Label listaAlunos;
    public Label alunoMatricula;


    AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
    ConsultaDAOImpl consultaAlunoDAO = new ConsultaDAOImpl();

    public void setMatricula(String matricula) {
        alunoMatricula.setText("Matrícula: " + matricula);
    }

    public void setAlunoNome(String nome) {
        alunoNome.setText("Olá, " + nome + " !");
    }

    public void setSalaNumero(String numero) {
        salaNumero.setText(numero);
    }

    public void setSalaBloco(String bloco) {
        salaBloco.setText(bloco);
    }

    public void setSalaCapacidade(String capacidade) {
        salaCapacidade.setText(capacidade);
    }

    public void setTurmaCurso(String curso) {
        turmaCurso.setText(curso);
    }
    public void setTurmaQtndAlunos(String quantidadeAlunos) {
        turmaQtndAlunos.setText(quantidadeAlunos);
    }
    public void setTurmaProfessor(String professor) {
        turmaProfessor.setText(professor);
    }
    public void setAgendaData(String data) {
        agendaData.setText(data);
    }
    public void setAgendaHora(String hora) {
        agendaHora.setText(hora);
    }
    public void setAgendaTurno(String turno) {
        agendaTurno.setText(turno);
    }


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
        trocarTela("/view/FormEditarAluno.fxml", event);
    }

    @FXML
    private void abrirMenuInserir(MouseEvent event) throws IOException {
        trocarTela("/view/FormInserirAluno.fxml", event);
    }

    @FXML
    private void abrirMenuExcluir(MouseEvent event) throws IOException {
        trocarTela("/view/FormExcluirAluno.fxml", event);
    }

    @FXML
    private void inserirAluno() throws SQLException {
        Aluno aluno1 = new Aluno(txtCPF.getText(),txtEmail.getText(),txtData.getText(),txtMatricula.getText(),txtNome.getText());
        alunoDAO.insertAluno(aluno1);
        LimparFormulario();
    }

    @FXML
    private void carregarDados(ActionEvent event) throws IOException, SQLException {

        String matricula = txtSelecionado.getText();
        String alunonome = alunoDAO.getAluno(matricula).getNome();

        String numeroSala = consultaAlunoDAO.buscarSalaPorAluno(matricula);
        String blocoSala = consultaAlunoDAO.buscarBlocoDaSalaPorAluno(matricula);
        int capacidadeSala = consultaAlunoDAO.buscarCapacidadeDaSalaPorAluno(matricula);

        String cursoTurma = consultaAlunoDAO.buscarCursoDaTurmaDoAluno(matricula);
        String qntdAlunosTurma = String.valueOf(consultaAlunoDAO.buscarQuantidadeAlunosDaTurmaDoAluno(matricula));

        String professorTurma = consultaAlunoDAO.buscarProfessorPorTurma(Integer.parseInt(matricula));
        String dataAgenda = consultaAlunoDAO.buscarDataDaTurmaDoAluno(matricula);
        String horaAgenda = consultaAlunoDAO.buscarHorarioDaTurmaDoAluno(matricula);
        String turnoAgenda = consultaAlunoDAO.buscarTurnoDaTurmaDoAluno(matricula);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/AlunoDesc.fxml")
        );

        Parent root = loader.load();

        AlunoController controller = loader.getController();

        controller.setMatricula(matricula);
        controller.setAlunoNome(alunonome);

        controller.setSalaNumero(numeroSala);
        controller.setSalaBloco(blocoSala);
        controller.setSalaCapacidade(String.valueOf(capacidadeSala));
        controller.setTurmaCurso(cursoTurma);
        controller.setTurmaQtndAlunos(qntdAlunosTurma);
        controller.setTurmaProfessor(professorTurma);
        controller.setAgendaData(dataAgenda);
        controller.setAgendaHora(horaAgenda);
        controller.setAgendaTurno(turnoAgenda);

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void excluirAluno() throws SQLException {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja realmente excluir?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.YES) {
            Aluno aluno1 = new Aluno(txtMatricula.getText());
            alunoDAO.deleteAluno(aluno1);
            txtMatricula.clear();
        }
    }

    @FXML
    private void atualizarAluno() throws SQLException {
        Aluno alunoNovo = new Aluno(txtCPF.getText(),txtEmail.getText(),txtData.getText(),txtMatricula.getText(),txtNome.getText());

        Aluno alunoAntigo = alunoDAO.getAluno(txtMatricula.getText());

        alunoDAO.updateAluno(alunoAntigo,alunoNovo);
        LimparFormulario();
    }

    @FXML
    private void LimparFormulario() {
        txtNome.clear();
        txtCPF.clear();
        txtData.clear();
        txtEmail.clear();
        txtMatricula.clear();
        txtTurma.clear();
    }

}
