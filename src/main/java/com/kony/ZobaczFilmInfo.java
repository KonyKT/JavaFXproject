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

public class ZobaczFilmInfo implements Initializable {
    @FXML
    public Text witaj;
    public Text tytul;
    ArrayList<Integer> gatunki = new ArrayList<Integer>();
    public ListView<String> listaGatunkow;
    public Text rezyser;
    ArrayList<Integer> aktorzy = new ArrayList<Integer>();
    public ListView<String> listaAktorow;
    public int idfil;
    public int rez;


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
        idfil = ZobaczFilmList.jakifilm;
        tytul.setText("");
        rezyser.setText(" ");
        gatunki.clear();
        aktorzy.clear();
        int i = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Filmy ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Filmy klient = (Filmy) it.next();
            if(klient.getId_filmy() == idfil){
                rez = klient.getId_rezyser();
                tytul.setText(klient.getTytul());
            }

        }
        session.getSessionFactory().openSession();
        hql = "FROM Filmy_has_gatunek ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        while(it.hasNext()) {
            Filmy_has_gatunek klient = (Filmy_has_gatunek) it.next();
            if(klient.getId_filmy() == idfil){
                gatunki.add(klient.getId_gatunek());
            }
        }
        session.getSessionFactory().openSession();
        hql = "FROM Filmy_has_aktor ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        while(it.hasNext()) {
            Filmy_has_aktor klient = (Filmy_has_aktor) it.next();
            if(klient.getId_filmy() == idfil){
                aktorzy.add(klient.getId_aktor());
            }
        }
        session.getSessionFactory().openSession();
        hql = "FROM Rezyser ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        while(it.hasNext()) {
            Rezyser klient = (Rezyser) it.next();
            if(klient.getId_rezyser() == rez){
                rezyser.setText(klient.getImie()+ " " +klient.getNazwisko());
            }
        }
        session.getSessionFactory().openSession();
        hql = "FROM Gatunek ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        Collections.sort(gatunki);
        while(gatunki.size() > i) {
            Gatunek klient = (Gatunek) it.next();
            if(klient.getId_gatunek() == gatunki.get(i)){
                listaGatunkow.getItems().add(klient.getGatunek());
                i++;
            }
        }
        session.getSessionFactory().openSession();
        hql = "FROM Aktor ";
        i = 0;
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        Collections.sort(aktorzy);
        while(aktorzy.size() > i) {
            Aktor klient = (Aktor) it.next();
            if(klient.getId_aktor() == aktorzy.get(i)){
                listaAktorow.getItems().add(klient.getImie()+ "  " + klient.getNazwisko());
                i++;
            }
        }

        session.close();

    }
}
