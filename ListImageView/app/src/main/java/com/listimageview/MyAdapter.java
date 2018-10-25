package com.listimageview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.listimageview.R;

/**
 * Created by adpr270759 on 12-03-2018.
 */

public class MyAdapter extends ArrayAdapter<String>
{
    private final Activity context;
    private final String[] Prognames;
    private final Integer[] progimage;

    public MyAdapter(Activity context, String[] prognames, Integer[] progimage) {

        //Call the Super class Contructer

        super(context, R.layout.activity_list_view_image,prognames);
        this.context = context;
        this.Prognames = prognames;
        this.progimage = progimage;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_list_view_image,null,true);
        TextView txttitle=(TextView) rowView.findViewById(R.id.mytext);
        ImageView img=(ImageView)rowView.findViewById(R.id.myimage);


        //After fecthing values put the values

        txttitle.setText(Prognames[position]);
        img.setImageResource(progimage[position]);


        return rowView;
    }
}
