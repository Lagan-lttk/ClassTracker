package com.classtracker;

import com.classtracker.controller.AlunoDAOImpl;
import com.classtracker.controller.ProfessorDAOimpl;
import com.classtracker.model.Aluno;
import com.classtracker.model.Professor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassTracker extends Application {
    public static void main(String[] args) throws SQLException {
        Professor claudio = new Professor
                ("11322233345","claudiopinto@gmail.com","filosofia","Claudio"
                        ,"61996904466");

        Professor sandro = new Professor
                ("12122213345","sandroFera@gmail.com","plantio","Sandro"
                        ,"61993404466");

        Aluno gustavo = new Aluno(
                "02131345744","ADS","2006-04-18","UC23335316","Gustavo"
        );

        Aluno pablo = new Aluno(
                "11122233345","ADS","2006-06-10","UC25103016","Pablo"
        );

        ProfessorDAOimpl teste = new ProfessorDAOimpl();
        AlunoDAOImpl alunos = new AlunoDAOImpl();

        List<String> teste3 = alunos.getAlunos();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/images/ClassTrackerLogo1-removebg-preview.png"))
        );

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/MainMenu.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Class Tracker");
        stage.setScene(scene);
        stage.show();
    }
}
