package com.kony;

public class Ocena_aktora {
    private int id_ocena_aktora;
    private int id_uzytkownik;
    private int id_aktor;
    private int ocena_aktora;

    public int getId_uzytkownik() {
        return id_uzytkownik;
    }

    public void setId_uzytkownik(int id_uzytkownik) {
        this.id_uzytkownik = id_uzytkownik;
    }

    public int getId_ocena_aktora() {
        return id_ocena_aktora;
    }

    public void setId_ocena_aktora(int id_ocena_aktora) {
        this.id_ocena_aktora = id_ocena_aktora;
    }

    public int getId_aktor() {
        return id_aktor;
    }

    public void setId_aktor(int id_aktor) {
        this.id_aktor = id_aktor;
    }

    public int getOcena_aktora() {
        return ocena_aktora;
    }

    public void setOcena_aktora(int ocena_aktora) {
        this.ocena_aktora = ocena_aktora;
    }
}
