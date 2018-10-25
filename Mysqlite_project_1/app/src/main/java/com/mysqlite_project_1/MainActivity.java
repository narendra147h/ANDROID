package com.mysqlite_project_1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Name,UserName,Password,Phone;
    Button Register,Show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=(EditText)findViewById(R.id.Name);
        UserName=(EditText)findViewById(R.id.UserName);
        Password=(EditText)findViewById(R.id.Password);
        Phone=(EditText)findViewById(R.id.Phone);


        Register=(Button)findViewById(R.id.Register);

        Show=(Button)findViewById(R.id.Show);


            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                     String name=Name.getText().toString();
                     String uname=UserName.getText().toString();
                     String password=Password.getText().toString();
                     String phone=Phone.getText().toString();


                    if(name!=null && uname!=null && password!=null && phone!=null)
                    {
                        Mysqlhelper mysqlhelper=new Mysqlhelper(getApplicationContext());
                        SQLiteDatabase database=mysqlhelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("name",name);
                        values.put("username",uname);
                        values.put("password",password);
                        values.put("phone",phone);
                        long row=database.insert("registration",null,values);
                        Log.d("row updated",String.valueOf(row));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please fill all Details",Toast.LENGTH_SHORT);
                    }

                }
            });

            Show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i=new Intent(getApplicationContext(),ListViewActivity.class);
                    startActivity(i);
                }
            });











    }
}
