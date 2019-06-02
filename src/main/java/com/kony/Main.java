package com.kony;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane pane = new BorderPane();
        pane.setCenter(new Label("Hello there"));
        pane.setStyle("-fx-border-color : black; -fx-border-width : 0 5 ");
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Baza danych filmow CONY");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.show();
        primaryStage.setResizable(false);

    }


    public static void main(String[] args) {
        launch(args);


    }
}
