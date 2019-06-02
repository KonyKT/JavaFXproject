package com.kony;

public class Gatunek {
    private int id_gatunek;
    private String gatunek;

    public Gatunek(int id_gatunek, String gatunek) {
        this.id_gatunek = id_gatunek;
        this.gatunek = gatunek;
    }

    public Gatunek(){

    }
    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public int getId_gatunek() {
        return id_gatunek;
    }

    public void setId_gatunek(int id_gatunek) {
        this.id_gatunek = id_gatunek;
    }

}
