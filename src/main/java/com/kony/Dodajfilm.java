package com.kony;

import org.hibernate.cfg.Configuration;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Dodajfilm implements Initializable {
public ComboBox combo;
public static int zalogowany;
    private static SessionFactory factory;

    private ArrayList<String> data = new ArrayList<>();

    public void xd() {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Dodajfilm RK = new Dodajfilm();

        RK.klikaszliste();

    }

    public void klikaszliste()  {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List uzytkownicy = session.createQuery("FROM Uzytkownik ").list();
            for (Iterator iterator = uzytkownicy.iterator(); iterator.hasNext();){
                Uzytkownik uzy = (Uzytkownik) iterator.next();




            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
