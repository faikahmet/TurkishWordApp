package com.example.ahmet.turkishwords.Activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.turkishwords.Database.DatabaseHelper;
import com.example.ahmet.turkishwords.Model.Kategori;
import com.example.ahmet.turkishwords.Model.Sorular;
import com.example.ahmet.turkishwords.R;

import com.nex3z.flowlayout.FlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoruActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivGoToBack, ivBack, ivNext, ivLogoPhoto;
    TextView tvTitle, tvPuan;
    FlowLayout flowDogruYanitKutucuklari, flowYanitTumHarfler;
    Button btnIpucu, btnTemizle, btnSoruyuAtla,btnTurkcesi;
    int soru_indis = 0;
    Kategori kategori;
    private int soruIndex = 0;
    private String yeniYanit = "";
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    private int harfIndex = 0;
    int kacsoruvard = 0;
    int kacinciHarfIndex = 0;
    int puan = 0;
    int kazanilanPuan = 10;
    int ipucuKullanma = 5;
    int soruyuAtla = 10;
    ArrayList<Sorular> sorular;

    String uretilenKelime = "";
    //Kullanıcının verdiği yanıta göre üretilen yeni kelimenin değeri
    ArrayList<Integer> uretilenKelimeIndisler = new ArrayList<>();

    public void harfEkle(String c) {
        //üretilenKelime değişkeninin değerini güncelledik.
        uretilenKelime += c;
    }

    public void soruHarfleriGetir(String kelime) {
        for (int i = 0; i < kelime.length(); i += 1) {
            final Button btn1 = new Button(this);
            btn1.setId(i);
            btn1.setBackgroundResource(R.drawable.custom_button_border);
            btn1.setLayoutParams(new LinearLayout.LayoutParams(75, 75));
            btn1.setGravity(Gravity.CENTER);
            flowDogruYanitKutucuklari.addView(btn1);

        }

    }


    public void soruHarfleriGuncelle(String uretilenYeniKelime) {
        Button b = findViewById(uretilenYeniKelime.length());
        b.setText("");

    }

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
        //Argüman olarak gönderilen String değeri karıştıp,yeni bir kelime olarak döndürür.
    }

    int i;

    public void rastgeleHarfleriGetir(final String kelime) {
        int uzunluk = kelime.length() * 2;
        String harfler = "QWERTYUIOPĞÜASDFGHJKLŞİZXCVBNMÖÇ";
        String yeniKelime = "";
        for (int i = 0; i < kelime.length(); i += 1) {
            Random ran = new Random();
            int sayi = ran.nextInt(harfler.length());
            yeniKelime += harfler.charAt(sayi);

        }
        //Kelimenin uzunluğu kadar rastgele kelime ürettik
        String sonUretilen = kelime + yeniKelime;
        final String sonDeger = shuffle(sonUretilen);
        //Orjinal kelime ve rastgele seçilen karakterleri sonUretilen içerisinde birleştirdik.
        // sonDeger string'i shuffle metodu sayesinde kelime ve yeniKelimeyi tekrar karıştırıp
        //yeni bir String haline çevirdi.

        for (i = 0; i < sonDeger.length(); i += 1) {
            final Button btn = new Button(this);
            final int id = i + 100;
            btn.setId(id);
            btn.setLayoutParams(new LinearLayout.LayoutParams(100, 120));
            btn.setBackgroundResource(R.drawable.custom_button_border);
            btn.setText("" + sonDeger.charAt(i));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        databaseHelper = new DatabaseHelper(getApplicationContext());
                        databaseHelper.createDatabase();
                        db = databaseHelper.getReadableDatabase();

                        Cursor c = db.rawQuery("Select * from sorular", null);
                        while (c.moveToNext()) {
                            // Toast.makeText(SoruActivity.this, c.getInt(0), Toast.LENGTH_SHORT).show();
                            //Log.i("DB_LOG",c.getInt(0)+" "+c.getString(1));
                        }

                    } catch (Exception e) {
                        //  Toast.makeText(SoruActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        // Log.e("DB_LOG",e.getMessage());
                        // Log.e("DB_LOG","Veritabanı oluşturulamadı veya kopyalanamadı !");
                    }
                    //.setText("a");
                    // harfEkle("a");
                }
            });
            flowYanitTumHarfler.addView(btn);

        }
    }

    public void soruGetir() {
        //Kelimeye ait kutuları üretir.
        //    soruHarfleriGetir(sorular.get(soru_indis).getKelime());
        //Orjinal kelimeyi de içerisine dahil edip karmaşık sırada harfleri flowLayout2
        //içerisine ekler.
        //  rastgeleHarfleriGetir(sorular.get(soru_indis).getKelime());

        //Bu uygulama paketi içerisinde drawable klasöründeki  araba.png
        // dosyasının referans adresi karşılığını öğrenmek istediğimiz zaman Identifier kavramına ihtiyaç duyarız.
        // int imgId=getResources().getIdentifier(
        //   sorular.get(soru_indis).getLogo_url(),
        //     "mipmap",
        //   this.getPackageName()
        //);
        //System.out.println("merhaba dünya"+imgId);
        //ivLogoPhoto.setImageResource(imgId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru);
        this.getSupportActionBar().hide();
        sorular = new ArrayList<>();
        /*
        int id, int seviye_id, String logo_url, String kelime, int puan, int cozuldu_mu
         */

      /*  sorular.add(new Sorular(
        1,1,"hp","HP",10,0
        ));

        sorular.add(new Sorular(
                2,1,"ibb","İBB",10,0
        ));

        sorular.add(new Sorular(
                3,1,"android","ANDROİD",10,0
        ));
*/
        ivBack = findViewById(R.id.ivBack);
        ivLogoPhoto = findViewById(R.id.ivLogoPhoto);
        ivGoToBack = findViewById(R.id.ivGoToBack);
        ivNext = findViewById(R.id.ivNext);
        tvTitle = findViewById(R.id.tvTitle);
        flowDogruYanitKutucuklari = findViewById(R.id.flowDogruYanitKutucuklari);
        flowYanitTumHarfler = findViewById(R.id.flowYanitTumHarfler);
        btnIpucu = findViewById(R.id.btnIpucu);
        btnTemizle = findViewById(R.id.btnTemizle);
        btnSoruyuAtla = findViewById(R.id.btnSoruyuAtla);
        btnTurkcesi=findViewById(R.id.btnTurkcesi);
        tvPuan = findViewById(R.id.tvPuan);
      /*  soruHarfleriGetir(sorular.get(0).getKelime());
        rastgeleHarfleriGetir(sorular.get(0).getKelime());*/
        //soruGetir();
        ivGoToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnivGeriDonme();

            }
        });
        btnTemizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Temizle();
            }
        });

        btnSoruyuAtla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                harfIndex++;

                //birbirine eşit ise hiçbir işlem yapma
                Log.i("YANIT", yeniYanit);
                Log.i("ESKİYANIT", sorular.get(soruIndex).getYanit());


                kacsoruvard = sonSoruyaGeldiMi();


                soruIndex++;
                if (kacsoruvard == soruIndex) {
                    Toast.makeText(SoruActivity.this, "Son Soru buydu", Toast.LENGTH_SHORT).show();
                    int arttirkategoriidyi = kategori.getId();
                    arttirkategoriidyi++;
                    db.execSQL("update Ayarlar set ayar_degeri='" + 1 + "' where ayar_adi='kategori_id_" + arttirkategoriidyi + "_index'");
                    db.execSQL("update Ayarlar set ayar_degeri='" + soruIndex + "' where ayar_adi='kategori_id_" + kategori.getId() + "_index'");
                    db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri-" + soruyuAtla + " where ayar_adi='kullanici_kredi'");

                    finish();

                } else {
                    harfIndex = 0;
                    yeniYanit = "";
                    kacinciHarfIndex = 0;

                    harfKutucuklariniKaldir();
                    dogruYanitKutucuklariGetir(sorular.get(soruIndex).getYanit().length());
                    resimleriGetir(soruIndex);
                    harfleriGetir(soruIndex);

                    db.execSQL("update Ayarlar set ayar_degeri='" + soruIndex + "' where ayar_adi='kategori_id_" + kategori.getId() + "_index'");
                    db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri-" + soruyuAtla + " where ayar_adi='kullanici_kredi'");
                    puanEkle();
                }

            }

        });
        btnIpucu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SoruActivity.this, ""+sorular.get(soruIndex).getYanit().length(), Toast.LENGTH_SHORT).show();
                //  Toast.makeText(SoruActivity.this, ""+kacinciHarfIndex, Toast.LENGTH_SHORT).show();
                //    Toast.makeText(SoruActivity.this, ""+sorular.get(soruIndex).getYanit().charAt(harfIndex), Toast.LENGTH_SHORT).show();
                //  Button btnIpucuyuEkle=findViewById(200+harfIndex);
                // btnIpucuyuEkle.setText(String.valueOf(sorular.get(soruIndex).getYanit().charAt(harfIndex)));
                //btnIpucuyuEkle.setText("z");
                // kacinciHarfIndex++;
                // harfIndex++;
                //  Toast.makeText(SoruActivity.this, ""+kacinciHarfIndex, Toast.LENGTH_SHORT).show();
                // Toast.makeText(SoruActivity.this, ""+harfIndex, Toast.LENGTH_SHORT).show();
                IpucunuKullan();
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (soru_indis < sorular.size() - 1) {
                    soru_indis += 1;
                    degerleriTemizle();
                    soruGetir();
                }

            Karşımıza yeni soru getirir
            yeni soru gelmeden önce de değerleri temizleyerek, bir önceki sorudaki
            butonları kaldırdık.  */
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (soru_indis > 0) {
                    soru_indis -= 1;
                    degerleriTemizle();
                    soruGetir();
                }*/
            }

        });
        kategori = (Kategori) getIntent().getSerializableExtra("kategori");
        try {
            databaseHelper = new DatabaseHelper(getApplicationContext());
            db = databaseHelper.getWritableDatabase();
            Cursor cr = db.rawQuery("select*from Soru where kategori_id=" + kategori.getId(), null);
            while (cr.moveToNext()) {
                int soru_id = cr.getInt(cr.getColumnIndex("soru_id"));
                int kategori_id = cr.getInt(cr.getColumnIndex("kategori_id"));
                String resim1 = cr.getString(cr.getColumnIndex("resim1"));
                String yanit = cr.getString(cr.getColumnIndex("yanit"));
                String yanit_harfler = cr.getString(cr.getColumnIndex("yanit_harfler"));
                String soru = cr.getString(cr.getColumnIndex("soru"));
                sorular.add(new Sorular(
                        soru_id, kategori_id, resim1, yanit, yanit_harfler,soru
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //  int kacsoruvard=sonSoruyaGeldiMi();
        soruIndex = dbKategoriIndexCagir();
        //Toast.makeText(this, "soruIndex"+soruIndex, Toast.LENGTH_SHORT).show();
        harfleriGetir(soruIndex);
        resimleriGetir(soruIndex);
        dogruYanitKutucuklariGetir(sorular.get(soruIndex).getYanit().length());
        puanEkle();


    }

    public void puanEkle() {
        Cursor c = db.rawQuery("select*from Ayarlar where ayar_adi='kullanici_kredi'", null);
        while (c.moveToNext()) {
            puan = Integer.parseInt(c.getString(c.getColumnIndex("ayar_degeri")));
            tvPuan.setText("" + puan);
        }
    }

    public int sonSoruyaGeldiMi() {
        int kacsoruvar = 0;

        Cursor cr = db.rawQuery("select*from Soru where kategori_id=" + kategori.getId(), null);

        while (cr.moveToNext()) {
            kacsoruvar++;
        }
        //Toast.makeText(this, "" + kacsoruvar, Toast.LENGTH_SHORT).show();
        return kacsoruvar;

    }

    public void resimleriGetir(int indexId) {
        //Toast.makeText(this, ""+indexId, Toast.LENGTH_SHORT).show();
       // Glide.with(getApplicationContext()).load(sorular.get(indexId).getResim1()).into(ivLogoPhoto);
        btnTurkcesi.setText(""+sorular.get(indexId).getSoru());
        tvTitle.setText("Soru:"+sorular.get(indexId).getSoru_id());


    }

    public String harfleriKaristir(String input) {
        //Shuffle - Sıralamayı karıştıran algoritma
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    public void harfleriGetir(int soruIndex) {
        Log.i("SORUINDEX", String.valueOf(soruIndex));
        sorular.get(soruIndex).setYanit_harfler(harfleriKaristir(sorular.get(soruIndex).getYanit_harfler()));

        for (int i = 0; i < sorular.get(soruIndex).getYanit_harfler().length(); i++) {
            Button btn = new Button(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            params.setMargins(4, 4, 4, 4);
            btn.setId(100 + i);
            btn.setOnClickListener(this);
            btn.setTextSize(11f);
          btn.setBackgroundColor(getResources().getColor(R.color.harfKutusuDolu));
            btn.setLayoutParams(params);
            btn.setText("" + sorular.get(soruIndex).getYanit_harfler().charAt(i));
            flowYanitTumHarfler.addView(btn);
        }
    }

    public void harfKutucuklariniKaldir() {
        flowDogruYanitKutucuklari.removeAllViews();
        flowYanitTumHarfler.removeAllViews();
    }

    public boolean verilenYanitDogruMu() {
        boolean bool = false;
        String verilneYanitt=yeniYanit.toUpperCase();
        if (sorular.get(soruIndex).getYanit().equals(verilneYanitt)) {
            bool = true;
        }

        return bool;
    }

    public void dogruYanitKutucuklariGetir(int yanitUzunlugu) {

        for (int i = 0; i < yanitUzunlugu; i++) {
            Button btn = new Button(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, 70);
            params.setMargins(2, 2, 2, 2);
            btn.setId(200 + i);
            btn.setTextSize(10f);
            btn.setBackgroundColor(getResources().getColor(R.color.harfKutusuDolu));
          //  btn.setTextColor(getResources().getColor(R.color.harfYazi));
            btn.setLayoutParams(params);
            btn.setText("");
            flowDogruYanitKutucuklari.addView(btn);
            flowDogruYanitKutucuklari.setGravity(Gravity.CENTER);
        }


    }

    public int dbKategoriIndexCagir() {
        Cursor c = db.rawQuery("select*from Ayarlar where ayar_adi='kategori_id_" + kategori.getId() + "_index'", null);
        int deger = 0;
        while (c.moveToNext()) {
            deger = Integer.parseInt(c.getString(c.getColumnIndex("ayar_degeri")));
        }
        // Toast.makeText(this, "deger"+deger, Toast.LENGTH_SHORT).show();

        return deger;
    }

    public void IpucunuKullan() {
//        Toast.makeText(this, ""+String.valueOf(yeniYanit.charAt(harfIndex)), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Bir sonraki harf=" + String.valueOf(sorular.get(soruIndex).getYanit().charAt(harfIndex)), Toast.LENGTH_SHORT).show();
        db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri-" + ipucuKullanma + " where ayar_adi='kullanici_kredi'");
        puanEkle();
        // Button button = findViewById(200+harfIndex);
        //button.setText(String.valueOf(yeniYanit.charAt(harfIndex)));
        //button.setText(String.valueOf(String.valueOf(sorular.get(soruIndex).getYanit().charAt(harfIndex))));
        // kacinciHarfIndex++;
        // harfIndex++;
        //soruIndex++;
    }

    public void sonSoruyaGelmeyiKontrolEt() {

        kacsoruvard = sonSoruyaGeldiMi();


        soruIndex++;
        if (kacsoruvard == soruIndex) {
            Toast.makeText(this, "Son Soru buydu", Toast.LENGTH_SHORT).show();
            int arttirkategoriidyi = kategori.getId();
            arttirkategoriidyi++;
            //  Toast.makeText(this, ""+arttirkategoriidyi, Toast.LENGTH_SHORT).show();
            db.execSQL("update Ayarlar set ayar_degeri='" + 1 + "' where ayar_adi='kategori_id_" + arttirkategoriidyi + "_index'");
            db.execSQL("update Ayarlar set ayar_degeri='" + soruIndex + "' where ayar_adi='kategori_id_" + kategori.getId() + "_index'");
            // kacinciHarfIndex++;
            db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri+" + kazanilanPuan + " where ayar_adi='kullanici_kredi'");
            finish();
        }

    }

    public void btnivGeriDonme() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SoruActivity.this);
        builder.setTitle("Menüye Dön");
        builder.setMessage("Sorudan çıkma 50 puandır.Sorudan çıkmak istediğinize emin misiniz.!");
        builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

            }
        });


        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Tamam butonuna basılınca yapılacaklar
                db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri-" + soruyuAtla + " where ayar_adi='kullanici_kredi'");

                finish();

            }
        });
        builder.show();
    }

    public void Temizle() {
        harfIndex = 0;
        yeniYanit = "";
        kacinciHarfIndex = 0;

        harfKutucuklariniKaldir();
        dogruYanitKutucuklariGetir(sorular.get(soruIndex).getYanit().length());
        resimleriGetir(soruIndex);
        harfleriGetir(soruIndex);
    }


    @Override
    public void onClick(View view) {


        if (sorular.get(soruIndex).getYanit().length() != yeniYanit.length()) {

            /*
            Sorunun yanıtındaki karakter uzunluğu ile yeniYanıt değişken uzunluğu eşit
            değil ise, işlemi yap
             */

            Button b = (Button) view;
            Button button = findViewById(200 + harfIndex);
            yeniYanit += b.getText();

            button.setText("" + yeniYanit.charAt(harfIndex));
            // kacinciHarfIndex++;
            harfIndex++;
            //  Toast.makeText(SoruActivity.this, ""+kacinciHarfIndex, Toast.LENGTH_SHORT).show();
            // Toast.makeText(SoruActivity.this, ""+harfIndex, Toast.LENGTH_SHORT).show();
            if (sorular.get(soruIndex).getYanit().length() == yeniYanit.length()) {
                //birbirine eşit ise hiçbir işlem yapma
                Log.i("YANIT", yeniYanit);
                Log.i("ESKİYANIT", sorular.get(soruIndex).getYanit());
                if (verilenYanitDogruMu()) {

                    kacsoruvard = sonSoruyaGeldiMi();


                    soruIndex++;
                    if (kacsoruvard == soruIndex) {
                        Toast.makeText(this, "Son Soru buydu", Toast.LENGTH_SHORT).show();
                        int arttirkategoriidyi = kategori.getId();
                        arttirkategoriidyi++;
                        //  Toast.makeText(this, ""+arttirkategoriidyi, Toast.LENGTH_SHORT).show();
                        db.execSQL("update Ayarlar set ayar_degeri='" + 1 + "' where ayar_adi='kategori_id_" + arttirkategoriidyi + "_index'");
                        db.execSQL("update Ayarlar set ayar_degeri='" + soruIndex + "' where ayar_adi='kategori_id_" + kategori.getId() + "_index'");
                        // kacinciHarfIndex++;
                        db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri+" + kazanilanPuan + " where ayar_adi='kullanici_kredi'");

                        finish();

                    } else {
                        harfIndex = 0;
                        yeniYanit = "";
                        kacinciHarfIndex = 0;

                        harfKutucuklariniKaldir();
                        dogruYanitKutucuklariGetir(sorular.get(soruIndex).getYanit().length());
                        resimleriGetir(soruIndex);
                        harfleriGetir(soruIndex);

                        db.execSQL("update Ayarlar set ayar_degeri='" + soruIndex + "' where ayar_adi='kategori_id_" + kategori.getId() + "_index'");
                        db.execSQL("update Ayarlar set ayar_degeri=ayar_degeri+" + kazanilanPuan + " where ayar_adi='kullanici_kredi'");
                        puanEkle();
                    }

                } else {
                    Toast.makeText(this, "Yanlış Cevap", Toast.LENGTH_SHORT).show();
                    finish();

                }
                //puan verme yada puan kırma işlemini yap
                //  if(verilenYanitDogruMu()){
                //   soruIndex++;
                // harfIndex=0;
                //yeniYanit = "";
                //resimleriGetir(soruIndex);
                //   krediIslemi("arttir",10);
                // dbKategoriIndexGuncelle(soruIndex);
                // Log.i("TEST","YANIT DOĞRU");
                //tvSoruNo.setText(""+dbKategoriIndexCagir());
                // sureDurdur();
                // sureSay();
                //       harfKutucuklariniKaldir();
                //    dogruYanitKutucuklariGetir(sorular.get(soruIndex).getYanit().length());
                //  harfleriGetir(soruIndex);
                // harfleriGetir(sorular.get(soruIndex).getYanit().length()); eski hali bu şekildeydi harfleri getirmemiz gereken sorunun indexi
                // yerine yanıt uzunluğunu parametre olarak yollamışız

                // }else{
                //krediIslemi("azalt",10);
                //Log.i("TEST","YANIT YANLIŞ");
                // }
                // tvKredi.setText(""+krediyiDon());

            }

        }
        //  Toast.makeText(this, "1"+harfIndex, Toast.LENGTH_SHORT).show();
        //  Toast.makeText(this, "2"+kacinciHarfIndex, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            btnivGeriDonme();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}