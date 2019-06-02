package com.kony;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ModDodajFilm implements Initializable {
    @FXML
    public Text witaj;
    public TextField imie;
    public ChoiceBox<String> rezyserzy;
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
        if (imie.getText().equals("") || data.getValue() == null || rezyserzy.getSelectionModel().isEmpty()) {
            witaj.setText("UZUPELNIJ POLA");
        } else {
            int id = ZnajdzID(rezyserzy.getValue());
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Filmy uzytkownik = new Filmy();
            System.out.println(rezyserzy.getValue());
            uzytkownik.setTytul(imie.getText());
            uzytkownik.setData_premiery(java.sql.Date.valueOf(data.getValue()));
            uzytkownik.setId_rezyser(id);
            session.save(uzytkownik);
            session.getTransaction().commit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zalogowanomod.fxml"));
            Parent porejestracji = loader.load();
            ZalogowanoMod controller = loader.getController();
            controller.ustawWitaj("dodales film");
            Scene porejestracjiScene = new Scene(porejestracji);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(porejestracjiScene);
            window.show();
            window.setResizable(false);


        }


        return "zarejestrowano";
    }

    public int ZnajdzID(String text){
        int flag = 0;
        String help;
        System.out.println(text);
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        String hq12 = "FROM Rezyser ";
        Query query = session2.createQuery(hq12);
        java.util.List results = query.list();
        Iterator it2 = results.iterator();
        while(it2.hasNext()) {
            Rezyser klient = (Rezyser) it2.next();
            help = klient.getImie()+" "+klient.getNazwisko();
            if (help.equals(text)){
                flag = klient.getId_rezyser();
            }
        }
        session2.close();



        return flag;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        witaj.setText("Dodaj film");
        Session session0 = HibernateUtil.getSessionFactory().openSession();
        String hq0 = "FROM Rezyser ";
        Query query0 = session0.createQuery(hq0);
        java.util.List results0 = query0.list();

        Iterator it0 = results0.iterator();
        while (it0.hasNext()) {
            Rezyser t = (Rezyser) it0.next();
            rezyserzy.getItems().add(t.getImie()+" "+t.getNazwisko());
        }
        session0.close();

    }
}
