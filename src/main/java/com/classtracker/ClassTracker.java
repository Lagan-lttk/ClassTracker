package com.classtracker;

import com.classtracker.controller.*;
import com.classtracker.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassTracker extends Application {
    public static void main(String[] args) throws SQLException {

        AlunoDAOImpl teste = new AlunoDAOImpl();



        launch(args);


    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.getIcons().add(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ClassTrackerLogo1-removebg-preview.png")))
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

