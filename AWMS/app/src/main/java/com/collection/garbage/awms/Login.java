package com.collection.garbage.awms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login,register;


    ProgressDialog mProgressDialog;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /**
         * Initialize Firebase Auth
         */


        mProgressDialog=new ProgressDialog(this);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);

        databaseReference= FirebaseDatabase.getInstance().getReference("signup");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAuthentication();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });




    }

    private void loginAuthentication() {

        final String mUsername=username.getText().toString();
        final String mPassword=password.getText().toString();

        if(TextUtils.isEmpty(mUsername))
        {
            username.setError("Please Enter Email");
            return;

        }
        if(TextUtils.isEmpty(mPassword))
        {
            password.setError("Please Enter Password");
            return;
        }
        /**
         * If Everything is fine then we go for sign in
         */
        mProgressDialog.setMessage("Sign in... ");
        mProgressDialog.show();

        /**
         * Now here we Write all The logic related to fetch data and validate
         * user is registered or not ?
         */

        databaseReference.addValueEventListener(new ValueEventListener() {


            int flag=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot user : dataSnapshot.getChildren())
                {
                    if(user.exists())
                    {
                        Registration registration = user.getValue(Registration.class);
                        if(registration.getEmail().equals(mUsername) && registration.getPassword().equals(mPassword))
                        {
                            flag=1;
                            Intent intent=new Intent(Login.this,Profile.class);
                            intent.putExtra("Name",registration.getName());
                            startActivity(intent);
                            username.setText(null);
                            password.setText(null);
                            mProgressDialog.dismiss();

                        }
                    }
                }


                    if(flag==0)
                    Toast.makeText(getApplicationContext(),"Please Check Email and Password",Toast.LENGTH_LONG).show();

                    mProgressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.about)
            Toast.makeText(getApplicationContext(), "About us Clicked", Toast.LENGTH_SHORT).show();

        return true;

    }
    public void onBackPressed() {

        startActivity(new Intent(this,Login.class));
        super.onBackPressed();
    }

}

