package com.example.res_project;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {

    Context context;
    MyData [] data;

    MyAdapter(Context context, MyData[] data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length * 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.menu_list, viewGroup, false);
        }
        i %= data.length;
        ImageView image = view.findViewById(R.id.menuImg);
        image.setImageResource(data[i].menu);

        return view;
    }

}
