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


public class AdminDodajModa implements Initializable {
    @FXML
    public Text witaj;
    public TextField login;
    public TextField haslo;








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

    @FXML
    public String Dodaj(ActionEvent event) throws IOException {

        if ( login.getText().length() < 5 || haslo.getText().length() < 5)  {
            witaj.setText("LOGIN I HASLO MUSI BYC DLUZSZE NIZ 4");
        }
        else {
            if(sprawdzczyistnieje() == 1){
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Uzytkownik uzytkownik = new Uzytkownik();
                uzytkownik.setLogin(login.getText());
                uzytkownik.setHaslo(haslo.getText());
                uzytkownik.setMode(1);
                session.save(uzytkownik);
                session.getTransaction().commit();
                session.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/zalogowanoadmin.fxml"));
                Parent porejestracji = loader.load();
                ZalogowanoAdmin controller = loader.getController();
                controller.ustawWitaj("Dodales Moda");
                Scene porejestracjiScene = new Scene(porejestracji);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(porejestracjiScene);
                window.show();
                window.setResizable(false);


            }
            else{
                witaj.setText("PODAJ INNY LOGIN");

            }
        }
        return "zarejestrowano";

    }

    public int sprawdzczyistnieje(){
        int flag = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hq1 = "FROM Moderator ";
        String hq2 = "FROM Uzytkownik ";
        Query query = session.createQuery(hq1);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Moderator klient = (Moderator) it.next();

            if (login.getText().equals(klient.getLogin())){
                flag=flag+1;
            }
        }
        session.close();

        Session session2 = HibernateUtil.getSessionFactory().openSession();

        Query query2 = session2.createQuery(hq2);
        java.util.List results2 = query2.list();
        Iterator it2 = results2.iterator();
        while(it2.hasNext()) {
            Uzytkownik klient = (Uzytkownik) it2.next();
            if (login.getText().equals(klient.getLogin())){
                flag=flag+2;
            }
        }
        session2.close();

        if( flag == 0)
            return 1;
        else
            return 0;
    }







    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }}
