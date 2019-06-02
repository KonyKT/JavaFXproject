package com.kony;

import org.hibernate.Session;

import java.util.Date;


public class Test {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Filmy filmy = new Filmy();
        filmy.setTytul("Titanic 4");
        filmy.setData_premiery(new Date());
        filmy.setId_rezyser(1);
        Rezyser rezyser = new Rezyser();
        rezyser.setImie("Peter");
        rezyser.setNazwisko("Jackson");
        rezyser.setData_urodzin(new Date());
        rezyser.setMiejsce_urodzenia("SZIKAGO");
        session.save(rezyser);
        session.save(filmy);
        session.getTransaction().commit();

    }
 
}



