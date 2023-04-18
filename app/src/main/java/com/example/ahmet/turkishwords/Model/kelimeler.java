package com.example.ahmet.turkishwords.Model;

public class kelimeler {
    private int id;
    private String TrKelime;
    private String IngKelime;

    public kelimeler() {
    }

    public kelimeler(int id, String trKelime, String ingKelime) {
        this.id = id;
        TrKelime = trKelime;
        IngKelime = ingKelime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrKelime() {
        return TrKelime;
    }

    public void setTrKelime(String trKelime) {
        TrKelime = trKelime;
    }

    public String getIngKelime() {
        return IngKelime;
    }

    public void setIngKelime(String ingKelime) {
        IngKelime = ingKelime;
    }
}
