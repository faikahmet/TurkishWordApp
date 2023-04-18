package com.example.ahmet.turkishwords.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmet.turkishwords.Model.Kategori;
import com.example.ahmet.turkishwords.R;


import java.util.ArrayList;

public class AdapterKategori extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Kategori> kategoriler;
    public AdapterKategori(Context context, ArrayList<Kategori> kategoriler){
        this.context = context;
        this.kategoriler = kategoriler;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return kategoriler.size();
    }

    @Override
    public Object getItem(int i) {
        return kategoriler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View v=layoutInflater.inflate(R.layout.kategori_goruntusu,null);
        ImageView ivKategoriResim = v.findViewById(R.id.ivKategoriResim);
        TextView tvKategoriAd = v.findViewById(R.id.tvKategoriAd);
        ivKategoriResim.setImageResource(Integer.parseInt(kategoriler.get(i).getResim()));
        //Glide.with(context).load(kategoriler.get(i).getResim()).into(ivKategoriResim);
        tvKategoriAd.setText(kategoriler.get(i).getAd());
        return v;
    }
}
