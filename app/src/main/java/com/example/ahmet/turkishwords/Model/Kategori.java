package com.example.ahmet.turkishwords.Model;

import java.io.Serializable;

public class Kategori implements Serializable {
    private int id;
    private String ad;
    private String resim;
  //  private String resim2;
    /*
        resim2 veritabanından gelen fotoğraflardan bazılarını kategoride
        çekmeye yarar.ihtiyaç halinde kullanılır
    */

    public Kategori() {
    }

    public Kategori(int id, String ad ,String resim/*, String resim2*/) {
        this.id = id;
        this.ad = ad;
        this.resim = resim;
      //  this.resim2 = resim2;
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

    /* public String getResim2() {
        return resim2;
    }

    public void setResim2(String resim2) {
        this.resim2 = resim2;
    }*/
}
