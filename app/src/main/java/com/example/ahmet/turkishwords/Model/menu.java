package com.example.ahmet.turkishwords.Model;

public class menu {
    private int id;
    private String ad;
    private String resim;

    public menu() {
    }

    public menu(int id, String ad, String resim) {
        this.id = id;
        this.ad = ad;
        this.resim = resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
