package com.example.ahmet.turkishwords.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.ahmet.turkishwords.Model.menu;
import com.example.ahmet.turkishwords.R;

import java.util.ArrayList;

public class menuAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<menu> menuler;

    public menuAdapter() {
    }
    public menuAdapter(Context context,ArrayList<menu> menuler){
        this.context = context;
        this.menuler = menuler;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menuler.size();
    }

    @Override
    public Object getItem(int i) {
        return menuler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=layoutInflater.inflate(R.layout.menugoruntusu,null);
        ImageView ivKategoriResim = v.findViewById(R.id.ivMenuResim);
        TextView tvKategoriAd = v.findViewById(R.id.ivMenuAD);
        // ivKategoriResim.setImageDrawable(menuler.get(i).getResim());
       Glide.with(context).load(menuler.get(i).getResim()).into(ivKategoriResim);
        tvKategoriAd.setText(menuler.get(i).getAd());
        return v;
    }
}
