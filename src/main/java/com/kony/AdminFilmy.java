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


public class AdminFilmy implements Initializable {
    @FXML
    public Text witaj;
    public TextField id;
    public TextField tytul;
    public TextField idrez;
    public DatePicker datata;

    @FXML
    private TableView<Filmy> tabela;

    @FXML
    private TableColumn<Filmy,Integer> col_id;

    @FXML
    private TableColumn<Filmy,String> col_tyt;

    @FXML
    private TableColumn<Filmy,Date> col_data;

    @FXML
    private TableColumn<Filmy,Integer> col_idrez;

    ObservableList<Filmy> olist = FXCollections.observableArrayList();





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
        if (tytul.getText().equals("") || id.getText().equals("") || idrez.getText().equals("") || datata.getValue() == null) {
            witaj.setText("UZUPELNIJ POLA");
        } else {

            Session session3 = HibernateUtil.getSessionFactory().openSession();
            session3.beginTransaction();
            Filmy upd = session3.get(Filmy.class, Integer.valueOf(id.getText()));
            upd.setTytul(tytul.getText());
            upd.setData_premiery(java.sql.Date.valueOf(datata.getValue()));
            upd.setId_rezyser(Integer.valueOf(idrez.getText()));
            session3.update(upd);
            session3.getTransaction().commit();
            session3.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminFilmy.fxml"));
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
            String hq12 = "FROM Filmy_has_aktor WHERE id_filmy=" + id.getText();
            Query query = session2.createQuery(hq12);
            java.util.List results = query.list();
            Iterator it2 = results.iterator();
            while (it2.hasNext()) {

                Filmy_has_aktor klient = (Filmy_has_aktor) it2.next();
                System.out.println(klient.getId_filmy_has_aktor());
                session2.delete(klient);


            }

            hq12 = "FROM Filmy_has_gatunek WHERE id_filmy=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Filmy_has_gatunek klient = (Filmy_has_gatunek) it2.next();
                session2.delete(klient);


            }
            hq12 = "FROM Ocena_filmu WHERE id_filmy=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Ocena_filmu klient = (Ocena_filmu) it2.next();
                session2.delete(klient);


            }

            hq12 = "FROM Filmy WHERE id_filmy=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Filmy klient = (Filmy) it2.next();
                session2.delete(klient);


            }

            session2.getTransaction().commit();
            session2.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminFilmy.fxml"));
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
            id.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getId_filmy()));
            tytul.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getTytul()));
            idrez.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getId_rezyser()));

        }
    }




    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setEditable(false);
        Session session0 = HibernateUtil.getSessionFactory().openSession();
        String hq0 = "FROM Filmy ";
        Query query0 = session0.createQuery(hq0);
        java.util.List results0 = query0.list();
        Iterator it0 = results0.iterator();
        while (it0.hasNext()) {
            Filmy t = (Filmy) it0.next();
        olist.add(new Filmy(t.getId_filmy(),t.getTytul(),t.getData_premiery(),t.getId_rezyser()));
        System.out.println(t.getId_filmy()+t.getTytul());
        }
        session0.close();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_filmy"));
        col_tyt.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data_premiery"));
        col_idrez.setCellValueFactory(new PropertyValueFactory<>("id_rezyser"));
        tabela.setItems(olist);

    }}
