package com.kony;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class ZalogowanoUser implements Initializable {
    @FXML
    public Text witaj;

    public void ustawWitaj(String text){
        witaj.setText(text);
    }

    public void zmianadodajfilmFX(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/ocenFilm.fxml"));
        Scene dodajfilmScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajfilmScene);
        window.setResizable(false);

        window.show();
    }

    public void zmianaDodajOceneAkt(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/ocenAkt.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.setResizable(false);

        window.show();
    }

    public void zobaczOceneFil(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczFil.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.setResizable(false);

        window.show();
    }

    public void zobaczOceneAkt(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczAkt.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.setResizable(false);

        window.show();
    }

    public void zobaczFilmy(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczfilmlist.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.setResizable(false);

        window.show();
    }

    public void zobaczAktorow(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczaktlist.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.setResizable(false);

        window.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        witaj.setText("witaj użytkowniku,twoj numer to: " + Controller.zalogowany);

    }
}
