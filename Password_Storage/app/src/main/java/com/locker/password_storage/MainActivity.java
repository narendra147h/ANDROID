package com.locker.password_storage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {


    private static final String TABLE_NAME = "password_storage_registration";
    private static final String NAME = "uname";
    private static final String EMAIL = "uemail";
    private static final String PASSWORD = "upassword";

    EditText name, email, password, cpassword;
    Button register;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.cpassword);
        register = (Button) findViewById(R.id.register);


        status=(TextView)findViewById(R.id.status);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String upassword = password.getText().toString();
                String ucpassword = cpassword.getText().toString();



                if(fieldValidation(uname,uemail,upassword))
                {



                    if (upassword.equals(ucpassword)) {


                        Registration registration = new Registration(getApplicationContext());

                        SQLiteDatabase database = registration.getWritableDatabase();


                        //Encrypt the password

                        try {
                            String encData= encrypt("keykey".getBytes("UTF-16LE"),(ucpassword).getBytes("UTF-16LE"));
                            ContentValues values = new ContentValues();
                            values.put(NAME, uname);
                            values.put(EMAIL, uemail);
                            values.put(PASSWORD,encData);

                            try {
                                Long row = database.insert(TABLE_NAME, null, values);
                                Log.d("row updated ", String.valueOf(row));
                                name.setText("");
                                email.setText("");
                                password.setText("");
                                cpassword.setText("");
                                Toast.makeText(getApplicationContext(), "Register Successfully inserted ", Toast.LENGTH_LONG).show();
                                register.setVisibility(View.GONE);


                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Data Not inserted ", Toast.LENGTH_LONG).show();
                            }




                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Data Not inserted ", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_LONG).show();
                    }

                }





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

    //Email Validator

    public boolean emailValidator(final String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Password Validator
    public  boolean isPasswordValidMethod(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    //Name Validator

    public  boolean isNameValidMethod(final String name) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "[a-zA-Z][a-zA-Z ]*";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(name);

        return matcher.matches();

    }


    public boolean fieldValidation(String name,String email,String password)
    {
        int flag=0;
        if(isNameValidMethod(name))
        {
            if(emailValidator(email))
            {
                if(isPasswordValidMethod(password))
                {
                    flag=1;

                }
                else
                {

                    status.setText("Please Enter valid Password, it have atleast alphanumeric and 1 special character");

                }

            }
            else
            {
                status.setText("Please Enter valid Email");
            }

        }
        else
        {
            status.setText("Please Enter valid Name");
        }
        if(flag==1)
            return true;
        else
            return false;

    }
    private static String encrypt(byte[] key, byte[] clear) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digestOfPassword = md.digest(key);

        SecretKeySpec skeySpec = new SecretKeySpec(digestOfPassword, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return Base64.encodeToString(encrypted,Base64.DEFAULT);
    }

}
