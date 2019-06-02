package com.kony;

import java.util.Date;

public class Filmy {
    private int id_filmy;
    private String tytul;
    private Date data_premiery;
    private int id_rezyser;

    public Filmy(int id_filmy, String tytul, Date data_premiery, int id_rezyser) {
        this.id_filmy = id_filmy;
        this.tytul = tytul;
        this.data_premiery = data_premiery;
        this.id_rezyser = id_rezyser;
    }

    public Filmy() {
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Date getData_premiery() {
        return data_premiery;
    }

    public void setData_premiery(Date data_premiery) {
        this.data_premiery = data_premiery;
    }

    public int getId_filmy() {
        return id_filmy;
    }

    public void setId_filmy(int id_filmy) {
        this.id_filmy = id_filmy;
    }

    public int getId_rezyser() {
        return id_rezyser;
    }

    public void setId_rezyser(int id_rezyser) {
        this.id_rezyser = id_rezyser;
    }

}