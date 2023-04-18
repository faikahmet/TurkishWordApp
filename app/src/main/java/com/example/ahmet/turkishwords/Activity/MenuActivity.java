package com.example.ahmet.turkishwords.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.ahmet.turkishwords.Adapter.menuAdapter;
import com.example.ahmet.turkishwords.Model.menu;
import com.example.ahmet.turkishwords.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    GridView gridviewMenu;
    ArrayList<menu> menuler = new ArrayList<>();
    menuAdapter menuAdapter1;
    menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        gridviewMenu=findViewById(R.id.gridviewMenu);
        menuler=new ArrayList<>();
        menuler.add(new menu(
                1,"Kelimeler","https://www.maxpixel.net/static/photo/1x/Study-Lapis-Write-School-Teach-Information-2866132.png"
        ));
        menuler.add(new menu(
                2,"Cümle Kalıpları","https://www.nicepng.com/png/full/200-2002605_brochure-.png"
        ));
        menuler.add(new menu(
                3,"Alıştırma","https://cdn.pixabay.com/photo/2017/02/11/22/38/quiz-2058883_960_720.png"
        ));
        /*menuler.add(new menu(
                4,"Bilgi","https://upload.wikimedia.org/wikipedia/commons/a/a5/Messagebox_info.svg"
        ));*/
        menuAdapter1=new menuAdapter(getApplicationContext(),menuler);
        gridviewMenu.setAdapter(menuAdapter1);
        gridviewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    Intent intent = new Intent(getApplicationContext(), kelimelerActivity.class);
                    startActivity(intent);
                }
                else if(i==1){
                    Intent intent = new Intent(getApplicationContext(), cumleActivity.class);
                    startActivity(intent);
                }
                else if(i==2){
                    Intent intent = new Intent(getApplicationContext(), KategoriActivity.class);
                    startActivity(intent);
                }
                /*else if(i==3){
                    Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                    startActivity(intent);
                }*/
                else{
                   // Toast.makeText(MenuActivity.this, "Hİçbiri", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
