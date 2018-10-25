package myfirst.com.assignment_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Next1 extends AppCompatActivity {

    Button next1;
    TextView status,s1;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next1);

        display();

        s1=(TextView)findViewById(R.id.s1);
        next1=(Button)findViewById(R.id.next1);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gonext();
            }
        });
    }
    public void gonext()
    {
        //For setting Alert Dialog Boxes

        AlertDialog.Builder builder=new AlertDialog.Builder(Next1.this);
        builder.setMessage("Are you Sure !")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        gonextsure();

                    }
                })
                .setNegativeButton("Cancel",null)
                .setCancelable(false);

        AlertDialog alert=builder.create();
        alert.show();




    }
    public void gonextsure()
    {

        Intent intent=new Intent(this.getApplicationContext(),Next2.class);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        if(requestCode==10)
        {
            if(resultCode == RESULT_OK){
               // int rt=data.getIntExtra("rating",10);
                int strEditText = data.getIntExtra("rating",10);
                Log.d("Aaditya",String.valueOf(strEditText));
                s1=(TextView)findViewById(R.id.s1);
                s1.setText("Your App Rating is "+strEditText);

            }
            //status1.setText((int)data.getFloatExtra("Rating",0));
        }
    }

    public void display()
    {
        status=(TextView)findViewById(R.id.status);
        sharedPreferences=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);

        String uname=sharedPreferences.getString("username","");
        status.setText("Hello "+uname);

    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Next1.this);
        builder.setTitle("Are you want to Exit !")
                .setMessage("Are you Sure")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                })
                .setNegativeButton("cancel",null)
                .setCancelable(false);

        AlertDialog dialog=builder.create();
        dialog.show();

    }
}
