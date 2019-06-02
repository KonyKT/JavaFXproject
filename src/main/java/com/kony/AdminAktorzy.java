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


public class AdminAktorzy implements Initializable {
    @FXML
    public Text witaj;
    public TextField id;
    public TextField imie;
    public TextField miejsce;
    public TextField nazwisko;
    public DatePicker datata;

    @FXML
    private TableView<Aktor> tabela;

    @FXML
    private TableColumn<Aktor,Integer> col_id;

    @FXML
    private TableColumn<Aktor,String> col_im;

    @FXML
    private TableColumn<Aktor,String> col_naz;

    @FXML
    private TableColumn<Aktor,Date> col_data;

    @FXML
    private TableColumn<Aktor,String> col_miejsce;

    ObservableList<Aktor> olist = FXCollections.observableArrayList();





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
        if (imie.getText().equals("") || id.getText().equals("") || nazwisko.getText().equals("") || miejsce.getText().equals("") || datata.getValue() == null) {
            witaj.setText("UZUPELNIJ POLA");
        } else {

            Session session3 = HibernateUtil.getSessionFactory().openSession();
            session3.beginTransaction();
            Aktor upd = session3.get(Aktor.class, Integer.valueOf(id.getText()));
            upd.setImie(imie.getText());
            upd.setData_urodzin(java.sql.Date.valueOf(datata.getValue()));
            upd.setNazwisko(nazwisko.getText());
            upd.setMiejsce_urodzenia(miejsce.getText());
            session3.update(upd);
            session3.getTransaction().commit();
            session3.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminAktorzy.fxml"));
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
            String hq12 = "FROM Filmy_has_aktor WHERE id_aktor=" + id.getText();
            Query query = session2.createQuery(hq12);
            java.util.List results = query.list();
            Iterator it2 = results.iterator();
            while (it2.hasNext()) {

                Filmy_has_aktor klient = (Filmy_has_aktor) it2.next();
                System.out.println(klient.getId_filmy_has_aktor());
                session2.delete(klient);


            }


            hq12 = "FROM Ocena_aktora WHERE id_aktor=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Ocena_filmu klient = (Ocena_filmu) it2.next();
                session2.delete(klient);


            }

            hq12 = "FROM Aktor WHERE id_aktor=" + id.getText();
            query = session2.createQuery(hq12);
            results = query.list();
            it2 = results.iterator();
            while (it2.hasNext()) {

                Aktor klient = (Aktor) it2.next();
                session2.delete(klient);


            }

            session2.getTransaction().commit();
            session2.close();
            Parent zalogowanoParent = FXMLLoader.load(getClass().getResource("/adminAktorzy.fxml"));
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
            id.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getId_aktor()));
            imie.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getImie()));
            nazwisko.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getNazwisko()));
            miejsce.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getMiejsce_urodzenia()));

        }
    }




    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setEditable(false);
        Session session0 = HibernateUtil.getSessionFactory().openSession();
        String hq0 = "FROM Aktor ";
        Query query0 = session0.createQuery(hq0);
        java.util.List results0 = query0.list();
        Iterator it0 = results0.iterator();
        while (it0.hasNext()) {
            Aktor t = (Aktor) it0.next();
            olist.add(new Aktor(t.getId_aktor(),t.getImie(),t.getNazwisko(),t.getData_urodzin(),t.getMiejsce_urodzenia()));
        }
        session0.close();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_aktor"));
        col_im.setCellValueFactory(new PropertyValueFactory<>("imie"));
        col_naz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data_urodzin"));
        col_miejsce.setCellValueFactory(new PropertyValueFactory<>("miejsce_urodzenia"));
        tabela.setItems(olist);

    }}
