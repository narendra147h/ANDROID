package com.mycontentprovider;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyShowList extends AppCompatActivity {

    ListView mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_show_list);

        mylist=(ListView)findViewById(R.id.mylist);


        String projection[]={"emp_id","emp_desig"};
        Cursor c=getContentResolver().query(MyContent.CONTENT_URI,projection,null,null,null);

       ArrayList arrayList=new ArrayList();

       arrayList.add("EMPLOYEE_ID "+"    "+" DESIGNATON");


       c.moveToFirst();

       if(c!=null)
       {
           while(c.moveToNext())
           {
               arrayList.add(c.getString(c.getColumnIndex("emp_id"))+"           "+c.getString(c.getColumnIndex("emp_desig")));
           }


       }


        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);

       mylist.setAdapter(adapter);




    }
}
