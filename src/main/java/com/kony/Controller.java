package com.kony;

import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Controller implements Initializable {

    @FXML
    private static SessionFactory factory;
    public TextField textLog;
    public TextField textHas;
    public Text info;
    public static int zalogowany;


public void ustawInfo(String text){
    info.setText(text);
}
public int getZalogowany() {
    return zalogowany;
    }


    public int xd(ActionEvent event ) throws  IOException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Uzytkownik ";
        Query query = session.createQuery(hql);
        java.util.List results = query.list();
        Iterator it = results.iterator();
        while(it.hasNext()) {
            Uzytkownik klient = (Uzytkownik) it.next();

            System.out.print("ID: " + klient.getId_uzytkownik()+ "  LOGIN:  " + klient.getLogin() + " HASLO:  "+ klient.getHaslo() + "\n");
            if (textLog.getText().equals(klient.getLogin()) && textHas.getText().equals(klient.getHaslo())){
                if(klient.getMode() == 0) {
                    zalogowany = klient.getId_uzytkownik();
                    Parent rejestracjaParent = FXMLLoader.load(getClass().getResource("/zalogowanouser.fxml"));
                    Scene rejestracjaScene = new Scene(rejestracjaParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(rejestracjaScene);
                    window.show();
                    window.setResizable(false);

                    info.setText("   ");
                }
                else {
                    zalogowany = klient.getId_uzytkownik();
                    Parent rejestracjaParent = FXMLLoader.load(getClass().getResource("/zalogowanomod.fxml"));
                    Scene rejestracjaScene = new Scene(rejestracjaParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(rejestracjaScene);
                    window.show();
                    window.setResizable(false);

                    info.setText("   ");
                }

            }

        }
        session.getSessionFactory().openSession();
        hql = "FROM Admin ";
        query = session.createQuery(hql);
        results = query.list();
        it = results.iterator();
        while(it.hasNext()) {
            Admin admini = (Admin) it.next();
            if(textLog.getText().equals(admini.getLogin()) && textHas.getText().equals(admini.getHaslo())){
                Parent rejestracjaParent = FXMLLoader.load(getClass().getResource("/zalogowanoadmin.fxml"));
                Scene rejestracjaScene = new Scene(rejestracjaParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(rejestracjaScene);
                window.show();
                window.setResizable(false);

                info.setText("   ");
            }
        }
        session.close();


        ustawInfo("nie ma takiego konta");
        return 0;

    }

    public void rejestracja(ActionEvent event) throws IOException {

        Parent rejestracjaParent = FXMLLoader.load(getClass().getResource("/rejestracja.fxml"));
        Scene rejestracjaScene = new Scene(rejestracjaParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(rejestracjaScene);
        window.show();
        window.setResizable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
