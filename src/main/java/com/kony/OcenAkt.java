package com.kony;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
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

public class OcenAkt implements Initializable {
    @FXML
    private Text witaj;
    public TextField ocenaFIlm;
    public TextField idFilm;
    public ListView<String> listaFilm;




    public void zmianadodajfilmFX(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/ocenFilm.fxml"));
        Scene dodajfilmScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajfilmScene);
        window.show();
        window.setResizable(false);

    }

    public void zmianaDodajOceneAkt(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/ocenAkt.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void zobaczOceneFil(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczFil.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void zobaczOceneAkt(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczAkt.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }
    public void zobaczFilmy(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczfilmlist.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void zobaczAktorow(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/zobaczaktlist.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }
    public void wyslijOceneAkt(ActionEvent event) throws  IOException {
        int filmistnieje = 0;
        int dobraocena = 0;
        int ocenionojuz = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Aktor ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Aktor klient = (Aktor) it.next();
            if (idFilm.getText().equals(String.valueOf(klient.getId_aktor()))){
                filmistnieje = 1;
            }
        }
        if (Integer.valueOf(ocenaFIlm.getText()) < 0 || Integer.valueOf(ocenaFIlm.getText()) > 10 || ocenaFIlm.getText().trim().isEmpty()){
            dobraocena = 1;
        }
        session.getSessionFactory().openSession();
        hql = "FROM Ocena_aktora ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        while(it.hasNext()) {
            Ocena_aktora oceny = (Ocena_aktora) it.next();
            if(Controller.zalogowany == oceny.getId_uzytkownik() && oceny.getId_aktor() == Integer.valueOf(idFilm.getText())){
                ocenionojuz = 1;
            }
        }
        session.close();
        if ( filmistnieje == 1 && dobraocena == 0 && ocenionojuz == 0 ){
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2.beginTransaction();
            Ocena_aktora ocena = new Ocena_aktora();
            ocena.setId_aktor(Integer.valueOf(idFilm.getText()));
            ocena.setId_uzytkownik(Controller.zalogowany);
            ocena.setOcena_aktora(Integer.valueOf(ocenaFIlm.getText()));
            session2.save(ocena);
            session2.getTransaction().commit();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zalogowanouser.fxml"));
            Parent dodanoOcene = loader.load();
            ZalogowanoUser controller = loader.getController();
            controller.witaj.setText("Dodałeś ocene aktora!");
            Scene porejestracjiScene = new Scene(dodanoOcene);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(porejestracjiScene);
            window.show();
            window.setResizable(false);

            session2.close();

        }
        else {
            listaFilm.getItems().add("nie dodano");
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String help;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Aktor ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Aktor klient = (Aktor) it.next();
            help = String.valueOf(klient.getId_aktor());
            listaFilm.getItems().add(help + "  " + klient.getImie() + "  " + klient.getNazwisko());

        }
        session.close();



    }}



