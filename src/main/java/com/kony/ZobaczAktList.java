package com.kony;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;

public class ZobaczAktList implements Initializable {
    @FXML
    private Text witaj;

    public TextField textid;
    public ListView<String> listaOcen;
    public static int jakifilm;





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
    public void ZobaczInfo(ActionEvent event) throws IOException {
        int istnieje = 0;
        try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Aktor ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Aktor klient = (Aktor) it.next();
            if (klient.getId_aktor() == Integer.valueOf(textid.getText())){
                istnieje = 1;
            }

        }
        session.close();

        if (istnieje == 0 ){
            witaj.setText("NIE MA TAKIEGO Aktora");
        }
        else {
            jakifilm = Integer.valueOf(textid.getText());
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczinfoakt.fxml"));
            Scene dodajaktScene = new Scene(zalogowanoParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dodajaktScene);
            window.show();
        }
        }
        catch (NumberFormatException e){
            witaj.setText("WPISZ LICZBE");
        }
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Integer> ocenki = new ArrayList<Integer>();
        int i = 0;
        double avrage;
        DecimalFormat one = new DecimalFormat("#0.0");
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Aktor ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            ocenki.clear();
            Aktor klient = (Aktor) it.next();
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            String hql2 = "FROM Ocena_aktora ";
            Query query2 = session2.createQuery(hql2);
            java.util.List results2 = query2.list();
            Iterator it2 = results2.iterator();
            while(it2.hasNext()){
                Ocena_aktora oceny = (Ocena_aktora) it2.next();
                if (oceny.getId_aktor() == klient.getId_aktor() ){
                    ocenki.add(oceny.getId_ocena_aktora());
                }
            }
            avrage = ocenki.stream().mapToInt(val -> val).average().orElse(0.0);
            session2.close();

            if(String.valueOf(one.format(avrage)).length() < 4) {
                listaOcen.getItems().add("ID: " + klient.getId_aktor()  + " Srednia: "+ one.format(avrage) + "\t Imie: " + klient.getImie() +" "+ klient.getNazwisko()  );
            }
            else {
                listaOcen.getItems().add("ID: " + klient.getId_aktor()  + " Srednia: "+ one.format(avrage) + " Imie: " + klient.getNazwisko() + " " +klient.getNazwisko()  );

            }
        }
        session.close();




    }}




