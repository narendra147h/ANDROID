package com.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by adpr270759 on 19-03-2018.
 */

public class MyContent extends ContentProvider {


    static final String PROVIDER_NAME = "com.mycontentprovider.MyContent";
    static final String URL = "content://" + PROVIDER_NAME + "/Employee";
    static final String DATABASE_NAME = "Employee.db";

    static final Uri CONTENT_URI = Uri.parse(URL);
    private Context mContext;
    final static String ID = "id";
    final static String EMP_ID = "emp_id";
    final static String EMP_DESIG = "emp_desig";
    final static String TABLE_NAME = "Employee";


    final String QUERY = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " integer primary key autoincrement , "
            + EMP_ID + " INTEGER Unique , " + EMP_DESIG + " TEXT )";

    static final int uricCde = 1;




    private class Mydbhelper extends SQLiteOpenHelper {

        public Mydbhelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(QUERY);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);

        }
    }

    @Override
    public boolean onCreate()
    {
        this.mContext=getContext();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Mydbhelper mydbhelper=new Mydbhelper(mContext);
        SQLiteDatabase database=mydbhelper.getReadableDatabase();

        Cursor cursor=database.query(TABLE_NAME,strings,s,strings1,s1,null,null);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        Mydbhelper mydbhelper = new Mydbhelper(mContext);

        SQLiteDatabase database = mydbhelper.getWritableDatabase();

        long row = database.insert(TABLE_NAME, null, contentValues);

        Log.d("row", String.valueOf(row));



        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings)
    {


        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


}
