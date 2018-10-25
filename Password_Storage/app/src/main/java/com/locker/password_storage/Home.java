package com.locker.password_storage;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Home extends AppCompatActivity {


    private static final String PASSWORD_FOR ="password_for";
    private static final String PASSWORD="password";
    private static final String EMAIL="email_id";
    private static final String TABLE_NAME ="password_container";

    TextView uname;
    Button add,logout;
    String email;
    EditText passfor,password;
    ListView showdata;
    LayoutInflater layoutinflater_one;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uname=(TextView)findViewById(R.id.uname);
        add=(Button) findViewById(R.id.add);
        logout=(Button)findViewById(R.id.logout);

        showdata=(ListView) findViewById(R.id.showdata);


        final Intent intent=getIntent();
        uname.setText("Hello "+intent.getStringExtra("name"));
        email=intent.getStringExtra("email");



        //Adding Header for First List view

        layoutinflater_one = getLayoutInflater();

        ViewGroup header1 = (ViewGroup)layoutinflater_one.inflate(R.layout.header,showdata,false);

        showdata.addHeaderView(header1);






            showData();

        //Click on Add Button

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //inflater--> fill

                View view= LayoutInflater.from(Home.this).inflate(R.layout.activity_password__store,null);

                 passfor=(EditText)view.findViewById(R.id.passfor);
                 password=(EditText)view.findViewById(R.id.password);


                //Create a Alert Builder Object and set Property

                AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
                builder.setView(view)
                        .setMessage("Password Details ...")
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                try {
                                    saveData();
                                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(),"Data Not Inserted",Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }



                                showData();

                            }
                        });
                builder.setNegativeButton("Cancel",null)
                .setCancelable(false);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(Home.this,Login.class);
                startActivity(intent1);

            }
        });


    }
    public void saveData() throws Exception {
        String passwdfor=passfor.getText().toString();
        String passwd=password.getText().toString();

        String encrypt_passwd=encrypt("keykeykey".getBytes("UTF-16LE"),(passwd).getBytes("UTF-16LE"));

        Log.d("Encryptpassword",encrypt_passwd);

        Password_Storage_class password_storage_class=new Password_Storage_class(getApplicationContext());
        SQLiteDatabase database=password_storage_class.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(PASSWORD_FOR,passwdfor);
        values.put(PASSWORD,encrypt_passwd);
        values.put(EMAIL,email);

        long temp=database.insert(TABLE_NAME,null,values);
        Log.d("Row updated",String.valueOf(temp));


    }
    public void showData()
    {
        Password_Storage_class storageClass=new Password_Storage_class(getApplicationContext());
        SQLiteDatabase database=storageClass.getReadableDatabase();

        String projection[]={PASSWORD_FOR,PASSWORD};
        String selection = EMAIL+" =?";
        String[] selectionArgs = {email};

        Cursor cursor=database.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null);

        cursor.moveToFirst();

        final ArrayList arrayList1=new ArrayList();
        final HashMap<String,String> hashMap=new HashMap<String,String>();

        if(cursor.isFirst())
        {
            do
            {
                arrayList1.add(cursor.getString(cursor.getColumnIndex(PASSWORD_FOR)));
                hashMap.put(cursor.getString(cursor.getColumnIndex(PASSWORD_FOR)),cursor.getString(cursor.getColumnIndex(PASSWORD)));
            }while (cursor.moveToNext());
        }



        ArrayAdapter adapter1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList1);

        showdata.setAdapter(adapter1);


        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final String passwdfor =(String) arrayList1.get(position-1);
                Log.d("passwdfor",passwdfor);

                AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);


                final LayoutInflater inflater=getLayoutInflater();
                View view1=inflater.inflate(R.layout.master_passwd,null);
                final EditText mpasswd=(EditText)view1.findViewById(R.id.mpasswd);


                builder.setView(view1)
                .setMessage("Master Password")
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {



                            String master_password=mpasswd.getText().toString();


                            if(validateMasterPassword(master_password,email))
                            {
                                Log.d("status","validate password");
                                AlertDialog.Builder builder1=new AlertDialog.Builder(Home.this);

                                LayoutInflater inflater1=getLayoutInflater();
                                View view2=inflater.inflate(R.layout.password_info,null);
                                TextView passwd_info=(TextView)view2.findViewById(R.id.passwd_info);
                                String encrypt_passwd=hashMap.get(passwdfor);
                                try {
                                    String decrpyt_passwd= decrypt("keykeykey", Base64.decode(encrypt_passwd.getBytes("UTF-16LE"), Base64.DEFAULT));
                                    Log.d("decryptpassword",decrpyt_passwd);
                                    passwd_info.setText("Your password is "+" "+decrpyt_passwd);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                builder1.setView(view2);


                                AlertDialog dialog1=builder1.create();
                                dialog1.show();



                            }
                            else
                            {
                                Toast.makeText(Home.this,"Master password is not Corrected",Toast.LENGTH_LONG).show();
                            }


                     }
                 })
                 .setCancelable(false)
                 .setNegativeButton("Cancel",null);


                AlertDialog mdialog=builder.create();
                mdialog.show();






            }
        });




    }
    public boolean validateMasterPassword(String master_password,String email)
    {


        Registration registration=new Registration(getApplicationContext());

        SQLiteDatabase database=registration.getReadableDatabase();

        String projection[]={"upassword"};
        String selection = "uemail"+" =?";
        String[] selectionArgs = {email};

        try
        {


            Cursor cursor=database.query("password_storage_registration",projection,selection,selectionArgs,null,null,null);

            cursor.moveToFirst();

            String mconfirm_master_passwd=cursor.getString(cursor.getColumnIndex("upassword"));

            String decData= decrypt("keykey", Base64.decode(mconfirm_master_passwd.getBytes("UTF-16LE"), Base64.DEFAULT));

            if(decData.equals(master_password))
            {
                Log.d("status","Password matched");
                return true;
            }

            else
            {
                Log.d("status","Password not matched");
                return false;
            }



        }
        catch (Exception e)
        {
            Log.d("Status","Master password is not correct");
            Toast.makeText(Home.this,"Master password is not Corrected",Toast.LENGTH_LONG).show();
            return false;

        }







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
