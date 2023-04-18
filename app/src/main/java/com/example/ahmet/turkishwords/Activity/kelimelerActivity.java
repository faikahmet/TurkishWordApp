package com.example.ahmet.turkishwords.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.turkishwords.Database.DatabaseHelper;
import com.example.ahmet.turkishwords.R;

import java.util.Locale;

public class kelimelerActivity extends AppCompatActivity  {
    /*

    Ses ile ilgili işlemler
     */
    private TextToSpeech mTTs;
    /*
       Ses ile ilgili işlemler
        */
    LinearLayout linearLayout;
    TextView kelimeler_Kelime,tvGeriKelimelerActivity,tvkelimeSayisi;
    ImageView kelime_ses,ivGoToBackKelimelerKelime,img_seslen;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button kelimelerBtn;
    int soruId=1;
    int arayuz=1;
    String trKelime="";
    String IngKelime="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelimeler);
        /*
        Ses ile ilgili işlemler
         */

        mTTs=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    int result=mTTs.setLanguage(Locale.ENGLISH);
                    if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(kelimelerActivity.this,"Language not supported",Toast.LENGTH_SHORT).show();
                    }else {
                    }
                }
                else
                {
                    Toast.makeText(kelimelerActivity.this, "Initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        Ses ile ilgili işlemler
         */
        getSupportActionBar().hide();
        img_seslen=findViewById(R.id.img_seslen);
        tvkelimeSayisi=findViewById(R.id.tvkelimeSayisi);
        linearLayout=findViewById(R.id.linearKelime);
        kelimeler_Kelime=findViewById(R.id.kelimeler_Kelime);
        kelime_ses=findViewById(R.id.kelime_ses);
        kelimelerBtn=findViewById(R.id.kelimelerBtn);
        tvGeriKelimelerActivity=findViewById(R.id.tvGeriKelimelerActivity);
        ivGoToBackKelimelerKelime=findViewById(R.id.ivGoToBackKelimelerKelime);
        dbSoruGetir();
        kelime_ses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arayuzDegistir();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arayuzDegistir();
            }
        });
        tvGeriKelimelerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
        ivGoToBackKelimelerKelime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
        img_seslen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
        kelimelerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soruId++;
                tvkelimeSayisi.setText("Kelime:"+soruId);
                //Toast.makeText(kelimelerActivity.this, ""+soruId, Toast.LENGTH_SHORT).show();
                dbSoruGetir();
            }
        });
    }
    public void arayuzDegistir(){

        if (arayuz==0){
            kelimeler_Kelime.setText(trKelime);
            arayuz=1;
        }
        else if(arayuz==1){
            kelimeler_Kelime.setText(IngKelime);
            arayuz=0;
        }
    }



    public void dbSoruGetir() {

        try{
            databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.createDatabase();
        db = databaseHelper.getReadableDatabase();
        }
                                        catch (Exception e){
                                System.out.println("Hata db: "+e);
}
        Cursor c = db.rawQuery("select*from kelimeler where id=" +soruId , null);
        while (c.moveToNext()) {

                trKelime=c.getString(c.getColumnIndex("trKelime"));


                   IngKelime= c.getString(c.getColumnIndex("IngKelime"));
            kelimeler_Kelime.setText(trKelime);



        }
        // Toast.makeText(this, "deger"+deger, Toast.LENGTH_SHORT).show();
    }
/*
ses ile ilgili işlemler
 */
private void speak(){
    String text=  IngKelime;               //mEditText.getText().toString();
    float pitch=(float) 1;
    if(pitch<0.1)pitch=0.1f;
    float speed=(float)1;
    if (speed<0.1)speed=0.1f;
    mTTs.setPitch(pitch);
    mTTs.setSpeechRate(speed);
    mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);
}

    @Override
    protected void onDestroy() {
        if (mTTs!=null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }

}
