package com.mysimplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylist=(ListView)findViewById(R.id.mylist);

        //After that we create an Array

        final String[] list={"c","c++","Java","Android","Python","Scala","C#","JavaScript"};

        //After that we need a one Adapter where we put my Array


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, Arrays.asList(list));

        //Then after we set adpater to list view

        mylist.setAdapter(arrayAdapter);

        //If you want ..if you clicked on listview then it's respond then you have to create one listner


        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"You clicked: "+list[i],Toast.LENGTH_SHORT).show();
            }
        });

    }
}
