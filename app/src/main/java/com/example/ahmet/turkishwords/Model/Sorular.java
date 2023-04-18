package com.example.ahmet.turkishwords.Model;

public class Sorular {
    /*
    Global alanda değişkenlerin tanımlanması
    Boş ve dolu constructorlar,
    get ve set metodların üretilmesi
   */

        private int soru_id;
        private int kategori_id;
        private String resim1;
        private String yanit;
        private String yanit_harfler;
        public String soru;

    public Sorular() {
    }

    public Sorular(int soru_id, int kategori_id, String resim1, String yanit, String yanit_harfler, String soru) {
        this.soru_id = soru_id;
        this.kategori_id = kategori_id;
        this.resim1 = resim1;
        this.yanit = yanit;
        this.yanit_harfler = yanit_harfler;
        this.soru = soru;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public void setSoru_id(int soru_id) {
        this.soru_id = soru_id;
    }

    public int getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(int kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getResim1() {
        return resim1;
    }

    public void setResim1(String resim1) {
        this.resim1 = resim1;
    }

    public String getYanit() {
        this.yanit=yanit.toUpperCase();
        return yanit;
    }

    public void setYanit(String yanit) {
        this.yanit = yanit;
    }

    public String getYanit_harfler() {
        return yanit_harfler;
    }

    public void setYanit_harfler(String yanit_harfler) {
        this.yanit_harfler = yanit_harfler;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }
}

