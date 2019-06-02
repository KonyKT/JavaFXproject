package com.kony;

public class Ocena_filmu {
    private int id_ocena_filmu;
    private int id_uzytkownik;
    private int id_filmy;
    private int ocena_filmu;

    public int getId_uzytkownik() {
        return id_uzytkownik;
    }

    public void setId_uzytkownik(int id_uzytkownik) {
        this.id_uzytkownik = id_uzytkownik;
    }

    public int getId_ocena_filmu() {
        return id_ocena_filmu;
    }

    public void setId_ocena_filmu(int id_ocena_filmu) {
        this.id_ocena_filmu = id_ocena_filmu;
    }

    public int getId_filmy() {
        return id_filmy;
    }

    public void setId_filmy(int id_filmy) {
        this.id_filmy = id_filmy;
    }

    public int getOcena_filmu() {
        return ocena_filmu;
    }

    public void setOcena_filmu(int ocena_filmu) {
        this.ocena_filmu = ocena_filmu;
    }
}
