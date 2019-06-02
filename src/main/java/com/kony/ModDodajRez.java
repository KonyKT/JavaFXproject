package com.kony;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;

public class ModDodajRez implements Initializable {
    @FXML
    public Text witaj;
    public TextField imie;
    public TextField nazwisko;
    public TextField miejsce;
    public DatePicker data;



    public void ustawWitaj(String text){
        witaj.setText(text);
    }

    public void modDodajAkt(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/modDodajAkt.fxml"));
        Scene dodajfilmScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajfilmScene);
        window.show();
        window.setResizable(false);

    }

    public void modDodajFilm(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/modDodajFilm.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void modDodajGat(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/modDodajGat.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void modDodajRez(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/modDodajRez.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void modDodajGatFil(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/modDodajGatFil.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void modDodajAktFil(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/modDodajAktFil.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }
    @FXML
    public String dodaj(ActionEvent event) throws IOException {
        if (imie.getText().equals("") || nazwisko.getText().equals("") || miejsce.getText().equals("") || data.getValue() == null) {
            witaj.setText("UZUPELNIJ POLA");
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Rezyser uzytkownik = new Rezyser();
            uzytkownik.setImie(imie.getText());
            uzytkownik.setNazwisko(nazwisko.getText());
            uzytkownik.setMiejsce_urodzenia(miejsce.getText());
            uzytkownik.setData_urodzin(java.sql.Date.valueOf(data.getValue()));
            session.save(uzytkownik);
            session.getTransaction().commit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zalogowanomod.fxml"));
            Parent porejestracji = loader.load();
            ZalogowanoMod controller = loader.getController();
            controller.ustawWitaj("dodales rezysera");
            Scene porejestracjiScene = new Scene(porejestracji);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(porejestracjiScene);
            window.show();
            window.setResizable(false);


        }


        return "zarejestrowano";
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        witaj.setText("Dodaj rezysera:");

    }
}
