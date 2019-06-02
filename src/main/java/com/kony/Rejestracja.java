package com.kony;

import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class Rejestracja implements Initializable {
    @FXML
    public TextField rejLog;
    public TextField rejHas;
    public TextField textLog;
    public TextField textHas;
    public Text infoo;

    @FXML
    public int zmianaFx(ActionEvent event) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Uzytkownik ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();

        while(it.hasNext()) {
            Uzytkownik klient = (Uzytkownik) it.next();

            System.out.print("ID: " + klient.getId_uzytkownik()+ "  LOGIN:  " + klient.getLogin() + " HASLO:  "+ klient.getHaslo() + "\n");
            if (textLog.getText().equals(klient.getLogin()) && textHas.getText().equals(klient.getHaslo())){
                Controller.zalogowany = klient.getId_uzytkownik();
                Parent rejestracjaParent = FXMLLoader.load(getClass().getResource("/zalogowanouser.fxml"));
                Scene rejestracjaScene = new Scene(rejestracjaParent);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(rejestracjaScene);
                window.show();
                window.setResizable(false);

                infoo.setText("  ");

            }



        }
        infoo.setText("nie ma takiego konta");

        session.close();
        return 0;


    }
    @FXML
    public String wyslij(ActionEvent event) throws IOException {

        if ( rejLog.getText().length() < 5 || rejHas.getText().length() < 5)  {
            infoo.setText("LOGIN I HASLO MUSI BYC DLUZSZE NIZ 4");
        }
        else {
            if(sprawdzczyistnieje() == 1){
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Uzytkownik uzytkownik = new Uzytkownik();
                uzytkownik.setLogin(rejLog.getText());
                uzytkownik.setHaslo(rejHas.getText());
                uzytkownik.setMode(0);
                session.save(uzytkownik);
                session.getTransaction().commit();
                session.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
                Parent porejestracji = loader.load();
                Controller controller = loader.getController();
                controller.ustawInfo("Zarejestrowałeś się. Możesz się teraz zalogować.");
                Scene porejestracjiScene = new Scene(porejestracji);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(porejestracjiScene);
                window.show();
                window.setResizable(false);


            }
            else{
                infoo.setText("PODAJ INNY LOGIN");

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

            if (rejLog.getText().equals(klient.getLogin())){
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
            if (rejLog.getText().equals(klient.getLogin())){
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
