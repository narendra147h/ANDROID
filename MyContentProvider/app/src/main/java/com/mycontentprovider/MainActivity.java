package com.mycontentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText id,desig;
    Button submit,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id=(EditText)findViewById(R.id.id);
        desig=(EditText)findViewById(R.id.desig);
        submit=(Button)findViewById(R.id.submit);
        show=(Button)findViewById(R.id.show);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Emp_Id=Integer.parseInt(id.getText().toString());
                String Emp_desig=desig.getText().toString();
                ContentValues values=new ContentValues();
                values.put(MyContent.EMP_ID,Emp_Id);
                values.put(MyContent.EMP_DESIG,Emp_desig);
                Uri uri=getContentResolver().insert(MyContent.CONTENT_URI,values);


            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent=new Intent(getApplicationContext(),MyShowList.class);
                startActivity(intent);


            }
        });



    }
}
