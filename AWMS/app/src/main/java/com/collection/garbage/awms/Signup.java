package com.collection.garbage.awms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,password,address,pincode;
    Button signup;
    TextView already_registered;
    RadioGroup gender;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        address = (EditText)findViewById(R.id.address);
        pincode = (EditText)findViewById(R.id.pincode);

        gender = (RadioGroup)findViewById(R.id.gender);

        already_registered = (TextView)findViewById(R.id.already_registered);

        signup = (Button)findViewById(R.id.signup);




        databaseReference= FirebaseDatabase.getInstance().getReference("signup");
    }

    @Override
    public void onClick(View v) {

        if(v == already_registered)
        {
            Log.d("test","aa gya ");
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
       if(v == signup)
        {
            Log.d("test","aa gya ");
            registerUser();
        }

    }

    private void registerUser() {

        String mName,mEmail,mPassword,mGender,mAddress,mPincode;

        mName = name.getText().toString();
        mEmail = email.getText().toString().trim();
        mPassword = password.getText().toString();
        mAddress = address.getText().toString();
        mPincode = pincode.getText().toString();

        Log.d("test","aa gya ");
        if(TextUtils.isEmpty(mName))
        {
            name.setError("Please Enter Name");
            return;
        }
        if(TextUtils.isEmpty(mEmail))
        {
            email.setError("Plase Enter Email");
            return;
        }
        if(TextUtils.isEmpty(mPassword))
        {
            password.setError("Please Enter password");
            return;
        }
        if(TextUtils.isEmpty(mAddress))
        {
            address.setError("Plase Enter Address");
            return;
        }
        if(TextUtils.isEmpty(mPincode))
        {
         pincode.setError("Plase Enter Pincode");
         return;
        }

        if(gender.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            // get selected radio button from radioGroup
            int selectedId = gender.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);
            Toast.makeText(getApplicationContext(), selectedRadioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
            mGender = selectedRadioButton.getText().toString();
        }


        Log.d("test","phir aa gya ");
        /**
         *  Cursor Is Here When We Sumit all the details correctly so that now we need to stored
         *  These values.
         */







        Registration registration = new Registration(mName,mEmail,mPassword,mGender,mAddress,mPincode);

        String id = databaseReference.push().getKey();

        Task task = databaseReference.child(id).setValue(registration);

        if(!task.equals(null))
        {

            Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_LONG).show();

            name.setText(null);
            email.setText(null);
            password.setText(null);
            gender.clearCheck();
            address.setText(null);
            pincode.setText(null);
        }




    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,Login.class));
        super.onBackPressed();
    }
}

