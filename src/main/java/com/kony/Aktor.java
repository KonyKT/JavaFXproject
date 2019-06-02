package com.kony;

import java.util.Date;

public class Aktor {
    private int id_aktor;
    private String imie;
    private String nazwisko;
    private Date data_urodzin;
    private String miejsce_urodzenia;

    public Aktor(int id_aktor, String imie, String nazwisko, Date data_urodzin, String miejsce_urodzenia) {
        this.id_aktor = id_aktor;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzin = data_urodzin;
        this.miejsce_urodzenia = miejsce_urodzenia;
    }

    public Aktor() {

    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getData_urodzin() {
        return data_urodzin;
    }

    public void setData_urodzin(Date data_urodzin) {
        this.data_urodzin = data_urodzin;
    }

    public String getMiejsce_urodzenia() {
        return miejsce_urodzenia;
    }

    public void setMiejsce_urodzenia(String miejsce_urodzenia) {
        this.miejsce_urodzenia = miejsce_urodzenia;
    }

    public int getId_aktor() {
        return id_aktor;
    }

    public void setId_aktor(int id_aktor) {
        this.id_aktor = id_aktor;
    }

}
