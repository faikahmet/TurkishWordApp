package com.example.ahmet.turkishwords.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ahmet.turkishwords.Adapter.AdapterKategori;
import com.example.ahmet.turkishwords.Database.DatabaseHelper;
import com.example.ahmet.turkishwords.Model.Kategori;
import com.example.ahmet.turkishwords.R;

import java.io.IOException;
import java.util.ArrayList;

public class KategoriActivity extends AppCompatActivity {
    GridView gridViewKategoriler;
    ArrayList<Kategori> kategoriler = new ArrayList<>();
    AdapterKategori adapterKategori;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Kategori kategori;
    TextView tvTitleKategori,tvKategoriPuanSayisi;
    ImageView ivGoToBackkategori;
    int kacsoruvard=0;
    int kacıncıSoruCozuldu=0;
    int kacsoruvar=0;
    int puan = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        this.getSupportActionBar().hide();//üst paneli gizliyor.

        gridViewKategoriler=findViewById(R.id.gridViewKategoriler);
        ivGoToBackkategori=findViewById(R.id.ivGoToBackkategori);
        tvTitleKategori=findViewById(R.id.tvTitleKategori);
        tvKategoriPuanSayisi=findViewById(R.id.tvKategoriPuanSayisi);
        try {
            databaseHelper=new DatabaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        db=databaseHelper.getWritableDatabase();
        puanEkle();

        Cursor cr=db.rawQuery("select*from Kategori",null);
        int[] dizi={
          R.drawable.easy,R.drawable.medium,R.drawable.hard
        };
        int dizielman=0;
        while (cr.moveToNext()) {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String ad = cr.getString(cr.getColumnIndex("ad"));
            String resim = String.valueOf(dizi[dizielman]);
            //String resim2 = cr.getString(cr.getColumnIndex("resim2"));

            kategoriler.add(new Kategori(
                    id,
                    ad,
                    resim/*,
                    resim2*/));
            dizielman++;

        }

        adapterKategori = new AdapterKategori(getApplicationContext(),kategoriler);
        gridViewKategoriler.setAdapter(adapterKategori);
        ivGoToBackkategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvTitleKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        gridViewKategoriler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int b;
              secilenIddeEnSonHangiSoruCozuldu(i+1);
                acikMı(i+1,i);

                //Toast.makeText(KategoriActivity.this, ""+ kacSoruVar(i+1), Toast.LENGTH_SHORT).show();
               // Toast.makeText(KategoriActivity.this,""+kategoriler.get(i).getAd(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(KategoriActivity.this,""+ kategoriler.get(i).getId(), Toast.LENGTH_SHORT).show();
           //      Intent intent=new Intent(getApplicationContext(),SoruActivity.class);
       //    intent.putExtra("kategori",kategoriler.get(i));
            //startActivity(intent);
            }
        });

    }
   public void acikMı(int kac,int i){
     //    kacsoruvard=sonSoruyaGeldiMi();
         //.makeText(this, ""+kacsoruvard, Toast.LENGTH_SHORT).show();
        Cursor c= db.rawQuery("select*from Ayarlar where ayar_adi='kategori_id_"+kac+"_index'",null);
        int deger=0;
        while (c.moveToNext()) {
            deger = Integer.parseInt(c.getString(c.getColumnIndex("ayar_degeri")));
           // Toast.makeText(this, "kacSoruVar"+kacSoruVar(i+1), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "kacıncıSoruCozuldu"+kacıncıSoruCozuldu, Toast.LENGTH_SHORT).show();
            if (kacSoruVar(i+1)==kacıncıSoruCozuldu){
                //db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri-" + 0 + " where ayar_adi='kullanici_kredi'");
                db.execSQL("update Ayarlar set ayar_degeri=1  where ayar_adi='kategori_id_"+(i+1)+"_index'");
               // Cursor cr = db.rawQuery("select*from Ayarlar where ayar_adi='kategori_id_"+i+"_index'", null);


                Toast.makeText(getApplicationContext(),"Bu kategoride tüm sorular çözüldü.Bu kategorinin soru kaydı sıfırlandı.",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),SoruActivity.class);
                intent.putExtra("kategori",kategoriler.get(i));
                startActivity(intent);
                finish();
            }
           else if (deger>=0){
               // Toast.makeText(this, "deger 1dir", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(getApplicationContext(),SoruActivity.class);
                 intent.putExtra("kategori",kategoriler.get(i));
                startActivity(intent);
                finish();
           //   Toast.makeText(this, ""+c.getString(c.getColumnIndex("ayar_degeri")), Toast.LENGTH_SHORT).show();
             // Toast.makeText(this, ""+c.getString(c.getColumnIndex("ayar_adi")), Toast.LENGTH_SHORT).show();
              //Toast.makeText(this, ""+c.getInt(c.getColumnIndex("ayar_id")), Toast.LENGTH_SHORT).show();
          }

           // else if (deger==0){
             //   Toast.makeText(this, "Lütfen Önceki Kategorileri Bitirin.", Toast.LENGTH_SHORT).show();

            //    Toast.makeText(this, "deger 0dır.", Toast.LENGTH_SHORT).show();
            //}
        }

      //  return deger;
    }
    public void secilenIddeEnSonHangiSoruCozuldu(int i){
         kacıncıSoruCozuldu=0;

        Cursor cr = db.rawQuery("select*from Ayarlar where ayar_adi='kategori_id_"+i+"_index'", null);

        while (cr.moveToNext()) {
            kacıncıSoruCozuldu=cr.getInt(cr.getColumnIndex("ayar_degeri"));
        }
     //   Toast.makeText(this, ""+kacıncıSoruCozuldu, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "" + kacsoruvar, Toast.LENGTH_SHORT).show();
    }
    public int kacSoruVar(int i){
         kacsoruvar=0;

        Cursor cr = db.rawQuery("select*from Soru where kategori_id="+i, null);

        while (cr.moveToNext()) {
            kacsoruvar++;
        }
        //Toast.makeText(this, "" + kacsoruvar, Toast.LENGTH_SHORT).show();
        return kacsoruvar;

    }
    public void puanEkle() {

        Cursor c = db.rawQuery("select*from Ayarlar where ayar_adi='kullanici_kredi'", null);

        while (c.moveToNext()) {
            puan = Integer.parseInt(c.getString(c.getColumnIndex("ayar_degeri")));
            tvKategoriPuanSayisi.setText("Puan:" + puan);
        }
    }
}
