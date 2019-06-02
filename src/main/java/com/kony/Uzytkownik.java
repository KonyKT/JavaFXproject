package com.kony;

public class Uzytkownik {
    private int id_uzytkownik;
    private String login;
    private String haslo;
    private int mode;

    public Uzytkownik(int id_uzytkownik, String login, String haslo, int mode) {
        this.id_uzytkownik = id_uzytkownik;
        this.login = login;
        this.haslo = haslo;
        this.mode = mode;
    }

    public Uzytkownik(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public int getId_uzytkownik() {
        return id_uzytkownik;
    }

    public void setId_uzytkownik(int id_uzytkownik) {
        this.id_uzytkownik = id_uzytkownik;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }


}
