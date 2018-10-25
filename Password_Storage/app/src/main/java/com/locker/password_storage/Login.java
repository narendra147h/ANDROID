package com.locker.password_storage;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;

public class Login extends AppCompatActivity {

    private static final String TABLE_NAME="password_storage_registration";
    private static final int VERSION=2;
    private static final String ID="user_id";
    private static final String NAME="uname";
    private static final String EMAIL="uemail";
    private static  final String PASSWORD="upassword";

    EditText email,password;
    Button login,signup,forget;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        status=(TextView)findViewById(R.id.status);

        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        forget=(Button)findViewById(R.id.forget);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail=email.getText().toString();
                String upassword=password.getText().toString();

                Registration registration=new Registration(getApplicationContext());

                SQLiteDatabase database=registration.getReadableDatabase();

                String projection[]={NAME,EMAIL,PASSWORD};
                String selection = EMAIL+" =?";
                String[] selectionArgs = {useremail};

                try
                {
                    Cursor cursor=database.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                    cursor.moveToFirst();

                    String mfetch_email=cursor.getString(cursor.getColumnIndex(EMAIL));
                    String mfetch_passwd=cursor.getString(cursor.getColumnIndex(PASSWORD));
                    String mfetch_name=cursor.getString(cursor.getColumnIndex(NAME));


                    Log.d("encryption",mfetch_passwd);
                    String decData= decrypt("keykey", Base64.decode(mfetch_passwd.getBytes("UTF-16LE"), Base64.DEFAULT));

                    Log.d("temp",mfetch_email+" "+mfetch_name+" "+decData);

                    if (mfetch_email.equals(useremail) && decData.equals(upassword))
                    {
                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        intent.putExtra("name",mfetch_name);
                        intent.putExtra("email",mfetch_email);
                        startActivity(intent);

                        email.setText("");
                        password.setText("");

                    }
                    else
                    {
                        status.setText("Please check Email and password ! ");

                    }


                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Email is not registered here",Toast.LENGTH_LONG).show();
                    status.setText("Please check Email and password ! ");

                }



            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText editText;

                final AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);

                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.forget_password,null);

                editText=(EditText)view.findViewById(R.id.email);
                builder.setView(view);
                builder.setCancelable(false);
                builder.setNegativeButton("Cancel",null);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String forget_email_id=editText.getText().toString();

                        try {
                            final String value=forgetEmailCheck(forget_email_id);

                            if(value!="Wrong Email Id")
                            {

                                Runnable runnable=new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            sendEmail(forget_email_id,value);
                                            Log.i("Gmail","MailSend");

                                            AlertDialog.Builder builder1=new AlertDialog.Builder(Login.this);
                                            builder1.setMessage("Mail Sending Successfully");

                                            AlertDialog alertDialog=builder1.create();
                                            alertDialog.show();

                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        } catch (MessagingException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                Thread thread=new Thread(runnable);
                                thread.start();


                            }
                            else
                            {
                                Toast.makeText(Login.this,"Wrong Enter Email Id",Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();



            }
        });

    }

    /*For checking Mail Is Corrected or not
    *
    * if it's Corrected then return true
    * */


    private String forgetEmailCheck(String email) throws Exception {
        String password;
        Registration  registration=new Registration(getApplicationContext());

        SQLiteDatabase database=registration.getReadableDatabase();

        String projection[]={EMAIL,PASSWORD};
        String selection=EMAIL+"=? ";
        String selectionArgs[]={email};

        Cursor cursor=database.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null);

        cursor.moveToFirst();
        if(cursor.isFirst())
        {
           password=cursor.getString(cursor.getColumnIndex(PASSWORD));
           Log.i("password is ",password);
            String decData= decrypt("keykey", Base64.decode(password.getBytes("UTF-16LE"), Base64.DEFAULT));
            return decData;

        }
        else
        {
            return "Wrong Email Id";


        }







    }

    /* *
    *
    *   For sending Mail
    *
    *
    * */

    protected void sendEmail(String email,String Password) throws UnsupportedEncodingException, MessagingException {
        /*Log.i("Send email", "");

        String[] TO = {"singhadityaftp@gmail.com"};
        String[] CC = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Password Storage Application Send Your Forget Password");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Your Password is "+Password);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Login.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }*/

        Gmail gmail=new Gmail(email,"Your Password is "+Password);

        gmail.createEmailMessage();

        gmail.sendEmail();
        Log.i("Gmail","MailSend Properly");

    }



    private static String decrypt(String key, byte[] encrypted) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digestOfPassword = md.digest(key.getBytes("UTF-16LE"));

        SecretKeySpec skeySpec = new SecretKeySpec(digestOfPassword, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted, "UTF-16LE");
    }
}
