package com.kony;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
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
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


public class AdminUzytkownicy implements Initializable {
    @FXML
    public Text witaj;
    public TextField id;
    public TextField login;
    public TextField haslo;

    @FXML
    private TableView<Uzytkownik> tabela;

    @FXML
    private TableColumn<Uzytkownik,Integer> col_id;

    @FXML
    private TableColumn<Uzytkownik,String> col_log;

    @FXML
    private TableColumn<Uzytkownik,String> col_has;

    @FXML
    private TableColumn<Uzytkownik,Integer> col_mod;



    ObservableList<Uzytkownik> olist = FXCollections.observableArrayList();





    public void ustawWitaj(String text){
        witaj.setText(text);
    }

    public void AdminFilmy(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminFilmy.fxml"));
        Scene dodajfilmScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajfilmScene);
        window.show();
        window.setResizable(false);

    }

    public void AdminAktorzy(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminAktorzy.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void AdminUzytkownicy(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminUzytkownicy.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void AdminRezyserzy(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminRezyserzy.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void AdminGatunki(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminGatunki.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }

    public void AdminDodajModa(ActionEvent event) throws IOException {

        Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminDodajModa.fxml"));
        Scene dodajaktScene = new Scene(zalogowanoParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dodajaktScene);
        window.show();
        window.setResizable(false);

    }


    public void Edytuj(ActionEvent event) throws IOException {
        if (login.getText().equals("") || id.getText().equals("") || haslo.getText().equals("")){
            witaj.setText("UZUPELNIJ POLA");
        } else {

            Session session3 = HibernateUtil.getSessionFactory().openSession();
            session3.beginTransaction();
            Uzytkownik upd = session3.get(Uzytkownik.class, Integer.valueOf(id.getText()));
            upd.setLogin(login.getText());
            upd.setHaslo(haslo.getText());
            session3.update(upd);
            session3.getTransaction().commit();
            session3.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminUzytkownicy.fxml"));
            Scene dodajaktScene = new Scene(zalogowanoParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(dodajaktScene);
            window.show();
            window.setResizable(false);



        }
    }

    public void Usun(ActionEvent event) throws IOException {
        if (id.getText().equals("")) {
            witaj.setText("UZUPELNIJ POLA");
        } else {

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            session2.beginTransaction();
            String hq12 = "FROM Ocena_aktora WHERE id_uzytkownik=" + id.getText();
            Query query = session2.createQuery(hq12);
            java.util.List results = query.list();
            Iterator it2 = results.iterator();
            while (it2.hasNext()) {

                Ocena_aktora klient = (Ocena_aktora) it2.next();
                session2.delete(klient);


            }

            hq12 = "FROM Ocena_filmu WHERE id_uzytkownik=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Ocena_filmu klient = (Ocena_filmu) it2.next();
                session2.delete(klient);


            }


            hq12 = "FROM Uzytkownik WHERE id_uzytkownik=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Uzytkownik klient = (Uzytkownik) it2.next();
                session2.delete(klient);


            }

            session2.getTransaction().commit();
            session2.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminUzytkownicy.fxml"));
            Scene dodajaktScene = new Scene(zalogowanoParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(dodajaktScene);
            window.show();
            window.setResizable(false);


        }
    }

    @FXML
    public void clickItem(MouseEvent event)
    {

        if (event.getClickCount() == 2) //Checking double click
        {
            id.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getId_uzytkownik()));
            login.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getLogin()));
            haslo.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getHaslo()));

        }
    }




    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setEditable(false);
        Session session0 = HibernateUtil.getSessionFactory().openSession();
        String hq0 = "FROM Uzytkownik ";
        Query query0 = session0.createQuery(hq0);
        java.util.List results0 = query0.list();
        Iterator it0 = results0.iterator();
        while (it0.hasNext()) {
            Uzytkownik t = (Uzytkownik) it0.next();

                olist.add(new Uzytkownik(t.getId_uzytkownik(),t.getLogin(),t.getHaslo(),t.getMode()));


        }
        session0.close();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_uzytkownik"));
        col_log.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_has.setCellValueFactory(new PropertyValueFactory<>("haslo"));
        col_mod.setCellValueFactory(new PropertyValueFactory<>("mode"));
        tabela.setItems(olist);

    }}
