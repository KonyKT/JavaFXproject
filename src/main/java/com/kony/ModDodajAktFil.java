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

public class ModDodajAktFil implements Initializable {
    @FXML
    public Text witaj;
    public ChoiceBox<String> aktorzy;
    public ChoiceBox<String> filmy;



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
        if (filmy.getSelectionModel().isEmpty() || aktorzy.getSelectionModel().isEmpty()  ) {
            witaj.setText("UZUPELNIJ POLA");
        } else {
            int idfilm = ZnajdzIDFILM(filmy.getValue());
            int idgat = ZnajdzIDAKTOR(aktorzy.getValue());
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Filmy_has_aktor uzytkownik = new Filmy_has_aktor();
            uzytkownik.setId_filmy(idfilm);
            uzytkownik.setId_aktor(idgat);
            session.save(uzytkownik);
            session.getTransaction().commit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zalogowanomod.fxml"));
            Parent porejestracji = loader.load();
            ZalogowanoMod controller = loader.getController();
            controller.ustawWitaj("dodales aktora do filmu");
            Scene porejestracjiScene = new Scene(porejestracji);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(porejestracjiScene);
            window.show();
            window.setResizable(false);


        }


        return "zarejestrowano";
    }

    public int ZnajdzIDFILM(String text){
        int flag = 0;
        String help;
        System.out.println(text);
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        String hq12 = "FROM Filmy ";
        Query query = session2.createQuery(hq12);
        java.util.List results = query.list();
        Iterator it2 = results.iterator();
        while(it2.hasNext()) {
            Filmy klient = (Filmy) it2.next();
            help = klient.getTytul();
            if (help.equals(text)){
                flag = klient.getId_filmy();
            }
        }
        session2.close();



        return flag;
    }

    public int ZnajdzIDAKTOR(String text){
        int flag = 0;
        String help;
        System.out.println(text);
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        String hq12 = "FROM Aktor ";
        Query query = session2.createQuery(hq12);
        java.util.List results = query.list();
        Iterator it2 = results.iterator();
        while(it2.hasNext()) {
            Aktor klient = (Aktor) it2.next();
            help = klient.getImie()+" "+klient.getNazwisko();
            if (help.equals(text)){
                flag = klient.getId_aktor();
            }
        }
        session2.close();



        return flag;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        witaj.setText("Dodaj aktora do filmu");
        Session session0 = HibernateUtil.getSessionFactory().openSession();
        String hq0 = "FROM Filmy ";
        Query query0 = session0.createQuery(hq0);
        java.util.List results0 = query0.list();

        Iterator it0 = results0.iterator();
        while (it0.hasNext()) {
            Filmy t = (Filmy) it0.next();
            filmy.getItems().add(t.getTytul());
        }
        session0.getSessionFactory().openSession();
        hq0 = "FROM Aktor ";
        query0 = session0.createQuery(hq0);
        results0 = query0.list();
        it0 = results0.iterator();
        while (it0.hasNext()) {
            Aktor t = (Aktor) it0.next();
            aktorzy.getItems().add(t.getImie()+" "+t.getNazwisko());
        }

        session0.close();

    }
}
