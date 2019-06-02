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


public class AdminGatunki implements Initializable {
    @FXML
    public Text witaj;
    public TextField id;
    public TextField gatunek;

    @FXML
    private TableView<Gatunek> tabela;

    @FXML
    private TableColumn<Gatunek,Integer> col_id;

    @FXML
    private TableColumn<Gatunek,String> col_gat;





    ObservableList<Gatunek> olist = FXCollections.observableArrayList();





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
        if (gatunek.getText().equals("") || id.getText().equals("")){
            witaj.setText("UZUPELNIJ POLA");
        } else {

            Session session3 = HibernateUtil.getSessionFactory().openSession();
            session3.beginTransaction();
            Gatunek upd = session3.get(Gatunek.class, Integer.valueOf(id.getText()));
            upd.setGatunek(gatunek.getText());
            session3.update(upd);
            session3.getTransaction().commit();
            session3.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminGatunki.fxml"));
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
            String hq12 = "FROM Filmy_has_gatunek WHERE id_gatunek=" + id.getText();
            Query query = session2.createQuery(hq12);
            java.util.List results = query.list();
            Iterator it2 = results.iterator();
            while (it2.hasNext()) {

                Filmy_has_gatunek klient = (Filmy_has_gatunek) it2.next();
                session2.delete(klient);


            }

            hq12 = "FROM Gatunek WHERE id_gatunek=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Gatunek klient = (Gatunek) it2.next();
                session2.delete(klient);


            }



            session2.getTransaction().commit();
            session2.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminGatunki.fxml"));
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
            id.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getId_gatunek()));
            gatunek.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getGatunek()));

        }
    }




    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setEditable(false);
        Session session0 = HibernateUtil.getSessionFactory().openSession();
        String hq0 = "FROM Gatunek ";
        Query query0 = session0.createQuery(hq0);
        java.util.List results0 = query0.list();
        Iterator it0 = results0.iterator();
        while (it0.hasNext()) {
            Gatunek t = (Gatunek) it0.next();

            olist.add(new Gatunek(t.getId_gatunek(),t.getGatunek()));


        }
        session0.close();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_gatunek"));
        col_gat.setCellValueFactory(new PropertyValueFactory<>("gatunek"));

        tabela.setItems(olist);

    }}
