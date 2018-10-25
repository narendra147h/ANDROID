package com.listimageview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MyListView extends AppCompatActivity {


    ListView mylist;
    Context context;
    //ArrayList arrayList;


    //Craete arrays of which you want to show

    public static String[] progname={"c","c++","java","javascript","python","c#","php","scala"};

    public static Integer[] progimages={


            R.drawable.cpp,
            R.drawable.cpp,
            R.drawable.cpp,
            R.drawable.js,
            R.drawable.js,
            R.drawable.js,
            R.drawable.php,
            R.drawable.php,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);

        MyAdapter myAdapter=new MyAdapter(this,progname,progimages);

        mylist=(ListView)findViewById(R.id.mylist);

        mylist.setAdapter(myAdapter);

    }
}
