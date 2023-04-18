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

public class cumleActivity extends AppCompatActivity {
    /*

     Ses ile ilgili işlemler
      */
    private TextToSpeech mTTs;
    /*
       Ses ile ilgili işlemler
        */
    LinearLayout linearLayout;
    TextView cumleler_cumle,cumle_sayisi,tvGeriCumleActivity;
    ImageView cumle_ses,ivGoToBackCumle,img_cumle_seslen;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button cumlelerBtn;
    int soruId=1;
    int arayuz=0;
    String trCumle="";
    String ingCumle="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumle);
        //this.getSupportActionBar().hide();
        getSupportActionBar().hide();
        /*
        Ses ile ilgili işlemler
         */

        mTTs=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    int result=mTTs.setLanguage(Locale.ENGLISH);
                    if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(cumleActivity.this,"Language not supported",Toast.LENGTH_SHORT).show();
                    }else {
                    }
                }
                else
                {
                    Toast.makeText(cumleActivity.this, "Initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        Ses ile ilgili işlemler
         */
        linearLayout=findViewById(R.id.linearCumle);
        cumleler_cumle=findViewById(R.id.cumleler_cumle);
        cumle_ses=findViewById(R.id.cumle_ses);
        cumle_sayisi=findViewById(R.id.tvcumleSayisi);
        cumlelerBtn=findViewById(R.id.cumlelerBtn);
        ivGoToBackCumle=findViewById(R.id.ivGoToBackCumle);
        tvGeriCumleActivity=findViewById(R.id.tvGeriCumleActivity);
        img_cumle_seslen=findViewById(R.id.img_cumle_seslen);
        dbSoruGetir();
        cumle_ses.setOnClickListener(new View.OnClickListener() {
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
        tvGeriCumleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
        ivGoToBackCumle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
        cumlelerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soruId++;
                cumle_sayisi.setText("Cümle:"+soruId);
                //  Toast.makeText(cumleActivity.this, ""+soruId, Toast.LENGTH_SHORT).show();
                dbSoruGetir();
            }
        });
        img_cumle_seslen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }
    public void arayuzDegistir(){

        if (arayuz==0){
            cumleler_cumle.setText(ingCumle);
            arayuz=1;
        }
        else if(arayuz==1){
            cumleler_cumle.setText(trCumle);
            arayuz=0;
        }
    }
    public void dbSoruGetir() {
        // arayuz=1;
        try{
            databaseHelper = new DatabaseHelper(getApplicationContext());
            databaseHelper.createDatabase();
            db = databaseHelper.getReadableDatabase();
        }
        catch (Exception e){
            System.out.println("Hata db: "+e);
        }
        Cursor c = db.rawQuery("select*from cumle where id=" +soruId , null);
        //Cursor c = db.rawQuery("select*from kelimeler where id="+1, null);
        while (c.moveToNext()) {

            trCumle=c.getString(c.getColumnIndex("tr_cumle"));


            ingCumle= c.getString(c.getColumnIndex("ing_cumle"));
            cumleler_cumle.setText(trCumle);



        }
        // Toast.makeText(this, "deger"+deger, Toast.LENGTH_SHORT).show();
    }
    /*
ses ile ilgili işlemler
 */
    private void speak(){
        String text=  ingCumle;               //mEditText.getText().toString();
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
