package com.mysqlite_project_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adpr270759 on 17-03-2018.
 */

public class Mysqlhelper extends SQLiteOpenHelper
{
    public Mysqlhelper(Context context)
    {
        super(context,"Regidter.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table registration (name text,username text,password text,phone text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("drop table if exists registration");
        onCreate(sqLiteDatabase);

    }
}
