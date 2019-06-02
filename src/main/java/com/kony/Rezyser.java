package com.kony;

import java.util.Date;

public class Rezyser {
    private int id_rezyser;
    private String imie;
    private String nazwisko;
    private Date data_urodzin;
    private String miejsce_urodzenia;

    public Rezyser(int id_rezyser, String imie, String nazwisko, Date data_urodzin, String miejsce_urodzenia) {
        this.id_rezyser = id_rezyser;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzin = data_urodzin;
        this.miejsce_urodzenia = miejsce_urodzenia;
    }

    public Rezyser(){

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

    public int getId_rezyser() {
        return id_rezyser;
    }

    public void setId_rezyser(int id_rezyser) {
        this.id_rezyser = id_rezyser;
    }

}