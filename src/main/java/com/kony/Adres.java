package com.kony;

public class Adres {
    private int id_adres;
    private String ulica;
    private int numer;
    private String kod_pocztowy;
    private String miasto;
    private int telefon_komorkowy;
    private String kraj;


    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public int getTelefon_komorkowy() {
        return telefon_komorkowy;
    }

    public void setTelefon_komorkowy(int telefon_komorkowy) {
        this.telefon_komorkowy = telefon_komorkowy;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public int getId_adres() {
        return id_adres;
    }

    public void setId_adres(int id_adres) {
        this.id_adres = id_adres;
    }

}
