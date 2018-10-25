package com.mysqlite_project_1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends AppCompatActivity {

    ListView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        view=(ListView)findViewById(R.id.list);

        Mysqlhelper mysqlhelper=new Mysqlhelper(getApplicationContext());

        SQLiteDatabase database=mysqlhelper.getReadableDatabase();

        String projection[]={"name","username","password","phone"};
        Cursor cursor=database.query("registration",projection,null,null,null,null,null);

        //int i=0;
        ArrayList<String>  arrayList=new ArrayList<>();
       /* if(cursor.moveToFirst())
        {
            do {
                arrayList.add(cursor.getString(i));
                i++;
            }while (cursor.moveToNext());

        }*/

        cursor.moveToFirst();
        if (cursor != null) {
            do {
                for (int i = 0; i < cursor.getColumnCount(); i++) {

                    arrayList.add(cursor.getString(i));
                }
            }while (cursor.moveToNext());
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        view.setAdapter(arrayAdapter);



    }
}
