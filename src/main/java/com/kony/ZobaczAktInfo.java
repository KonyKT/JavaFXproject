package com.kony;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ZobaczAktInfo implements Initializable {
    @FXML
    public Text witaj;
    public Text imie;
    public Text data;
    public Text miejsce;
    ArrayList<Integer> filmy = new ArrayList<Integer>();
    public ListView<String> listaFilmow;
    public Text nazwisko;
    public int idfil;


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
        idfil = ZobaczAktList.jakifilm;
        imie.setText(" ");
        nazwisko.setText(" ");
        filmy.clear();
        int i = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Aktor ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Aktor klient = (Aktor) it.next();
            if(klient.getId_aktor() == idfil){
                System.out.println("KURWAMRWAUJRNAIRUNAWRIUJ " + idfil);

                imie.setText("\t" +klient.getImie());
                nazwisko.setText("\t" +klient.getNazwisko());
                miejsce.setText("\t" +klient.getMiejsce_urodzenia());
                data.setText("\t" + klient.getData_urodzin());
            }

        }
        session.getSessionFactory().openSession();
        hql = "FROM Filmy_has_aktor ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        while(it.hasNext()) {
            Filmy_has_aktor klient = (Filmy_has_aktor) it.next();
            if(klient.getId_aktor() == idfil){
                filmy.add(klient.getId_filmy());
            }
        }



        session.getSessionFactory().openSession();
        hql = "FROM Filmy ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        Collections.sort(filmy);
        System.out.println(filmy);
        while(filmy.size() > i) {
            Filmy klient = (Filmy) it.next();
            if(klient.getId_filmy() == filmy.get(i)){
                listaFilmow.getItems().add(klient.getTytul());
                i++;
            }
        }



        session.close();

    }
}
