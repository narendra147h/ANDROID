package com.locker.password_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adpr270759 on 30-03-2018.
 */

public class Registration extends SQLiteOpenHelper
{
    private static final String TABLE_NAME="password_storage_registration";
    private static final int VERSION=2;
    private static final String ID="user_id";
    private static final String NAME="uname";
    private static final String EMAIL="uemail";
    private static  final String PASSWORD="upassword";
    private static final String DATABASE_NAME="password_storage.db";

    private static final String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                                                NAME+" TEXT NOT NULL , "+EMAIL+" TEXT NOT NULL UNIQUE , "+PASSWORD+" TEXT NOT NULL ) ";



    public Registration(Context context) {
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
